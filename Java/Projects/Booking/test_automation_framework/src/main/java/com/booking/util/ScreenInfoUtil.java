package com.booking.util;

import java.awt.*;

public class ScreenInfoUtil {

    private static Dimension screenSize = null;

    private ScreenInfoUtil() {
    }

    private static Dimension getScreenSize() {
        if (screenSize == null) {
            screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        }
        return screenSize;
    }

    public static double getWidth() {
        return getScreenSize().getWidth();
    }

    public static double getHeight() {
        return getScreenSize().getHeight();
    }
}
