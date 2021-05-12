package hw3;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Test
@CucumberOptions(features = "src/test/resources/hw3/cucumber_ex2.feature")
public class LaunchCucumber_ex2 extends AbstractTestNGCucumberTests{

}

