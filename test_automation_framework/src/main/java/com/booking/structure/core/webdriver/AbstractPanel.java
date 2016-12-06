package com.booking.structure.core.webdriver;

import static ch.lambdaj.Lambda.convert;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.util.List;
import java.util.function.Predicate;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import ch.lambdaj.function.convert.Converter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementFacadeImpl;
import net.serenitybdd.core.pages.WebElementState;
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

    protected WebDriver getDriver() {
        return driverDelegate.getDriver();
    }

    protected Actions withAction() {
        final WebDriver proxiedDriver = ((WebDriverFacade) getDriver()).getProxiedDriver();
        return new Actions(proxiedDriver);
    }

    protected AbstractPage getDriverDelegate() {
        return driverDelegate;
    }


    protected WebElementFacade findBy(final String xpathOrCssSelector) {
        final WebElement webElement = panelToWebDriver.findElement(xpathOrCssSelector(xpathOrCssSelector));
        return WebElementFacadeImpl.wrapWebElement(getDriver(), webElement, waitForTimeoutInMilliseconds);
    }

    protected List<WebElementFacade> findMultipleBy(final String xpathOrCssSelector) {
        getDriver().manage().timeouts().implicitlyWait(500, MILLISECONDS);
        List<WebElement> matchingWebElements;
        try {
            matchingWebElements = panelToWebDriver.findElements(xpathOrCssSelector(xpathOrCssSelector));
        } finally {
            getDriverDelegate().resetImplicitTimeout();
        }
        return convert(matchingWebElements, toWebElementFacades());
    }

    protected String getText(final String xpathLocator) {
        final String value = isElementPresent(xpathLocator)
                && findBy(xpathLocator).isCurrentlyVisible() ? findBy(xpathLocator).then().getText() : null;
        return value;
    }

    protected String getValue(final String xpathLocator) {
        final String value = isElementPresent(xpathLocator)
                && findBy(xpathLocator).isCurrentlyVisible() ? findBy(xpathLocator).then().getValue() : null;
        return value;
    }

    protected String getAttribute(final String xpathLocator, final String attributeName) {
        final boolean isElementFound = isElementPresent(xpathLocator) && findBy(xpathLocator).isCurrentlyVisible();
        final String attribute = isElementFound ? findBy(xpathLocator).then().getAttribute(attributeName) : null;
        return attribute;
    }

    protected Predicate<WebElementFacade> elementShouldBePresent = WebElementState::isCurrentlyVisible;

    protected boolean isElementPresent(final String xpathLocator) {
        getDriverDelegate().setImplicitTimeout(500, MILLISECONDS);
        final boolean isElementsPresent = isNotEmpty(findMultipleBy(xpathLocator));
        getDriverDelegate().resetImplicitTimeout();
        return isElementsPresent;
    }

    private long waitForTimeoutInSeconds() {
        return (waitForTimeoutInMilliseconds < 1000) ? 1 : (waitForTimeoutInMilliseconds / 1000);
    }

    private Converter<WebElement, WebElementFacade> toWebElementFacades() {
        return from -> element(from);
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

    private void initPanel(final WebElementFacade panelBaseLocation, final AbstractPage driverDelegate) {
        this.driverDelegate = driverDelegate;
        waitForTimeoutInMilliseconds = driverDelegate.waitForTimeoutInMilliseconds();
        panelToWebDriver = new WebDriverAdaptor(panelBaseLocation, getDriver());
        final ElementLocatorFactory finder = new SmartElementLocatorFactory(panelToWebDriver, null,
                (int) waitForTimeoutInSeconds());
        final FieldDecorator decorator = new SmartFieldDecorator(finder, getDriver(), driverDelegate);
        PageFactory.initElements(decorator, this);
    }
}