package Framework.TestingFramework.test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.methods.TimesheetHome;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class TimesheetTest extends BaseTest
{
	EventFiringWebDriver driver;
	TimesheetHome TimeSheetHome;
	String user="aman@zz.com";
	
	@BeforeClass
	public void testSetUp() {
    	driver = BaseTest.findMyDriver();
    	TimeSheetHome = new TimesheetHome(driver);
	}
	@Test(priority=5)
	public void fillAndApproveTimesheet() throws InterruptedException, ClassNotFoundException, SQLException 
	{
		Assert.assertTrue(TimeSheetHome.fillTimesheet(user,"Qwerty@1","current","save","11"),"Timesheet is not filled for-->");
		Assert.assertTrue(TimeSheetHome.ApproveTimeSheet(user,"Qwerty@1","current","Approve"),"Timesheet is not approved");
	}
	@Test(priority=10)
	public void CheckDsrVisibilityInTimesheet() throws InterruptedException
	{
		Calendar calendar = Calendar.getInstance();
		java.util.Date date = calendar.getTime();
		LocalDate currentDate = LocalDate.now(); 
		int dom = currentDate.getDayOfMonth();	
		String day=new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime());
		String DsrDate=dom+"-"+day;
		System.out.println("CurrentDayName-->>"+DsrDate);
		Assert.assertTrue(TimeSheetHome.DsrVisibilityInTimesheet(user,"Qwerty@1","Dsr Visibility in timesheet",DsrDate), "Filled DSR is Not visible in timesheet");
	}
}
