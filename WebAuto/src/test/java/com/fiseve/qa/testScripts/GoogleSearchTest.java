package com.fiseve.qa.testScripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fiserve.qa.Utils.TestUtils;
import com.fiserve.qa.base.BaseActions;
import com.fiserve.qa.pages.GoogleResultPage;
import com.fiserve.qa.pages.GoogleSearchPage;

public class GoogleSearchTest extends BaseActions{
	
	GoogleSearchPage googleSearchPage;
	GoogleResultPage googleResultPage;
	String sheetName="SearchData";
	
	public GoogleSearchTest() {
		super();
	}
	
	@BeforeMethod
	public void driverSetUp() {
		initDriver();
	}
	
	@DataProvider
	public Object[][] getSearchTestData() {
		Object[][] data = TestUtils.getTestData(sheetName);
		return data;
	}
	
	@Test()
	public void googleSearchresultTest() {
		try {
			
			// Script to lunch google page and perform search
			googleSearchPage = new GoogleSearchPage();
			googleResultPage = new GoogleResultPage();
			SoftAssert sAssert = new SoftAssert();
			String searchPageTitle = googleSearchPage.getGoogleSearchPageTitle();
			sAssert.assertEquals(searchPageTitle, prop.getProperty("googleSearchPageTitle"));
			googleSearchPage.enterText(googleSearchPage.googleSearchBox, "Fiserve Share");
			sAssert.assertEquals(googleResultPage.getResultText(), "Fiserv Inc Share Price, FISV Stock Price Quote Today");
			sAssert.assertAll();
		}catch(Exception e) {
			
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
