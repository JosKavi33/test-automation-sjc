package utilities;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final static String screenShotPath = "src/test/resources/screenshoots";
    private final static String pageStructurePath = "src/test/resources/pageStructure";

    public static void getScreenshot(String screenshotName) {
        Logs.debug("Taking Screenshot");

        final var screenshotFile = ((TakesScreenshot) WebDriverProvider.get()).getScreenshotAs(OutputType.FILE);

        final var path = String.format("%s/%s.png", screenShotPath, screenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error Capturing the Screenshot: %s", ioException.getLocalizedMessage());
        }
    }

    public static void getPageSource(String fileName) {
        Logs.debug("Capturing the Page Source");

        final var path = String.format("%s/page-source-%s.html", pageStructurePath, fileName);

        try {
            final var file = new File(path);

            Logs.debug("Creating Parent Directories if They Don't Exist");
            if (file.getParentFile().mkdirs()) {
                final var fileWriter = new FileWriter(file);
                final var pageSource = new WebDriverProvider().get().getPageSource();
                fileWriter.write(Jsoup.parse(pageSource).toString());
                fileWriter.close();
            }
        } catch (IOException ioException) {
            Logs.error("Error Capturing the Page Source: %s", ioException.getLocalizedMessage());
        }
    }


    public static void deletePreviousEvidence() {
        try {
            Logs.debug("Erasing Previous Evidence");
            FileUtils.deleteDirectory(new File(screenShotPath));
            FileUtils.deleteDirectory(new File(pageStructurePath));
        } catch (IOException ioException) {
            Logs.error("Error al borrar la evidencia anterior: %s", ioException.getLocalizedMessage());
        }
    }

    public static void attachScreenshot(Scenario scenario) {
        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.BYTES);

        scenario.attach(
                screenshotFile,
                "image/png",
                scenario.getName()
        );
    }

    public static void attachPageSource(Scenario scenario) {
        final var pageSource = new WebDriverProvider().get().getPageSource();
        final var parsedPageSource = Jsoup.parse(pageSource).toString();

        scenario.attach(
                parsedPageSource,
                "text/plain",
                scenario.getName()
        );
    }
}
