package utilities;

import org.openqa.selenium.WebDriver;
import pages.authentication.SignupLogin;
import pages.cart.CartPage;
import pages.homepage.HomePage;
import pages.products.ProductsPage;
import pages.topbar.TopBar;

public class CommonFlows {

    private WebDriver getDriver() {
        return WebDriverProvider.get();
    }

    // TODO: This block contains the code for cookie assignment; on this project's page, the login cookie is HttpOnly, so we cannot execute it.
    /*public void assignLoginCookies() {
        Logs.info("🔑 Assigning Login Cookies and Refreshing Page");
        getDriver().get("https://www.automationexercise.com/");

        Logs.info("🔍 Cookies before deletion:");
        for (Cookie cookie : getDriver().manage().getCookies()) {
            Logs.info(cookie.getName() + " = " + cookie.getValue());
        }

        getDriver().manage().deleteAllCookies();
        Logs.info("🗑️ All cookies cleared.");

        Cookie sessionCookie = new Cookie.Builder("sessionid", "hgt8db2j3k3q84ivlbehjgk8kmz6a4u4")
                .domain("www.automationexercise.com")
                .path("/")
                .isSecure(true)
                .build();
        getDriver().manage().addCookie(sessionCookie);

        Logs.info("🔍 Cookies after assignment:");
        for (Cookie cookie : getDriver().manage().getCookies()) {
            Logs.info("📝 Present Cookie: " + cookie.getName() + " = " + cookie.getValue());
        }

        getDriver().navigate().refresh();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[contains(text(), 'Signup / Login')]")));
            Logs.info("✅ Login successful, session active.");
        } catch (TimeoutException e) {
            Logs.debug("⚠️ Login may have failed, 'Signup / Login' button is still visible.");
        }

        getDriver().get("https://www.automationexercise.com/account");
        String currentUrl = getDriver().getCurrentUrl();
        if (currentUrl.contains("account")) {
            Logs.info("✅ Successfully redirected to account page, session is valid.");
        } else {
            Logs.debug("⚠️ Failed to access account page, session may not be valid.");
        }
    }*/

    public void goToHomePage() {
        Logs.info("🏠 Navigating to Home Page");
        getDriver().get("https://www.automationexercise.com/");
        validateUrlContains("automationexercise.com", "Home Page");
        new HomePage().waitPageToLoad();
    }

    public void goToSignUpLoginPage() {
        Logs.info("🔑 Navigating to SignUp Login Page");
        goToHomePage();
        new TopBar().loginButtonClick();
        validateUrlContains("login", "SignUp/Login Page");
        new SignupLogin().waitPageToLoad();
    }

    public void goToLoginPage() {
        Logs.info("🔑 Navigating to Login Page");
        goToHomePage();
        Logs.info("🧭 Clicking Login button on TopBar");
        new TopBar().loginButtonClick();
        validateUrlContains("login", "Login Page");
        Logs.info("📝 Filling login form with valid credentials");
        new SignupLogin().fillLogin();
    }


    public void goToProductsPage() {
        Logs.info("🛍️ Navigating to Products Page");
        goToLoginPage();
        new TopBar().waitPageToLoad();
        new TopBar().productsButtonClick();
        validateUrlContains("products", "Products Page");
    }

    public void goToShoppingCartPage() {
        Logs.info("🛒 Navigating to Shopping Cart Page");
        goToLoginPage();
        new TopBar().waitPageToLoad();
        new TopBar().cartButtonClick();
        validateUrlContains("view_cart", "Shopping Cart Page");
    }

    public void goToShoppingCartPageAndDeletePreviousItems() {
        Logs.info("🛒 Navigating to Shopping Cart Page and Deleting Previous Items");
        goToShoppingCartPage();
        new CartPage().waitPageToLoad();
        new CartPage().deletePreviousProducts();
        Logs.info("🛍️ Navigating to Products Page");
        new CartPage().hereLinkClick();
        new ProductsPage().waitPageToLoad();
        validateUrlContains("products", "Products Page");
    }

    public void goToContactUsPage() {
        Logs.info("📩 Navigating to Contact Us Page");
        goToLoginPage();
        new TopBar().waitPageToLoad();
        new TopBar().contactUsClick();
        validateUrlContains("contact_us", "Contact Us Page");
    }

    private void validateUrlContains(String expectedFragment, String pageName) {
        String currentUrl = getDriver().getCurrentUrl();
        if (!currentUrl.contains(expectedFragment)) {
            Logs.error("🚨 Navigation to " + pageName + " failed. Current URL: " + currentUrl);
        } else {
            Logs.info("✅ Navigation to " + pageName + " successful. URL: " + currentUrl);
        }
    }

}
