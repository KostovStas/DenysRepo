package com.company.core.webdriver;

import static ch.lambdaj.Lambda.convert;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.ui.Duration;

import ch.lambdaj.function.convert.Converter;
import net.serenitybdd.core.pages.RenderedPageObjectView;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementFacadeImpl;
import net.thucydides.core.annotations.locators.SmartElementLocatorFactory;
import net.thucydides.core.annotations.locators.SmartFieldDecorator;
import net.thucydides.core.webdriver.WebDriverFacade;

public abstract class AbstractPanel {

    private long waitForTimeoutInMilliseconds = 5000;
    private AbstractPage driverDelegate;
    private WebDriverAdaptor panelToWebDriver;

    protected AbstractPanel(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        initPanel(panelBaseLocation, driverDelegate);
    }

    private void initPanel(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        this.driverDelegate = driverDelegate;
        waitForTimeoutInMilliseconds = driverDelegate.waitForTimeoutInMilliseconds();
        panelToWebDriver = new WebDriverAdaptor(panelBaseLocation, getDriver());
        final ElementLocatorFactory finder = new SmartElementLocatorFactory(panelToWebDriver, null,
            (int) waitForTimeoutInSeconds());
        final FieldDecorator decorator = new SmartFieldDecorator(finder, getDriver(), driverDelegate);
        PageFactory.initElements(decorator, this);
    }

    public WebDriver getDriver() {
        return driverDelegate.getDriver();
    }

    public AbstractPage getDriverDelegate() {
        return driverDelegate;
    }

    protected WebDriverAdaptor getPanelToWebDriver() {
        return panelToWebDriver;
    }

    public Actions withAction() {
        final WebDriver proxiedDriver = ((WebDriverFacade) getDriver()).getProxiedDriver();
        return new Actions(proxiedDriver);
    }

    private long waitForTimeoutInSeconds() {
        return (waitForTimeoutInMilliseconds < 1000) ? 1 : (waitForTimeoutInMilliseconds / 1000);
    }

    public WebElementFacade findBy(final String xpathOrCssSelector) {
        final WebElement webElement = panelToWebDriver.findElement(xpathOrCssSelector(xpathOrCssSelector));
        return WebElementFacadeImpl.wrapWebElement(getDriver(), webElement, waitForTimeoutInMilliseconds);
    }

    public List<WebElementFacade> findMultipleBy(final String xpathOrCssSelector) {
        getDriver().manage().timeouts().implicitlyWait(500, MILLISECONDS);
        List<WebElement> matchingWebElements;
        try {
            matchingWebElements = panelToWebDriver.findElements(xpathOrCssSelector(xpathOrCssSelector));
        } finally {
            getDriverDelegate().resetImplicitTimeout();
        }
        return convert(matchingWebElements, toWebElementFacades());
    }

    public RenderedPageObjectView withTimeoutOf(final int timeout, final TimeUnit units) {
        return new RenderedPageObjectView(getDriver(), getDriverDelegate(), new Duration(timeout, units), false);
    }

    protected boolean isElementPresent(final String xpathLocator) {
        getDriverDelegate().setImplicitTimeout(500, MILLISECONDS);
        final boolean isElementsPresent = isNotEmpty(findMultipleBy(xpathLocator));
        getDriverDelegate().resetImplicitTimeout();
        return isElementsPresent;
    }

    private Converter<WebElement, WebElementFacade> toWebElementFacades() {
        return this::element;
    }

    private WebElementFacade element(final WebElement webElement) {
        return WebElementFacadeImpl.wrapWebElement(getDriver(), webElement, waitForTimeoutInMilliseconds);
    }

    private By xpathOrCssSelector(final String xpathOrCssSelector) {
        By locator;
        if (isXPath(xpathOrCssSelector)) {
            locator = By.xpath(xpathOrCssSelector);
        } else {
            locator = By.cssSelector(xpathOrCssSelector);
        }
        return locator;
    }

    private boolean isXPath(final String xpathExpression) {
        final XPathFactory factory = XPathFactory.newInstance();
        final XPath xpath = factory.newXPath();
        try {
            xpath.compile(xpathExpression);
        } catch (final Exception e) {
            return false;
        }
        return true;
    }
}