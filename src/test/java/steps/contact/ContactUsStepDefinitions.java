package steps.contact;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pages.contact.ContactUsPage;
import utilities.CommonFlows;

public class ContactUsStepDefinitions {

    private final CommonFlows commonFlows = new CommonFlows();
    private final ContactUsPage contactUsPage = new ContactUsPage();

    @Step("🌍 The user navigates to the Contact Us page")
    @Given("The user navigates to the Contact Us page")
    public void goToContactUsPage() {
        commonFlows.goToContactUsPage();
        contactUsPage.waitPageToLoad();
    }

    @Step("📋 The user should see the Contact Us page loaded")
    @Then("The user should see the Contact Us page loaded")
    public void verifyContactUsPageLoaded() {
        contactUsPage.verifyPage();
    }

    @Step("✍️ The user fills and submits the Contact Us form")
    @And("The user fills and submits the Contact Us form")
    public void fillAndSubmitContactUsForm() {
        contactUsPage.fillContactUsForm();
    }

    @Step("✅ The user should see a success message")
    @Then("The user should see a success message")
    public void verifySuccessMessage() {
        contactUsPage.verifySuccessMessage();
    }
}
