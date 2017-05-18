package com.booking.structure.core.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.booking.structure.core.panels.SearchByCriteriaPanel;
import com.booking.structure.core.webdriver.AbstractPage;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://www.booking.com/index.en-gb.html")
public class HotelsSearchPage extends AbstractPage {

    private static final String SEARCH_BY_CRITERIA_PANEL = "//div[@class='sb-searchbox__outer']";

    public HotelsSearchPage(final WebDriver driver) {
        super(driver);
    }

    public SearchByCriteriaPanel getSearchByCriteriaPanel() {
        withTimeoutOf(10, TimeUnit.SECONDS).waitFor(SEARCH_BY_CRITERIA_PANEL);
        return new SearchByCriteriaPanel(findBy(SEARCH_BY_CRITERIA_PANEL), this);
    }
}
