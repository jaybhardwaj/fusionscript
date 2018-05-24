package Framework.TestingFramework.utils;

import java.net.UnknownHostException;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import Framework.TestingFramework.utils.WebDriverListeners;

public class BrowserUtil {
	
	public synchronized static EventFiringWebDriver invokeBrowser(String browser,String os) throws UnknownHostException {
		EventFiringWebDriver driver=null;
		if (browser.equalsIgnoreCase("chrome")) {
			if (os.equalsIgnoreCase("win")) {
				System.setProperty("webdriver.chrome.driver","src//test//resources//chromedriver.exe");
			} else {
				 System.setProperty("webdriver.chrome.driver", "src//test//resources//chromedriver");
			}
			WebDriver tempdriver= null;
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			options.addArguments("--disable-popup-blocking");
			System.out.println(System.getProperty("webdriver.chrome.driver"));
			DesiredCapabilities caps = DesiredCapabilities.chrome();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.SEVERE);
            caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            tempdriver = new ChromeDriver(caps);
			driver = new EventFiringWebDriver(tempdriver);
			WebDriverListeners eventListener = new WebDriverListeners();
			driver.register(eventListener);
			driver.manage().window().maximize();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			if (os.equalsIgnoreCase("win")) {
				System.setProperty("webdriver.gecko.driver","src//test//resources//geckodriver.exe");
			} else {
				 System.setProperty("webdriver.gecko.driver", "src//test//resources//geckodriver");
			}
			WebDriver tempdriver= null;
			/*DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
			tempdriver = new FirefoxDriver();
			driver = new EventFiringWebDriver(tempdriver);
			WebDriverListeners eventListener = new WebDriverListeners();
			driver.register(eventListener);
		}
		return driver;
	}

}
