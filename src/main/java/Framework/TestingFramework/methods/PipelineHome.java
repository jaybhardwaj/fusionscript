package Framework.TestingFramework.methods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;

public class PipelineHome 
{
	CommonMethods CMT;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String pageName = "Pipeline";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[@ui-sref='main.rms.onboard']";
	String pageMenuXpath = "//li[@ng-if='userModules.RMS']";

	public PipelineHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
	}
//  ******************common xpath to select option 1 and 2 in any select/multiSelect drop down**************
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'On Going')]")
	})
	public WebElement tabOnGoing;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'On Board')]")
	})
	public WebElement tabOnBoard;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'On BGV')]")
	})
	public WebElement tabOnBGV;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[1]")
	})
	public WebElement Option1;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[2]")
	})
	public WebElement Option2;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[3]")
	})
	public WebElement Option3;
//  ******************common xpath to select option 1 and 2 in any select/multiSelect drop down**************
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='candidateItem.email']")
	})
	public WebElement canEmail;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='candidateItem.joiningday']")
	})
	public WebElement joiningDate;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='candidateItem.contactperson']")
	})
	public WebElement contactPerson;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='pipelineFilter.status']")
	})
	public WebElement filterStatusSelect;
	 	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//input")
	})
	public WebElement filterStatusSearch;
 	@FindAll({
		@FindBy(xpath = "//form[@name='candidatelink']//button[@submit-button='Send Link']")
	})
	public WebElement sendLinkBtn;
 	@FindAll({
		@FindBy(xpath = "//button[@ng-click='okClicked()']"),
		@FindBy(xpath = "//button//span[text()='OK']")
	})
	public WebElement OkBtn;
 	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='joingData.offerstatus']")
	})
	public WebElement offerStatusSelect;
 	@FindAll({
		@FindBy(xpath = "//form[@name='joiningform']//input[@class ='md-datepicker-input md-input']")
	})
	public WebElement Calender;
	@FindAll({
		@FindBy(xpath = "//td[@class='md-calendar-date md-calendar-date-today']/span")
	})
	public WebElement todayDate;
	@FindAll({
		@FindBy(xpath = "//form[@name='joiningform']//button[@submit-button='Update']")
	})
	public WebElement updateBtn;
	/*@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='editInfo.resumesource']")
	})
	public WebElement resumeSourceSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.testduration']")
	})
	public WebElement testDuration;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.startedon']")
	})
	public WebElement startOn;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.expireafter']")
	})
	public WebElement expireAfter;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='editInfo.instructionid']")
	})
	public WebElement instructionSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.negativescore']")
	})
	public WebElement negativeMarking;
	*/
	
	public boolean actionOnPipelineTable(String candidate,String req,String canStatus,String action) throws InterruptedException
	{
		String RMS_UserEmail = "ajay@zz.com";
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			if(!tabOnGoing.getAttribute("aria-selected").equalsIgnoreCase("true"))
			{
				tabOnGoing.click();
				WaitUtil.sleep(5000);
			}
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.filterStatusSelect);
			filterStatusSelect.click();			
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.filterStatusSearch);
			filterStatusSearch.click();
			filterStatusSearch.clear();
			filterStatusSearch.sendKeys("Offered");
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
			Option1.click();
			WaitUtil.sleep(1000);
			Option1.sendKeys(Keys.TAB);
			WaitUtil.sleep(5000);
			List<WebElement> allRows = driver.findElements(By.xpath("//tr[@md-select-id='name']"));
			for(WebElement row : allRows)
			{
				String canName = null;
				String jobTitle = null;
				String state = null;
				String status = null;
				WebElement ReqactionBtn;
				WebElement canNameField = row.findElement(By.xpath("td[2]/a"));
				WebElement jobTitleField = row.findElement(By.xpath("td[3]"));
				WebElement stateField = row.findElement(By.xpath("td[8]"));
				WebElement statusField = row.findElement(By.xpath("td[9]//label"));
				canName = canNameField.getText();
				state = stateField.getText();
				status = statusField.getText();
				if(jobTitleField.getAttribute("aria-label") == null)
				{
					jobTitle = jobTitleField.getText();				
				}else
					{
						jobTitle = jobTitleField.getAttribute("aria-label");
					}
				if(canName.equalsIgnoreCase(candidate) && jobTitle.equalsIgnoreCase(req) && status.equalsIgnoreCase(canStatus))
				{
					row.findElement(By.xpath("td//button[@aria-label='menu']")).click();
					WaitUtil.sleep(1000);
					if(action.equalsIgnoreCase("sendLink"))
					{
						row.findElement(By.xpath("td//md-icon[contains(text(),'launch')]")).click();
						return sendLink();
					}
					else if(action.equalsIgnoreCase("sendOffer"))
					{
						row.findElement(By.xpath("td//md-icon[contains(text(),'description')]")).click();
						return sendOffer();
					}
					else if(action.equalsIgnoreCase("changeStatus"))
					{
						row.findElement(By.xpath("td//md-icon[contains(text(),'date_range')]")).click();
						return changeStatus();
					}
				}
				
			}

		}
		return false;
	}
	
	private boolean sendLink()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.canEmail);
		canEmail.click();
		canEmail.clear();
		canEmail.sendKeys("jaybhardwaj2991@gmail.com");
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.joiningDate);
		joiningDate.click();
		joiningDate.clear();
		joiningDate.sendKeys(dtf.format(now));
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.contactPerson);
		contactPerson.click();
		contactPerson.clear();
		contactPerson.sendKeys("Jinal Doshi");
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.sendLinkBtn);
		sendLinkBtn.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OkBtn);
		OkBtn.click();
		String Msg = CMT.getToastMsg();
		if(Msg.equalsIgnoreCase("Documents upload link sent successfully!"))
		{
			WaitUtil.sleep(2500);
			return true;
		}
		return false;
	}
	private boolean sendOffer()
	{
		return true;
	}
	private boolean changeStatus()
	{
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.offerStatusSelect);
		offerStatusSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Calender);
		Calender.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.todayDate);
		todayDate.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.todayDate);
		updateBtn.click();
		String Msg = CMT.getToastMsg();
		if(Msg.equalsIgnoreCase("Joining Status updated successfully"))
		{
			return true;
		}
		return false;
	}
}
