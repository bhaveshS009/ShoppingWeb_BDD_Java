package testrunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
//@CucumberOptions(
//		features = "src/features",
//		tags = "@FunctionalTest",
//		glue = "stepDefinitions",
//		plugin= {
//				"pretty","html:target/cucumber-reports.html",
//				"json:target/cucumber-reports.json",
//				"junit:target/cucumber-reports.xml"
//		}
//)

@CucumberOptions(
		features = "src/features",
		tags = "@FunctionalTest",
		glue = "stepDefinitions",
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class TestRunner extends AbstractTestNGCucumberTests {
	

}
