package com.healthybasket.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.healthybasket.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseclasshealthy {
	String baseurl="https://healthybasket.co.uk/";
	ReadConfig readconfig = new ReadConfig();
	//String baseurl = readconfig.getapplicationurl();
	
	public static WebDriver wd;
	

	SoftAssert sa = new SoftAssert();
		@Parameters("browser")	
		@BeforeClass
		public void setup(String br) throws Exception
		{
			if(br.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",readconfig.getchromepath());
				wd = new ChromeDriver();
			}
			else if(br.equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver",readconfig.getfirefoxpath());
				wd = new FirefoxDriver();
			}
			else if(br.equals("ie"))
			{
				System.setProperty("webdriver.ie.driver",readconfig.getiepath());
				wd = new InternetExplorerDriver();
			}
			//WebDriverManager.chromedriver().setup();
		     //wd = new ChromeDriver();
			//WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver","./drivers\\geckodriver.exe");
			//wd = new FirefoxDriver();
		     
		   	}
		
		
		private void elseif(boolean equals) {
			// TODO Auto-generated method stub
			
		}


		@AfterClass
		public void teardown()
		{
			wd.quit();
		}

}
