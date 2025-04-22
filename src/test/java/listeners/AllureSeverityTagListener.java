package listeners;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.util.ResultsUtils;

import java.util.Collection;

import static io.qameta.allure.Allure.getLifecycle;

public class AllureSeverityTagListener implements EventListener {

    private final AllureLifecycle lifecycle = getLifecycle();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        Collection<String> tags = event.getTestCase().getTags();

        if (tags.contains("@blocker")) {
            addSeverity(SeverityLevel.BLOCKER);
        } else if (tags.contains("@critical")) {
            addSeverity(SeverityLevel.CRITICAL);
        } else if (tags.contains("@normal")) {
            addSeverity(SeverityLevel.NORMAL);
        } else if (tags.contains("@minor")) {
            addSeverity(SeverityLevel.MINOR);
        } else if (tags.contains("@trivial")) {
            addSeverity(SeverityLevel.TRIVIAL);
        }
    }

    private void addSeverity(SeverityLevel level) {
        lifecycle.updateTestCase(testResult ->
                testResult.getLabels().add(ResultsUtils.createSeverityLabel(level))
        );
    }
}
