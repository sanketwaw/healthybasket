package com.practice.test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class testclass {
	
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
		
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				
				long  longphone = (long) s1.getRow(i).getCell(j).getNumericCellValue();
				 phone = Long.toString(longphone);
				 
				   j++;
				  password = s1.getRow(i).getCell(j).getStringCellValue();
				  j++;
				  expectedoutput = s1.getRow(i).getCell(j).getStringCellValue();
				  
				  WebDriverManager.chromedriver().setup();
				WebDriver wd = new ChromeDriver();
				
				wd.get("https://healthybasket.co.uk/");
				Thread.sleep(3000);
				
				wd.manage().window().maximize();
				Thread.sleep(3000);
				
				wd.findElement(By.xpath("//button[@data-tab='login']")).click();
				Thread.sleep(3000);
				
				 				
				WebElement phoneinput = wd.findElement(By.xpath("//*[@id='cardLogin']/div/div/form/div[1]/input"));
				phoneinput.clear();
		        phoneinput.sendKeys(phone);
		        Thread.sleep(3000);
		        
		        WebElement passinput = wd.findElement(By.xpath("//*[@id='cardLogin']/div/div/form/div[2]/input"));
		        passinput.clear();
		        passinput.sendKeys(password);
		        Thread.sleep(3000);
		        
		        
		     try{
		        wd.findElement(By.xpath("//*[@id='cardLogin']/div/div/form/div[4]/button")).click();
		        Thread.sleep(3000);
		        
		        WebElement loginstatus= wd.findElement(By.xpath("//*[@id='body']/div[13]/p[1]"));
		       System.out.println(loginstatus.getText());
		       System.out.println(expectedoutput);
		       String TCstatus = "Test Case"+ i+"Failed";
		       sa.assertEquals(loginstatus.getText(), expectedoutput,TCstatus);
		        
		        /*if(loginstatus.getText()=="Successfully logged in!")
		        	assertTrue(true);
		        else
		        	assertFalse(false);
		        */
		        wd.findElement(By.xpath("//*[@id='body']/div[13]/div[7]/div/button")).click();
		        Thread.sleep(3000);
		        
		       WebElement hellosan= wd.findElement(By.partialLinkText("Sanket"));
		        Actions act = new Actions(wd);
		        act.moveToElement(hellosan).perform();
		        Thread.sleep(3000);
		        
		        wd.findElement(By.partialLinkText("Logout")).click();
		        Thread.sleep(3000);
				}
			
		        catch(Exception e)
		        {
		        	
		        }
		        wd.quit();
		        	       
				//System.out.print(phone);   
	           //System.out.print(password);
	           //System.out.print("\n");
			}
			
		}
		sa.assertAll();  
		
		/*
		WebDriverManager.chromedriver().setup();
		WebDriver wd = new ChromeDriver();
		
		wd.get("https://healthybasket.co.uk/");
		Thread.sleep(3000);
		
		wd.manage().window().maximize();
		Thread.sleep(3000);
		
		wd.findElement(By.xpath("//button[@data-tab='login']")).click();
		Thread.sleep(3000);
		
		wd.findElement(By.id("btnRegister")).click();
		Thread.sleep(3000);
		
		wd.findElement(By.xpath("//div[@role='combobox']")).click();
				Thread.sleep(3000);
				
		wd.findElement(By.id("iti-0__item-in")).click();
		Thread.sleep(3000);
		
		wd.findElement(By.id("phone")).sendKeys(ss);
		Thread.sleep(3000);
		
		wd.findElement(By.xpath("//*[@id='formRegister']/div[3]/button")).click();
		Thread.sleep(3000);
		
		wd.quit();
		
		*/
	}
	

}
