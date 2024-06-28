package com.training.Utilities;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportUtility {
	WebDriver driver;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;
	private static ExtentReportUtility extentObject;
	
	private ExtentReportUtility() {
		
	}
	
	public static ExtentReportUtility getInstance() {
		if(extentObject==null) {
			extentObject=new ExtentReportUtility();
		}
		return extentObject;
	}
	
	public void startExtentReport() {
		extent=new ExtentReports();
		spark=new ExtentSparkReporter(Constants.Extent_Report);
		
		extent.setSystemInfo("HostName", "SalesForce");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("UserName", "Gowtham");
		
		spark.config().setDocumentTitle("SalesForce Tests Execution Report");
		spark.config().setReportName("SalesForce Regression Tests");
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);
	}
	
	public void startSingleTestReport(String method) {
		test=extent.createTest(method);
	}
	
	public void endReport() {
		extent.flush();
	}
	
	public void logTestInfo(String Test) {
		test.log(Status.INFO, Test);
		test.info(Test);
	}
	
	public void logTestPasses(String Test) {
		test.log(Status.PASS, MarkupHelper.createLabel(Test, ExtentColor.GREEN));
		test.info(Test);
	}
	
	public void logTestFailed(String Test) {
		test.log(Status.FAIL, MarkupHelper.createLabel(Test, ExtentColor.RED));
		test.info(Test);
	}
	
	public void logTestFailedWithException(Throwable e) {
		test.log(Status.FAIL, e);
	}
	
	public void logTestFailScreenShot(String path) {
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	}

}
