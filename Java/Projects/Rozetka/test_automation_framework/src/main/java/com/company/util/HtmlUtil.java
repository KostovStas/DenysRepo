package com.company.util;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlUtil {

    private static final String LINK_TAG = "a";
    private static final String SEARCHED_TEXT = "authorize";

    private HtmlUtil() {
    }

    public static String getActivationLinkFromHtml(final String html) {
        final Document doc = Jsoup.parse(html);
        final List<Element> links = doc.select(LINK_TAG);
        final Element foundLink = filterLinksBySearchedText(links);
        Objects.requireNonNull(foundLink, "There is no found links by searching text!");
        return foundLink.text();
    }

    private static Element filterLinksBySearchedText(final List<Element> links) {
        return links.stream()
                .filter(link -> containsIgnoreCase(link.text(), SEARCHED_TEXT))
                .findFirst()
                .orElse(null);
    }
}
