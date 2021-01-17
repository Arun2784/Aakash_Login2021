package com.reporting.aakash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utilities.aakash.Setup;

public class Reporting extends Setup {

	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static File rootFolder = new File(System.getProperty("user.dir"));

	// Creating a method getScreenshot and passing two parameters
	// driver and screenshotName
	public static String getScreenshot(String screenshotName) throws Exception {
		// below line is just to append the date format with the screenshot name to
		// avoid duplicate names
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + dateName + ".png";
		File finalDestination = new File(destination);
		// FileUtils.copyFile(source, finalDestination);
		// Returns the captured file path
		return destination;
	}

	@BeforeSuite
	public void report() throws IOException {

		htmlreporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/Aakash_SIT_Smoke_Test_Report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Application", reader("application"));
		extent.setSystemInfo("Environment", reader("environment"));
		extent.setSystemInfo("QA", reader("qa"));
		extent.setSystemInfo("Browser", reader("browser"));
		htmlreporter.config().setDocumentTitle("Smoke Test Report");
		htmlreporter.config().setReportName("Smoke Test");
		htmlreporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlreporter.config().setTheme(Theme.DARK);

		Start();

	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getDescription(), ExtentColor.RED));
			String screenshotPath = Reporting.getScreenshot(result.getMethod().getDescription());
			test.addScreenCaptureFromPath(screenshotPath);
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getDescription(), ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getDescription(), ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}

	}

	@AfterSuite
	public void endReport() {
		extent.flush();
		Stop();
	}

	public String reader(String in) throws IOException {
		Properties prop = new Properties();
		FileInputStream fp;
		// objFile = new FileInputStream(System.getProperty("D:\\Core
		// Java\\AakashLogin\\target\\configuration.properties"));
		fp = new FileInputStream("D:\\Core Java\\AakashLogin\\target\\configuration.properties");
		prop.load(fp);
		// obj.load(objFile);
		String out = prop.getProperty(in);
		return out;
	}

}
