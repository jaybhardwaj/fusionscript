package Framework.TestingFramework.test;

import java.io.IOException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.DSRHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;
import Framework.TestingFramework.utils.ExcelRead;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class FillDSRTest extends BaseTest 
{
	EventFiringWebDriver driver;
	DSRHome DsrHome;
	
	@BeforeClass
	public void testSetUp() {
    	driver = BaseTest.findMyDriver();
    	DsrHome = new DSRHome(driver);
	}
	
	@Test(dataProvider="empLogin",description = "TC_LLN_02")
	public void fillDsr(String userName, String password)
	{
	  Assert.assertTrue(DsrHome.fillDsr(userName,password,null), "Filling DSR for"+ userName+" is Not Successfull");
	}
	
	@DataProvider(name="empLogin")
	public String[][] loginData() throws IOException
	{
		ExcelRead Exl2 = new ExcelRead();
		String[][] arrayObject = Exl2.readExcel("/home/psslass11153/Desktop","LoginDetails.xlsx","Sheet2");
		return arrayObject;
	}
///my name is khan
}
