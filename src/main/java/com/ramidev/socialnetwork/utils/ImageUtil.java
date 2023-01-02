package com.ramidev.socialnetwork.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageUtil {

    public static boolean isValidImage(MultipartFile image) {
        try {
            BufferedImage bufferedImage = ImageIO.read(image.getResource().getInputStream());
            return (bufferedImage != null);
        } catch (Exception e) {
            return false;
        }
    }
}
