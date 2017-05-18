package jbehave.steps;

import com.booking.structure.common.EnvironmentProperty;
import net.serenitybdd.jbehave.SerenityStories;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.steps.ParameterControls;

import java.util.Optional;

public class AcceptanceTestSuite extends SerenityStories {

    public AcceptanceTestSuite() {
        super();
        Optional.ofNullable(EnvironmentProperty.BOOKING_STORIES.readProperty()).ifPresent(this::findStoriesCalled);
        configuration().useParameterControls(new ParameterControls().useDelimiterNamedParameters(true));
        configuration().usePendingStepStrategy(new FailingUponPendingStep());
    }
}
