package pages.contact;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ContactUsPage extends BasePage {

    private final By contactUsTitle = By.xpath("//h2[text()='Contact ']");
    private final By getInTouchTitle = By.xpath("//h2[text()='Get In Touch']");
    private final By nameInput = By.cssSelector("input[data-qa='name']");
    private final By emailInput = By.cssSelector("input[data-qa='email']");
    private final By subjectInput = By.cssSelector("input[data-qa='subject']");
    private final By messageInput = By.id("message");
    private final By selectFileButton = By.cssSelector("input[name='upload_file']");
    private final By submitButton = By.cssSelector("input[data-qa='submit-button']");
    private final By successAlertMessage = By.className("status");

    @Override
    public void waitPageToLoad() {
        Logs.info("⏳ Waiting ProductsPage");
        waitPage(contactUsTitle, this.getClass().getSimpleName());
        Logs.info("✅ ProductsPage is Loaded");
    }

    @Override
    public void verifyPage() {
        Logs.info("🔍 Verifying ContactUsPage");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(contactUsTitle).isDisplayed(), "❌ Failure in the Locator contactUsTitle"),
                () -> Assertions.assertTrue(find(getInTouchTitle).isDisplayed(), "❌ Failure in the Locator getInTouchTitle"),
                () -> Assertions.assertTrue(find(nameInput).isDisplayed(), "❌ Failure in the Locator nameInput"),
                () -> Assertions.assertTrue(find(emailInput).isDisplayed(), "❌ Failure in the Locator emailInput"),
                () -> Assertions.assertTrue(find(subjectInput).isDisplayed(), "❌ Failure in the Locator subjectInput"),
                () -> Assertions.assertTrue(find(messageInput).isDisplayed(), "❌ Failure in the Locator messageInput"),
                () -> Assertions.assertTrue(find(selectFileButton).isDisplayed(), "❌ Failure in the Locator selectFileButton"),
                () -> Assertions.assertTrue(find(submitButton).isDisplayed(), "❌ Failure in the Locator submitButton")
        );
        Logs.info("✅ The ContactUsPage Verification Passed");
    }

    public void fillContactUsForm() {
        Logs.info("📝 Filling ContactUs Form");
        final var faker = new Faker();
        final var name = faker.name().fullName();
        final var email = faker.internet().emailAddress();
        final var subject = faker.internet().emailSubject();
        final var message = faker.lorem().sentence();

        find(nameInput).sendKeys(name);
        find(emailInput).sendKeys(email);
        find(subjectInput).sendKeys(subject);
        find(messageInput).sendKeys(message);

        find(submitButton).click();
        getDriver().switchTo().alert().accept();
        Logs.info("✅ The Form has Been Filled");
    }

    public void verifySuccessMessage() {
        Logs.info("🔍 Verifying Success Message");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(successAlertMessage).isDisplayed(), "❌ Failure in the Locator successAlertMessage"),
                () -> Assertions.assertEquals(find(successAlertMessage).getText(), "Success! Your details have been submitted successfully.", "❌ Failure in the Expected Message")
        );
        Logs.info("✅ The Success Message Verification Passed");
    }
}
