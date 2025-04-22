package steps.products;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import pages.products.DetailsProductPage;
import pages.products.ProductsPage;
import utilities.CommonFlows;

public class DetailsProductStepDefinitions {

    private final DetailsProductPage detailsProductPage = new DetailsProductPage();
    private final ProductsPage productsPage = new ProductsPage();
    private final CommonFlows commonFlows = new CommonFlows();

    @Step("🛍️ The user navigates to the Products page")
    @Given("The user navigates to the Products page")
    public void goToProductsPage() {
        commonFlows.goToProductsPage();
    }

    @Step("📦 The user opens the first product details")
    @When("The user opens the first product details")
    public void openFirstProductDetails() {
        productsPage.firstProductDetailClick();
        detailsProductPage.waitPageToLoad();
    }

    @Step("🔍 The user should see the Product Details page loaded")
    @Then("The user should see the Product Details page loaded")
    public void verifyProductDetailsPage() {
        detailsProductPage.verifyPage();
    }

    @Step("✍️ The user submits a product review")
    @And("The user submits a product review")
    public void submitProductReview() {
        detailsProductPage.fillReviewFormDetailsProductPage();
    }

    @Step("✅ The user should see a confirmation or success message")
    @Then("The user should see a confirmation or success message")
    public void verifyReviewSuccessMessage() {
        detailsProductPage.verifyReviewSuccessMessage();
    }
}
