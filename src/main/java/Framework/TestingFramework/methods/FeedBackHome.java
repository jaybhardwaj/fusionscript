package Framework.TestingFramework.methods;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.DatabaseUtil;
import Framework.TestingFramework.utils.WaitUtil;

public class FeedBackHome
{
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String supervisor=null;
	String UserFullName=null;
	
	CommonMethods CMT ;
	
	@FindAll({
		@FindBy(xpath = "//div[@class='md-toast-content']//button")
	})
	public WebElement ToastCancelBtn;
	@FindAll({
		@FindBy(xpath = "//div[@class='md-padding']/h4")
	})
	public WebElement Alert;
	@FindAll({
		@FindBy(xpath = "//span[@class='md-toast-text ng-binding flex']")
	})
	public WebElement Toast;
	@FindAll({
		@FindBy(xpath = "//span[@class='fa fa-angle-down ng-scope']")
	})
	public WebElement LogOutMenuBtn;
	@FindAll({
		@FindBy(xpath = "//a[contains(text(),'FEEDBACK')]"),
		@FindBy(xpath ="//a[@href='/feedback']")
	})
	public WebElement FeedBackMenuBtn;
	@FindAll({
		@FindBy(xpath = "//button[@ng-if='userInfo.ismanager']"),
		@FindBy(xpath = "//button[@aria-label='Eat cake']")
	})
	public WebElement AddFeedBackBtn;
	@FindAll({
		@FindBy(xpath = "//md-select[@aria-label='Employee name']")
	})
	public WebElement EmployeeNameSelect;
	@FindAll({
		@FindBy(xpath = "//md-select-header//input[@ng-model='searchEmployee']"),
		@FindBy(xpath = "//md-select-header//input[@ng-model='searchEmployee']")
	})
	public WebElement SearchUserToRaise;
	@FindAll({
		@FindBy(xpath = "//md-option [@ng-value='user']")
	})
	public WebElement User;
	@FindAll({
		@FindBy(xpath = "//md-select[@aria-label='Feedback Type']")
	})
	public WebElement FeedBackTypeSelect;
	@FindAll({
		@FindBy(xpath = "//md-option//div[text()='Appreciation']")
	})
	public WebElement OptionAppreciation;	
	@FindAll({
		@FindBy(xpath = "//md-option//div[text()='Escalation']")
	})
	public WebElement OptionEscalation;
	@FindAll({
		@FindBy(xpath = "//md-select[@aria-label='Feedback Reason']")
	})
	public WebElement FeedBackReasonSelect;
	@FindAll({
		@FindBy(xpath = "//md-optgroup//md-option[@ng-value='feedbackReason.id'][1]"),
		@FindBy(xpath = "//md-option//div[text()='Disciplined']")
	})
	public WebElement FeedBackReasonOption;
	@FindAll({
		@FindBy(xpath = "//input[@class ='md-datepicker-input md-input']")
	})
	public WebElement RaisingDate;
	@FindAll({
		@FindBy(xpath = "//td[@class='md-calendar-date md-calendar-date-today md-focus']/span"),
		@FindBy(xpath = "//div[@class='md-virtual-repeat-offsetter']//span[text()='19']")
	})
	public WebElement RaisingDateOption;
	@FindAll({
		@FindBy(xpath = "//textarea[@ng-model='feedbackInfo.feedbackdescription']"),
		@FindBy(xpath = "//textarea[@name='feedbackdescription']")
	})
	public WebElement FeedBackComments;
	@FindAll({
		@FindBy(xpath = "//button//span[text()='Raise']"),
		@FindBy(xpath = "//button[@ submit-button='Raise']")
	})
	public WebElement RaiseBtn;
	@FindAll({
		@FindBy(xpath = "//div[@class='nav_menu']//p[@class='ng-binding']"),
		@FindBy(xpath = "//div[@class='x_title clearfix layout-xs-column']/h2")
	})
	public WebElement PageHeading;
	@FindAll({
		@FindBy(xpath = "//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")
	})
	public WebElement ToggleMenuBarBtn;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[text()='Appreciation']")
	})
	public WebElement AppreciationTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[text()='Escalation']")
	})
	public WebElement EscalationTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[text()='Feedback Recieved']")
	})
	public WebElement FeedbackRecieved;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[text()='Feedback Raised']")
	})
	public WebElement FeedbackRaised;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[text()='My Approvals']")
	})
	public WebElement MyApprovals;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[text()='Other Approvals']")
	})
	public WebElement OtherApprovals;
	@FindAll({
		@FindBy(xpath = "//button[@aria-label='menu']")
	})
	public WebElement MenuTray;
	@FindAll({
		@FindBy(xpath = "//md-fab-actions//button[@aria-label='Google Hangout']")
	})
	public WebElement RejectFeedbackBtn;
	@FindAll({
		@FindBy(xpath = "//md-fab-actions//button[@aria-label='Facebook']")
	})
	public WebElement ApproveFeedbackBtn;
	@FindAll({
		@FindBy(xpath = "//div[@table-search='approvalsSearch']//input[@placeholder='Search']")
	})
	public WebElement SearchUserToApprove;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='okClicked()']"),
		@FindBy(xpath = "//button//span[text()='OK']"),
		@FindBy(xpath = "//button[@ng-click='closeAlertDialog()']")
	})
	public WebElement OkBtn;
	@FindAll({
		@FindBy(xpath = "//a[@aria-label='HR']"),
		@FindBy(xpath = "//a/i [@class='fa fa-users']")		
	})
	public WebElement HRSection;
	
	public FeedBackHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
	}
	
	public boolean CheckRaisefeedback(String user,String FeedBackType,String date) throws InterruptedException, ClassNotFoundException, SQLException
	{
		System.out.println("CheckRaisefeedback-->"+user);
		String[] temp=findSupervisor(user);
		supervisor=temp[0];
		UserFullName=temp[1];
		System.out.println("CheckRaisefeedback-->"+UserFullName);
		if(CMT.GotoFeedbackPage(supervisor))
		{
			WaitUtil.sleep(5000);
			Raisefeedback(FeedBackType,UserFullName);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Toast);
			String Msg=Toast.getText();
			System.out.println("Toast Msg-->>"+Msg);
			ToastCancelBtn.click();	
			WaitUtil.sleep(1500);		
		}
		else{
			System.out.println("Some Error Occuered in GoToFeedback Process.");
			return false;
		    }
		return true;		
	}
	
	public void Raisefeedback(String FeedBackType, String UserFullName)
	{
		//System.out.println("Raisefeedback-->"+UserFullName);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.AddFeedBackBtn);
		AddFeedBackBtn.click();
		WaitUtil.sleep(2000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.EmployeeNameSelect);
		EmployeeNameSelect.click();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SearchUserToRaise);
		SearchUserToRaise.click();
		SearchUserToRaise.clear();
		SearchUserToRaise.sendKeys(UserFullName);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.User);
		User.click();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FeedBackTypeSelect);
		FeedBackTypeSelect.click();
		if(FeedBackType.equalsIgnoreCase("Escalation"))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OptionEscalation);
			OptionEscalation.click();
		}
		else{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OptionAppreciation);
			OptionAppreciation.click();
		    }
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FeedBackReasonSelect);
		FeedBackReasonSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FeedBackReasonOption);
		FeedBackReasonOption.click();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.RaisingDate);
		RaisingDate.click();
		WaitUtil.sleep(1500);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.RaisingDateOption);
		RaisingDateOption.click();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FeedBackComments);
		FeedBackComments.click();
		FeedBackComments.clear();
		FeedBackComments.sendKeys("He deserve it.");
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.RaiseBtn);
		RaiseBtn.click();	
	}
	
	public boolean ApproveRejectfeedback(String user,String FeedBackType,String Action) throws InterruptedException, ClassNotFoundException, SQLException
	{
		String[] temp=findSupervisor(supervisor);
		supervisor=temp[0];
		System.out.println("ApproveRejectfeedback-->"+UserFullName);
		if(CMT.GotoFeedbackPage(supervisor))
		{
			WaitUtil.sleep(5000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.MyApprovals);
			MyApprovals.click();
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SearchUserToApprove);
			SearchUserToApprove.click();
			SearchUserToApprove.clear();
			SearchUserToApprove.sendKeys(UserFullName);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.MenuTray);
			MenuTray.click();
			WaitUtil.sleep(1500);
			if(Action.equalsIgnoreCase("Approve"))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ApproveFeedbackBtn);
				ApproveFeedbackBtn.click();
			}
			else if(Action.equalsIgnoreCase("Reject"))
				{
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.RejectFeedbackBtn);
					RejectFeedbackBtn.click();
				}
			else{
				System.out.println("Plz send rightly spelled Action parameter");
				return false;
			    }
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OkBtn);
			OkBtn.click();
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Toast);
			String Msg=Toast.getText();
			System.out.println("Approve/Reject Status->"+Msg);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ToastCancelBtn);
			ToastCancelBtn.click();
		}else{
			System.out.println("Some Error Occuered in GoToFeedback Process.");
			return false;
		     }			   		
		return true;
	}
	
	public String[] findSupervisor(String user) throws ClassNotFoundException, SQLException
	{
		String query=null;
		String[] Result = new String[2];
		query="Select m.useremail,concat(e.firstname, ' ', e.lastname) as EmployeeName from mstemployee e left join mstemployee m on e.managerid=m.userid and m.isactive=1 where e.useremail='"+user+"' and e.isactive=1;";
		DatabaseUtil db = new DatabaseUtil();
			db.makeConnection();
			ResultSet result=db.runQuery(query);
			result.beforeFirst();
			if(result.next())
			{
				
				Result[0] = result.getString(1);
				Result[1]= result.getString(2);
			}
			System. out.println(Result[0]);
			System. out.println(Result[1]);
			db.ConnectionClose();
		return Result;
	}
	
	public Boolean GotoFeedbackPage(String user) throws InterruptedException
	{
		FusionLoginLogout fusionloginlogout= new FusionLoginLogout(driver);
		if(driver.findElements(By.xpath("//button[@ng-click='$mdMenu.open($event)']/span")).size() != 0)
		{
			fusionloginlogout.LogOutFusion();
			WaitUtil.sleep(3000);
		}
		
		boolean Loginresult = fusionloginlogout.LoginToFusion(user,"Qwerty@1");
		if(Loginresult)
		{
			System.out.println("Login Successful");
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.PageHeading);
			String Heading= PageHeading.getText();
			System.out.println("We are at page-->"+Heading);
			if(Heading.equalsIgnoreCase("Feedback"))
			{
				System.out.println("Already at Feedback Page.");
			}else
				{
					if(driver.findElements(By.xpath("//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")).size() != 0)
						{
						 System.out.println("Inside ToggleNav Bar");
							if(ToggleMenuBarBtn.isDisplayed() && ToggleMenuBarBtn.isEnabled())
								{  
									ToggleMenuBarBtn.click();
									WaitUtil.sleep(1000);
									System.out.println("Inside ToggleNav Bar Clicked");
								}
						}
					if(HRSection.isDisplayed() && HRSection.isEnabled())
						{
							System.out.println("Inside HR Section");
							WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.HRSection);
							HRSection.click();	
						}
					if(driver.findElements(By.xpath("//a[contains(text(),'FEEDBACK')]")).size() != 0)
						{
							WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FeedBackMenuBtn);
							FeedBackMenuBtn.click();
							WaitUtil.sleep(5000);
						}else
							{
								System.out.println("FeedBack Module is not given to this user");
								return false;
							}	
				}
		 }else{
			System.out.println("Login Unsuccessful");
			return false;
		      }	   
		return true;
	}

	public boolean CheckDuplicateFeedback(String user,String FeedBackType) throws ClassNotFoundException, SQLException, InterruptedException 
	{
		String[] temp=findSupervisor(user);
		supervisor=temp[0];
		UserFullName=temp[1];
		if(CMT.GotoFeedbackPage(supervisor))
		{
			WaitUtil.sleep(5000);
			Raisefeedback(FeedBackType,UserFullName);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Toast);
			String Msg=Toast.getText();
			System.out.println("Toast Msg-->>"+Msg);
			ToastCancelBtn.click();
			System.out.println("First Feedback raised successfully");
			WaitUtil.sleep(1500);
			Raisefeedback(FeedBackType,UserFullName);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Alert);
			Msg=Alert.getText();
			System.out.println("Alert Msg-->>"+Msg);
			if(Msg.equalsIgnoreCase("Feedback already raised for the User."))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OkBtn);
				OkBtn.click();
				return true;
			}else
				{
					return false;
				}
		}
		else{
			System.out.println("Some Error Occuered in GoToFeedback Process.");
			return false;
		    }
	}

}
