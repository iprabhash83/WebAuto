package com.fiserve.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseActions {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public BaseActions() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\fiserve\\qa\\config\\config.properties");
			prop.load(fis);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initDriver() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\com\\fiserve\\qa\\config\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\com\\fiserve\\qa\\config\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("googleUrl"));
	}
	
	//This method is used to enter text in textbox element
	public void enterText(WebElement element, String text) {
		if(element !=null) {
			try {
				if(!element.isDisplayed()) {
					scrollIntoView(element, text);
				}
				
			}catch(Exception e) {}
			try {
				if(element.getTagName().equalsIgnoreCase("textarea")) {
					element.clear();
					element.sendKeys(text, Keys.ENTER);
					
				}
			}catch(Exception e) {
				System.out.println("Failed to enter text in the element:"+e.getMessage());
			}
		}
	}
	
	
	// This method is used to click on any element like button on webpage.
	public boolean clickElement(WebElement element, String elementDescription) {
		boolean isClicked = false;
		if(element!=null) {
			try {
				if(element.isDisplayed()) {
					element.click();
					isClicked = true;
				}
				else
					System.out.println("Element not displayed:");
				return isClicked;
			}catch(Exception e) {
				System.out.println("Fail to click Element: "+e.getMessage());
			}
		}else
			System.out.println("Element not found:");
		return isClicked;
	}
	
	//This method is used to bring element into view on the page
	public void scrollIntoView(WebElement ele, String Desc) {
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block:'Center'})",ele);
			
		}catch(Exception e) {
			System.out.println("Failed to scroll the element: "+Desc);
		}
	}

}
