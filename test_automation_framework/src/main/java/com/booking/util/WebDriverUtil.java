package com.booking.util;

import java.util.Objects;

import com.booking.structure.core.webdriver.AbstractPage;

public class WebDriverUtil {

    private WebDriverUtil() {

    }

    public static <T extends AbstractPage> void openPage(final T page) {
        Objects.requireNonNull(page, "Cannot open page from null reference!");
        page.open();
    }
}
