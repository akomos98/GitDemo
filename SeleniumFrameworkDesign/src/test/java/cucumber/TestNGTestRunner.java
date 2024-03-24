package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber - > testNg, junit
@CucumberOptions(features = "src/test/java/cucumber",glue = "ilkbeySirketi.stepDefinitions",
monochrome = true,tags = "@Regression",plugin = {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

	
	
}
