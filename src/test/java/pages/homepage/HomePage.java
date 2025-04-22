package pages.homepage;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class HomePage extends BasePage {

    private final By itemCarrusel = By.id("recommended-item-carousel");

    @Override
    public void waitPageToLoad() {
        Logs.info("⏳ Waiting for HomePage Load");
        waitPage(itemCarrusel, this.getClass().getSimpleName());
        Logs.info("✅ HomePage is Loaded");
    }

    @Override
    public void verifyPage() {
        Logs.info("🔍 Verifying HomePage");
        Assertions.assertTrue(find(itemCarrusel).isDisplayed(), "❌ Failure in the itemCarrusel locator");
        Logs.info("✅ The HomePage Verification Passed");
    }

}
