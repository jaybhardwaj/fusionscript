package Framework.TestingFramework.methods;

import java.sql.SQLException;

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

public class SchedulingHome 
{
	CommonMethods CMT ;
	RequisitionHome Req_Home;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String RMS_UserEmail=null;
	String RMS_UserFullName=null;
	String pageName = "Schedule Interview";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[contains(text(),'Scheduling')]";
	String pageMenuXpath = "//a[contains(text(),'RMS')]";
	
	public SchedulingHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
		Req_Home = new RequisitionHome(driver);
	}
	
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[1]")
	})
	public WebElement Option1;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[2]")
	})
	public WebElement Option2;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-change='changeRequisition(Requisition);']"),
		@FindBy(xpath = "//md-select[@ng-model='editInfo.requisitionid']"),
		@FindBy(xpath = "//md-select[@name='requisitionid']")
	})
	public WebElement requisitionSelect;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class, 'md-active')]//input")
	})
	public WebElement SelectSearch;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-change='stateChange();getScheduleCandidate()']"),
		@FindBy(xpath = "//md-select[@ng-model='editInfo.state']"),
		@FindBy(xpath = "//md-select[@name='state']")
	})
	public WebElement stageSelect;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-change='getCandidateOnSelect();']"),
		@FindBy(xpath = "//md-select[@ng-model='editInfo.candidateid']"),
		@FindBy(xpath = "//md-select[@name='candidateid']")
	})
	public WebElement candidateSelect;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='editInfo.interviewerid']"),
		@FindBy(xpath = "//md-select[@aria-label='Interviewer']"),
		@FindBy(xpath = "//md-select[@name='interviewerid']")
	})
	public WebElement interviewerSelect;
	@FindAll({
		@FindBy(xpath = "//input[@name='interviewdate']"),
		@FindBy(xpath = "//input[@ng-model='editInfo.interviewdate']")
	})
	public WebElement interviewDate;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='picker.ok()']"),
		@FindBy(xpath = "//button[contains(@class, 'dtp-btn-ok')]")
	})
	public WebElement interviewDateOkBtn;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='picker.cancel()']"),
		@FindBy(xpath = "//button[contains(@class, 'dtp-btn-cancel')]")
	})
	public WebElement interviewDateCancelBtn;
	@FindAll({
		@FindBy(xpath = "//div[@ng-model='html']"),
		@FindBy(xpath = "div[@ng-model='html']/p")
	})
	public WebElement Remarks;
	@FindAll({
		@FindBy(xpath = "//button[@submit-button='Schedule']"),
		@FindBy(xpath = "//button//span[text()='Schedule']")
	})
	public WebElement scheduleBtn;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='preScreeningAccepted()']"),
		@FindBy(xpath = "//button//span[text()='Schedule']")
	})
	public WebElement preScreeningAcceptBtn;
	@FindAll({
		@FindBy(xpath = "//div[@ id='cube-close']//md-icon[text()='clear']")
	})
	public WebElement cubeClose;
	@FindAll({
		@FindBy(xpath = "//span[@class='md-toast-text ng-binding flex']")
	})
	public WebElement toast;
	@FindAll({
		@FindBy(xpath = "//div[@class='md-toast-content']//button")
	})
	public WebElement toastCancelBtn;

	public boolean scheduler(String jobTitle, String stage, String Candidate, String Interviewer, String PreScreenStatus) throws InterruptedException, ClassNotFoundException, SQLException 
	{
		if(CMT.GotoRequiredPage("ajay@zz.com",pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			System.out.println("Reached to scheduling page");
			return scheduleInterview(jobTitle,stage,Candidate,Interviewer,PreScreenStatus);		
		}else
			{
				System.out.println("Some error occoured in GO TO Scheduling Page");
			}
		return false;
	}
	
	public boolean scheduleInterview(String Requisition, String desiredStage, String candidate, String interviewer, String PreScreenStatus)
	{
		String Interviewer = null;
		System.out.println("scheduleInterview is called");
		WaitUtil.sleep(5000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.requisitionSelect);
		requisitionSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SelectSearch);
		SelectSearch.click();
		SelectSearch.clear();
		SelectSearch.sendKeys(Requisition);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.stageSelect);
		stageSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SelectSearch);
		SelectSearch.click();
		SelectSearch.clear();
		SelectSearch.sendKeys(desiredStage);
		WaitUtil.sleep(1000);
		if(desiredStage.equalsIgnoreCase("Screening") && PreScreenStatus.equalsIgnoreCase("true"))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option2);
			Option2.click();
		}else
			{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
			Option1.click();
			}
		WaitUtil.sleep(2000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.candidateSelect);
		candidateSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SelectSearch);
		SelectSearch.click();
		SelectSearch.clear();
		SelectSearch.sendKeys(candidate);
		WaitUtil.sleep(1000);
		Option1.click();
		Option1.sendKeys(Keys.TAB);
		WaitUtil.sleep(1000);
		if(desiredStage.equalsIgnoreCase("Pre Screening"))
		{
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.preScreeningAcceptBtn);
			preScreeningAcceptBtn.click();
			
		}else
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.interviewerSelect);
				interviewerSelect.click();
				WaitUtil.sleep(1000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SelectSearch);
				SelectSearch.click();
				SelectSearch.clear();
				if(interviewer == null)
				{
					Interviewer = "Ajay sharma";
				}else
				{
					Interviewer = interviewer;
				}
				SelectSearch.sendKeys(Interviewer);
				WaitUtil.sleep(1000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
				Option1.click();
				Option1.sendKeys(Keys.TAB);
				WaitUtil.sleep(2000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Remarks);
				Remarks.click();
				Remarks.clear();
				Remarks.sendKeys("Please process on high priority basis.");
				WaitUtil.sleep(2000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.scheduleBtn);
				scheduleBtn.click();
			}
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.toastCancelBtn);
		toastCancelBtn.click();
		WaitUtil.sleep(5000);
		return true;
	}
}
