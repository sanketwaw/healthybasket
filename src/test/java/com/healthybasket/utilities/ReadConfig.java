package com.healthybasket.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;
	
	public ReadConfig()
	{
		File src= new File("./Configuration/config.properties");
		
		try{
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}
		catch(Exception e)
		{
			System.out.println("Exception is:" + e.getMessage());
		}
	}
	
	public String getapplicationurl()
	{
		String url = pro.getProperty("baseurl");
		return url;
	}

	public String getchromepath()
	{
		String ch = pro.getProperty("chromepath");
		return ch;
	}
	public String getfirefoxpath()
	{
		String ch = pro.getProperty("firefoxpath");
		return ch;
	}
	public String getiepath()
	{
		String ch = pro.getProperty("iepath");
		return ch;
	}
	
}
