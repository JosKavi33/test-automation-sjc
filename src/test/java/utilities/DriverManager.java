package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverManager {

    private final boolean runServer = System.getenv("JOB_NAME") != null;

    public void buildDriver() {
        if (runServer) {
            buildRemoteDriver();
        } else {
            buildLocalDriver();
        }
    }

    public void killDriver() {
        Logs.debug("🛑 Killing the WebDriver");
        var driver = WebDriverProvider.get();
        if (driver != null) {
            try {
                driver.quit();
                Logs.info("✅ WebDriver closed successfully");
            } catch (Exception e) {
                Logs.error("❌ Error while quitting WebDriver: " + e.getMessage());
            }
        }
        WebDriverProvider.remove();
    }

    private void buildLocalDriver() {
        final boolean headlessMode = Boolean.parseBoolean(System.getProperty("headless", "false"));
        String browserProperty = System.getProperty("browser", "EDGE").toUpperCase();
        BROWSER browser = BROWSER.valueOf(browserProperty);

        Logs.debug("🔧 Initializing local driver: %s | Headless: %b", browser, headlessMode);

        WebDriver driver = switch (browser) {
            case CHROME -> new ChromeDriver(getChromeOptions(headlessMode));
            case EDGE -> new EdgeDriver(getEdgeOptions(headlessMode));
            case FIREFOX -> new FirefoxDriver(getFirefoxOptions(headlessMode));
            case SAFARI -> new SafariDriver();
        };

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverProvider.set(driver);

        // Allure (opcional)
        System.setProperty("allure.labels.browser", browser.name());
    }

    public void buildRemoteDriver() {
        Logs.warning("🌐 Remote driver setup is not yet implemented.");
        // Puedes implementar con RemoteWebDriver + DesiredCapabilities aquí.
    }

    // === Configuraciones por navegador ===

    private ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new", "--disable-extensions");
        }
        return options;
    }

    private EdgeOptions getEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new", "--disable-extensions");
        }
        return options;
    }

    private FirefoxOptions getFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        return options;
    }

    private enum BROWSER {
        CHROME,
        FIREFOX,
        EDGE,
        SAFARI
    }
}
