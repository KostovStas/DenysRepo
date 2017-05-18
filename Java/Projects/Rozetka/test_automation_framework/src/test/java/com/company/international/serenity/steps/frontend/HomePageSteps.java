package com.company.international.serenity.steps.frontend;

import com.company.pageobject.pages.HomePage;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import static com.company.core.common.EnvironmentProperty.BASE_SITE_URL;

public class HomePageSteps extends ScenarioSteps {

    private HomePage homePage;

    public HomePageSteps(final Pages pages) {
        homePage = pages.getPage(HomePage.class);
    }

    public void openHomePage() {
        homePage.openAt(BASE_SITE_URL.readProperty());
        homePage.waitForAjaxToComplete();
        homePage.waitForReadyStateToComplete();
    }
}
