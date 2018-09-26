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
import Framework.TestingFramework.methods.VendorHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class vendorHomeTest extends BaseTest
{
	EventFiringWebDriver driver;
	VendorHome Ven_Home;
	ArrayList<String> ReqCan = new ArrayList<String>();
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	Ven_Home = new VendorHome(driver);
	}
	
	@Test(description = "TC_Req_01")
	public void Vendor_Registration() throws IOException, FindFailed, ClassNotFoundException, SQLException, InterruptedException
	{
		  System.out.println("<<<<<<<<<<<<<<------------Vendor Registration test starts now--------------->>>>>>>>>>>>>>>>>>>>>>>>>>>");
		  Assert.assertTrue(Ven_Home.CompleteRegistration("jaybhardwaj2991@gmail.com","polestar@123","submit"), "Vendor is not registerred Successfully.");
//		  Assert.assertTrue(Ven_Home.CompleteRegistration("jay.prakash@polestarllp.com","q3dpog2h","submit"), "Vendor is not registerred Successfully.");
		  Assert.assertTrue(Ven_Home.CompleteRegistration("jay.docx@gmail.com","q3dpog2h","submit"), "Vendor is not registerred Successfully.");
    }

}
