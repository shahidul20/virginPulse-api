package restful.cukes.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "de.monochromata.cucumber.report.PrettyReports:build/cucumber" }, features = {
		"classpath:features" }, glue = { "restful.cukes.steps" }, strict = true)
@RunWith(Cucumber.class)
public abstract class AbstractRunner {

}
