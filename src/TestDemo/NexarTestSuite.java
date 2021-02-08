package TestDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import TestDemo.NexarGeneric;

import org.junit.Assert;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class NexarTestSuite {
	
	public static AppiumDriver<MobileElement> driver = null;
	public static WebElement mainScreen = null;
	public static DesiredCapabilities NexarCaps;
	
	@BeforeSuite
	public void LunchApp() throws InterruptedException {
		
		driver = NexarGeneric.LunchApp(driver, NexarCaps);
		
	}
	
	@Test
	public static void LoginTest() throws InterruptedException {
		try {
			
			NexarGeneric.login(driver);
			
			Thread.sleep(5000); 
	
			//Ensure the user navigates to the main page
			mainScreen =  driver.findElementByXPath("//android.widget.TextView[@text='Activity']");
			Assert.assertEquals(mainScreen.getText(), "Activity");
			
		}catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public static void LogoutTest() throws InterruptedException {
		try {
			
			NexarGeneric.logout(driver);
			
			Thread.sleep(5000);
	
			//Ensure the user navigates to the login page
			mainScreen =  driver.findElementByXPath("//android.widget.TextView[@text='Log in']");
			Assert.assertEquals(mainScreen.getText(), "Log in");
			
		}catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@AfterSuite
	public static void CloseApp() {
		
		//Close the Nexar App
		driver.quit();
	}
	
		
		
}
