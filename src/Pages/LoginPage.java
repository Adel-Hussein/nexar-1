package Pages;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import Selectors.loginSelectors;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class LoginPage {
	
public static void login(AppiumDriver<MobileElement> driver) throws InterruptedException {
		
		try {
			
		//Skip the main screen
		if(isDisplayedSkip(driver)) {
			driver.findElementByXPath(loginSelectors.skipButton).click();
		}
		
		//Navigate to login screen
		WebElement loginLink = driver.findElementByXPath(loginSelectors.loginLink);
		loginLink.click();
		
		//Get the App Elements
		WebElement enterPhoneNumber = driver.findElementByXPath(loginSelectors.phoneNumber);
		enterPhoneNumber.sendKeys("598477851");
		
		driver.findElementById(loginSelectors.phoneLoginButton).click();
	
		Thread.sleep(10000);
		
		//Open the notification panel
		((AndroidDriver<MobileElement>) driver).openNotifications();
		
		//Get the Code from the notification
		String codeNumber = getCode(driver);
		
		//Close the notification panel
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

		//Enter the verification code
		driver.findElementById(loginSelectors.codeInput).sendKeys(codeNumber);
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(5000);
		driver.findElementById(loginSelectors.phoneLoginButton).click();
		Thread.sleep(5000);
		driver.findElementById(loginSelectors.agreeButton).click();
		
		
		}catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			}
		}
	
		public static boolean isDisplayedSkip(AppiumDriver<MobileElement> driver) {
			try {
				return driver.findElementByXPath(loginSelectors.skipButton).isDisplayed();
			}catch (NoSuchElementException e) {
				return false;
			
					}
		}
		
		private static String getCode(AppiumDriver<MobileElement> driver) {
			// TODO Auto-generated method stub
			try {
				WebElement msgCode = driver.findElementById(loginSelectors.messageText);
				String msgCodeText = msgCode.getText();
				Pattern p = Pattern.compile("\\d+");
		        Matcher m = p.matcher(msgCodeText);
		        String result = "";
		        while(m.find()) {
		        	result += m.group();
		        }
		        System.out.println("verification code is : "+ result);
				return result;
			}catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
}
