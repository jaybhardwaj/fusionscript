package Framework.TestingFramework.methods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;

public class InterviewCalendarHome 
{
	CommonMethods CMT ;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String RMS_UserEmail=null;
	String RMS_UserFullName=null;
	String pageName = "Interview Calendar";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[contains(text(),'Interview Calendar')]";
	String pageMenuXpath = "//a[contains(text(),'RMS')]";
	
	public InterviewCalendarHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
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
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[3]")
	})
	public WebElement Option3;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[4]")
	})
	public WebElement Option4;
	@FindAll({
		@FindBy(xpath = "//span[@class='md-toast-text ng-binding flex']")
	})
	public WebElement toast;
	@FindAll({
		@FindBy(xpath = "//div[@class='md-toast-content']//button")
	})
	public WebElement toastCancelBtn;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='okClicked()']"),
		@FindBy(xpath = "//button//span[text()='OK']")
	})
	public WebElement OkBtn;
	@FindAll({
		@FindBy(xpath = "//button[contains(@class,'fc-basicDay-button')]")
	})
	public WebElement todaySectionBtn;
	@FindAll({
		@FindBy(xpath = "//div[@class='fc-content-skeleton']//tr//a[1]")
	})
	public WebElement todayInterviewSectionlink;
	@FindAll({
		@FindBy(xpath = "td//md-icon[contains(text(),'menu')]")
	})
	public WebElement menuBtn;
	@FindAll({
		@FindBy(xpath = "//div//a[@href='/interview/feedback']")
	})
	public WebElement feedbackBtn;
	@FindAll({
		@FindBy(xpath = "//input[@name='totalScore']"),
		@FindBy(xpath = "//input[@ng-model='generalFeedbackData.totalScore']")
	})
	public WebElement inputMarks;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='generalFeedbackData.finalStatus']")
	})
	public WebElement statusSelectTechnical;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='finalHRStatus']"),
		@FindBy(xpath = "//md-select[@ng-change='showJoiningDate()']")
	})
	public WebElement statusSelectHR;
	@FindAll({
		@FindBy(xpath = "//input[@name='interviewduration']"),
		@FindBy(xpath = "//input[@ng-model='generalFeedbackData.interviewduration']")
	})
	public WebElement interviewDurationTechnical;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='interviewdurationhr']"),
		@FindBy(xpath = "//input[@name='interviewdurationhr']")
	})
	public WebElement interviewDurationHR;
	@FindAll({
		@FindBy(xpath = "//div[@ng-model='generalFeedbackData.generalfeedback']//div[@ng-model='html']")
	})
	public WebElement generalRemarks;
	@FindAll({
		@FindBy(xpath = "//div[@ng-model='hrRemark']//div[@ng-model='html']")
	})
	public WebElement HRRemarks;
	@FindAll({
		@FindBy(xpath = "//button[@ng-disabled='saveFeedbackLoading']")
	})
	public WebElement saveTechBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='hrFeedbackForm']//button[@submit-button='Save']")
	})
	public WebElement saveHRBtn;
	@FindAll({
		@FindBy(xpath = "//input[@class ='md-datepicker-input']")
	})
	public WebElement Calender;
	@FindAll({
		@FindBy(xpath = "//td[@class='md-calendar-date md-calendar-date-today md-focus']/span"),
		@FindBy(xpath = "//div[@class='md-virtual-repeat-offsetter']//span[text()='28']")
	})
	public WebElement date;
	
	public boolean approveStage(String Requisition, String Stage, String Candidate, String Interviewer,String action) throws InterruptedException
	{
		if(CMT.GotoRequiredPage(Interviewer,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			System.out.println("Approve stage method is called.Reached to Interviewer's Calendar page.");
			WaitUtil.sleep(3000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.todaySectionBtn);
			todaySectionBtn.click();
			WaitUtil.sleep(2000);
			if(driver.findElements(By.xpath("//div[@class='fc-content-skeleton']//tr//a[1]")).size() != 0)
			{
				System.out.println("clicking to goto interview table.");
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, todayInterviewSectionlink);
				todayInterviewSectionlink.click();
			}else
				{
					System.out.println("There is no interview for today.");
					return false;
				}
			WaitUtil.sleep(3000);
			List<WebElement> allRows = driver.findElements(By.xpath("//md-tab-content[contains(@class,'md-active')]//tr[@md-select-id='name']"));
			int rowCount = allRows.size();
			System.out.println("Total rows in table-->>"+rowCount);
			for(WebElement rowElement:allRows)
			{
			      WebElement Name = rowElement.findElement(By.xpath("td[1]//div"));
			      WebElement Req = rowElement.findElement(By.xpath("td[5]//div"));
			      WebElement round = rowElement.findElement(By.xpath("td[6]"));
			      String CanName = Name.getText();
			      String jobTitle = Req.getText();
			      String stage = round.getText();
			      System.out.println("Candidate name= "+CanName);
			      System.out.println("jobTitle name= "+jobTitle);
			      System.out.println("stage name= "+stage);
			      if(CanName.equalsIgnoreCase(Candidate) && jobTitle.equalsIgnoreCase(Requisition) && stage.equalsIgnoreCase(Stage))
			      {
			    	  if(Stage.equalsIgnoreCase("Screening"))
			    	  {
			    		  System.out.println("Do approve Screening");
			    		  WebElement actionBtn;
			    		  if(action.equalsIgnoreCase("Approve"))
			    		  {			    			  
			    			  actionBtn = rowElement.findElement(By.xpath("td//md-icon[contains(text(),'done')]"));
			    		  }else
				    		  {
				    			  actionBtn = rowElement.findElement(By.xpath("td//md-icon[contains(text(),'close')]"));
				    		  }
			    		  WaitUtil.explicitWaitByVisibilityOfElement(driver, time, actionBtn);
			   			  actionBtn.click();
			   			  WaitUtil.sleep(1000);
			   			  WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OkBtn);
			        	  OkBtn.click();
			       		  String ToastMsg = CMT.getToastMsg();
			       		  System.out.println("Stage Screening toast Msg-->"+ToastMsg);
				    	  //Can write code to verify screening approved
				    	  return true;
			    	  }
			    	  else
			    	  {
			    		  WebElement actionTray =  rowElement.findElement(By.xpath("td//md-icon[contains(text(),'menu')]"));
			   			  WaitUtil.explicitWaitByVisibilityOfElement(driver, time, actionTray);
			   			  actionTray.click();
			   			  WaitUtil.sleep(1000);
			   			  WebElement feedbackBtn =  rowElement.findElement(By.xpath("td//a[@href='/interview/feedback']"));
			   			  WaitUtil.explicitWaitByVisibilityOfElement(driver, time, feedbackBtn);
			   			  feedbackBtn.click();
			   			  WaitUtil.sleep(5000);
			   			  completeInterview(Stage,action);
			   			  WaitUtil.sleep(10000);
			   			  return true;
			    	  }
			    }
			}	
		}else
			{
				System.out.println("Some errror occoured in go to Interview Calendar process");
				return false;
			}
		return true;
	}
	
	private void completeInterview(String stage, String action) 
	{
		if(stage.equalsIgnoreCase("Technical Interview"))
		{
 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.inputMarks);
			inputMarks.click();
			inputMarks.clear();
			inputMarks.sendKeys("5");
			WaitUtil.sleep(1000);
 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.statusSelectTechnical);
			statusSelectTechnical.click();
			WaitUtil.sleep(1000);
			if(action.equalsIgnoreCase("Approve"))
			{
	 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
				Option1.click();
			}else if(action.equalsIgnoreCase("On Hold"))
				{
		 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option2);
					Option2.click();
				}else
					{
			 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option3);
						Option3.click();
					}
 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.interviewDurationTechnical);
 			interviewDurationTechnical.click();
 			interviewDurationTechnical.clear();
 			interviewDurationTechnical.sendKeys("25");
 			WaitUtil.sleep(1000);
 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.generalRemarks);
 			generalRemarks.click();
 			generalRemarks.clear();
 			generalRemarks.sendKeys("Taking necessary action as asked by you.");
 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveTechBtn);
 			saveTechBtn.click();
		}else
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.HRRemarks);
				HRRemarks.click();
				HRRemarks.clear();
				HRRemarks.sendKeys("Taking necessary action as asked by you.");
				WaitUtil.sleep(1000);
 				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.statusSelectHR);
				statusSelectHR.click();
				WaitUtil.sleep(1000);
				if(action.equalsIgnoreCase("Approve"))
				{
		 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
					Option1.click();
				}else if(action.equalsIgnoreCase("Offered"))
					{
			 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option2);
						Option2.click();
					}else if(action.equalsIgnoreCase("On Hold"))
						{
				 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option3);
							Option3.click();
						}else
						{
							WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option4);
							Option4.click();
						}
				WaitUtil.sleep(1000);
	 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.interviewDurationHR);
				interviewDurationHR.click();
				interviewDurationHR.clear();
				interviewDurationHR.sendKeys("20");
				WaitUtil.sleep(1000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Calender);
				Calender.click();
				WaitUtil.sleep(1500);
	 			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.date);
				date.click();
				WaitUtil.sleep(1000);
 				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveHRBtn);
				saveHRBtn.click();
			}
 		String ToastMsg=CMT.getToastMsg();
 		System.out.println("Stage complete Interview toast Msg-->"+ToastMsg);
	}

}
