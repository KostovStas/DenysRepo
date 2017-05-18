package com.company.core.webdriver;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.core.pages.PageObject;

public abstract class AbstractPage extends PageObject {

    public AbstractPage(final WebDriver driver) {
        super(driver);
        getDriver().manage().window().maximize();
    }

    public boolean isElementPresent(final String xpathLocator) {
        setImplicitTimeout(500, MILLISECONDS);
        final boolean isElementsPresent = isNotEmpty(getDriver().findElements(By.xpath(xpathLocator)));
        resetImplicitTimeout();
        return isElementsPresent;
    }

    public void waitForAjaxToComplete() {
        final String script = "return window.jQuery != undefined && jQuery.active == 0;";
        final Object result = getJavascriptExecutorFacade().executeScript(script);
        waitFor(condition -> result);
    }

    public void waitForReadyStateToComplete() {
        final String script = "return document.readyState";
        final Object result = getJavascriptExecutorFacade().executeScript(script);
        waitFor(condition -> result.equals("complete"));
    }
}