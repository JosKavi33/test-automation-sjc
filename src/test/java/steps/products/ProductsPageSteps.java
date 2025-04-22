package steps.products;

import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import models.ItemDetails;
import pages.products.ProductsPage;

import java.util.List;

public class ProductsPageSteps {

    private final ProductsPage productsPage = new ProductsPage();

    @Step("🔍 I check the details of product '{string}' with price {int}")
    @When("I check the details of {string} with price {int}")
    public void iCheckDetailsOfProduct(String productName, int productPrice) {
        ItemDetails item = new ItemDetails(productName, productPrice);
        List<ItemDetails> oneItemList = List.of(item);

        productsPage.verifyNameAndPriceProducts(oneItemList);
    }
}
