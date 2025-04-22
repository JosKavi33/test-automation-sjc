package steps.products;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import pages.cart.CartPage;
import pages.products.ProductsPage;
import utilities.CommonFlows;

public class ProductAddedStepDefinitions {

    private final CommonFlows commonFlows = new CommonFlows();
    private final ProductsPage productsPage = new ProductsPage();
    private final CartPage cartPage = new CartPage();

    @Step("🛒 The user has an empty shopping cart and navigates to the Products page")
    @Given("The user has an empty shopping cart and navigates to the Products page")
    public void goToShoppingPage() {
        commonFlows.goToShoppingCartPageAndDeletePreviousItems();
    }

    @Step("📦 The Products page loads correctly")
    @Then("The Products page loads correctly")
    public void verifyProductsPage() {
        productsPage.verifyPage();
    }

    @Step("➕ The user adds {int} products to the shopping cart")
    @When("The user adds {int} products to the shopping cart")
    public void theUserAddsProductsToTheShoppingCart(int quantity) {
        productsPage.addProducts(quantity);
        cartPage.waitPageToLoad();
    }

    @Step("🛍️ The shopping cart should contain {int} products")
    @Then("The shopping cart should contain {int} products")
    public void theShoppingCartShouldContainProducts(int quantity) {
        cartPage.verifyAddedProducts(quantity);
    }
}
