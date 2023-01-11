package org.parish.attendancesb.utils.image;

import org.parish.attendancesb.controllers.utils.alert.AlertFx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class ImageBase64 {

    public static String encoder(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream outputStream = null;

        if (bufferedImage != null) {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
        }

        if (outputStream == null) {
            return null;
        }

        byte[] imageBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static ByteArrayInputStream toBais(String imageBase64) {
        // Decodifica la cadena y obtiene un arreglo de bytes
        byte[] imageBytes = Base64.getDecoder().decode(imageBase64);

        return new ByteArrayInputStream(imageBytes);
    }

    public static BufferedImage decodeToBufferImage(String imageBase64) {
        if (imageBase64 == null)
            return null;

        BufferedImage image = null;
        ByteArrayInputStream bis = toBais(imageBase64);

        try {
            image = ImageIO.read(bis);
            bis.close();
        } catch (IOException e) {
            AlertFx.error(e.getMessage());
        }

        return image;
    }
}
