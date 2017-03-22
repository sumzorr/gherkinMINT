package com.uxpsystems.mint.GHRunner;

/**
 * Created by sumadragos on 22/03/17.
 */

import com.google.inject.Binder;
import com.google.inject.Module;
import cucumber.api.CucumberOptions;
import cucumber.api.guice.CucumberScopes;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty"},
        features = "classpath:features/",
        glue = {"com.uxpsystems.mint.Steps.CSRCustomSteps"},
        strict = true
)

public class Runner {

    @BeforeClass
    public static void setUp() throws Exception {

    }
}
