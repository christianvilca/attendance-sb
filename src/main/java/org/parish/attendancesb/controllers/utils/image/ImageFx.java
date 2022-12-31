package org.parish.attendancesb.controllers.utils.image;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.services.utils.image.ImageBase64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFx {
    public static String encoder(Image fxImage) {
        try {
            if (fxImage != null) {
                return ImageBase64.encoder(SwingFXUtils.fromFXImage(fxImage, null));
            }
        } catch (IOException e) {
            Alert.error(e.getMessage());
        }

        return null;
    }

    public static Image decoder(String imageBase64) {
        if (imageBase64 == null)
            return null;

        return new Image(ImageBase64.toBais(imageBase64));
    }

    public static void writeToFile(BufferedImage bufferedImage, String nameFile) {
        if (nameFile == null) {
            nameFile = "default";
        }
        File file = new File(nameFile + ".png");

        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            Alert.error(e.getMessage());
        }
    }
}
