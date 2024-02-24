package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//import io.cucumber

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/features",
		glue = "stepDefinitions" //Package Name
)

public class TestRunner {
	

}
