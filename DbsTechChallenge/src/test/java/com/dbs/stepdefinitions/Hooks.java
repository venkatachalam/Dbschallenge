package com.dbs.stepdefinitions;

import com.dbs.library.BrowserActions;
import com.dbs.library.BrowserUtils;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BrowserUtils {

	public static Scenario scenario;

	@Before
	public void beforeScenario(Scenario scenario) throws Exception {
		try {

			Hooks.scenario = scenario;
			driver = initialize();
		} catch (Exception e) {
			throw new Exception("Error occurred: " + e);
		}
	}

	@After
	public void afterScenario(Scenario scenario) throws Exception {
		destroyDriver();
	}

	@AfterStep
	public void afterStep(Scenario scenario) throws Exception {
		BrowserActions.takeScreenshot();
	}
}
