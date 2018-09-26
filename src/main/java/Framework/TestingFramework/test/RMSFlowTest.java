//jay bhardwaj
package Framework.TestingFramework.test;
import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.RMSFlowHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class RMSFlowTest extends BaseTest
{
	EventFiringWebDriver driver;
	RMSFlowHome RM_Home;
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	RM_Home = new RMSFlowHome(driver);
	}
	
	@Test(description = "TC_RMSFLOW_01")
	public void RMSFlow_Test() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed
	{//String stage, String action, String type,String PreScreeningStatus															
		  Assert.assertTrue(RM_Home.completeInterviewStage("HR Interview","Offered","Direct","false"), "HR Interview is not completed Successfully.");
    }

}
