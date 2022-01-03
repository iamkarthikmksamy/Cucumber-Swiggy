package com.swiggy.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty",
                "html:RootPage.html",
                "json:RootPage.json",
                "junit:RootPage.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
		features="src/test/resources/features/RootPage.feature",
		glue="com.swiggy.steps"
		)
public class TestRunnerRootPage extends AbstractTestNGCucumberTests {

}
