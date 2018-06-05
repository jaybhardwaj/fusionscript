package Framework.TestingFramework.methods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;

public class RaiseExpenseHome 
{
	CommonMethods CMT ;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String pageName = "feedback";
	String pageSectionXpath = "//a[@aria-label='Finance']";
	String PageXpath = "//a[contains(text(),'My Expenses')]";
	String pageMenuXpath = "//a[contains(text(),'EXPENSE')]";
	
	public RaiseExpenseHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
	}
	@FindAll({
		@FindBy(xpath = "//a[contains(text(),'EXPENSE')]")
	})
 	public WebElement ExpenseMenuBtn;
	@FindAll({
		@FindBy(xpath = "//a[@href='/raiseExpense']")
	})
	public WebElement GoToRaiseExpenseBtn;
	@FindAll({
		@FindBy(xpath = "//a[@aria-label='Finance']"),
		@FindBy(xpath = "//a/i [@class='fa fa-university']"),		
	})
	public WebElement FinanceSection;
	@FindAll({
		@FindBy(xpath = "//div[@class='nav_menu']//p[@class='ng-binding']"),
		@FindBy(xpath = "//ul[@layout='row']//p[@class='ng-binding']")
	})
	public WebElement PageHeading;	
	@FindAll({
		@FindBy(xpath = "//span[text()='Hotel']"),
		@FindBy(xpath = "//md-icon[contains(text(),'hotel')]/parent::*")
	})
	public WebElement ExpenseTypeHotelBtn;
	@FindAll({
		@FindBy(xpath = "//span[text()='Per Diem']")
	})
	public WebElement ExpenseTypePerDiemBtn;
	@FindAll({
		@FindBy(xpath = "//span[text()='Phone']")
	})
	public WebElement ExpenseTypePhoneBtn;	
	@FindAll({
		@FindBy(xpath = "//span[text()='Travel']")
	})
	public WebElement ExpenseTypeTravelBtn;	
	@FindAll({
		@FindBy(xpath = "//*[@tabindex='0' and @aria-label='Trip/Assignment']")
	})
	public WebElement TripSelect;
	@FindAll({
		@FindBy(xpath = "//md-option[@ng-repeat='trip in tripAssignmentList'][1]"),
		@FindBy(xpath = "//md-option[@ng-repeat='trip in tripAssignmentList' and @selected='selected']")
	})
	public WebElement SelectTripOption;
	@FindAll({
		@FindBy(xpath = "//*[@tabindex='0' and @aria-label='Hotel Name']")
	})
	public WebElement HotelNameSelect;
	@FindAll({
		@FindBy(xpath = "//md-option[@ng-repeat='hotel in expenseHotelsList'][1]")
	})
	public WebElement SelectHotelOption;
	@FindAll({
		@FindBy(xpath = "//input[@name ='reasonForStayModal']")
	})
	public WebElement ReasonForExp;
	@FindAll({
		@FindBy(xpath = "//*[@tabindex='0' and @aria-label='Travel Type']")
	})
	public WebElement TravelTypeSelect;
	@FindAll({
		@FindBy(xpath = "//div[text()='Flight']")
	})
	public WebElement SelectTravelOption;
	@FindAll({
		@FindBy(xpath = "//input[@name ='totalAmountModal']")
	})
	public WebElement TotalAmountPhn;
	@FindAll({
		@FindBy(xpath = "//input[@name ='comments']")
	})
	public WebElement CommentsExp;
	@FindAll({
		@FindBy(xpath = "//input[@name ='ratesPerDayModal']")
	})
	public WebElement RatePerDay;
	@FindAll({
		@FindBy(xpath = "(//input[@class ='md-datepicker-input md-input'])[1]")
	})
	public WebElement From_date;
	@FindAll({
		@FindBy(xpath = "(//input[@class ='md-datepicker-input md-input'])[2]")
	})
	public WebElement To_date;
	@FindAll({
		@FindBy(xpath = "//div[@class='md-virtual-repeat-offsetter']//span[text()='5']")
	})
	public WebElement FrDate;
	@FindAll({
		@FindBy(xpath = "//div[@class='md-virtual-repeat-offsetter']//span[text()='24']")
	})
	public WebElement ToDate;
	@FindAll({
		@FindBy(xpath = "//button[@type='submit' and @submit-button='Save']")
	})
	public WebElement saveBtn;
	@FindAll({
		@FindBy(xpath = "//div[@class='layout-align-end-end layout-row']//span[ text()='Submit']")
	})
	public WebElement ExpSubmitBtn;
	@FindAll({
		@FindBy(xpath = "//button[@aria-label='Eat cake']")
	})
	public WebElement AddExpenseBtn;
	@FindAll({
		@FindBy(xpath = "//*[@tabindex='0' and @aria-label='Currency']")
	})
	public WebElement CurrencySelect;
	@FindAll({
		@FindBy(xpath = "//div[text()='INR']")
	})
	public WebElement SelectCurrencyOption;
	@FindAll({
		@FindBy(xpath = "//input[@name ='billUpload']")
	})
	public WebElement UploadFile;
	@FindAll({
		@FindBy(xpath = "//button[@aria-label='More']")
	})
	public WebElement ChangeFortnightBtn;
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
		@FindBy(xpath = "//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")
	})
	public WebElement ToggleMenuBarBtn;
	
	
	
	public boolean CheckRaiseExpense(String user,String Amount,String Type,String Action) throws InterruptedException
	{
		if(CMT.GotoFeedbackPage(user,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			int ExpAmount = Integer.parseInt(Amount);
			raiseHotelExp(ExpAmount,Action);		
		}
		else{
			System.out.println("Some Error Occuered in GoToRaiseExpense Page Process.");
			return false;
		    }
		
		return true;
		
	}
	
/*	public Boolean GotoRaiseExpPage(String user) throws InterruptedException
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
			if(Heading.equalsIgnoreCase("Raise Expense"))
			{
				System.out.println("Already at Raise Expense Page.");
				WaitUtil.sleep(5000);
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
					if(FinanceSection.isDisplayed() && FinanceSection.isEnabled())
						{
							System.out.println("Inside Finance Section");
							WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FinanceSection);
							FinanceSection.click();	
						}
					if(driver.findElements(By.xpath("//a[contains(text(),'EXPENSE')]")).size() != 0)
						{
							WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ExpenseMenuBtn);
							ExpenseMenuBtn.click();
							WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.GoToRaiseExpenseBtn);
							GoToRaiseExpenseBtn.click();
							WaitUtil.sleep(5000);
						}else
							{
								System.out.println("Expense Module is not given to this user");
								return false;
							}	
				}
		 }else{
			System.out.println("Login Unsuccessful");
			return false;
		      }	   
		return true;
	}
	*/
//Functions To raise various types of Expenses===============================>>>>>>>>>>>>>>>>>>>>>
		public void raiseHotelExp(int ExpAmount,String Reqstatus) throws NullPointerException, InterruptedException
		{
			int Amount = ExpAmount/20;
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.AddExpenseBtn);
			AddExpenseBtn.click();
			Thread.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ExpenseTypeHotelBtn);
		    ExpenseTypeHotelBtn.click();
		    WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.TripSelect);
		   	TripSelect.click();
			Thread.sleep(1000);
	 		SelectTripOption.click();
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.From_date);
	    	From_date.click();
			Thread.sleep(1000);
	    	FrDate.click();
			Thread.sleep(1000);
	    	ToDate.click();
	    	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.HotelNameSelect);
	    	HotelNameSelect.click();
			Thread.sleep(1000);
	       	SelectHotelOption.click();
	       	Thread.sleep(1000);
	        ReasonForExp.sendKeys("For Meeting Client");
	        WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.RatePerDay);
	       	RatePerDay.sendKeys(String.valueOf(Amount));
	       	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.CurrencySelect);
	    	CurrencySelect.click();
	    	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SelectCurrencyOption);
	    	SelectCurrencyOption.click();
	    	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.UploadFile);
	    	UploadFile.sendKeys("/home/psslass11153/Downloads/krishna.jpg");
	    	WaitUtil.sleep(1000);
	    	CommentsExp.sendKeys("Please Approve as soon as possible.");
	    	//ReasonForExp.submit();
	    	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveBtn);
	    	saveBtn.click();
	    	WaitUtil.sleep(1000);
	    	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Toast);
	    	String Msg=Toast.getText();
			System.out.println("Raise Expense Toast Status->"+Msg);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ToastCancelBtn);
			ToastCancelBtn.click();
			Thread.sleep(1500);
	    	if(Reqstatus.equals("submit"))
	    	{
	    		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ExpSubmitBtn);;
	        	ExpSubmitBtn.click();
	        	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Toast);
		    	String Msg1=Toast.getText();
				System.out.println("Raise Expense Toast Status->"+Msg1);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ToastCancelBtn);
				ToastCancelBtn.click();
				Thread.sleep(1500);
	    	}
	    	Thread.sleep(3000);
	    }

}
