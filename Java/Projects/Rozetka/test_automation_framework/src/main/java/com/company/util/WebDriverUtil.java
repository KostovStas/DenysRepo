package com.company.util;

import com.company.core.common.EnvironmentProperty;
import com.company.core.webdriver.AbstractPage;
import net.thucydides.core.annotations.DefaultUrl;

import java.util.Objects;
import java.util.Optional;

public class WebDriverUtil {

    private WebDriverUtil() {
    }

    public static <T extends AbstractPage> void openPageByExtraPath(final T page) {
        Objects.requireNonNull(page, "Cannot open page from null reference!");
        final String defaultUrl = getDefaultUrl(page);
        final String path = EnvironmentProperty.BASE_SITE_URL.readProperty() + defaultUrl;
        page.openAt(path);
    }

    public static <T extends AbstractPage> void openPage(final T page, final String link) {
        Objects.requireNonNull(page, "Cannot open page from null reference!");
        page.openAt(link);
    }

    private static <T extends AbstractPage> String getDefaultUrl(final T pageType) {
        return Optional.ofNullable(pageType)
                .map(type -> type.getClass().getAnnotation(DefaultUrl.class))
                .map(DefaultUrl::value)
                .orElse(null);
    }
}
