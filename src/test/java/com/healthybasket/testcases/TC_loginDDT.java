package com.healthybasket.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.healthybasket.pageobject.loginpagehealthy;
import com.healthybasket.utilities.XLutils;

public class TC_loginDDT extends baseclasshealthy{
	
	static int testno=0;
	@Test(dataProvider="logindata")
	public void login_DDT(String phone, String password, String expectedoutput) throws Exception
	{ 
		testno++;
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

				
			System.out.print(lph.getloginstatus());
			System.out.println(expectedoutput);
			String TCstatus=null;
			
			
			if(!lph.getloginstatus().equals(expectedoutput))
			{	
				TCstatus = "Test Case" + testno + "Failed";
				System.out.print(TCstatus);
				File filesrc = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(filesrc, new File("./screenshots/Testcase"+testno+".png"));
				Thread.sleep(3000);
				//Assert.assertTrue(false);
				Assert.assertEquals(lph.getloginstatus(), expectedoutput, TCstatus);
				
			}
			
			//sa.assertEquals(lph.getloginstatus(), expectedoutput,TCstatus);
			
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
	//sa.assertAll();  
	}


	@DataProvider(name="logindata")
	public String[][] getdata() throws Exception
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\healthybasket\\testdata\\phoneno.xlsx";
		int rownum = XLutils.getRowcount(path, "login");
		int colcount = XLutils.getCellcount(path, "login", 1);

		String logindata[][] = new String[rownum][colcount];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLutils.getcelldata(path, "login", i, j);
			}

		}
		return logindata;
	}
	

}
