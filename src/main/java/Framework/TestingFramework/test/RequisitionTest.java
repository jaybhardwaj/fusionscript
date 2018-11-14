package Framework.TestingFramework.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.RequisitionHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class RequisitionTest extends BaseTest
{
	EventFiringWebDriver driver;
	RequisitionHome Requisition;
	ArrayList<String> ReqCan = new ArrayList<String>();
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	Requisition = new RequisitionHome(driver);
	}
	
	@Test(description = "TC_Req_01", priority = 0)
	public void Create_Approve_Reject_Requisition() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		  System.out.println("<<<<<<<<<<<<<<------------Create_Approve_Reject_Requisition test starts now--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>");
		  Assert.assertTrue(Requisition.addRequisition("Hiring Manager","Approve"), "Requisition by Hiring Manager is Not raised Successfully");
		  Assert.assertTrue(Requisition.addRequisition("HOD","Reject"), "Requisition by HOD is Not rejected Successfully");
    }

	@Test(description = "TC_Req_02", priority = 1)
	public void Assign_OpenForRefferal_Requisition() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		  System.out.println("<<<<<<<<<<<<<<------------Assign Requisition test starts now--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>");
		  Assert.assertTrue(Requisition.actionOnRequisition("HR Admin","Assign",ReqCan,"true"), "Requisition is Not assigned Successfully");
    }
	
	@Test(description = "TC_Req_03", priority = 2)
	public void Delete_Requisition() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		  System.out.println("<<<<<<<<<<<<<<------------Delete Requisition test starts now--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>");
		  Assert.assertTrue(Requisition.actionOnRequisition("HR Admin","Delete",ReqCan,"false"), "Requisition is Not Deleted Successfully");
    }
	
	@Test(description = "TC_Req_04", priority = 3)
	public void TagCandidate_Requisition() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		  System.out.println("<<<<<<<<<<<<<<------------Tag Requisition test starts now--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>");
		  ReqCan.clear();
		  Assert.assertTrue(Requisition.actionOnRequisition("HR Admin","Tag",ReqCan,"true"), "Candidates are Not tagged to requisition Successfully");
    }
	
	@Test(description = "TC_Req_05", priority = 4)
	public void Copy_Requisition() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		  System.out.println("<<<<<<<<<<<<<<------------Copy Requisition test starts now--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>");
		  Assert.assertTrue(Requisition.actionOnRequisition("HR Admin","Copy",ReqCan,"false"), "Requisition is Not copied Successfully");
    }

}