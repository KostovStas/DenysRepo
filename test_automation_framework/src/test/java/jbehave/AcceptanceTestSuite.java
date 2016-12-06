package jbehave;

import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.steps.ParameterControls;

import net.serenitybdd.jbehave.SerenityStories;

public class AcceptanceTestSuite extends SerenityStories {

    private static final String STORIES_PATTERN = "booking.stories";

    public AcceptanceTestSuite() {
        super();
        findStoriesCalled(System.getProperty(STORIES_PATTERN));
        this.configuration().useParameterControls(new ParameterControls().useDelimiterNamedParameters(true));
        this.configuration().usePendingStepStrategy(new FailingUponPendingStep());
    }
}
