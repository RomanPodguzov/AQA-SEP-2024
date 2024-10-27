package org.prog.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@newEpic",
        features = "src/test/resources/features",
        glue = "org.prog.cucumber.steps",
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-report.html"
        })
public class CucumberEpicRunner extends AbstractTestNGCucumberTests {
}
