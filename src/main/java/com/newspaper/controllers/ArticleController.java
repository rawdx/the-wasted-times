package com.newspaper.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import com.newspaper.entities.ArticleStatus;
import com.newspaper.entities.dtos.*;
import com.newspaper.services.CommentService;
import com.newspaper.services.RoleService;
import com.newspaper.utils.ImageUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.newspaper.services.ArticleService;
import com.newspaper.services.CategoryService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;

/**
 * Controller responsible for handling article-related operations.
 */
@Controller
public class ArticleController {

	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
    private final ArticleService articleService;
	private final CategoryService categoryService;
    private final RoleService roleService;
    
    public ArticleController(ArticleService articleService, CategoryService categoryService, RoleService roleService) {
    	this.articleService = articleService;
    	this.categoryService = categoryService;
        this.roleService = roleService;
    }

    /**
     * Displays the writing page for users with the role of WRITER.
     *
     * @param session The HttpSession for checking user role.
     * @return The view name for the writing page ("write") or redirects to home if not authorized.
     */
    @GetMapping("/write")
    public String showWritingPage(HttpSession session, Model model) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");
            if (user == null) {
                logger.warn("User not found in session.");
                return "redirect:/";
            }

            RoleDto role = roleService.getRoleByName("WRITER");

            if (user.getRole().getId() != role.getId()) {
                logger.warn("Non-writer user attempted to access writing page: {}", user.getEmail());
                return "redirect:/";
            }

            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);

            return "write";
        } catch (Exception e) {
            logger.error("Error during writing page access", e);
            return "redirect:/";
        }
    }

    /**
     * Handles the creation of a new article by a WRITER user.
     *
     * @param session The HttpSession for retrieving the writer user.
     * @param title   The title of the article.
     * @param subtitle The subtitle of the article.
     * @param content The content of the article.
     * @param file    The image file associated with the article.
     * @return A redirect to the writing page ("/write") after article creation.
     */
    @PostMapping("/write/create")
    public String createArticle(RedirectAttributes redirectAttributes, HttpSession session, Model model, @RequestParam String title, @RequestParam String subtitle, @RequestParam String content, @RequestPart("image") MultipartFile file) {
        try {
            UserDto writer = (UserDto) session.getAttribute("user");

            ArticleDto article = new ArticleDto(title, subtitle, content, ImageUtils.convertToBase64(file.getBytes()), LocalDateTime.now(), ArticleStatus.PENDING);

            if (articleService.createArticle(article, writer.getEmail())) {
                logger.info("Article created successfully by user {}", writer.getEmail());
                redirectAttributes.addFlashAttribute("successMessage", "Article created successfully.");
            } else{
                logger.warn("Failed to create article by user {}", writer.getEmail());
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to create article. Please try again later.");
            }
            return "redirect:/write";
        } catch (Exception e) {
            logger.error("Error creating article", e);
            redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
            return "redirect:/write";
        }
    }

    /**
     * Displays the detailed view of a specific article.
     *
     * @param id    The ID of the article to view.
     * @param model The model for adding attributes.
     * @return The view name for the article details page ("article") or redirects to home if not found.
     */
    @GetMapping("/article/{id}")
    public String viewArticle(@PathVariable long id, Model model) {
        try {
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);

            ArticleDto article = articleService.getArticleById(id);

            model.addAttribute("comments", article.getComments());

                model.addAttribute("article", article);
                return "article";
        } catch (Exception e) {
            logger.error("Error viewing article with ID: {}", id, e);
            return "redirect:/";
        }
    }


    @GetMapping("/article/{id}/pdf")
    public void generateArticlePDF(@PathVariable long id, HttpServletResponse response) {
        try {
            // Obtener el artículo
            ArticleDto article = articleService.getArticleById(id);

            // Crear un nuevo documento PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Obtener el ancho y alto de la página
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
            float margin = 50;
            float usableWidth = pageWidth - 2 * margin;

            // Crear un nuevo contenido de página
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Iniciar el texto
            contentStream.beginText();

            // Agregar el título del artículo
            PDType1Font titleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            int titleFontSize = 16;
            String title = article.getTitle().replaceAll("[\\r\\n]", " ");
            float titleWidth = titleFont.getStringWidth(title) / 1000 * titleFontSize;
            float titleX = (pageWidth - titleWidth) / 2;
            contentStream.setFont(titleFont, titleFontSize);
            contentStream.newLineAtOffset(titleX, pageHeight - 50); // Cambiar la coordenada y según sea necesario
            contentStream.showText(title);
            contentStream.newLineAtOffset(-titleX, -30); // Mover el cursor hacia abajo y resetear x

            // Agregar el subtítulo del artículo
            PDType1Font subtitleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            int subtitleFontSize = 12;
            String subtitle = article.getSubtitle().replaceAll("[\\r\\n]", " ");
            float subtitleWidth = subtitleFont.getStringWidth(subtitle) / 1000 * subtitleFontSize;
            float subtitleX = (pageWidth - subtitleWidth) / 2;
            contentStream.setFont(subtitleFont, subtitleFontSize);
            contentStream.newLineAtOffset(subtitleX, -20); // Mover el cursor hacia arriba
            contentStream.showText(subtitle);
            contentStream.newLineAtOffset(-subtitleX, -30); // Mover el cursor hacia abajo y resetear x

            // Finalizar el texto
            contentStream.endText();

            // Agregar la imagen
            if (article.getImage() != null && !article.getImage().isEmpty()) {
                byte[] imageBytes = ImageUtils.convertToByteArray(article.getImage());
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageBytes, "image");

                // Obtener el tamaño de la imagen
                float imageWidth = pdImage.getWidth();
                float imageHeight = pdImage.getHeight();

                // Definir el tamaño del contenedor de la imagen
                float containerHeight = 300; // Altura fija del contenedor
                float containerWidth = (containerHeight / imageHeight) * imageWidth;

                // Calcular las coordenadas para centrar la imagen en la página
                float imageX = (pageWidth - containerWidth) / 2;
                float imageY = pageHeight - 150 - containerHeight;

                // Dibujar la imagen en el contenedor
                contentStream.drawImage(pdImage, imageX, imageY, containerWidth, containerHeight);
            }

            // Reanudar el texto para la descripción
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 300); // Cambiar la coordenada y según sea necesario

            // Agregar la descripción del artículo
            PDType1Font contentFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
            int contentFontSize = 12;
            String content = article.getContent().replaceAll("[\\r\\n]", " ");
            contentStream.setFont(contentFont, contentFontSize);
            contentStream.showText(content);
            contentStream.newLine();

            // Finalizar el texto
            contentStream.endText();

            // Cerrar el contenido de la página
            contentStream.close();

            // Guardar el documento en un flujo de bytes
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            // Establecer la respuesta del servlet para descargar el PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + article.getTitle() + ".pdf");
            response.setContentLength(byteArrayOutputStream.size());
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
            response.getOutputStream().flush();

            // Cerrar el flujo de bytes
            byteArrayOutputStream.close();
        } catch (Exception e) {
            logger.error("Error generating PDF for article with ID: {}", id, e);
        }
    }
}
