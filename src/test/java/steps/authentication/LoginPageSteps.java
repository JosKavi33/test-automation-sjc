package steps.authentication;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pages.topbar.TopBar;
import utilities.CommonFlows;

public class LoginPageSteps {

    private final CommonFlows commonFlows = new CommonFlows();
    private final TopBar topBar = new TopBar();

    @Step("🔑 The user goes to the Login page")
    @Given("The user goes to the Login page")
    public void goToLoginPage() {
        commonFlows.goToLoginPage();
    }

    @Step("✅ The user should be logged in and see the correct page")
    @Then("The user should be logged in and see the correct page")
    public void verifyUserIsLoggedIn() {
        topBar.verifyAfterLoginPage();
    }
}
