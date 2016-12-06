package com.booking.structure.common;

import org.apache.commons.lang.StringUtils;

import com.booking.structure.beans.HotelSearchCriteriaBean;
import com.booking.structure.core.pages.HotelsSearchPage;
import com.booking.util.WebDriverUtil;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

public class HotelSearchPageSteps extends ScenarioSteps {

    private HotelsSearchPage hotelsSearchPage;

    private final static String DATE_SEPARATOR = ",";

    public HotelSearchPageSteps(final Pages pages) {
        super(pages);
        hotelsSearchPage = getPages().getPage(HotelsSearchPage.class);
    }

    @Step
    public void openPage() {
        WebDriverUtil.openPage(hotelsSearchPage);
    }

    @Step
    public void fillInTravelingInfo(final HotelSearchCriteriaBean searchCriteria) {
        selectCheckInDate(searchCriteria.getCheckInDate());
        selectCheckOuDate(searchCriteria.getCheckOutDate());
        fillDestinationInfo(searchCriteria.getDestination());
        clickSearchButton();
    }

    @Step
    public void selectCheckInDate(final String checkInDate) {
        selectTravelingDate(checkInDate, true);
    }

    @Step
    public void selectCheckOuDate(final String checkOuDate) {
        selectTravelingDate(checkOuDate, false);
    }

    @Step
    public void fillDestinationInfo(final String destinationInfo) {
        hotelsSearchPage.getSearchByCriteriaPanel()
                .fillDestinationField(destinationInfo);
    }

    @Step
    public void clickSearchButton() {
        hotelsSearchPage.getSearchByCriteriaPanel()
                .clickSearchButton();
    }

    private void selectTravelingDate(final String travelingDate, final boolean isCheckInDate) {
        final String[] items = travelingDate.split(DATE_SEPARATOR);
        if (isCheckInDate) {
            selectCheckInDate(items);
        } else {
            selectCheckOutDate(items);
        }
    }

    private void selectCheckInDate(final String[] dateItems) {
        hotelsSearchPage.getSearchByCriteriaPanel()
               .openCheckInDatePicker()
                .getMonthTablePanel(StringUtils.trim(dateItems[0]))
                .selectDateByValue(dateItems[1]);
    }

    private void selectCheckOutDate(final String[] dateItems) {
        hotelsSearchPage.getSearchByCriteriaPanel()
                .openCheckOutDatePicker()
                .getMonthTablePanel(StringUtils.trim(dateItems[0]))
                .selectDateByValue(StringUtils.trim(dateItems[1]));
    }
}
