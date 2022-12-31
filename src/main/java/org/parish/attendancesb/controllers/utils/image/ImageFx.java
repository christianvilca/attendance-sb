package org.parish.attendancesb.controllers.utils.image;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.parish.attendancesb.controllers.utils.Alert;
import org.parish.attendancesb.services.utils.image.ImageBase64;

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
}
