import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java"
)


public class Runner extends AbstractTestNGCucumberTests {

}
