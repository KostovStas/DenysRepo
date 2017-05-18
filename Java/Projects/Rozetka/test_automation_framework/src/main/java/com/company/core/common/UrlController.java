package com.company.core.common;

import static org.apache.commons.lang3.StringUtils.*;

public class UrlController {

    private UrlController() {
    }

    public static String buildUri(final String host, final String partialPath) {
        if (isAnyBlank(host, partialPath)) {
            throw new IllegalArgumentException("Host or partial path are missed while creating URI.");
        }
        return String.format("%s://%s", host, partialPath);
    }
}
