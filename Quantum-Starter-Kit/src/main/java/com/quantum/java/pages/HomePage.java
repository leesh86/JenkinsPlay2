package com.quantum.java.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * 
 */

/**
 * @author Lee Shoham
 * @date Jan 19, 2017
 */
public class HomePage {
	
	 private WebDriver driver = null;
	
	public HomePage(WebDriver driver_) {
		this.driver = driver_;
		PageFactory.initElements(new AppiumFieldDecorator(driver,10,TimeUnit.SECONDS), this);
	}
	
	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@AndroidFindBy(xpath = "//android.widget.TextView[1]")
	@iOSFindBy(xpath = "//*[@label='  PMTest']")
	public MobileElement title;
	
//	@WithTimeout(time = 15, unit = TimeUnit.SECONDS)
	@AndroidFindBy(xpath = "//*[@resource-id='com.perfectomobile.test:id/TestTouch']")
	@iOSFindBy(xpath = "//*[@label='TEST TOUCH']")
	public MobileElement testTouch;
	
	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@AndroidFindBy(xpath = "//*[@resource-id='com.perfectomobile.test:id/back']")
	@iOSFindBy(xpath = "//*[@label='BACK']")
	public MobileElement back;
	

	
	public String getTitle() {
		return title.getText();
	}
	
	public void goToTestTouch() {
		testTouch.click();
	}
	
	public void goBack() {
		back.click();
	}

}
