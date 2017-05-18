package com.booking.structure.core.webdriver;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverAdaptor implements WebDriver {

    private WebElement wrappedElement;
    private WebDriver wrappedDriver;

    public WebDriverAdaptor(final WebElement wrappedElement, final WebDriver wrappedDriver) {
        this.wrappedElement = wrappedElement;
        this.wrappedDriver = wrappedDriver;
    }

    @Override
    public void close() {
        // do nothing
    }

    @Override
    public WebElement findElement(final By arg0) {
        return wrappedElement.findElement(arg0);
    }

    @Override
    public List<WebElement> findElements(final By arg0) {
        return wrappedElement.findElements(arg0);
    }

    @Override
    public void get(final String arg0) {
        wrappedDriver.get(arg0);

    }

    @Override
    public String getCurrentUrl() {
        return wrappedDriver.getCurrentUrl();
    }

    @Override
    public String getPageSource() {
        return wrappedDriver.getPageSource();
    }

    @Override
    public String getTitle() {
        return wrappedDriver.getTitle();
    }

    @Override
    public String getWindowHandle() {
        return null;
    }

    @Override
    public Set<String> getWindowHandles() {
        return null;
    }

    @Override
    public Options manage() {
        return wrappedDriver.manage();
    }

    @Override
    public Navigation navigate() {
        return wrappedDriver.navigate();
    }

    @Override
    public void quit() {
        // do nothing
    }

    @Override
    public TargetLocator switchTo() {
        return wrappedDriver.switchTo();
    }
}
