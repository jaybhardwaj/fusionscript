package Framework.TestingFramework.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import Framework.TestingFramework.utils.BrowserUtil;
import Framework.TestingFramework.utils.ReadEvironmentProperties;


public class BaseTest {
	
	public static Vector<EventFiringWebDriver> driver = new Vector<EventFiringWebDriver>();
	public static Vector<Long> threadCounter = new Vector<Long>();
	public static Hashtable<Long,String> multipleInput=new Hashtable<Long,String>();
	public static Map<String, String> env;
	public static Map<String, String> inputs;
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		env = ReadEvironmentProperties.getEnvironmentProperties();
		inputs = this.getTestInputs();
	}
	
	@BeforeTest
	public synchronized void setUp() throws Exception {
		EventFiringWebDriver LocalDriver = BrowserUtil.invokeBrowser(env.get("browser").toLowerCase(), env.get("os").toLowerCase());
		driver.addElement(LocalDriver);
		threadCounter.add(Thread.currentThread().getId());
	}
	
	@AfterTest(alwaysRun = true)
	public synchronized void tearDown() throws Exception {
		driver.get(threadCounter.indexOf(Thread.currentThread().getId())).quit();
	}
	
	private Map<String, String> getTestInputs() {
		Properties prop = new Properties();
		Map<String, String> propMap = new HashMap<String, String>();
		FileInputStream fis = null;
		try {
			String filePATH = "src//main//java//Framework//TestingFramework//testInput//Input.properties";
			fis = new FileInputStream(filePATH);
			prop.load(fis);
			for (String key : prop.stringPropertyNames()) {
				propMap.put(key, prop.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Collections.unmodifiableMap(propMap);
	}
	
	public static synchronized EventFiringWebDriver findMyDriver() {
		return driver.get(threadCounter.indexOf(Thread.currentThread().getId()));
	}

}
