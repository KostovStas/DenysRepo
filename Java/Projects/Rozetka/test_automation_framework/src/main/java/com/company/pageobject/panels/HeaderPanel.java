package com.company.pageobject.panels;

import com.company.core.webdriver.AbstractPage;
import com.company.core.webdriver.AbstractPanel;
import net.serenitybdd.core.pages.WebElementFacade;

import static org.apache.commons.lang3.StringUtils.*;

public class HeaderPanel extends AbstractPanel {

    private static final String USER_NAME = ".//a[@name, 'profile')]";

    public HeaderPanel(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        super(panelBaseLocation, driverDelegate);
    }

    public String getUserName() {
        return trim(findBy(USER_NAME).then().getText());
    }
}
