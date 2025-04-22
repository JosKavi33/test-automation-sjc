package pages.cart;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class CartPage extends BasePage {

    private final By shoppingCartTitle = By.xpath("//li[text()='Shopping Cart']");
    private final By messageOfStateCart = By.xpath("//b[text()='Cart is empty!']");
    private final By hereLinkButton = By.xpath("//a[u[text()='here']]");
    private final By productList = By.className("cart_product");
    private final By deleteProductsButton = By.className("cart_quantity_delete");


    @Override
    public void waitPageToLoad() {
        Logs.info("⏳ Waiting the CartPage Load");
        waitPage(shoppingCartTitle, this.getClass().getSimpleName());
        Logs.info("✅ CartPage is Loaded");
    }

    @Override
    public void verifyPage() {
        Logs.info("🔍 Verifying the CartPage");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(shoppingCartTitle).isDisplayed()),
                () -> Assertions.assertTrue(find(messageOfStateCart).isDisplayed()),
                () -> Assertions.assertTrue(find(hereLinkButton).isDisplayed())
        );
        Logs.info("✅ The CartPage Verification Passed.");
    }

    public void hereLinkClick() {
        Logs.info("👉 Making Click in Here link button");
        find(hereLinkButton).click();
        Logs.info("✅ hereLinkButton Has Been Clicked");
    }

    public void linkProductsVerify() {
        final var hereLinkLabel = find(hereLinkButton);
        Logs.info("🔍 Verifying the href link products");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(hereLinkButton).isEnabled(), "❌ Failure for locator availability"),
                () -> Assertions.assertEquals(hereLinkLabel.getAttribute("href"), "https://www.automationexercise.com/products", "❌ Failure in the href Link Expected")
        );
        Logs.info("✅ The href Links Products Passed.");
    }

    public void deletePreviousProducts() {
        var productos = findAll(deleteProductsButton);
        for (var element : productos) {
            Logs.info("🗑️ Deleted Previous Products");
            element.click();
            new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                    .until(ExpectedConditions.stalenessOf(element));
        }
        new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(hereLinkButton));

        Logs.info("✅ All Previous Products Deleted.");
    }

    public void verifyAddedProducts(int expectedSize) {
        Logs.info("🔍 Verifying that " + expectedSize + " Products were Added");
        int actualSize = findAll(productList).size();

        Assertions.assertEquals(actualSize, expectedSize,
                "❌ Mismatch in Product List Size. Expected: " + expectedSize + ", but found: " + actualSize);

        Logs.info("✅ The Added Products Verification Passed.");
    }


}
