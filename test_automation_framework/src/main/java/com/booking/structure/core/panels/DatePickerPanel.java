package com.booking.structure.core.panels;

import static java.lang.String.format;

import com.booking.structure.core.webdriver.AbstractPage;
import com.booking.structure.core.webdriver.AbstractPanel;

import net.serenitybdd.core.pages.WebElementFacade;

public class DatePickerPanel extends AbstractPanel {

    private static final String MONTH_TABLE_PANEL = ".//table[.//th[contains(text(), '%s')]]";
    private static final String NEXT_MONTH_BUTTON = ".//span[@class='c2-button-inner' and not(ancestor::*[contains(@class, 'disabled')])]";

    public DatePickerPanel(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        super(panelBaseLocation, driverDelegate);
    }

    public MonthPanel getMonthTablePanel(final String monthAndYear) {
        final WebElementFacade monthPanel = findBy(format(MONTH_TABLE_PANEL, monthAndYear));
        final WebElementFacade nextMonthButton = findBy(NEXT_MONTH_BUTTON);
        while (!monthPanel.isDisplayed()) {
            nextMonthButton.waitUntilVisible().click();
        }
        return new MonthPanel(findBy(format(MONTH_TABLE_PANEL, monthAndYear)), getDriverDelegate());
    }
}
