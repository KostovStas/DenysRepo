package com.company.pageobject.panels;

import com.company.core.webdriver.AbstractPage;
import com.company.core.webdriver.AbstractPanel;
import net.serenitybdd.core.pages.WebElementFacade;

public class SignUpPanel extends AbstractPanel {

    private static final String NAME_INPUT = ".//input[contains(@name, 'title')]";
    private static final String LOGIN_INPUT = ".//input[contains(@name, 'email')]";
    private static final String PASSWORD_INPUT = ".//input[contains(@name, 'password')]";
    private static final String SIGN_UP_BUTTON = ".//button[@type='submit']";

    public SignUpPanel(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        super(panelBaseLocation, driverDelegate);
    }

    public SignUpPanel typeName(final String name) {
        findBy(NAME_INPUT).then().type(name);
        return this;
    }

    public SignUpPanel typeLogin(final String login) {
        findBy(LOGIN_INPUT).then().type(login);
        return this;
    }

    public SignUpPanel typePassword(final String password) {
        findBy(PASSWORD_INPUT).then().type(password);
        return this;
    }

    public SignUpPanel clickSignUpButton() {
        findBy(SIGN_UP_BUTTON).then().click();
        return this;
    }
}
