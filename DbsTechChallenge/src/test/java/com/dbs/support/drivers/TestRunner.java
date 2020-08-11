package com.dbs.support.drivers;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/com/dbs/features" }, glue = {
		"com.dbs.stepdefinitions" }, monochrome = true, plugin = {
				"json:target/cucumber-reports/cucumber.json" }, tags = "@AllTestCases")

public class TestRunner extends AbstractTestNGCucumberTests {

}
