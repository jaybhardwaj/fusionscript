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
import Framework.TestingFramework.methods.RaiseExpenseHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;
import Framework.TestingFramework.utils.ExcelRead;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class RaiseExpenseTest extends BaseTest
{
	EventFiringWebDriver driver;
	RaiseExpenseHome raiseExpense;
	
	@BeforeClass
	public void testSetUp()
	{
    	driver = BaseTest.findMyDriver();
    	raiseExpense = new RaiseExpenseHome(driver);
	}
	
	@Test (dataProvider="ExpenseData", description="TC_RE_01")
	public void RaiseExpense(String user,String Amount,String Type,String Action) throws InterruptedException, ClassNotFoundException, SQLException
	{
	  Assert.assertTrue(raiseExpense.CheckRaiseExpense(user,Amount,Type,Action), "Expense for "+user+" is Not raised Successfully");
	}
	
	@DataProvider(name="ExpenseData")
	public String[][] RaiseExpData() throws IOException
	{
		ExcelRead Exl2 = new ExcelRead();
		String[][] arrayObject = Exl2.readExcel("/home/psslass11153/Desktop","LoginDetails.xlsx","ExpUserAmountCases");
		return arrayObject;
	}

}
