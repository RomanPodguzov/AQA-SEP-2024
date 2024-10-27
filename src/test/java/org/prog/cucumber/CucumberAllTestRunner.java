package org.prog.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.prog.cucumber.steps.WebSteps;
import org.prog.util.WebDriverFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.prog.cucumber.steps",
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        })
public class CucumberAllTestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        WebSteps.driver = WebDriverFactory.getDriver();
    }

    @AfterSuite
    public void tearDown() {
        WebSteps.driver.quit();
    }


}
