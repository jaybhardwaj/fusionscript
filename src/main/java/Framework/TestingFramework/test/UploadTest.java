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
import Framework.TestingFramework.methods.UploadHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class UploadTest extends BaseTest  
{
	EventFiringWebDriver driver;
	UploadHome upload;
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	upload = new UploadHome(driver);
	}
	@Test(description = "TC_UP_01")
	public void TagCandidate_To_Requisition() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		Assert.assertTrue(upload.uploadCandidate(5,"self"), "Candidates are Not tagged to requisition Successfully");
		Assert.assertTrue(upload.uploadCandidate(2,"Referral"), "Candidates are Not tagged to requisition Successfully");
    }
}

