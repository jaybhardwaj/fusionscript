package Framework.TestingFramework.test;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.FusionTheme;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class ThemeTest extends BaseTest
{
	EventFiringWebDriver driver;
	FusionTheme FusionThemeHome;
	
	@BeforeClass
	public void testSetUp() {
    	driver = BaseTest.findMyDriver();
    	FusionThemeHome = new FusionTheme(driver);
	}
	
	@Test
	public void CheckCollapsability() throws InterruptedException
	{
	  Assert.assertEquals(FusionThemeHome.CheckCollapsabilityOfNavBar("wasim@zz.com","Qwerty@1","Enable"),"Enabled");
	}

}
