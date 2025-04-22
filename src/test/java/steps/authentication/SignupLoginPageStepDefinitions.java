package steps.authentication;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pages.authentication.SignupLogin;
import utilities.CommonFlows;

public class SignupLoginPageStepDefinitions {

    private final CommonFlows commonFlows = new CommonFlows();
    private final SignupLogin signupLogin = new SignupLogin();

    @Step("🌐 The user navigates to the Signup/Login page")
    @Given("The user navigates to the SignupLogin page")
    public void goToSignupLoginPage() {
        commonFlows.goToSignUpLoginPage();
    }

    @Step("📄 The user should see the Signup/Login form correctly displayed")
    @Then("The user should see the SignupLogin form correctly displayed")
    public void verifySignupLoginPage() {
        signupLogin.verifyPage();
    }
}
