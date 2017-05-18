package com.booking.structure.core.panels;

import org.apache.commons.lang3.StringUtils;

import com.booking.structure.core.webdriver.AbstractPage;
import com.booking.structure.core.webdriver.AbstractPanel;

import net.serenitybdd.core.pages.WebElementFacade;

public class ResultsPanelItem extends AbstractPanel {

    private static final String HOTEL_NAME = ".//span[@class='sr-hotel__name']";
    private static final String HOTEL_LOCATION = ".//div[@class='address']//a";

    public ResultsPanelItem(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        super(panelBaseLocation, driverDelegate);
    }

    public String getHotelName() {
        return StringUtils.trim(findBy(HOTEL_NAME).then().getText());
    }

    public String getHotelLocation() {
        return StringUtils.trim(findBy(HOTEL_LOCATION).then().getText());
    }
}
