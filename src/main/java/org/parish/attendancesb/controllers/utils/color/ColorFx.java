package org.parish.attendancesb.controllers.utils.color;

import javafx.scene.paint.Color;

public class ColorFx {

    private ColorFx() {
    }

    public static Color hex2Rgb(String colorStr) {
        if (colorStr == null) {
            return Color.BLUE;
        }

        return Color.rgb(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    public static String rgba2Hex(Color color) {
        int r = ((int) Math.round(color.getRed() * 255)) << 24;
        int g = ((int) Math.round(color.getGreen() * 255)) << 16;
        int b = ((int) Math.round(color.getBlue() * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));
        return String.format("#%08X", (r + g + b + a));
    }
}
