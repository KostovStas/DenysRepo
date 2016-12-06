package com.booking.structure.core.panels;

import com.booking.structure.core.BaseDatePickerPanel;
import com.booking.structure.core.webdriver.AbstractPage;
import com.booking.structure.core.webdriver.AbstractPanel;

import net.serenitybdd.core.pages.WebElementFacade;

public class MonthPanel extends AbstractPanel implements BaseDatePickerPanel {

    private static final String DAYS_OF_MONTH = ".//span[@class='c2-day-inner']";

    public MonthPanel(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        super(panelBaseLocation, driverDelegate);
    }

    @Override
    public void selectDateByValue(final String value) {
        findMultipleBy(DAYS_OF_MONTH)
                .stream().filter(item -> item.waitUntilVisible().getText()
                .equals(value)).findFirst()
                .get().click();
    }
}
