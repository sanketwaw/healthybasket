package com.healthybasket.utilities;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext)
	{
		//String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date(0));
		Calendar c = Calendar.getInstance();
		
		String repName ="Test-Report"+ c.getTime()+".html";
		
		htmlReporter = new ExtentHtmlReporter("C:\\testing_projects\\healthybasket\\test-output\\"+repName);
		htmlReporter.loadXMLConfig("C:\\testing_projects\\healthybasket\\extent-config.xml");
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Hostname","localhost");
		extent.setSystemInfo("Enviornment", "QA");
		extent.setSystemInfo("user", "Sanket");
		
		htmlReporter.config().setDocumentTitle("Healthy Basket");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
	}
	
	public void onTestsuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestfailure(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenshotpath = System.getProperty("user.dir")+"\\screenshots\\" + tr.getName()+".png";
		
		File f = new File(screenshotpath);
		
		if(f.exists())
		{
			try
			{
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
			}
			catch(Exception e)
			{
				
			}
		}
				
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish()
	{
		extent.flush();
	}
}
