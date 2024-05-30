package com.newspaper.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller advice to handle exceptions globally.
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * Handles MaxUploadSizeExceededException and provides a warning message.
     *
     * @param model The model for adding attributes.
     * @return A redirect to the home page with an error message if the file size exceeds the allowed limit.
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(RedirectAttributes redirectAttributes,  Model model) {
        logger.warn("File size exceeds the allowed limit.");
        redirectAttributes.addFlashAttribute("errorMessage", "File size exceeds the allowed limit.");
        model.addAttribute("error", "File size exceeds the allowed limit.");
        return "redirect:/";
    }
}
