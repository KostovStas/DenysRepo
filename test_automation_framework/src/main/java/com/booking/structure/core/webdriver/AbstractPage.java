package com.booking.structure.core.webdriver;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import org.openqa.selenium.WebDriver;

import com.booking.util.ScreenInfoUtil;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public abstract class AbstractPage extends PageObject {

    private static final double RELATIVE_SCROLL_OFFSET = 0.33;
    private final int defaultScrollOffset;

    public AbstractPage(final WebDriver driver) {
        super(driver);
        defaultScrollOffset = (int) Math.round(ScreenInfoUtil.getHeight() * RELATIVE_SCROLL_OFFSET);
        getDriver().manage().window().maximize();
    }

    protected boolean isElementPresent(final String xpathLocator) {
        setImplicitTimeout(500, MILLISECONDS);
        final boolean isElementsPresent = isNotEmpty(getDriver().findElements(By.xpath(xpathLocator)));
        resetImplicitTimeout();
        return isElementsPresent;
    }

    protected final void scrollPageUp() {
        scrollByOffset(-10000);
    }

    protected final void scrollUp() {
        scrollByOffset(-defaultScrollOffset);
    }

    protected final void scrollPageDown() {
        scrollByOffset(10000);
    }

    protected final void scrollDown() {
        scrollByOffset(defaultScrollOffset);
    }

    protected final void scrollByOffset(final int offset) {
        final String script = format("window.scrollTo(0,%d);", offset);
        getJavascriptExecutorFacade().executeScript(script);
    }
}