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
import Framework.TestingFramework.methods.InterviewCalendarHome;
import Framework.TestingFramework.methods.RMSFlowHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class InterviewCalendarTest extends BaseTest
{
	EventFiringWebDriver driver;
	InterviewCalendarHome Interview_Home;
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	Interview_Home = new InterviewCalendarHome(driver);
	}
	
	@Test(description = "TC_IC_01")
	public void TagCandidate_Requisition() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
//		String Requisition, String Stage, String Candidate, String Interviewer
		  Assert.assertTrue(Interview_Home.approveStage("ZRZBNAONKK","Screening","Subhash","ajay@zz.com","Approve"), "Candidates are Not tagged to requisition Successfully");
    } 

}
