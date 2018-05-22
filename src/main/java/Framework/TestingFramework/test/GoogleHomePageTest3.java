/*package Framework.TestingFramework.test;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.GoogleHomePage1;
import Framework.TestingFramework.methods.GoogleHomePage3;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;
import Framework.TestingFramework.utils.CSVUtil;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class GoogleHomePageTest3 extends BaseTest {
	
	EventFiringWebDriver driver;
	GoogleHomePage3 googlehomepage;

	@BeforeClass
	public void testSetUp() {
    	driver = BaseTest.findMyDriver();
    	googlehomepage = new GoogleHomePage3(driver);
	}
	
	@Test(dataProvider="getDataFromUrlCSV",description = "TC_CHC_0001")
	public void verifyGooglePage6Tittle(String url,String heading) {
		System.out.println("GoogleHomePageTest3");
		multipleInput.put(Thread.currentThread().getId(),heading);
		Assert.assertTrue(googlehomepage.verifyGooglePage6Tittle(url), "Title Did not match");
	}
	
	@DataProvider
	public Object[][] getDataFromUrlCSV(){
		return CSVUtil.getDataFromCSV("urls");
	}

}
*/