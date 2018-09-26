package Framework.TestingFramework.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.ReferralHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class ReferralTest extends BaseTest
{
	EventFiringWebDriver driver;
	ReferralHome Refer;
	ArrayList<String> ReqCan = new ArrayList<String>();
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	Refer = new ReferralHome(driver);
	}
	
	@Test(description = "TC_Req_01")
	public void Create_Approve_Reject_Requisition() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed
	{
		  System.out.println("<<<<<<<<<<<<<<------------Employee Referral test starts now--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>");
		  Assert.assertTrue(Refer.referToRequisition("","CPWKHIGMTR",ReqCan), "Candidate  is Not done Successfully");
//		  Assert.assertTrue(Requisition.addRequisition("HOD","Reject"), "Requisition by HOD is Not rejected Successfully");
    }

}
