package com.company.international.jbehave.scenariosteps.rozetka;

import com.company.core.common.bean.UserInfoBean;
import com.company.international.serenity.steps.backend.TempMailSteps;
import com.company.international.serenity.steps.frontend.HomePageSteps;
import com.company.international.serenity.steps.frontend.RegistrationPageSteps;
import com.company.util.JbehaveUtil;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import static java.lang.String.*;
import static org.junit.Assert.*;

public class CreateNewAccountScenario {

    @Steps
    private HomePageSteps homePageSteps;

    @Steps
    private RegistrationPageSteps registrationPageSteps;

    @Steps
    private TempMailSteps tempMailSteps;

    @Given("user opens home page")
    public void openHomePage() {
        homePageSteps.openHomePage();
    }

    @Given("user opens registration page")
    public void openRegistrationButton() {
        registrationPageSteps.openRegistrationPage();
    }

    @When("user performs registration, using following user's information: $userInfo")
    public void performRegistration(final ExamplesTable table) {
        final UserInfoBean userInfo = JbehaveUtil.populate(table, UserInfoBean.class).get(0);
        registrationPageSteps.registerUser(userInfo);
    }

    @When("user $mail activates his account, using activation link received from email")
    public void activateAccountByActivationLink(final String email) {
        final String activationLink = tempMailSteps.getActivationLink(email);
        registrationPageSteps.activateUserMailByLink(activationLink);
    }

    @Then("user $user should be logged in")
    public void isUserLoggedIn(final String user) {
        assertEquals(format("There is user '%s' is not signed up!", user), user,
                registrationPageSteps.getLoggedInUser());
    }
}
