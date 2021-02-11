package Setup;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SetEnvironment {

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
}
