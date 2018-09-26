package Framework.TestingFramework.methods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.github.javafaker.Faker;

import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;

public class LemoBatchHome 
{
	CommonMethods CMT;
	RequisitionHome Req_Home;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String pageName = "Batches";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[@ui-sref='main.assessment.home']";
	String pageMenuXpath = "//li[@ng-if='userModules.Assessment']";
	
	public LemoBatchHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
		Req_Home = new RequisitionHome(driver);
	}
	
//  ******************common xpath to select option 1 and 2 in any select/multiSelect drop down**************
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='picker.ok()']")
	})
	public WebElement OptionOKBtn;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='picker.today()']")
	})
	public WebElement OptionToday;
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
		@FindBy(xpath = "//button[@ng-click='updateBatch({id:null},$event)']")
	})
	public WebElement createBatchBtn;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='editInfo.requisitionid']")
	})
	public WebElement reqSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='searchRequistion']")
	})
	public WebElement reqSearch;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='editInfo.institute']")
	})
	public WebElement instituteSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.batchname']")
	})
	public WebElement batchName;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.secretcode']")
	})
	public WebElement scretCode;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.totalquestion']")
	})
	public WebElement totalQuestion;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.passon']")
	})
	public WebElement cutOffMarks;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='editInfo.aieeerank']")
	})
	public WebElement jeeRankSelect;
	@FindAll({
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
	@FindAll({
		@FindBy(xpath = "//form[@name='savebatchForm']//button[@submit-button='Save']")
	})
	public WebElement saveBtn;
	@FindAll({
		@FindBy(xpath = "//h2[text()='Add Batch']/..//button")
	})
	public WebElement cancelBtn;
//	**********************************Test form Xpaths****************************
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='OpenPopupWindow()']"),
		@FindBy(xpath = "//button[contains(text(),'OK, Lets Proceed')]")
	})
	public WebElement proceedBtn;
	@FindAll({
		@FindBy(xpath = "//div[@id='randblock']//span")
	})
	public WebElement passCode;
	@FindAll({
		@FindBy(xpath = "//form[@name='candidatebatchForm']//input[@ng-model='assessment.passcode']"),
		@FindBy(xpath = "//form[@name='candidatebatchForm']//input[@name='passcode']")
	})
	public WebElement passCodeInput;
	@FindAll({
		@FindBy(xpath = "//form[@name='candidatebatchForm']//button[@id='batchbutton']"),
		@FindBy(xpath = "//form[@name='candidatebatchForm']//button")
	})
	public WebElement submitBtn;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='assessmentDetail.candidatename']"),
		@FindBy(xpath = "//input[@name='candidatename']")
	})
	public WebElement candidateName;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='assessmentDetail.emailid']"),
		@FindBy(xpath = "//input[@name='emailid']")
	})
	public WebElement emailId;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='assessmentDetail.contactnumber']"),
		@FindBy(xpath = "//input[@name='contactnumber']")
	})
	public WebElement contactNo;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='assessmentDetail.college']"),
		@FindBy(xpath = "//md-select[@name='college']")
	})
	public WebElement collegeSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='assessmentDetail.tenthscore']"),
		@FindBy(xpath = "//input[@name='tenthmarks']")
	})
	public WebElement tenthPercentage;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='assessmentDetail.twelthscore']"),
		@FindBy(xpath = "//input[@name='twelthscore']")
	})
	public WebElement twelthPercentage;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='assessmentDetail.graduationscore']"),
		@FindBy(xpath = "//input[@name='graduationscore']")
	})
	public WebElement graduationPercentage;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='assessmentDetail.course']"),
		@FindBy(xpath = "//md-select[@name='course']")
	})
	public WebElement courseSelect;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='assessmentDetail.branch']"),
		@FindBy(xpath = "//input[@name='branch']")
	})
	public WebElement branchSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='assessmentDetail.jeerank']"),
		@FindBy(xpath = "//input[@name='jeerank']")
	})
	public WebElement JEErank;
	@FindAll({
		@FindBy(xpath = "//button[@ng-model='assessmentDetail.cvuploads']"),
		@FindBy(xpath = "//button[@name='cvuploads']")
	})
	public WebElement cvUploadBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='candidateDetailForm']//button[@type='submit']")
	})
	public WebElement startTestBtn;
	
	
	public boolean CreateBatch(String totalQuestn,String cutOff,String Duration,String expiryafter, ArrayList<String> batchDetails) throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		boolean flag = false;
		Random random = new Random();
		int num =  100+random.nextInt(900);
		String BatchName = "Test@"+num;
		String RMS_UserEmail = "support@polestarllp.com1";
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.createBatchBtn);
			createBatchBtn.click();
			WaitUtil.sleep(2000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.reqSelect);
			reqSelect.click();
			WaitUtil.sleep(2000);
			if(driver.findElements(By.xpath("//div[contains(@class,' md-active')]//md-option[1]")).size()==0)
			{
				System.out.println("raising requisition");
				driver.navigate().refresh();
				WaitUtil.sleep(10000);
				if(Req_Home.actionOnRequisition("HR Admin","Approve",batchDetails,"false"))
				{
					System.out.println("Requisition created successfully for lemonade.");
					flag = CreateBatch(totalQuestn, cutOff, Duration, expiryafter, batchDetails);
					if(flag) 
					{ return true; }
					else { return false; }
					
				}else
					{
						System.out.println("No requisition found to create batch.Some error occourred in Raising Requisition.");
					}
			}
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
			Option1.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.instituteSelect);
			instituteSelect.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
			Option1.click();
			Option1.sendKeys(Keys.TAB);
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.batchName);
			batchName.click();
			batchName.clear();
			batchName.sendKeys(BatchName);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.scretCode);
			scretCode.click();
			scretCode.clear();
			scretCode.sendKeys(BatchName);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.totalQuestion);
			totalQuestion.click();
			totalQuestion.clear();
			totalQuestion.sendKeys(totalQuestn);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.cutOffMarks);
			cutOffMarks.click();
			cutOffMarks.clear();
			cutOffMarks.sendKeys(cutOff);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.jeeRankSelect);
			jeeRankSelect.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
			Option1.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.resumeSourceSelect);
			resumeSourceSelect.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
			Option1.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.testDuration);
			testDuration.click();
			testDuration.clear();
			testDuration.sendKeys(Duration);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.startOn);
			startOn.click();
			WaitUtil.sleep(1500);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OptionOKBtn);
			OptionOKBtn.click();
			WaitUtil.sleep(1000);
			OptionOKBtn.click();
			WaitUtil.sleep(1000);
			OptionOKBtn.click();
			WaitUtil.sleep(1500);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.expireAfter);
			expireAfter.click();
			expireAfter.clear();
			expireAfter.sendKeys(expiryafter);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.instructionSelect);
			instructionSelect.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
			Option1.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.negativeMarking);
			negativeMarking.click();
			negativeMarking.clear();
			negativeMarking.sendKeys("-0.5");
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveBtn);
			saveBtn.click();
			String Msg = CMT.getToastMsg();
			System.out.println("Batch creation Msg -"+Msg);
			if(Msg.equalsIgnoreCase("Batch Details saved successfully"))
			{
				batchDetails.add(BatchName);
				return true; 
			}
		}
		return false;		
	}

	public boolean ConductTestFor(String BatchName,String Type) throws InterruptedException
	{
		String RMS_UserEmail = "support@polestarllp.com1";
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			List<WebElement> allRows = driver.findElements(By.xpath("//tr[@md-select-id='title']"));
			int rowCount = allRows.size();
			System.out.println("Total batches found = "+rowCount);
			for(WebElement rowElement:allRows)
			{
				String batchName = null; 
				String status = null;
				WebElement batchField = rowElement.findElement(By.xpath("td[2]"));
				WebElement statusField = rowElement.findElement(By.xpath("td[8]"));
				status = statusField.getText();
				if(batchField.getAttribute("aria-label") == null)
				{
					batchName = batchField.getText();				
				}else
					{
						batchName = batchField.getAttribute("aria-label");
					}
				System.out.println("requisired Batch name found---"+batchName);
				if(batchName.equalsIgnoreCase(BatchName) && status.equalsIgnoreCase(Type))
				{
					WebElement testLink = rowElement.findElement(By.xpath("td[4]//a"));
					testLink.click();
					WaitUtil.sleep(10000);
					CMT.switchTab();
					return StartTest(Type,BatchName);
				}else
					{
						System.out.println("No active batch found with the this "+BatchName+"name.");
					}
		    }
		}else
			{
				System.out.println("Some error occourred in GOTO Lemonade Batch page.");
			}
		return false;
	}

	public boolean StartTest(String Type, String Scretcode)
	{
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.proceedBtn);
		proceedBtn.click();
		WaitUtil.sleep(10000);
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(handles.get(2));
		WaitUtil.sleep(2000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.passCode);
		String pass = passCode.getText();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.passCodeInput);
		passCodeInput.click();
		passCodeInput.clear();
		passCodeInput.sendKeys(pass);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.submitBtn);
		submitBtn.click();
		WaitUtil.sleep(1000);
		if(Type.equalsIgnoreCase("Expired"))
		{
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();
			WaitUtil.sleep(1000);
			if(alertMsg.equalsIgnoreCase("Passcode is invalid. Please contact support team."))
			{
				passCodeInput.click();
				passCodeInput.clear();
				passCodeInput.sendKeys(Scretcode);
				WaitUtil.sleep(1000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.submitBtn);
				submitBtn.click();
				WaitUtil.sleep(1000);
			}
		}
		WaitUtil.sleep(4000);
		if(driver.findElements(By.xpath("//input[@ng-model='assessmentDetail.candidatename']")).size() != 0)
		{
			if(Type.equalsIgnoreCase("Expired"))
			{
				returnToMainWindow();
			}
			return true;
		}
		return false;
	}
	
	public String fillCandidateDetails() throws FindFailed
	{
		Faker faker = new Faker();
		String CandidateName = faker.name().firstName();
		String CandidateEmail = CandidateName+"@zz.com";
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.candidateName);
		candidateName.click();
		candidateName.clear();
		candidateName.sendKeys(CandidateName);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.emailId);
		emailId.click();
		emailId.clear();
		emailId.sendKeys(CandidateEmail);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.contactNo);
		contactNo.click();
		contactNo.clear();
		contactNo.sendKeys(CMT.getRandomNum(10));
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.collegeSelect);
		collegeSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.tenthPercentage);
		tenthPercentage.click();
		tenthPercentage.clear();
		tenthPercentage.sendKeys(CMT.getRandomNum(2));
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.twelthPercentage);
		twelthPercentage.click();
		twelthPercentage.clear();
		twelthPercentage.sendKeys(CMT.getRandomNum(2));
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.graduationPercentage);
		graduationPercentage.click();
		graduationPercentage.clear();
		graduationPercentage.sendKeys(CMT.getRandomNum(2));
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.courseSelect);
		courseSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(500);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.branchSelect);
		branchSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(500);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.JEErank);
		JEErank.click();
		JEErank.clear();
		JEErank.sendKeys(CMT.getRandomNum(2));
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.cvUploadBtn);
		cvUploadBtn.click();
		WaitUtil.sleep(2000);
		String resumepath = "/home/psslass11153/Pictures/sikuli/Bank Letter.docx";
		if(CMT.doUploadSikuli(resumepath))
		{
			startTestBtn.click();
			WaitUtil.sleep(5000);
			return CandidateName;
		}
		return null;
	}
	
	public boolean completeTestSikuli(String n, String submissionType) throws FindFailed
	{
		int size = Integer.parseInt(n);
		System.out.println("completeTestSikuli is called.");
		String imagePath = "/home/psslass11153/Pictures/sikuli/";
	    Screen sikuliObj = new Screen();
	    Pattern nextBtn = new Pattern(imagePath + "nextBtn.png");
	    Pattern option1 = new Pattern(imagePath + "option1.png");
	    Pattern option2 = new Pattern(imagePath + "option2.png");
	    Pattern option3 = new Pattern(imagePath + "option3.png");
	    Pattern submitTest = new Pattern(imagePath + "submitTest.png");
	    Pattern yesBtn = new Pattern(imagePath + "yesBtn.png");
	    Pattern TestSubmitted = new Pattern(imagePath + "thankyou.png");
	    for(int i=0;i<size-1;i++)
	    {
	    	int min=1,max=3;
	    	int x = (int)(Math.random()*((max-min)+1))+min;
	    	System.out.println("Random Integer number = "+x);
	    	if(x==1)
	    	{
	    		sikuliObj.click(option1);
		 	    WaitUtil.sleep(2000);
		 	    sikuliObj.click(nextBtn);
	    	}
	    	else if(x==2)
	    	{
	    		sikuliObj.click(option2);
		 	    WaitUtil.sleep(1000);
		 	    sikuliObj.click(nextBtn);
	    	}else
	    	{
	    		sikuliObj.click(option3);
		 	    WaitUtil.sleep(1000);
		 	    sikuliObj.click(nextBtn);
	    	}
	    }
	    sikuliObj.click(option2);
	    WaitUtil.sleep(2000);
	    if(submissionType.equalsIgnoreCase("Submit"))
	    {
	    	if(sikuliObj.exists(submitTest) != null)
	        {
	        	System.out.println("Test Completed submit button is visible.");
	        	sikuliObj.click(submitTest);
	        	WaitUtil.sleep(1000);
	        	sikuliObj.click(yesBtn);
	        	WaitUtil.sleep(2000);
	        }else
		        {
		        	System.out.println("There is some error.Sikuli is unable to find submit Button.");
		        	return false;
		        }
	    }else
		    {
		    	WaitUtil.sleep(200000);
		    }
	    if(sikuliObj.exists(TestSubmitted) != null)
	    {
	    	System.out.println("Test submitted successfully.");
	    	returnToMainWindow();
	    	/*ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(handles.get(0));
			
	    	String originalHandle = driver.getWindowHandle();
	        for(String handle : driver.getWindowHandles()) {
	            if (!handle.equals(originalHandle)) {
	                driver.switchTo().window(handle);
	                driver.close();
	            }
	        }
	        driver.switchTo().window(originalHandle);*/
	    	return true;
	    }
        return false;
	}
	
	public boolean startTestFor(String batchStatus, ArrayList<String> batchDetails) throws InterruptedException
	{
		String RMS_UserEmail = "support@polestarllp.com1";
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			List<WebElement> allRows = driver.findElements(By.xpath("//tr[@md-select-id='title']"));
			int rowCount = allRows.size();
			System.out.println("Total batches found = "+rowCount);
			for(WebElement rowElement:allRows)
			{
				String batchName = null;
				String status = null;
				WebElement batchField = rowElement.findElement(By.xpath("td[2]"));
				WebElement statusField = rowElement.findElement(By.xpath("td[8]"));
				status = statusField.getText();
				System.out.println("required Batch name found---"+batchName);
				if(status.equalsIgnoreCase(batchStatus))
				{
					if(batchField.getAttribute("aria-label") == null)
					{
						batchName = batchField.getText();				
					}else
						{
							batchName = batchField.getAttribute("aria-label");
						}
					batchDetails.add(batchName);
					WebElement testLink = rowElement.findElement(By.xpath("td[4]//a"));
					testLink.click();
					WaitUtil.sleep(10000);
					CMT.switchTab(); 
					return true;
				}
		    }
		}else
			{
				System.out.println("Some error occourred in GOTO Lemonade Batch page.");
			}
		return false;
	}
	
	public boolean actionONBatch(String batch, String action) throws InterruptedException
	{
		String RMS_UserEmail = "support@polestarllp.com1";
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			List<WebElement> allRows = driver.findElements(By.xpath("//tr[@md-select-id='title']"));
			int rowCount = allRows.size();
			for(WebElement rowElement:allRows)
			{
				String batchName = null; 
				String status = null;
				WebElement batchField = rowElement.findElement(By.xpath("td[2]"));
				WebElement statusField = rowElement.findElement(By.xpath("td[8]"));
				status = statusField.getText();
				if(batchField.getAttribute("aria-label") == null)
				{
					batchName = batchField.getText();				
				}else
					{
						batchName = batchField.getAttribute("aria-label");
					}
				System.out.println("required Batch name found---"+batchName);
				if(action.equalsIgnoreCase("Activate") && batchName.equalsIgnoreCase(batch))
				{
					if(status.equalsIgnoreCase("Active"))
					{
						return true;
					}else
					{
						WebElement menuBtn = rowElement.findElement(By.xpath("td[9]//button[@aria-label='menu']"));
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, menuBtn);
						menuBtn.click();
						WaitUtil.sleep(1500);
						WebElement editBtn = rowElement.findElement(By.xpath("td[9]//button[@ng-click='updateBatch(item,$event)']"));
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, editBtn);
						editBtn.click();
						WaitUtil.sleep(2000);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.reqSelect);
						reqSelect.click();
						WaitUtil.sleep(1000);
						if(driver.findElements(By.xpath("//div[contains(@class,' md-active')]//md-option[1]")).size()==0)
						{
							System.out.println("Can't Activate batch.There is no requisition available.");
							return false;
						}
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
						Option1.click();
						WaitUtil.sleep(1000);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.startOn);
						startOn.click();
						WaitUtil.sleep(1000);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OptionOKBtn);
						OptionOKBtn.click();
						WaitUtil.sleep(500);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OptionToday);
						OptionToday.click();
						WaitUtil.sleep(500);
						OptionOKBtn.click();
						WaitUtil.sleep(500);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OptionToday);
						OptionToday.click();
						WaitUtil.sleep(500);
						OptionOKBtn.click();
						WaitUtil.sleep(1000);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveBtn);
						saveBtn.click();
						String Msg = CMT.getToastMsg();
						System.out.println("Batch Update Msg -"+Msg);
						if(Msg.equalsIgnoreCase("Batch Details updated successfully"))
						{
							return true; 
						}
						
					}
				}
		    }
		}else
			{
				System.out.println("Some error occourred in GOTO Lemonade Batch page.");
			}
		return false;		
	}

	private void returnToMainWindow()
	{
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(handles.get(0));
    	String originalHandle = driver.getWindowHandle();
        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
	}
}
