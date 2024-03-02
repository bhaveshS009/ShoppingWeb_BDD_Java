package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/features",
		tags = "@FunctionalTest",
		glue = "stepDefinitions" //Package Name
)

public class TestRunner {
	

}
