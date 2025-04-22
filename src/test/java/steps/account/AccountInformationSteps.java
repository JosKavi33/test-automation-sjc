package steps.account;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import pages.account.AccountCreatedPage;
import pages.account.AccountInformationPage;
import pages.authentication.SignupLogin;
import utilities.CommonFlows;

public class AccountInformationSteps {

    private final CommonFlows commonFlows = new CommonFlows();
    private final SignupLogin signupLogin = new SignupLogin();
    private final AccountInformationPage accountInformationPage = new AccountInformationPage();
    private final AccountCreatedPage accountCreatedPage = new AccountCreatedPage();

    @Step("🔑 The user goes to the SignUpLogin page")
    @Given("The user goes to the SignUpLogin page")
    public void goToSignUpLoginPage() {
        commonFlows.goToSignUpLoginPage();
    }

    @Step("📝 The user fills out the signup form")
    @When("The user fills out the signup form")
    public void fillSignupForm() {
        signupLogin.fillSignUp();
        accountInformationPage.waitPageToLoad();
        accountInformationPage.verifyPage();
    }

    @Step("🖊️ The user fills out the account information form")
    @When("The user fills out the account information form")
    public void fillAccountInformationForm() {
        accountInformationPage.fillForms();
        accountCreatedPage.waitPageToLoad();
    }

    @Step("✅ The user sees the Account Created confirmation")
    @Then("The user sees the Account Created confirmation")
    public void verifyAccountCreated() {
        accountCreatedPage.verifyPage();
    }
}
