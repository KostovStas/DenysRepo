package com.company.international.jbehave;

import java.util.Optional;

import com.company.core.common.EnvironmentProperty;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.steps.ParameterControls;

import net.serenitybdd.jbehave.SerenityStories;

public class AcceptanceTestSuite extends SerenityStories {

    public AcceptanceTestSuite() {
        super();
        Optional.ofNullable(EnvironmentProperty.ROZETKA_STORIES.readProperty()).ifPresent(this::findStoriesCalled);
        configuration().useParameterControls(new ParameterControls().useDelimiterNamedParameters(true));
        configuration().usePendingStepStrategy(new FailingUponPendingStep());
    }
}