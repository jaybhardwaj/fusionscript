package Framework.TestingFramework.methods;
import java.sql.ResultSet;
import Framework.TestingFramework.methods.DSRHome;
import java.sql.SQLException;
import java.util.List;
import Framework.TestingFramework.utils.DatabaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;

public class TimesheetHome
{
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	
	@FindAll({
		@FindBy(xpath = "//a[contains(text(),'TIMESHEET')]")
	})
	public WebElement TimesheetMenuBtn;
	
	@FindAll({
		@FindBy(xpath = "//div[@class='x_title clearfix']/h2"),
		@FindBy(xpath = "//div[@class='x_title clearfix layout-xs-column']/h2")
	})//a[contains(text(),' DSR ')]/..//li
	public WebElement TimesheetHeading;
	
	@FindAll({
		@FindBy(xpath = "//div[@class='message_wrapper']//p[@ng-if='dsrData.scrum']/p")
	})
	public WebElement ViewDsrText;
	
	@FindAll({
		@FindBy(xpath = "//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")
	})
	public WebElement ToggleMenuBarBtn;
	
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='selectedReportee']"),
		@FindBy(xpath = "//div//span[text()='Reportee']")		
	})
	public WebElement SelectReportee;
	
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,'md-select-menu-container selectdemoSelectHeader' ) and @aria-hidden='false']//md-option")
	})
	public WebElement Reportee;
	
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,'md-select-menu-container selectdemoSelectHeader' ) and @aria-hidden='false']//input")
	})
	public WebElement SearchUser;
	
	@FindAll({
		@FindBy(xpath = "//a[contains(text(),'Fill Timesheet')]"),
		@FindBy(xpath ="//a[@href='/fill-timesheet']")
	})
	public WebElement FillTimeSheetBtn;
	
	@FindAll({
		@FindBy(xpath = "//a[contains(text(),'Approve Timesheet')]"),
		@FindBy(xpath ="//a[@href='/approve-timesheet']")
	})
	public WebElement ApproveTimeSheetBtn;
		
	@FindAll({
		@FindBy(xpath = "//button[@submit-loader='saveLoading']")
	})
	public WebElement SaveBtn;
	
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='approveTimesheet(1,$event)']"),
		@FindBy(xpath = "//button[text()='Approve']")
	})
	public WebElement ApproveBtn;
	
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='openReasonDialog($event)']"),
		@FindBy(xpath = "//button[text()='Reject']")
	})
	public WebElement RejectBtn;
	
	@FindAll({
		@FindBy(xpath = "//textarea[@name='rejectReason']")
	})
	public WebElement RejectReason;
	
	@FindAll({
		@FindBy(xpath = "//form[@name='reasonForm']//button[@submit-button='Reject']")
	})
	public WebElement RejectOkBtn;
	
	@FindAll({
		@FindBy(xpath = "//button[@submit-loader='submitLoading']")
	})
	public WebElement SubmitBtn;
	
	@FindAll({
		@FindBy(xpath = "//div[@id='fortnight-picker']//button/div"),
		@FindBy(xpath = "//div[@id='fortnight-picker']//button[2]")
	})
	public WebElement NextFortnight;
	
	@FindAll({
		@FindBy(xpath = "//div[@id='fortnight-picker']//button[1]")
	})
	public WebElement PreviousFortnight;
	
	@FindAll({
		@FindBy(xpath = "//table[@class='timesheet-table md-table ng-isolate-scope']/tbody")
	})
	public WebElement htmltable;
	
	@FindAll({
		@FindBy(xpath = "//div[@ng-if='timesheetStatus']")
	})
	public WebElement TimeSheetStatus;
	
	@FindAll({
		@FindBy(xpath = "//div[@class='md-toast-content']//button")
	})
	public WebElement ToastCancelBtn;
	
	@FindAll({
		@FindBy(xpath = "//span[@class='md-toast-text ng-binding flex']")
	})
	public WebElement Toast;
	@FindAll({
		@FindBy(xpath = "//span[@class='fa fa-angle-down ng-scope']")
	})
	public WebElement LogOutMenuBtn;
	
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='okClicked()']"),
		@FindBy(xpath = "//button//span[text()='OK']")
	})
	public WebElement OkBtn;
	
	@FindAll({
		@FindBy(xpath = "//div[@ id='cube-close']//a[@ng-click='cubeClosed = true']")
	})
	public WebElement CubeCloseIcon;
	
	public TimesheetHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
	}
	//LogOutFusion
	public boolean fillTimesheet(String username,String password,String fortnight,String Action,String hours) throws InterruptedException
	{
		String Currentstatus;
		FusionLoginLogout fusionloginlogout= new FusionLoginLogout(driver);
		if(driver.findElements(By.xpath("//span[@class='fa fa-angle-down ng-scope']")).size() != 0)
		{
			fusionloginlogout.LogOutFusion();
		}
		boolean result = fusionloginlogout.LoginToFusion(username,password);
		if(result)
		{
			System.out.println("Login Successful");
			WaitUtil.sleep(2000);
			String Heading= TimesheetHeading.getText();
			System.out.println(Heading);
			if(Heading.equalsIgnoreCase("timesheet"))
			{
				System.out.println("Already at Required Page.");
			}
			else
			 {
				if(driver.findElements(By.xpath("//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")).size() != 0)
				{
					if(ToggleMenuBarBtn.isDisplayed() && ToggleMenuBarBtn.isEnabled())
					{  ToggleMenuBarBtn.click();
						WaitUtil.sleep(3000);
					}
				}
					List <WebElement> MenuChild=driver.findElements(By.xpath("//a[contains(text(),'TIMESHEET')]/..//li"));
					int n=MenuChild.size();
					System.out.println(n);
					if(n>0)
					{
						System.out.println("First if");
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.TimesheetMenuBtn);
						TimesheetMenuBtn.click();
						WaitUtil.sleep(1000);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FillTimeSheetBtn);
						FillTimeSheetBtn.click();
					}
					else
					  {
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FillTimeSheetBtn);
						FillTimeSheetBtn.click();
						System.out.println("Else");
					  }
			}
			WaitUtil.sleep(5000);
		}
		else{
			System.out.println("Login Unsuccessful");
			Currentstatus="Login Unsuccessful";
			return false;
		}
		if(fortnight.equalsIgnoreCase("next")){
			NextFortnight.click();
			WaitUtil.sleep(3000);
	    }else if(fortnight.equalsIgnoreCase("Pre")){
			PreviousFortnight.click();
			WaitUtil.sleep(3000);
		}
		Currentstatus=TimeSheetStatus.getText();
		System.out.println("current timesheet status-->>"+Currentstatus);
		if(Currentstatus.equalsIgnoreCase("Draft") || Currentstatus.equalsIgnoreCase("Not Filled"))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.htmltable);
			List<WebElement> Reqcolumns=htmltable.findElements(By.xpath("//td[text()='Expense']/..//td[@class='md-cell ng-scope']//input"));
			for(int i=0;i<Reqcolumns.size();i++)
			{
				Reqcolumns.get(i).click();
				Reqcolumns.get(i).clear();
				Reqcolumns.get(i).sendKeys(hours);
				WaitUtil.sleep(1000);
			}
			WaitUtil.sleep(4000);
			if(Action.equalsIgnoreCase("save"))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SaveBtn);
				SaveBtn.click();
			}
			else if(Action.equalsIgnoreCase("submit"))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.CubeCloseIcon);
				CubeCloseIcon.click();
				WaitUtil.sleep(5000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SubmitBtn);
				SubmitBtn.click();
			}
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Toast);
			String Msg=Toast.getText();
			System.out.println("Toast Msg-->>"+Msg);
			ToastCancelBtn.click();
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.TimeSheetStatus);
			Currentstatus=TimeSheetStatus.getText();
			System.out.println("After Save/submit Status-->>"+Currentstatus);
			if(Msg.equalsIgnoreCase("Timesheet saved successfully.") && Currentstatus.equalsIgnoreCase("Saved"))
			{
				System.out.println("Verification Passed TimeSheet saved");
				Currentstatus="TimeSheet Saved";
				return true;
			}
			else if(Msg.equalsIgnoreCase("Timesheet submitted successfully.") && Currentstatus.equalsIgnoreCase("Approval Pending"))
			{
				System.out.println("Verification Passed TimeSheet Submitted");
				return true;
			}
			else if(Msg.equalsIgnoreCase("Total Hours for any date can not be greater than 24."))
			{
				System.out.println("Total Hours for Some date are greater than 24.");
				Currentstatus="Total Hours for Some date are greater than 24.Timesheet can'nt be submitted.";
				return true;
			}
		}
			Currentstatus="TimeSheet Already Filled";
			return false;
	}
	
	public boolean ApproveTimeSheet(String user,String password,String fortnight,String Action) throws InterruptedException, ClassNotFoundException, SQLException
	{
		WaitUtil.sleep(2000);
		String query=null;
		String supervisor=null;
		String UserFullName=null;
		FusionLoginLogout fusionloginlogout= new FusionLoginLogout(driver);
		if(driver.findElements(By.xpath("//span[@class='fa fa-angle-down ng-scope']")).size() != 0)
		{
			fusionloginlogout.LogOutFusion();
		}
		query="Select m.useremail,concat(e.firstname, ' ', e.lastname) as EmployeeName from mstemployee e left join mstemployee m on e.managerid=m.userid and m.isactive=1 where e.useremail='"+user+"' and e.isactive=1;";
		DatabaseUtil db = new DatabaseUtil();
			db.makeConnection();
			ResultSet result=db.runQuery(query);
			result.beforeFirst();
			if(result.next())
			{
				supervisor = result.getString(1);
				UserFullName= result.getString(2);
			}
			System. out.println(supervisor);
			System. out.println(UserFullName);
			db.ConnectionClose();
			boolean Loginresult = fusionloginlogout.LoginToFusion(supervisor,"Qwerty@1");
			if(Loginresult)
			{
				WaitUtil.sleep(5000);
				if(driver.findElements(By.xpath("//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")).size() != 0)
				{
					if(ToggleMenuBarBtn.isDisplayed() && ToggleMenuBarBtn.isEnabled())
					{
						ToggleMenuBarBtn.click();
						WaitUtil.sleep(3000);
					}
				}
				boolean ApproveTimeSheetPresence = ApproveTimeSheetBtn.isDisplayed();
				boolean ApproveTimeSheetEnabled = ApproveTimeSheetBtn.isEnabled();
				if (ApproveTimeSheetPresence==false || ApproveTimeSheetEnabled==false)
				{
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.TimesheetMenuBtn);
					TimesheetMenuBtn.click();
					WaitUtil.sleep(1000);
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ApproveTimeSheetBtn);
					ApproveTimeSheetBtn.click();
					WaitUtil.sleep(5000);
				}
				else if (ApproveTimeSheetPresence==true && ApproveTimeSheetEnabled==true)
				{
					ApproveTimeSheetBtn.click();
					WaitUtil.sleep(5000);				
				}
				if(fortnight.equalsIgnoreCase("next")){
					NextFortnight.click();
					WaitUtil.sleep(3000);
			    }else if(fortnight.equalsIgnoreCase("Pre")){
					PreviousFortnight.click();
					WaitUtil.sleep(3000);
				}
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SelectReportee);
				SelectReportee.click();
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SearchUser);
				SearchUser.click();
				SearchUser.clear();
				SearchUser.sendKeys(UserFullName);
				WaitUtil.sleep(4000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Reportee);
				Reportee.click();
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.CubeCloseIcon);
				CubeCloseIcon.click();
				if(Action.equalsIgnoreCase("Approve"))
				{
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ApproveBtn);
					ApproveBtn.click();
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OkBtn);
					OkBtn.click();
				}
				else if(Action.equalsIgnoreCase("Reject"))
				{
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.RejectBtn);
					RejectBtn.click();
					WaitUtil.sleep(2000);
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.RejectReason);
					RejectReason.click();
					RejectReason.clear();
					RejectReason.sendKeys("Verification Failed");
					WaitUtil.sleep(2000);
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.RejectOkBtn);
					RejectOkBtn.click();					
				}
				
				WaitUtil.sleep(5000);
			}
			else{
				System.out.println("Supervisor Login Unsuccessful");
				return false;
			}
			return true;

	}
	public boolean DsrVisibilityInTimesheet(String user,String password,String DsrText,String DsrDate) throws InterruptedException
	{
		WaitUtil.sleep(2000);
		FusionLoginLogout fusionloginlogout= new FusionLoginLogout(driver);
		if(driver.findElements(By.xpath("//span[@class='fa fa-angle-down ng-scope']")).size() != 0)
		{
			fusionloginlogout.LogOutFusion();
		}
		DSRHome DsrHome=new DSRHome(driver);
		DsrHome.fillDsr(user,password,DsrText);
		boolean result = fusionloginlogout.LoginToFusion(user,password);
		if(result)
		{
			WaitUtil.sleep(5000);
			if(driver.findElements(By.xpath("//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")).size() != 0)
			{
				if(ToggleMenuBarBtn.isDisplayed() && ToggleMenuBarBtn.isEnabled())
				{
					ToggleMenuBarBtn.click();
					WaitUtil.sleep(2000);
				}
			}
			boolean FillTimeSheetPresence = FillTimeSheetBtn.isDisplayed();
			boolean FillTimeSheetEnabled = FillTimeSheetBtn.isEnabled();
			if (FillTimeSheetPresence==false || FillTimeSheetEnabled==false)
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.TimesheetMenuBtn);
				TimesheetMenuBtn.click();
				WaitUtil.sleep(1000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FillTimeSheetBtn);
				FillTimeSheetBtn.click();
				WaitUtil.sleep(3000);
			}
			else if (FillTimeSheetPresence==true && FillTimeSheetEnabled==true)
			{
				FillTimeSheetBtn.click();
				WaitUtil.sleep(3000);				
			}
			Actions action = new Actions(driver);
			String xpath = "//th[contains(text(),'"+DsrDate+"')]";
			WebElement DsrHeader = driver.findElement(By.xpath(xpath));
			WaitUtil.sleep(2000);
			xpath = "//th[contains(text(),'"+DsrDate+"')]//span";
			WebElement ViewDsr = driver.findElement(By.xpath(xpath));
			WaitUtil.sleep(2000);
			action.moveToElement(DsrHeader).moveToElement(ViewDsr).click().build().perform();
			WaitUtil.sleep(2000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ViewDsrText);
			String VisibleDsrText=ViewDsrText.getText();
			if(VisibleDsrText.equalsIgnoreCase(DsrText))
			{
				System.out.println("Filled DSR is visible in timesheet");
				return true;
			}
			
		}
		else{
			System.out.println("Login Unsuccessful");
			return false;
		}
		return true;
		
	}

}
