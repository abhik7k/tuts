package com.example;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
    ExtentReports extent = ExtentReporterNG.getExtentReportObject();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        test.log(Status.PASS,"Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) { 
            e.printStackTrace();
        }
        String filePath = null;
        System.out.println("Test failed: " + result.getName());
        test.log(Status.FAIL,"Test failed");
        test.fail(result.getThrowable());
        try {
             filePath = getScreenshot(result.getMethod().getMethodName(), driver);     
        } catch (IOException e) {
            e.printStackTrace();
            test.fail("Failed to capture screenshot: " + e.getMessage());
        }
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
        test.log(Status.SKIP,"Test skipped");
        test.skip(result.getThrowable());
    }
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test finished: " + context.getName());
        extent.flush();
    }
}
