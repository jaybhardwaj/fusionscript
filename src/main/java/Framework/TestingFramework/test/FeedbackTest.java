package Framework.TestingFramework.test;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.FeedBackHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;
import Framework.TestingFramework.utils.ExcelRead;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class FeedbackTest extends BaseTest
{
	EventFiringWebDriver driver;
	FeedBackHome feedback;
	String userName="vivek@zz.com";
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	feedback = new FeedBackHome(driver);
	}
	@Test(description = "TC_FB_01")
	public void RaiseApproveRejectFeed() throws InterruptedException, ClassNotFoundException, SQLException
	{
	  Assert.assertTrue(feedback.CheckRaisefeedback(userName,"Appreciation",""), "Feedback for "+userName+" is Not raised Successfully");
	  Assert.assertTrue(feedback.ApproveRejectfeedback(userName,"Appreciation","Approve"), "Feedback for "+userName+" is Not Approved/Reject Successfully");
	}
	
	@Test(description = "TC_FB_02")
	public void DuplicateFeedback() throws ClassNotFoundException, SQLException, InterruptedException
	{
		  Assert.assertTrue(feedback.CheckDuplicateFeedback("umesh@zz.com","Escalation"), "Duplicate Escalation raised Successfully");
		  Assert.assertTrue(feedback.CheckDuplicateFeedback("umesh@zz.com","Appreciation"), "Duplicate Escalation raised Successfully");
	}
	/*@Test
	public void ChecktabData()
	{
		  Assert.assertTrue(feedback.VerifyTabData("support@polestarllp.com1"), "Feedback tab filters not verified Successfully");
	}*/
	
	
	/*@DataProvider(name="FeedbackForEmp")(dataProvider="FeedbackForEmp")
	public String[][] loginData() throws IOException
	{
		ExcelRead Exl2 = new ExcelRead();
		String[][] arrayObject = Exl2.readExcel("/home/psslass11153/Desktop","LoginDetails.xlsx","Feedback");
		return arrayObject;
	}*/

}
