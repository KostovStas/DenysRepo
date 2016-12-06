package com.booking.structure.core.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.booking.structure.core.panels.ResultsPanel;
import com.booking.structure.core.webdriver.AbstractPage;

public class SearchResultPage extends AbstractPage {

    private static final String RESULTS_PANEL = "//div[@id='hotellist_inner']";

    public SearchResultPage(final WebDriver driver) {
        super(driver);
    }

    public ResultsPanel getResultsPanel() {
        withTimeoutOf(10, TimeUnit.SECONDS).waitFor(RESULTS_PANEL);
        return new ResultsPanel(findBy(RESULTS_PANEL), this);
    }
}
