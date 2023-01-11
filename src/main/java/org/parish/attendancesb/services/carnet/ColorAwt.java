package org.parish.attendancesb.services.carnet;

import java.awt.*;

public class ColorAwt {

    private ColorAwt() {
    }

    public static Color hex2Rgb(String colorStr) {
        if (colorStr == null) {
            return Color.BLUE;
        }

        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }
}
