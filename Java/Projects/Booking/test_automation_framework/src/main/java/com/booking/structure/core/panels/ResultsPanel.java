package com.booking.structure.core.panels;

import java.util.stream.Stream;

import com.booking.structure.core.webdriver.AbstractPage;
import com.booking.structure.core.webdriver.AbstractPanel;

import net.serenitybdd.core.pages.WebElementFacade;

public class ResultsPanel extends AbstractPanel {

    private static final String SEARCH_RESULT_ITEMS = ".//div[contains(@class, 'sr_item sr_item_new')]";

    public ResultsPanel(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        super(panelBaseLocation, driverDelegate);
    }

    public Stream<ResultsPanelItem> getResultItems() {
        return findMultipleBy(SEARCH_RESULT_ITEMS).stream().map(this::getResultItem);
    }

    private ResultsPanelItem getResultItem(final WebElementFacade location) {
        return new ResultsPanelItem(location, getDriverDelegate());
    }
}
