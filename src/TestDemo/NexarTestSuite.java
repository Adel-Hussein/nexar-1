package TestDemo;

import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Pages.LogoutPage;
import Selectors.loginSelectors;
import Setup.SetEnvironment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;



public class NexarTestSuite {
	
	public static AppiumDriver<MobileElement> driver = null;
	public static WebElement mainScreen = null;
	public static DesiredCapabilities NexarCaps;
	
	@BeforeSuite
	public void LunchApp() throws InterruptedException {
		
		driver = SetEnvironment.LunchApp(driver, NexarCaps);
		
	}
	
	@Test
	public static void LoginTest() throws InterruptedException {
		try {
			
			LoginPage.login(driver);
			
			Thread.sleep(5000); 
	
			//Ensure the user navigates to the main page
			mainScreen =  driver.findElementByXPath(loginSelectors.activityTitle);
			Assert.assertEquals(mainScreen.getText(), "Activity");
			
		}catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public static void LogoutTest() throws InterruptedException {
		try {
			
			LogoutPage.logout(driver);
			
			Thread.sleep(5000);
	
			//Ensure the user navigates to the login page
			mainScreen =  driver.findElementByXPath(loginSelectors.loginLink);
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
