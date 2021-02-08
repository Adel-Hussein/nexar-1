package TestDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class NexarGeneric {
	
	public static AppiumDriver<MobileElement> LunchApp(AppiumDriver<MobileElement> driver, DesiredCapabilities NexarCaps) throws InterruptedException {
		
		NexarCaps = new DesiredCapabilities();
		
		NexarCaps.setCapability("deviceName", "My Phone");
		NexarCaps.setCapability("udid", "TMF4C19505002056"); //Give Device ID of your mobile phone
		NexarCaps.setCapability("platformName", "Android");
		NexarCaps.setCapability("platformVersion", "10.0");
		NexarCaps.setCapability("appWaitPackage", "mobi.nexar.dashcam");
		NexarCaps.setCapability("appWaitActivity", "mobi.nexar.dashcam.architecture.activities.login.LoginActivity");
		NexarCaps.setCapability("noReset", "true");
	
	try {
		
		System.out.println("Starting your test ...");
		
		//Launch the application 
		 driver = new AndroidDriver <MobileElement> ( new URL ("http://0.0.0.0:4723/wd/hub"), NexarCaps);	
		
		Thread.sleep(5000);
		return driver;
	} catch (MalformedURLException e) {
		System.out.println(e.getMessage());
		return null;
	} 
	}
	
	public static void login(AppiumDriver<MobileElement> driver) throws InterruptedException {
		
		try {
			
		//Skip the main screen
		if(isDisplayedSkip(driver)) {
			driver.findElementByXPath("//android.widget.TextView[@text='Skip for now']").click();
		}
		
		//Nvigate to login screen
		WebElement loginLink = driver.findElementByXPath("//android.widget.TextView[@text='Log in']");
		loginLink.click();
		
		//Get the App Elements
		WebElement enterPhoneNumber = driver.findElementByXPath("//android.widget.EditText[@text='+972']");
		enterPhoneNumber.sendKeys("598477851");
		
		driver.findElementById("mobi.nexar.dashcam:id/btnPhoneLogin").click();
	
		Thread.sleep(10000);
		
		//Open the notification panel
		((AndroidDriver<MobileElement>) driver).openNotifications();
		
		//Get the Code from the notification
		String codeNumber = getCode(driver);
		
		//Close the notification panel
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

		//Enter the verification code
		driver.findElementById("mobi.nexar.dashcam:id/pinCode").sendKeys(codeNumber);
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(5000);
		driver.findElementById("mobi.nexar.dashcam:id/btnPhoneLogin").click();
		Thread.sleep(5000);
		driver.findElementById("mobi.nexar.dashcam:id/agree").click();
		
		
		}catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			}
		}
	
	public static boolean isDisplayedSkip(AppiumDriver<MobileElement> driver) {
		try {
			return driver.findElementByXPath("//android.widget.TextView[@text='Skip for now']").isDisplayed();
		}catch (NoSuchElementException e) {
			return false;
		
 			}
	}
	public static void logout(AppiumDriver<MobileElement> driver) throws InterruptedException {
			
			try {
			//Click on More tab 
			driver.findElementById("mobi.nexar.dashcam:id/menu_more").click();

			Thread.sleep(2000);

			//Navigate to profile screen 
			driver.findElementById("mobi.nexar.dashcam:id/profile_edit").click();
			
			Thread.sleep(5000);
			
			//SwipeUp to logout button
			WebElement swipefrom = driver.findElementByXPath("//android.widget.TextView[@text='Drives']");
			WebElement swipeTo = driver.findElementByXPath("//android.widget.TextView[@text='Profile']");
			swipeUp(driver, swipefrom, swipeTo);

			//click on logout button
			driver.findElementByXPath("//android.widget.Button[@text='LOG OUT']").click();
			
			Thread.sleep(5000);

			//Skip the main screen
			if(isDisplayedSkip(driver)) {
				driver.findElementByXPath("//android.widget.TextView[@text='Skip for now']").click();
				}
			
			}catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
				}
			}
		
		private static String getCode(AppiumDriver<MobileElement> driver) {
			// TODO Auto-generated method stub
			try {
				WebElement msgCode = driver.findElementById("android:id/message_text");
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
		
		public static void swipeUp(AppiumDriver<MobileElement> driver, WebElement swipeFromWebElement, WebElement swipeToWebElement) {
			try {
				Point point1 = null;
				Point point2 = null;
				
				point1 = swipeFromWebElement.getLocation();
				point2 = swipeToWebElement.getLocation();

				int x1 = point1.x;
				int y1 = point1.y;
				int y2 = point2.y;
				int differ = y1-y2;
				int ToY = y1-differ;
				
				new TouchAction((PerformsTouchActions) driver).press(PointOption.point(x1,y1))
				 .waitAction(new WaitOptions().withDuration(Duration.ofMillis(10000))) //you can change wait durations as per your requirement
				 .moveTo(PointOption.point(x1, ToY))
				 .release()
				 .perform();
				
			}catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			}
		}


}
