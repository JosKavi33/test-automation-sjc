package steps.cart;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import pages.cart.CartPage;
import pages.topbar.TopBar;
import utilities.CommonFlows;

public class CartPageStepDefinitions {

    private final CommonFlows commonFlows = new CommonFlows();
    private final TopBar topBar = new TopBar();
    private final CartPage cartPage = new CartPage();

    @Step("🧹 The user goes to the ShoppingCart page with a clean state")
    @Given("The user goes to the ShoppingCart page with a clean state")
    public void goToShoppingCartWithCleanState() {
        commonFlows.goToShoppingCartPageAndDeletePreviousItems();
    }

    @Step("🛒 The user clicks on the cart button")
    @When("The user clicks on the cart button")
    public void clickCartButton() {
        topBar.cartButtonClick();
        cartPage.waitPageToLoad();
    }

    @Step("🔗 The user should see all product links displayed correctly")
    @Then("The user should see all product links displayed correctly")
    public void verifyProductLinks() {
        cartPage.linkProductsVerify();
    }
}
