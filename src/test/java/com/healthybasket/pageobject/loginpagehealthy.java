package com.healthybasket.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpagehealthy {
	
	WebDriver ldriver;
	
	public loginpagehealthy(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//button[@data-tab='login']")
	WebElement loginlink;
	
	@FindBy(xpath="//*[@id='cardLogin']/div/div/form/div[1]/input")
	WebElement phoneinput;
	
	@FindBy(xpath="//*[@id='cardLogin']/div/div/form/div[2]/input")
	WebElement passinput;
	
	@FindBy(xpath="//*[@id='cardLogin']/div/div/form/div[4]/button")
	WebElement loginclick;
	
	@FindBy(xpath="//*[@id='body']/div[12]/p")
	WebElement loginstatus;
	
    @FindBy(xpath="//*[@id='body']/div[12]/div[7]/div/button")
    WebElement loginok;
    
    @FindBy(partialLinkText="Hello") 
    WebElement loggedinuser;

    @FindBy(partialLinkText="Logout")
    WebElement logoutbutton;
    
    public void clickloginlink()
    {
    	loginlink.click();
    }
 
    public void setphone(String phone)
    {
    	phoneinput.clear();
    	phoneinput.sendKeys(phone);
    }

    public void setpassword(String pass)
    {
    	passinput.clear();
    	passinput.sendKeys(pass);
    }
    
    public void clickloginbutton()
    {
    	loginclick.click();
    }
    
    public String getloginstatus()
    {
    	
		return loginstatus.getText();
				
    }
    
    public void loginok()
    {
        loginok.click();
      }
    
    public void clickuser()
    {
    	Actions act = new Actions(ldriver);
        act.moveToElement(loggedinuser).perform();
    }
    
    public void logout()
    {
    	logoutbutton.click();
    }
}
