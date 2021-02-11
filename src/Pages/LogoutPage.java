package Pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import Selectors.loginSelectors;
import Selectors.logoutSelectors;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class LogoutPage {
	
	public static void logout(AppiumDriver<MobileElement> driver) throws InterruptedException {
		
		try {
		//Click on More tab 
		driver.findElementById(logoutSelectors.moreTab).click();

		Thread.sleep(2000);

		//Navigate to profile screen 
		driver.findElementById(logoutSelectors.profileEdit).click();
		
		Thread.sleep(5000);
		
		//SwipeUp to logout button
		WebElement swipefrom = driver.findElementByXPath(logoutSelectors.driversLabel);
		WebElement swipeTo = driver.findElementByXPath(logoutSelectors.profileLabel);
		swipeUp(driver, swipefrom, swipeTo);

		//click on logout button
		driver.findElementByXPath(logoutSelectors.logoutButton).click();
		
		Thread.sleep(5000);

		//Skip the main screen
		if(LoginPage.isDisplayedSkip(driver)) {
			driver.findElementByXPath(loginSelectors.skipButton).click();
			}
		
		}catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
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
