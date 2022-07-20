package com.healthybasket.testcases;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.healthybasket.pageobject.loginpagehealthy;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_login extends baseclasshealthy {

	@Test
	public void testcase1() throws Exception

	{
		FileInputStream inputfile = new FileInputStream("C:\\testing_projects\\healthybasket\\phoneno.xlsx");
		Workbook phoneworkbook = WorkbookFactory.create(inputfile);
		Sheet s1 = phoneworkbook.getSheet("login");

		int rowcount = s1.getLastRowNum();
		int colcount = s1.getRow(0).getLastCellNum();
		String password;
		String phone;
		String expectedoutput;

		SoftAssert sa = new SoftAssert();
		System.out.println(rowcount);
		System.out.println(colcount);

		for(int i=1;i<=1;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				long  longphone = (long) s1.getRow(i).getCell(j).getNumericCellValue();
				phone = Long.toString(longphone);
				j++;
				password = s1.getRow(i).getCell(j).getStringCellValue();
				j++;
				expectedoutput = s1.getRow(i).getCell(j).getStringCellValue();

				wd.get(baseurl);
				Thread.sleep(3000);

				wd.manage().window().maximize();
				Thread.sleep(3000);

				loginpagehealthy lph = new loginpagehealthy(wd);

				lph.clickloginlink();
				Thread.sleep(3000);

				lph.setphone(phone);
				Thread.sleep(3000);

				lph.setpassword(password);
				Thread.sleep(3000);

				
					lph.clickloginbutton();
					Thread.sleep(3000);

					//WebElement loginstatus= wd.findElement(By.xpath("//*[@id='body']/div[13]/p[1]"));
					//System.out.println(loginstatus.getText());
					
					System.out.print(lph.getloginstatus());
					System.out.println(expectedoutput);
					String TCstatus=null;
					if(!lph.getloginstatus().equals(expectedoutput))
					{	
						TCstatus = "Test Case"+ i+"Failed";
						File filesrc = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(filesrc, new File("./screenshots/Testcase"+i+".png"));
					}
					Thread.sleep(3000);
					sa.assertEquals(lph.getloginstatus(), expectedoutput,TCstatus);

					lph.loginok(); 
					Thread.sleep(3000);
			try{
					lph.clickuser();
					Thread.sleep(3000);

					lph.logout();
					Thread.sleep(3000);

				}

				catch(Exception e)
				{

				}
			}
		}
		sa.assertAll();  
	}
}
