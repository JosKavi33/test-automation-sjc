package pages.account;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class AccountCreatedPage extends BasePage {

    private final By accountCreatedTitle = By.xpath("//b[text()='Account Created!']");
    private final By messageSuccesfull = By.xpath("//p[text()='Congratulations! Your new account has been successfully created!']");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    @Override
    public void waitPageToLoad() {
        Logs.info("⏳ Waitting for the Page AccountCreatedPage Load");
        waitPage(accountCreatedTitle, this.getClass().getSimpleName());
        Logs.info("✅ AccountCreatedPage is Loaded");
    }

    @Override
    public void verifyPage() {
        Logs.info("🔍 Verifying the Page AccountCreatedPage");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(accountCreatedTitle).isDisplayed(), "❌ Failure in the Locator accountCreatedTitle"),
                () -> Assertions.assertTrue(find(messageSuccesfull).isDisplayed(), "❌ Failure in the Locator messageSuccesfull"),
                () -> Assertions.assertTrue(find(continueButton).isDisplayed(), "❌ Failure in the Locator continueButton")
        );
        Logs.info("✅ The AccountCreatedPage Verification Passed");
    }
}
