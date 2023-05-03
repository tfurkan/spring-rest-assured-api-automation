package com.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {
                "src/test/resources/features/"
        },
        glue = {"com.steps"},
        plugin = {
                "pretty",
                "html:target/cucumber/report-html.html",
                "json:target/jsonReports/report.json",
                "junit:target/cucumber/junit_report.xml",
                "timeline:target/cucumber/timeline"
        },
        tags = "@Pet"
        )
public class CucumberRunner extends AbstractTestNGCucumberTests {
    private static final Logger logger = LogManager.getLogger(com.steps.CucumberRunner.class.getName());

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Before
    public void setup(Scenario scenario) {
        logger.info("Running scenario : " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("Inside teardown(), now test will quit.....");
    }
}
