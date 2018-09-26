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
import Framework.TestingFramework.methods.LemonadeHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class LemonadeHomeTest extends BaseTest
{
	EventFiringWebDriver driver;
	LemonadeHome Lemo_Home;
	ArrayList<String> ReqCan = new ArrayList<String>();
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	Lemo_Home = new LemonadeHome(driver);
	}
	
	@Test(description = "TC_LM_01")
	public void Lemonade_Accept_Candidate_Test() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed
	{
		  System.out.println("<<<<<<<<<<<<<<------------Lemolnade Accept Candidate Test Starts Now------------->>>>>>>>>>>>>>>");
		  Assert.assertTrue(Lemo_Home.lemonadeFlow("Accepted"), "Lemonade flow test is failed.");
	}
	
	@Test(description = "TC_LM_02")
	public void Lemonade_Reject_Candidate_Test() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed
	{
		  System.out.println("<<<<<<<<<<<<<<------------Lemolnade Reject Candidate Test Starts Now------------->>>>>>>>>>>>>>>");
		  Assert.assertTrue(Lemo_Home.lemonadeFlow("Rejected"), "Lemonade flow test is failed.");
	}
	
	@Test(description = "TC_LM_05")
	public void Lemonade_ReTest_Candidate_Test() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed
	{
		  System.out.println("<<<<<<<<<<<<<<------------Lemolnade ReTest Candidate Test Starts Now------------->>>>>>>>>>>>>>>");
		  Assert.assertTrue(Lemo_Home.ExpiryBatchflow(), "Lemonade Self Submit test is failed.");
	}
	
	@Test(description = "TC_LM_03")
	public void Lemonade_Self_Submit_Test() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed
	{
		  System.out.println("<<<<<<<<<<<<<<------------Lemolnade Self Submit Test Starts Now------------->>>>>>>>>>>>>>>");
		  Assert.assertTrue(Lemo_Home.selfSubmit(),"Lemonade Self Submit test is failed.");
	}
	
	@Test(description = "TC_LM_04")
	public void Lemonade_Expiry_Batch_Test() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed
	{
		  System.out.println("<<<<<<<<<<<<<<------------Lemolnade Expiry Batch Test Starts Now------------->>>>>>>>>>>>>>>");
		  Assert.assertTrue(Lemo_Home.ExpiryBatchflow(), "Lemonade Self Submit test is failed.");
	}
	
}
