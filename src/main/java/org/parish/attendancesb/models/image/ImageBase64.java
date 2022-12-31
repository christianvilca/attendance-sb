package org.parish.attendancesb.models.image;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.parish.attendancesb.controllers.utils.Alert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class ImageBase64 {

    public static String encoder(Image fxImage) {
        ByteArrayOutputStream outputStream = null;
        try {
            if (fxImage != null) {
                outputStream = new ByteArrayOutputStream();
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);
//                ImageIO.write(bufferedImage, getFileExtension(fxImage.getUrl()), outputStream);
                ImageIO.write(bufferedImage, "png", outputStream);
            }
        } catch (IOException e) {
            Alert.error(e.getMessage());
        }

        if (outputStream == null) {
            return null;
        }

        byte[] imageBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static String encoder(BufferedImage bufferedImage) {
        ByteArrayOutputStream outputStream = null;
        try {
            if (bufferedImage != null) {
                outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);
            }
        } catch (IOException e) {
            Alert.error(e.getMessage());
        }

        if (outputStream == null) {
            return null;
        }

        byte[] imageBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private static String getFileExtension(String fileUri) {
        return fileUri.split("\\.")[1];
    }

    public static Image decoder(String imageBase64) {
        if (imageBase64 != null) {
            // Decodifica la cadena y obtiene un arreglo de bytes
            byte[] imageBytes = Base64.getDecoder().decode(imageBase64);

            return new Image(new ByteArrayInputStream(imageBytes));
        }

        return null;
    }

    public static BufferedImage decodeBuffer(String imageBase64) {
        BufferedImage image = null;

        if (imageBase64 != null) {
            // Decodifica la cadena y obtiene un arreglo de bytes
            byte[] imageBytes = Base64.getDecoder().decode(imageBase64);

            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            try {
                image = ImageIO.read(bis);
                bis.close();
            } catch (IOException e) {
                Alert.error(e.getMessage());
            }
        }

        return image;
    }

    public static String encoderFromPath(String path) throws IOException {
        // Abre la imagen como un stream de entrada
        FileInputStream imageInputStream = new FileInputStream(path);
        // Crea un arreglo de bytes para almacenar la imagen
        byte[] imageBytes = new byte[imageInputStream.available()];
        // Lee la imagen en el arreglo de bytes
        imageInputStream.read(imageBytes);
        // Cierra el stream de entrada
        imageInputStream.close();

        // Codifica el arreglo de bytes en una cadena en base64
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static void decoderToFile(String imageBase64) throws IOException {
        // Decodifica la cadena y obtiene un arreglo de bytes
        byte[] imageBytes = Base64.getDecoder().decode(imageBase64);
        // Abre un stream de salida para escribir la imagen en un archivo
        FileOutputStream imageOutputStream = new FileOutputStream("image.png");
        // Escribe el arreglo de bytes en el stream de salida
        imageOutputStream.write(imageBytes);
        // Cierra el stream de salida
        imageOutputStream.close();
    }
}
