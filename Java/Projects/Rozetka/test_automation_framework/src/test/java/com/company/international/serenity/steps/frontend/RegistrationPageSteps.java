package com.company.international.serenity.steps.frontend;

import com.company.core.common.bean.UserInfoBean;
import com.company.pageobject.pages.RegistrationPage;
import com.company.util.WebDriverUtil;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.Objects;

public class RegistrationPageSteps extends ScenarioSteps {

    private RegistrationPage registrationPage;

    public RegistrationPageSteps(final Pages pages) {
        registrationPage = pages.getPage(RegistrationPage.class);
    }

    @Step
    public void registerUser(final UserInfoBean userInfo) {
        Objects.requireNonNull(userInfo, "There is user's registration info is null!");
        registrationPage.getSignUpPanel()
                .typeName(userInfo.getUserName())
                .typeLogin(userInfo.getLogin())
                .typePassword(userInfo.getPassword())
                .clickSignUpButton();
        registrationPage.waitForAjaxToComplete();
        registrationPage.waitForReadyStateToComplete();
    }

    @Step
    public void openRegistrationPage() {
        WebDriverUtil.openPageByExtraPath(registrationPage);
        registrationPage.waitForAjaxToComplete();
        registrationPage.waitForReadyStateToComplete();
    }

    @Step
    public void activateUserMailByLink(final String activationLink) {
        WebDriverUtil.openPage(registrationPage, activationLink);
        registrationPage.waitForAjaxToComplete();
        registrationPage.waitForReadyStateToComplete();
    }

    @Step
    public String getLoggedInUser() {
        return registrationPage.getHeaderPanel()
                .getUserName();
    }
}
