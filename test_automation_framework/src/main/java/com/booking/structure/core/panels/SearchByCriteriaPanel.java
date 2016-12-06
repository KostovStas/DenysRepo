package com.booking.structure.core.panels;

import com.booking.structure.core.webdriver.AbstractPage;
import com.booking.structure.core.webdriver.AbstractPanel;
import com.booking.util.WaitUtil;

import net.serenitybdd.core.pages.WebElementFacade;

import static java.lang.String.*;

public class SearchByCriteriaPanel extends AbstractPanel {

    private static final String DESTINATION_INPUT = ".//input[contains(@class, 'sb-destination__input')]";
    private static final String OPEN_CHECK_IN_DATEPICKER_PANEL_BUTTON = "(.//i[contains(@class, 'bicon-downchevron')])[1]";
    private static final String OPEN_CHECK_OUT_DATEPICKER_PANEL_BUTTON = "(.//i[contains(@class, 'bicon-downchevron')])[2]";
    private static final String CHECK_IN_DATEPICKER_PANEL = "(.//div[@class='c2-calendar'])[1]";
    private static final String CHECK_OUT_DATEPICKER_PANEL = "(.//div[@class='c2-calendar'])[2]";
    private static final String AUTOCOMPLETED_LIST = "//ul[contains(@class, 'c-autocomplete__list')]//li";
    private static final String SEARCH_BUTTON = ".//button[contains(@class, 'sb-searchbox__button')]//span)[1]";

    private final long waitTimeout = 10000L;
    private final long waitInterval = 500L;

    public SearchByCriteriaPanel(WebElementFacade panelBaseLocation, AbstractPage driverDelegate) {
        super(panelBaseLocation, driverDelegate);
    }

    public void fillDestinationField(final String destinationInfo) {
        findBy(DESTINATION_INPUT).then().type(destinationInfo);
        final WebElementFacade autoCompletedListPanel = findBy(AUTOCOMPLETED_LIST);
        WaitUtil.waitFor(elementShouldBePresent, autoCompletedListPanel, waitTimeout, waitInterval);
        selectFirstItemFromList();
    }

    public DatePickerPanel openCheckInDatePicker() {
        final WebElementFacade checkInDatePicker = findBy(OPEN_CHECK_IN_DATEPICKER_PANEL_BUTTON);
        checkInDatePicker.then().click();
        WaitUtil.waitFor(elementShouldBePresent, checkInDatePicker, waitTimeout, waitInterval);
        return new DatePickerPanel(findBy(CHECK_IN_DATEPICKER_PANEL), getDriverDelegate());
    }

    public DatePickerPanel openCheckOutDatePicker() {
        final WebElementFacade checkOutDatePicker = findBy(OPEN_CHECK_OUT_DATEPICKER_PANEL_BUTTON);
        checkOutDatePicker.then().click();
        WaitUtil.waitFor(elementShouldBePresent, checkOutDatePicker, waitTimeout, waitInterval);
        return new DatePickerPanel(findBy(CHECK_OUT_DATEPICKER_PANEL), getDriverDelegate());
    }

    public void clickSearchButton() {
        findBy(SEARCH_BUTTON).then().click();
    }

    private void selectFirstItemFromList() {
        findMultipleBy(AUTOCOMPLETED_LIST).stream().findFirst().get().click();
    }
}
