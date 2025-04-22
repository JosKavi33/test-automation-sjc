package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utilities.DriverManager;
import utilities.FileManager;
import utilities.Logs;

public class Hooks {

    private static final DriverManager driverManager = new DriverManager();

    @BeforeAll
    public static void beforeAll() {
        Logs.info("beforeAll");
        FileManager.deletePreviousEvidence();
    }

    @AfterAll
    public static void afterAll() {
        Logs.info("afterAll");
    }

    @Before
    public void before(Scenario scenario) {
        Logs.info("before: %s", scenario.getName());
        driverManager.buildDriver();
    }

    @After
    public void after(Scenario scenario) {
        Logs.info("after: %s, status: %s", scenario.getName(), scenario.getStatus());
        switch (scenario.getStatus()) {
            case SKIPPED, FAILED -> {
                FileManager.getScreenshot(scenario.getName());
                FileManager.getPageSource(scenario.getName());
            }
        }
        driverManager.killDriver();
    }
}
