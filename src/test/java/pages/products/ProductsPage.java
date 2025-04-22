package pages.products;

import models.ItemDetails;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By categoryTitle = By.xpath("//h2[text()='Category']");
    private final By brandsTitle = By.xpath("//h2[text()='Brands']");
    private final By allProductsTitle = By.xpath("//h2[text()='All Products']");
    private final By allProductsItemsId = By.cssSelector("a[data-product-id]");
    private final By allProductsItems = By.className(".product-image-wrapper");
    private final By continueShoppingButton = By.xpath("//button[text()='Continue Shopping']");
    private final By viewCartButton = By.xpath("//a[u[text()='View Cart']]");
    private final By especialOfertImage = By.id("sale_image");
    private final By searchProductInput = By.id("search_product");

    private final By detailProductInput(String productDetailsLink) {
        final var xpath = String.format("a[href='/product_details/%s']", productDetailsLink);
        return By.cssSelector(xpath);
    }

    private final By getProductName(String productName) {
        final var xpath = String.format("//p[text()='%s']", productName);
        return By.xpath(xpath);
    }

    private final By getProductPrice(String itemName) {
        return RelativeLocator
                .with(By.xpath("//h2[text()='Rs. %s']"))
                .below(getProductName(itemName));
    }

    @Override
    public void waitPageToLoad() {
        Logs.info("⏳ Waiting ProductsPage");
        waitPage(allProductsTitle, this.getClass().getSimpleName());
        Logs.info("✅ ProductsPage is Loaded");
    }

    @Override
    public void verifyPage() {
        Logs.info("🔍 Verifying ProductsPage");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(especialOfertImage).isDisplayed(), "Failure in especialOfertImage"),
                () -> Assertions.assertTrue(find(searchProductInput).isDisplayed(), "Failure in searchProductInput"),
                () -> Assertions.assertFalse(findAll(allProductsItemsId).isEmpty(), "Failure in allProductsItemsId"),
                () -> Assertions.assertTrue(find(categoryTitle).isDisplayed(), "Failure in categoryTitle"),
                () -> Assertions.assertTrue(find(brandsTitle).isDisplayed(), "Failure in brandsTitle"),
                () -> Assertions.assertTrue(find(allProductsTitle).isDisplayed(), "Failure in allProductsTitle")
        );
        Logs.info("✅ The ProductsPage Verification Passed");
    }

    public void addProducts(int numberOfProducts) {
        Logs.info("🛒 Adding " + numberOfProducts + " Products to the Shopping Cart");

        List<WebElement> products = findAll(allProductsItemsId);
        int maxIndex = Math.min(products.size(), numberOfProducts * 2);

        for (int i = 0; i < maxIndex; i += 2) {
            WebElement product = products.get(i);
            product.click();

            String productId = product.getAttribute("data-product-id");
            if (productId != null) {
                Logs.info("🆔 Product ID: " + productId);
            }

            boolean isLastProduct = (i + 2 >= maxIndex);
            By buttonToClick = isLastProduct ? viewCartButton : continueShoppingButton;

            new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(buttonToClick))
                    .click();
        }

        Logs.info("✅ Successfully Added " + numberOfProducts + " Products to the Cart");
    }


    public void firstProductDetailClick() {
        Logs.info("👉 Making Click at First Product Detail");
        find(detailProductInput("1")).click();
        Logs.info("✅ The detailProductInput, whit index '1' has Been Clicked");
    }


    public void verifyNameAndPriceProducts(List<ItemDetails> itemList) {
        Logs.info("🔍 Verifying Name and Price For Excel Products");

        List<WebElement> products = getDriver().findElements(By.cssSelector(".product-image-wrapper"));
        Logs.info("🔍 Products Found on the Page: " + products.size());

        List<String> notFoundItems = new ArrayList<>();

        for (ItemDetails expectedItem : itemList) {
            String expectedName = expectedItem.getItemName().trim();
            int expectedPrice = expectedItem.getItemPrice();
            boolean matchFound = false;

            Logs.info("🔍 Looking: " + expectedName + " - Expected Price: " + expectedPrice);

            for (WebElement product : products) {
                String actualName = product.findElement(By.cssSelector(".productinfo p")).getText().trim();
                String priceString = product.findElement(By.cssSelector(".productinfo h2")).getText().replace("Rs. ", "").trim();
                int actualPrice = Integer.parseInt(priceString);

                if (actualName.equalsIgnoreCase(expectedName)) {
                    Assertions.assertEquals(actualPrice, expectedPrice, "❌ Price mismatch for: " + expectedName);
                    Logs.debug("✅ Product Matched: " + actualName + " - Expected Price: " + expectedPrice + ", Found: " + actualPrice);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                Logs.error("❌ Product not Found: " + expectedName);
                notFoundItems.add(expectedName);
            }
        }

        if (!notFoundItems.isEmpty()) {
            String missingProducts = String.join(", ", notFoundItems);
            Assertions.fail("❌ The Following Products Were Not Found on the Page: " + missingProducts);
        }

        Assertions.assertTrue(notFoundItems.isEmpty(), "✅ The Name and Price For Excel Products Verification Passed");

    }

}
