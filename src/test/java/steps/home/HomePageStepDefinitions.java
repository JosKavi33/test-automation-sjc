package steps.home;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pages.homepage.HomePage;
import pages.topbar.TopBar;
import utilities.CommonFlows;

public class HomePageStepDefinitions {

    private final CommonFlows commonFlows = new CommonFlows();
    private final HomePage homePage = new HomePage();
    private final TopBar topBar = new TopBar();

    @Step("🏠 The user goes to the HomePage")
    @Given("The user goes to the HomePage")
    public void goToHomePage() {
        commonFlows.goToHomePage();
        homePage.waitPageToLoad();
    }

    @Step("✅ The user verifies that the page UI is correct")
    @Then("The user verifies that the page UI is correct")
    public void verifyHomePage() {
        homePage.verifyPage();
        topBar.verifyPage();
    }
}
