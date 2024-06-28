package com.training.Utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.model.Log;
import com.training.Base.BaseTest;

import lombok.extern.log4j.Log4j2;

@Listeners(com.training.Utilities.TestListeners.class)

public class TestListeners extends BaseTest implements ITestListener{
	
Logger myLogger=Log4j2Utility.getLogger(TestListeners.class);

public static ExtentReportUtility extentReport=ExtentReportUtility.getInstance();

	public void onTestStart(ITestResult result) {
		Log4j2Utility.logInfo(result.getName()+"------Testcase Started------");
		extentReport.startSingleTestReport(result.getName()+"------Testcase Started------");
	}

	public void onTestSuccess(ITestResult result) {
		Log4j2Utility.logInfo(result.getName()+"-----Testcase got successful-----");
		extentReport.logTestPasses(result.getName()+"-----Testcase got successful-----");
	}

	public void onTestFailure(ITestResult result) {
		Log4j2Utility.logError(result.getName()+"-----Testcase got failed------");
		extentReport.logTestFailed(result.getMethod().getMethodName()+"-----Testcase got failed------");
		extentReport.logTestFailedWithException(result.getThrowable());
		String dir=Constants.Screen_Shot;
		String filename=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String path=dir+filename+".png";
		try {
			takeScreenShotOfFullPage(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentReport.logTestFailScreenShot(path);
	}

	public void onTestSkipped(ITestResult result) {
		Log4j2Utility.logInfo(result.getName()+"----Testcase got skipped------");
	}

	public void onStart(ITestContext context) {
		Log4j2Utility.logInfo(context.getName()+"----TestCase has been started-----");
		extentReport.startExtentReport();
	}

	public void onFinish(ITestContext context) {
		Log4j2Utility.logInfo(context.getName()+"----TestCase has been finished-----");
		extentReport.endReport();
	}
	
	

}
