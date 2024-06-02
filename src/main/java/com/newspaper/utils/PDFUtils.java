package com.newspaper.utils;

import com.newspaper.entities.dtos.ArticleDto;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Utility class for generating and downloading PDF for articles.
 */
public class PDFUtils {

    /**
     * Creates a PDF document for the given article and returns it as a byte array output stream.
     *
     * @param article the article data transfer object
     * @return the byte array output stream containing the PDF document
     * @throws IOException if an I/O error occurs
     */
    public static ByteArrayOutputStream createPdfDocument(ArticleDto article) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();
        float margin = 50;
        float usableWidth = pageWidth - 2 * margin;

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        addArticleTitle(contentStream, article.getTitle(), pageWidth, pageHeight);
        addArticleSubtitle(contentStream, article.getSubtitle(), pageWidth);
        addArticleImage(contentStream, article, document, pageWidth, pageHeight);
        addArticleContent(contentStream, article.getContent(), pageWidth, pageHeight);
        contentStream.close();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream;
    }

    /**
     * Adds the title of the article to the PDF document.
     *
     * @param contentStream the content stream of the PDF document
     * @param title         the title of the article
     * @param pageWidth     the width of the page
     * @param pageHeight    the height of the page
     * @throws IOException if an I/O error occurs
     */
    private static void addArticleTitle(PDPageContentStream contentStream, String title, float pageWidth, float pageHeight) throws IOException {
        PDType1Font titleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
        int titleFontSize = 16;
        title = title.replaceAll("[\\r\\n]", " ");
        float titleWidth = titleFont.getStringWidth(title) / 1000 * titleFontSize;
        float titleX = (pageWidth - titleWidth) / 2;
        contentStream.beginText();
        contentStream.setFont(titleFont, titleFontSize);
        contentStream.newLineAtOffset(titleX, pageHeight - 50);
        contentStream.showText(title);
        contentStream.endText();
    }

    /**
     * Adds the subtitle of the article to the PDF document.
     *
     * @param contentStream the content stream of the PDF document
     * @param subtitle      the subtitle of the article
     * @param pageWidth     the width of the page
     * @throws IOException if an I/O error occurs
     */
    private static void addArticleSubtitle(PDPageContentStream contentStream, String subtitle, float pageWidth) throws IOException {
        PDType1Font subtitleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
        int subtitleFontSize = 12;
        subtitle = subtitle.replaceAll("[\\r\\n]", " ");
        float subtitleWidth = subtitleFont.getStringWidth(subtitle) / 1000 * subtitleFontSize;
        float subtitleX = (pageWidth - subtitleWidth) / 2;
        contentStream.beginText();
        contentStream.setFont(subtitleFont, subtitleFontSize);
        contentStream.newLineAtOffset(subtitleX, -30);
        contentStream.showText(subtitle);
        contentStream.endText();
    }

    /**
     * Adds the image of the article to the PDF document.
     *
     * @param contentStream the content stream of the PDF document
     * @param article       the article data transfer object
     * @param document      the PDF document
     * @param pageWidth     the width of the page
     * @param pageHeight    the height of the page
     * @throws IOException if an I/O error occurs
     */
    private static void addArticleImage(PDPageContentStream contentStream, ArticleDto article, PDDocument document, float pageWidth, float pageHeight) throws IOException {
        if (article.getImage() != null && !article.getImage().isEmpty()) {
            byte[] imageBytes = ImageUtils.convertToByteArray(article.getImage());
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageBytes, "image");

            float imageWidth = pdImage.getWidth();
            float imageHeight = pdImage.getHeight();
            float containerHeight = 300;
            float containerWidth = (containerHeight / imageHeight) * imageWidth;
            float imageX = (pageWidth - containerWidth) / 2;
            float imageY = pageHeight - 150 - containerHeight;

            contentStream.drawImage(pdImage, imageX, imageY, containerWidth, containerHeight);
        }
    }

    /**
     * Adds the content of the article to the PDF document.
     *
     * @param contentStream the content stream of the PDF document
     * @param content       the content of the article
     * @param pageWidth     the width of the page
     * @param pageHeight    the height of the page
     * @throws IOException if an I/O error occurs
     */
    private static void addArticleContent(PDPageContentStream contentStream, String content, float pageWidth, float pageHeight) throws IOException {
        PDType1Font contentFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        int contentFontSize = 12;
        content = content.replaceAll("[\\r\\n]", " ");
        contentStream.beginText();
        contentStream.setFont(contentFont, contentFontSize);
        contentStream.newLineAtOffset(50, pageHeight - 450);
        contentStream.showText(content);
        contentStream.endText();
    }

    /**
     * Sends the generated PDF as a response.
     *
     * @param response            the HTTP servlet response
     * @param byteArrayOutputStream the byte array output stream containing the PDF document
     * @param title               the title of the article (used as the filename)
     * @throws IOException if an I/O error occurs
     */
    public static void sendPdfResponse(HttpServletResponse response, ByteArrayOutputStream byteArrayOutputStream, String title) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + title + ".pdf");
        response.setContentLength(byteArrayOutputStream.size());
        response.getOutputStream().write(byteArrayOutputStream.toByteArray());
        response.getOutputStream().flush();
        byteArrayOutputStream.close();
    }
}
