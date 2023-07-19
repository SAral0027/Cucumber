package com.runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClass;
import com.report.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = false, features = { "src\\test\\resources\\FeatureFiles" }, glue = {
		"com.stepdefinition" }, monochrome = true, stepNotifications = true, publish = true, plugin = { "pretty",
				"json:target\\report.json" })
public class TestRunnerClass extends BaseClass {

	@AfterClass
	public static void afterClass() throws IOException {
		Reporting.generateJVMReport(getProjectPath() + getPropertyFileValue("jsonPath"));
	}
}
