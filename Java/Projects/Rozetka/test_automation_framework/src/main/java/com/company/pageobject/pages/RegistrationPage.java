package com.company.pageobject.pages;

import org.openqa.selenium.WebDriver;

import com.company.core.webdriver.AbstractPage;
import com.company.pageobject.panels.HeaderPanel;
import com.company.pageobject.panels.SignUpPanel;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("/signup/")
public class RegistrationPage extends AbstractPage {

    private static final String SIGN_UP_PANEL = "//div[@class='signup']";
    private static final String HEADER_PANEL = "//div[@class='clearfix body-header-row-top']";

    public RegistrationPage(final WebDriver driver) {
        super(driver);
    }

    public SignUpPanel getSignUpPanel() {
        return new SignUpPanel(findBy(SIGN_UP_PANEL), this);
    }

    public HeaderPanel getHeaderPanel() {
        return new HeaderPanel(findBy(HEADER_PANEL), this);
    }
}
