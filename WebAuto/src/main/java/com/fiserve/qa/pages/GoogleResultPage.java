package com.fiserve.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fiserve.qa.base.BaseActions;
import com.fiserve.qa.consts.LocatorConstants;

public class GoogleResultPage extends BaseActions {
	
	@FindBy(xpath=LocatorConstants.GOOGLE_RESULT_TEXT)
	public WebElement googleResultText;
	
	public GoogleResultPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	// Page level method to fetch the text
	public String getResultText() {
		String text = null;
		try{
			if(googleResultText!=null) {
				text=googleResultText.getText().toString();
			}
			
		}catch(Exception e) {
			System.out.println("Element not found on page: "+e.getMessage());
		}
		return text;
	}

}
