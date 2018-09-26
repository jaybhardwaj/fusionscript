package Framework.TestingFramework.methods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.github.javafaker.Faker;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.ExcelRead;
import Framework.TestingFramework.utils.WaitUtil;

public class ReferralHome 
{
	CommonMethods CMT;
	RequisitionHome Req_Home ;
	UploadHome Up_Home;
	ArrayList<String> ReqCan = new ArrayList<String>();
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String pageName = "My Referrals";
	String pageSectionXpath = "//a[@aria-label='HR']";
	//a[@ng-click="changeCurrentNavSection('hr')"] (This can also be used.)
	String PageXpath = "//a[@ui-sref='main.rms.referral']";
	String pageMenuXpath = "//li[@ng-if='userModules.RMS']";
	
	public ReferralHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
		Req_Home = new RequisitionHome(driver);
		Up_Home = new UploadHome(driver);
	}
	//md-tab-content[contains(@class, 'md-active')]//tr[@md-select-id="title"] tr of job opening
	//md-tab-content[contains(@class, 'md-active')]//table//tr[@md-select-id="name"] tr of my referrals
	//td//md-icon[contains(text(),'queue')] tag candidate from here button
	//td//md-icon[contains(text(),'beenhere')] view tagged candidates
	//tr[@md-select="dessert"] tr of tagged candidate modal table
	//div[@id='taggedcandidateListDialog']//input tagged candidate modal search
	
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'My Referral')]")
	})
	public WebElement myReferralTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Job Openings')]")
	})
	public WebElement jobOpeningTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'All Referral')]")
	})
	public WebElement allReferralTab;
	@FindAll({
		@FindBy(xpath = "//input[@uploader='uploader']")
	})
	public WebElement uploadBtn;
	@FindAll({
		@FindBy(xpath = "//tr[@md-select-id='title'][1]//td[1]//div")
	})
	public WebElement firstReq;
	@FindAll({
		@FindBy(xpath = "//tr[@md-select-id='title'][1]//td[4]//button[@ngf-select='fileAdded(item,$files, $event)']")
	})
	public WebElement firstTagBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='updateCandidateInfo']//button[@submit-button='Save']")
	})
	public WebElement saveBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='updateCandidateInfo']//button[contains(text(), 'Cancel')]")
	})
	public WebElement cancelBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='updateCandidateInfo']//md-select[@name='skills']"),
		@FindBy(xpath = "//form[@name='updateCandidateInfo']//md-select[@ng-model='editInfo.skills']")
	})
	public WebElement skillSelect;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[1]")
	})
	public WebElement Option1;
	@FindAll({
		@FindBy(xpath = "//form[@name='updateCandidateInfo']//input[@name='candidatename']")
	})
	public WebElement candidate;
	@FindAll({
		@FindBy(xpath = "//form[@name='updateCandidateInfo']//input[@name='email']")
	})
	public WebElement emailId;
	@FindAll({
		@FindBy(xpath = "//form[@name='updateCandidateInfo']//input[@ng-model='editInfo.phone']")
	})
	public WebElement contactNo;

	/*public boolean doRefer() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed 
	{
		if(CMT.GotoRequiredPage("ajay@zz.com",pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.jobOpeningTab);
			jobOpeningTab.click();
			WaitUtil.sleep(5000);
			if(driver.findElements(By.xpath("//md-tab-content[contains(@class, 'md-active')]//tr[@md-select-id='title']")).size() == 0)
			{
				System.out.println("No Requisition present.");
				Req_Home.actionOnRequisition("HR Admin","Assign",ReqCan);
			}
			String jobTitle = firstReq.getText();
			System.out.println("requisition to refer candidate is "+jobTitle);
			String[][] resumeList = CMT.get_ExcelData("LoginDetails.xlsx","ResumeList");
			String query="SELECT email,filename,filepath FROM fusion.rmscandidate where isactive=1 and isblocked=0;";
			int resumeInDB = CMT.getRecordSizeInDB(query);
			System.out.println("Total resume in DB before upload = "+resumeInDB);
			int rows = resumeList.length;
			String resumePath = null;
			for(int i=resumeInDB;i<rows;i++)
			{
				resumePath = "/home/psslass11153/Desktop/RMS_Resume/"+resumeList[i][0];
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.firstTagBtn);
				firstTagBtn.click();
				if(doUploadSikuli(resumePath))
				{
					System.out.println("Candidate uploaded successfully.");
					WaitUtil.sleep(10000);
					return Up_Home.acceptResume("Referral");
				}
			}	
		}else
			{
				System.out.println("Some error occoured in goto My Referrals Page.");
				return false;
			}
		return false;
	}*/
	
	public boolean referToRequisition(String Referer, String jobTitle, ArrayList<String> reqCan2) throws InterruptedException, ClassNotFoundException, SQLException, FindFailed, IOException
	{
		if(CMT.GotoRequiredPage("ajay@zz.com",pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.jobOpeningTab);
			jobOpeningTab.click();
			WaitUtil.sleep(3000);
			List<WebElement> allRows = driver.findElements(By.xpath("//md-tab-content[contains(@class,'md-active')]//tr[@md-select-id='title']"));
			int rowCount = allRows.size();
			System.out.println("Total job openings = "+rowCount);
			for(WebElement rowElement:allRows)
			{
				WebElement title = rowElement.findElement(By.xpath("td[1]//div"));
				String requisition = title.getText();
				if(requisition.equalsIgnoreCase(jobTitle))
				{
					String queryDB="SELECT email,filename,filepath FROM fusion.rmscandidate where isactive=1 and isblocked=0;";
					int resumeInDB = CMT.getRecordSizeInDB(queryDB);
					System.out.println("Total resume in DB at present = "+resumeInDB);
					String[][] resumeList = CMT.get_ExcelData("LoginDetails.xlsx","ResumeList");
					int rows = resumeList.length;
					String resumepath = null;
					for(int i=0;i<rows;i++)
					{
						System.out.println("Starting Resume upload to refer");
						String[] temp = resumeList[i][0].split("[.]");
						String canName = temp[0];
					    resumepath = "/home/psslass11153/Desktop/RMS_Resume/"+resumeList[i][0];
						WebElement tagBtn= rowElement.findElement(By.xpath("td[4]//md-icon[contains(text(),'queue')]"));
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, tagBtn);
						tagBtn.click();
						WaitUtil.sleep(2000);
						if(doUploadSikuli(resumepath,canName))
						{
							reqCan2.add(canName);
							System.out.println("Referred candidate name = "+canName);
							System.out.println("Candidate uploaded successfully.");
							WaitUtil.sleep(5000);
							return Up_Home.acceptResume("Referral");
						}
					}
				}
			}
		}else
			{
				System.out.println("Some error occoured in goto Referral page.");
			}
		return false;
	}

	public boolean verifyReferalStatus(String Referrer, String jobTitle, String candidate, String stage) throws InterruptedException
	{
		if(CMT.GotoRequiredPage(Referrer,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			List<WebElement> allRows = driver.findElements(By.xpath("//md-tab-content[contains(@class, 'md-active')]//table//tr[@md-select-id='name']"));
			int rowCount = allRows.size();
			System.out.println("Total referred candidates = "+rowCount);
			for(WebElement rowElement:allRows)
			{
				WebElement title = rowElement.findElement(By.xpath("td[5]"));
				WebElement candidateName= rowElement.findElement(By.xpath("td[1]//a"));
				WebElement Status= rowElement.findElement(By.xpath("td[6]//label"));
				String requisition = title.getText();
				String canName = candidateName.getText();
				String status = Status.getText();
				if(requisition.equalsIgnoreCase(jobTitle) && canName.equalsIgnoreCase(candidate))
				{
					System.out.println("Referred Candidate's Status w.r.t-"+jobTitle+"is --"+status);
					return true;
				}
			}
		}else
			{
				System.out.println("Some error occoured in goto Referral page.");
			}
		
		return false;
	}
	
	private boolean doUploadSikuli(String resumepath,String name) throws FindFailed
	{
		Faker fake = new Faker();
		System.out.println("doUploadSikuli is called with resumePath = "+resumepath);
		String imagePath = "/home/psslass11153/Pictures/sikuli/";
        Screen sikuliObj = new Screen();
        Pattern openSearchBtn = new Pattern(imagePath + "openSearch.png");
        Pattern fileInputTextBox = new Pattern(imagePath + "locationInput.png");
        Pattern openBtn = new Pattern(imagePath + "openFile.png");
        Pattern Skill1 = new Pattern(imagePath + "skill1.png");
        Pattern Skill2 = new Pattern(imagePath + "skill2.png");
        if(sikuliObj.exists(fileInputTextBox) != null)
        {
        	System.out.println("inside Sikuli IF-search box present");
        	sikuliObj.type(fileInputTextBox, resumepath);
        }else
	        {
	        	System.out.println("inside Sikuli ElSE-search box not present");
	        	sikuliObj.click(openSearchBtn);
	            WaitUtil.sleep(2000);
	            sikuliObj.type(fileInputTextBox, resumepath);
	        }
        WaitUtil.sleep(1000);
        sikuliObj.click(openBtn);
        WaitUtil.sleep(5000);
        if(driver.findElements(By.xpath("//div[@id='rmsEditTCandidateInfo']//h2")).size() != 0)
        {
        	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveBtn);
            saveBtn.click();
            WaitUtil.sleep(1000);
            if(sikuliObj.exists(Skill1) != null || sikuliObj.exists(Skill2) != null)
            {
            	System.out.println("skill is not present.");
            	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.skillSelect);
                skillSelect.click();
                WaitUtil.sleep(1000);
                WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
                Option1.click();
                Option1.sendKeys(Keys.TAB);
            }
            WaitUtil.sleep(2000);
        	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.candidate);
            candidate.click();
            candidate.clear();
            candidate.sendKeys(name);
            WaitUtil.sleep(1000);
            emailId.click();
            emailId.clear();
            emailId.sendKeys(fake.internet().emailAddress());
            WaitUtil.sleep(1000);
            contactNo.click();
            contactNo.clear();
            contactNo.sendKeys(CMT.getRandomNum(10));
            WaitUtil.sleep(1000);
            WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveBtn);
            saveBtn.click();
            String Msg=CMT.getToastMsg();
            System.out.println("Refereal Message-->"+Msg);
     	   	if(Msg.equalsIgnoreCase("Candidate already exist."))
     	   	{
     	        WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.cancelBtn);
     	   		cancelBtn.click();
     	   		WaitUtil.sleep(2000);
     	   		return false;
     	   	}
        }else
	        {
	        	System.out.println("Either resume is not parsed or candidateInfo edit window takes too long to open.");
	        	return false;
	        }
    	
        return true;
	}
}
