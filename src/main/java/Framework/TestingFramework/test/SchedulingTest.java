package Framework.TestingFramework.test;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.SchedulingHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class SchedulingTest extends BaseTest
{
	EventFiringWebDriver driver;
	SchedulingHome schedulingObj;
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	schedulingObj = new SchedulingHome(driver);
	}
	
	@Test(description = "TC_SD_01")
	public void TagCandidate_Requisition() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		  Assert.assertTrue(schedulingObj.scheduler("","Screening","","",""), "Candidates are Not tagged to requisition Successfully");
    }

}
