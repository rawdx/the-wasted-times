package com.newspaper.utils;

import com.newspaper.entities.dtos.ArticleDto;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Utility class for generating and downloading PDF for articles.
 */
public class PDFUtils {

    public static ByteArrayOutputStream createPdfDocument(ArticleDto article) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();
        float margin = 50;

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        float currentY = pageHeight - margin; // Start from the top

        currentY = addArticleTitleAndSubtitle(contentStream, article.getTitle(), article.getSubtitle(), pageWidth, currentY);
        currentY = addArticleImage(contentStream, article, document, pageWidth, currentY, margin);

        contentStream.close(); // Close the content stream before adding the content with pagination

        addArticleContent(document, article.getContent(), pageWidth, currentY, margin);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream;
    }

    private static float addArticleTitleAndSubtitle(PDPageContentStream contentStream, String title, String subtitle, float pageWidth, float currentY) throws IOException {
        PDType1Font titleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
        PDType1Font subtitleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
        int titleFontSize = 16;
        int subtitleFontSize = 12;
        float titleLeading = 1.5f * titleFontSize;
        float subtitleLeading = 1.5f * subtitleFontSize;
        float margin = 40;
        float titleWidth = pageWidth - 2 * margin;
        float subtitleWidth = pageWidth - 2 * margin;
        float spaceBetweenTitleAndSubtitle = 20;
        float spaceAfterSubtitle = 30;
        float totalHeight = 0;

        // Add Title
        title = title.replaceAll("[\\r\\n]", " ");
        String[] titleWords = title.split(" ");
        StringBuilder currentTitleLine = new StringBuilder();

        contentStream.setFont(titleFont, titleFontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, currentY - titleFontSize); // Start position with margin

        for (String word : titleWords) {
            if (titleFont.getStringWidth(currentTitleLine.toString() + word) / 1000 * titleFontSize > titleWidth) {
                float titleX = (pageWidth - titleFont.getStringWidth(currentTitleLine.toString()) / 1000 * titleFontSize) / 2;
                contentStream.newLineAtOffset(titleX - margin, 0); // Adjust offset for centering
                contentStream.showText(currentTitleLine.toString().trim());
                contentStream.newLineAtOffset(margin - titleX, -titleLeading); // Reset offset and move to new line
                currentTitleLine = new StringBuilder();
                totalHeight += titleLeading;
            }
            currentTitleLine.append(word).append(" ");
        }

        if (currentTitleLine.length() > 0) {
            float titleX = (pageWidth - titleFont.getStringWidth(currentTitleLine.toString()) / 1000 * titleFontSize) / 2;
            contentStream.newLineAtOffset(titleX - margin, 0); // Adjust offset for centering
            contentStream.showText(currentTitleLine.toString().trim());
            totalHeight += titleLeading;
        }

        contentStream.endText();
        currentY -= totalHeight;
        currentY -= spaceBetweenTitleAndSubtitle; // Space between title and subtitle
        totalHeight = 0;

        // Add Subtitle
        subtitle = subtitle.replaceAll("[\\r\\n]", " ");
        String[] subtitleWords = subtitle.split(" ");
        StringBuilder currentSubtitleLine = new StringBuilder();

        contentStream.setFont(subtitleFont, subtitleFontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, currentY - subtitleFontSize); // Start position with margin

        for (String word : subtitleWords) {
            if (subtitleFont.getStringWidth(currentSubtitleLine.toString() + word) / 1000 * subtitleFontSize > subtitleWidth) {
                float subtitleX = (pageWidth - subtitleFont.getStringWidth(currentSubtitleLine.toString()) / 1000 * subtitleFontSize) / 2;
                contentStream.newLineAtOffset(subtitleX - margin, 0); // Adjust offset for centering
                contentStream.showText(currentSubtitleLine.toString().trim());
                contentStream.newLineAtOffset(margin - subtitleX, -subtitleLeading); // Reset offset and move to new line
                currentSubtitleLine = new StringBuilder();
                totalHeight += subtitleLeading;
            }
            currentSubtitleLine.append(word).append(" ");
        }

        if (currentSubtitleLine.length() > 0) {
            float subtitleX = (pageWidth - subtitleFont.getStringWidth(currentSubtitleLine.toString()) / 1000 * subtitleFontSize) / 2;
            contentStream.newLineAtOffset(subtitleX - margin, 0); // Adjust offset for centering
            contentStream.showText(currentSubtitleLine.toString().trim());
            totalHeight += subtitleLeading;
        }

        contentStream.endText();
        currentY -= totalHeight;
        currentY -= spaceAfterSubtitle; // Space after subtitle
        return currentY;
    }

    private static float addArticleImage(PDPageContentStream contentStream, ArticleDto article, PDDocument document, float pageWidth, float currentY, float margin) throws IOException {
        if (article.getImage() != null && !article.getImage().isEmpty()) {
            try {
                byte[] imageBytes = ImageUtils.convertToByteArray(article.getImage());
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

                if (bufferedImage == null) {
                    throw new IOException("BufferedImage is null, unsupported image format or corrupted image");
                }

                // Resize image if it is too large
                BufferedImage resizedImage = resizeImage(bufferedImage, 700, 447);

                // Convert to PNG format
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, "png", baos);
                baos.flush();
                byte[] pngBytes = baos.toByteArray();
                baos.close();

                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, pngBytes, "image");

                float imageWidth = pdImage.getWidth();
                float imageHeight = pdImage.getHeight();
                float containerHeight = 300;
                float containerWidth = (containerHeight / imageHeight) * imageWidth;
                float imageX = (pageWidth - containerWidth) / 2;
                float imageY = currentY - containerHeight;

                contentStream.drawImage(pdImage, imageX, imageY, containerWidth, containerHeight);
                return imageY - 40; // Increased space after image
            } catch (IOException e) {
                System.err.println("Failed to add image to PDF: " + e.getMessage());
                // Optionally, you can log the exception or handle it as needed
            }
        }
        return currentY;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }

    private static void addArticleContent(PDDocument document, String content, float pageWidth, float startY, float margin) throws IOException {
        PDType1Font contentFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        int contentFontSize = 12;
        float leading = 1.5f * contentFontSize;
        float contentWidth = pageWidth - 2 * margin;
        float currentY = startY;
        float pageHeight = PDRectangle.A4.getHeight();

        PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(document.getNumberOfPages() - 1), PDPageContentStream.AppendMode.APPEND, true, true);        contentStream.beginText();
        contentStream.setFont(contentFont, contentFontSize);
        contentStream.newLineAtOffset(margin, currentY);

        String sanitizedContent = content.replaceAll("\u200B", "");
        String[] lines = sanitizedContent.split("\n");

        for (String line : lines) {
            if (line.isEmpty()) {
                currentY -= leading;
                if (currentY < margin) {
                    // Close the current content stream and start a new page
                    contentStream.endText();
                    contentStream.close();

                    PDPage newPage = new PDPage(PDRectangle.A4);
                    document.addPage(newPage);
                    contentStream = new PDPageContentStream(document, newPage);
                    contentStream.beginText();
                    contentStream.setFont(contentFont, contentFontSize);
                    currentY = pageHeight - margin;
                    contentStream.newLineAtOffset(margin, currentY);
                } else {
                    contentStream.newLineAtOffset(0, -leading);
                }
            } else {
                line = line.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", ""); // Remove control characters except for carriage return, line feed, and tab
                line = line.replaceAll("[\\p{Cntrl}]", ""); // Remove remaining control characters

                String[] words = line.split(" ");
                StringBuilder currentLine = new StringBuilder();
                for (String word : words) {
                    if (contentFont.getStringWidth(currentLine.toString() + word) / 1000 * contentFontSize > contentWidth) {
                        contentStream.showText(currentLine.toString());
                        contentStream.newLineAtOffset(0, -leading);
                        currentY -= leading;
                        if (currentY < margin) {
                            // Close the current content stream and start a new page
                            contentStream.endText();
                            contentStream.close();

                            PDPage newPage = new PDPage(PDRectangle.A4);
                            document.addPage(newPage);
                            contentStream = new PDPageContentStream(document, newPage);
                            contentStream.beginText();
                            contentStream.setFont(contentFont, contentFontSize);
                            currentY = pageHeight - margin;
                            contentStream.newLineAtOffset(margin, currentY);
                        }
                        currentLine = new StringBuilder();
                    }
                    currentLine.append(word).append(" ");
                }
                contentStream.showText(currentLine.toString().trim());
                contentStream.newLineAtOffset(0, -leading);
                currentY -= leading;
                if (currentY < margin) {
                    // Close the current content stream and start a new page
                    contentStream.endText();
                    contentStream.close();

                    PDPage newPage = new PDPage(PDRectangle.A4);
                    document.addPage(newPage);
                    contentStream = new PDPageContentStream(document, newPage);
                    contentStream.beginText();
                    contentStream.setFont(contentFont, contentFontSize);
                    currentY = pageHeight - margin;
                    contentStream.newLineAtOffset(margin, currentY);
                }
            }
        }

        contentStream.endText();
        contentStream.close();
    }

    public static void sendPdfResponse(HttpServletResponse response, ByteArrayOutputStream byteArrayOutputStream, String title) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + title + ".pdf\"");
        response.setContentLength(byteArrayOutputStream.size());

        try (OutputStream os = response.getOutputStream()) {
            os.write(byteArrayOutputStream.toByteArray());
            os.flush();
        } catch (IOException e) {
            // Handle the exception (log it, rethrow it, etc.)
            throw new IOException("Error writing PDF to response", e);
        } finally {
            byteArrayOutputStream.close();
        }
    }
}