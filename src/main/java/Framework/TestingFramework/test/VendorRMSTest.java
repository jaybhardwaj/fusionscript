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
import Framework.TestingFramework.methods.vendorRMSHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class VendorRMSTest extends BaseTest
{
	EventFiringWebDriver driver;
	vendorRMSHome VenRMS_Home;
	ArrayList<String> ReqCan = new ArrayList<String>();
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	VenRMS_Home = new vendorRMSHome(driver);
	}
	
	@Test(description = "TC_Req_01")
	public void Vendor_Registration() throws IOException, FindFailed, ClassNotFoundException, SQLException, InterruptedException
	{
		  System.out.println("<<<<<<<<<<<<<<------------Register_Approve_Reject_ReSubmit_Vendor test starts now--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>");
		  Assert.assertTrue(VenRMS_Home.checkVendorFunctionality("jaybhardwaj2991@gmail.com","polestar@123","HR Admin","Approve"), "test failed Successfully.");
		  Assert.assertTrue(VenRMS_Home.checkVendorFunctionality("jay.docx@gmail.com","q3dpog2h","HR Admin","Reject"), "verification failed Successfully.");
	}
}