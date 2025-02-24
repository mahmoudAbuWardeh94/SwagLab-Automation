package com.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"com.automation.stepsdefinitions", "com.automation.base"},
        plugin = {"pretty", "html:target/assignment-reports.html"},
        monochrome = true
)
public class TestRunner {

}
