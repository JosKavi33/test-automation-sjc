package utilities;

import org.openqa.selenium.WebDriver;

public class WebDriverProvider {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static void set(WebDriver driver) {
        driverThread.set(driver);
    }

    public static WebDriver get() {
        return driverThread.get();
    }

    public static void remove() {
        driverThread.remove();
    }
}
