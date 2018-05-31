package Framework.TestingFramework.test;

import java.sql.SQLException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.FeedBackHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class FeedbackTest extends BaseTest
{
	EventFiringWebDriver driver;
	FeedBackHome feedback;
	String userName1="ankit@zz.com";
	String userName2="virat@zz.com";
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	feedback = new FeedBackHome(driver);
	}
	@Test(description = "TC_FB_01")
	public void RaiseApproveRejectFeed() throws InterruptedException, ClassNotFoundException, SQLException
	{
	  Assert.assertTrue(feedback.CheckRaisefeedback(userName1,"Escalation",""), "Feedback for "+userName1+" is Not raised Successfully");
	  Assert.assertTrue(feedback.ApproveRejectfeedback(userName1,"Escalation","Approve"), "Feedback for "+userName1+" is Not Approved/Reject Successfully");
	}
	
	@Test(description = "TC_FB_02")
	public void DuplicateFeedback() throws ClassNotFoundException, SQLException, InterruptedException
	{
		  Assert.assertTrue(feedback.CheckDuplicateFeedback(userName2,"Escalation"), "Duplicate Escalation raised Successfully");
		  Assert.assertTrue(feedback.CheckDuplicateFeedback(userName2,"Appreciation"), "Duplicate Appreciation raised Successfully");
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
