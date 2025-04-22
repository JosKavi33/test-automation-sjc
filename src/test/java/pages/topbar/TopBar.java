package pages.topbar;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class TopBar extends BasePage {

    private final By loginButton = By.cssSelector("a[href='/login']");
    private final By cartButton = By.cssSelector("a[href='/view_cart']");
    private final By logoutButton = By.xpath("//a[text()=' Logout']");
    private final By deleteAccountButton = By.xpath("//a[text()=' Delete Account']");
    private final By productsButton = By.cssSelector("a[href='/products']");
    private final By contacUsButton = By.cssSelector("a[href='/contact_us']");


    @Override
    public void waitPageToLoad() {
        Logs.info("⏳ Waiting the TopBar Load");
        waitPage(cartButton, this.getClass().getSimpleName());
        Logs.info("✅ TopBar is Loaded");
    }

    @Override
    public void verifyPage() {
        Logs.info("🔍 Verifying the TopBar");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(loginButton).isDisplayed()),
                () -> Assertions.assertTrue(find(loginButton).isEnabled()),
                () -> Assertions.assertTrue(find(productsButton).isDisplayed()),
                () -> Assertions.assertTrue(find(productsButton).isEnabled()),
                () -> Assertions.assertTrue(find(cartButton).isDisplayed()),
                () -> Assertions.assertTrue(find(cartButton).isEnabled()),
                () -> Assertions.assertTrue(find(contacUsButton).isDisplayed()),
                () -> Assertions.assertTrue(find(contacUsButton).isEnabled())
        );
        Logs.info("✅ The TopBar Verification Passed");
    }

    public void verifyAfterLoginPage() {
        Logs.info("🔍 Verifying the Logged TopBar");
        waitElementToBeVisible(logoutButton);
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(logoutButton).isDisplayed()),
                () -> Assertions.assertTrue(find(logoutButton).isEnabled()),
                () -> Assertions.assertTrue(find(deleteAccountButton).isDisplayed()),
                () -> Assertions.assertTrue(find(deleteAccountButton).isEnabled()),
                () -> Assertions.assertTrue(find(cartButton).isDisplayed()),
                () -> Assertions.assertTrue(find(cartButton).isEnabled()),
                () -> Assertions.assertTrue(find(productsButton).isDisplayed()),
                () -> Assertions.assertTrue(find(productsButton).isEnabled())
        );
        Logs.info("✅ TopBar after login successfully verified.");
    }

    public void loginButtonClick() {
        Logs.info("👉 Making Click in Login button");
        find(loginButton).click();
        Logs.info("✅ The Login button has Been Clicked");
    }

    public void cartButtonClick() {
        Logs.info("👉 Making Click in Cart button");
        find(cartButton).click();
        Logs.info("✅ The Cart button has Been Clicked");
    }

    public void productsButtonClick() {
        Logs.info("👉 Making Click in Products button");
        find(productsButton).click();
        Logs.info("✅ The Products button has Been Clicked");
    }

    public void contactUsClick() {
        Logs.info("👉 Making Click in ContacUs button");
        find(contacUsButton).click();
        Logs.info("✅ The ContactUs button has Been Clicked");

    }

    public void waitElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
