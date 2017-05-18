package com.company.pageobject.pages;

import com.company.core.webdriver.AbstractPage;
import com.company.pageobject.panels.HeaderPanel;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {

    private static final String HEADER_PANEL = "//div[@class='clearfix body-header-row-top']";

    public HomePage(final WebDriver driver) {
        super(driver);
    }

    public HeaderPanel getHeaderPanel() {
        return new HeaderPanel(findBy(HEADER_PANEL), this);
    }
}
