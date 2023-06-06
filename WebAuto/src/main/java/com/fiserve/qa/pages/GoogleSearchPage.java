package com.fiserve.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fiserve.qa.base.BaseActions;
import com.fiserve.qa.consts.LocatorConstants;

public class GoogleSearchPage extends BaseActions{
	
	@FindBy(name=LocatorConstants.GOOGLE_SEARCH_BOX)
	public WebElement googleSearchBox;
	@FindBy(name=LocatorConstants.BING_SEARCH_BOX)
	WebElement bingSearchBox;
	
	public GoogleSearchPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getGoogleSearchPageTitle() {
		return driver.getTitle();
	}

}
