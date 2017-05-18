package com.company.pageobject.panels;

import com.company.core.webdriver.AbstractPage;
import com.company.core.webdriver.AbstractPanel;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginBoxPanel extends AbstractPanel {

    private static final String SIGN_UP_LINK = ".//a[contains(@class, 'auth-f-signup-link')]";

    protected LoginBoxPanel(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        super(panelBaseLocation, driverDelegate);
    }

    public void clickSignUpLink() {
        findBy(SIGN_UP_LINK).then().click();
    }
}
