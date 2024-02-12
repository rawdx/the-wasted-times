package com.newspaper.utils;

import java.util.Base64;

/**
 * Utility class for converting images between byte arrays and Base64 strings.
 */
public class ImageUtils {

    /**
     * Converts a byte array to a Base64-encoded string.
     *
     * @param data The byte array representing the image.
     * @return Base64-encoded string representation of the image.
     */
    public static String convertToBase64(byte[] data) {
        if (data != null && data.length > 0) {
            return Base64.getEncoder().encodeToString(data);
        }
        return null;
    }


    /**
     * Converts a Base64-encoded string to a byte array.
     *
     * @param base64String The Base64-encoded string representation of the image.
     * @return Byte array representing the image.
     */
    public static byte[]  convertToByteArray(String base64String) {
        if (base64String != null && !base64String.isEmpty()) {
            return Base64.getDecoder().decode(base64String);
        }
        return null;
    }
}
