//blablablabla
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.annotations.IAfterClass;

import com.perfecto.reportium.WebDriverProvider;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.exception.ReportiumException;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

/**
 * @author Lee Shoham
 * @date Sep 6, 2016
 */

public class ReportingTest implements WebDriverProvider {

	private RemoteWebDriver driver;
	protected ReportiumClient reportiumClient;

	@Test(groups = "a")
	public void googleSearch1() {

		reportiumClient.testStep("Test1: hello world search");
		driver.get("http://www.google.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("Hello World");
		driver.findElement(By.xpath("//input[@id='lst-ib']")).submit();
	}

	@Test(dependsOnGroups = "a")
	public void googleSearch2() {

		reportiumClient.testStep("Test2: perfecto mobile search");
		driver.get("http://www.google.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("Perfecto Mobile");
		driver.findElement(By.xpath("//input[@id='lst-ib']")).submit();

	}

	@Parameters({ "mcm", "mcmUser", "mcmPassword", "browserName", "deviceName" })
	@BeforeClass
	public void beforeClass(String mcm, String mcmUser, String mcmPassword, String browserName, String deviceName, ITestContext context)
			throws UnsupportedEncodingException, MalformedURLException {
		System.out.println("Run started");

		// String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);

		// String host = mcm;
		capabilities.setCapability("user", mcmUser);
		capabilities.setCapability("password", mcmPassword);
		capabilities.setCapability("deviceName", deviceName);

		System.out.println(capabilities);

		this.driver = new RemoteWebDriver(new URL("https://" + mcm + "/nexperience/perfectomobile/wd/hub"),
				capabilities);

		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("Sample Reportium project", "1.0")).withContextTags("LEE", context.getSuite().getName())
				.withWebDriver(driver).build();
		reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {

		String testName = method.getDeclaringClass().getName() + "::" + method.getName();
		reportiumClient.testStart(testName, new TestContext(method.getName()));
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult testResult) {
		
	    int status = testResult.getStatus();
	    switch (status) {
	        case ITestResult.FAILURE:
	            reportiumClient.testStop(TestResultFactory.createFailure("An error occurred", testResult.getThrowable()));
	            break;
	        case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
	        case ITestResult.SUCCESS:
	            reportiumClient.testStop(TestResultFactory.createSuccess());
	            break;
	        case ITestResult.SKIP:
	            // Ignore
	            break;
	        default:
	            throw new ReportiumException("Unexpected status " + status);
	    }
	}
	

	@AfterClass
	public void afterClass(ITestContext context) {

		try {

			// Close the browser
			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Report url = " + reportiumClient.getReportUrl());
		
		driver.quit();
		System.out.println("Run ended");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.perfecto.reportium.WebDriverProvider#getWebDriver()
	 */
	public WebDriver getWebDriver() {
		// TODO Auto-generated method stub
		return driver;
	}

}
