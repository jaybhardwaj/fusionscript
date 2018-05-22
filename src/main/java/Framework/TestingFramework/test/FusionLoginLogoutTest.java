package Framework.TestingFramework.test;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.FusionLoginLogout;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class FusionLoginLogoutTest extends BaseTest {
	
	EventFiringWebDriver driver;
	FusionLoginLogout fusionloginlogout;

	@BeforeClass
	public void testSetUp() {
    	driver = BaseTest.findMyDriver();
    	fusionloginlogout = new FusionLoginLogout(driver);
	}
	
	@Test(description = "TC_LLN_01")
	public void LoginToFusion() throws InterruptedException {
		Assert.assertTrue(fusionloginlogout.LoginToFusion("amit@zz.com","Qwerty@1"), "Login Not Successfull");
		
	}

}
