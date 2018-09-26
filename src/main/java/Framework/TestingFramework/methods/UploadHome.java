package Framework.TestingFramework.methods;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.DatabaseUtil;
import Framework.TestingFramework.utils.ExcelRead;
import Framework.TestingFramework.utils.WaitUtil;

public class UploadHome 
{
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Success')]")
	})
	public WebElement successTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Failure')]")
	})
	public WebElement failureTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Employee Referral')]")
	})
	public WebElement EmployeeReferaltab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Vendor')]")
	})
	public WebElement vendorTab;
	@FindAll({
		@FindBy(xpath = "//input[@class='upload']")
	})
	public WebElement uploadBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='myForm']")
	})
	public WebElement uploadForm;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='parsingSource.resumeSource']")
	})
	public WebElement resumeSourceSelect;
	@FindAll({//div[contains(@class,' md-active')]//md-option[1]
		@FindBy(xpath = "//md-select[@ng-model='parsingSource.employeeName']")
	})
	public WebElement employeeSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='searchEmployee']"),
		@FindBy(xpath = "//input[@placeholder='Search for a Employee']")
	})
	public WebElement employeeSelectSearch;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='searchSource']")
	})
	public WebElement resumeSourceSearch;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[1]")
	})
	public WebElement Option1;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[3]")
	})
	public WebElement Option3;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='item.upload()']")
	})
	public WebElement uploadResume;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='item.remove()']")
	})
	public WebElement removeResume;
	@FindAll({
		@FindBy(xpath = "//span[@class='md-toast-text ng-binding flex']")
	})
	public WebElement toast;
	@FindAll({
		@FindBy(xpath = "//div[@class='md-toast-content']//button")
	})
	public WebElement toastCancelBtn;
	@FindAll({
		@FindBy(xpath = "//table[@ng-model='selected_RMS_3']//md-checkbox[@aria-label='Select All']")
	})
	public WebElement selectAllReffered;
	@FindAll({
		@FindBy(xpath = "//table[@ng-model='selected_RMS_1']//md-checkbox[@aria-label='Select All']")
	})
	public WebElement selectAll;
	@FindAll({
		@FindBy(xpath = "//table[@ng-model='selected_RMS_4']//md-checkbox[@aria-label='Select All']")
	})
	public WebElement selectAllVendor;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='addCandidateInfo(successData,1,$event)']"),
		@FindBy(xpath = "//button//md-icon[contains(text(),'verified_user')]")
	})
	public WebElement addResumeBtn;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='removeCandidateInfo(successData,1,$event)']"),
		@FindBy(xpath = "//button//md-icon[contains(text(),'delete')]")
	})
	public WebElement deleteResumeBtn;
	
	CommonMethods CMT ;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String RMS_UserEmail=null;
	String RMS_UserFullName=null;
	String pageName = "Resume Upload";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[@ui-sref='main.rms.upload']";
	String pageMenuXpath = "//li[@ng-if='userModules.RMS']";
	
	public UploadHome(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
	}
	
	public boolean uploadCandidate(int num, String source) throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		String uploaderUser = null;
		System.out.println("uploadCandidate is called");		
		if(source.equalsIgnoreCase("vendor"))
		{
//			 String vendor = get_vendor(); To upload from vendor
			 uploaderUser = "vendor";
		}else
		{
//			in Case of self and Refferals upload by HR Admin
			uploaderUser = "ajay@zz.com";//HR Admin
		}
		
			System.out.println("Time to call upload function");
			if(CMT.GotoRequiredPage(uploaderUser,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
			{
				return getResumes_and_Upload(num,source);
			}else
			   { 
				 System.out.println("Some error occured in got to upload page.");
				 return false;
			   }
    }		
	
	public boolean getResumes_and_Upload(int resumeToAdd, String source) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		String[][] temp = CMT.get_ExcelData("LoginDetails.xlsx","ResumeList");
		int availableResume = temp.length;
		if(availableResume<resumeToAdd)
		{
			System.out.println("Required num of resumes to upload are not available.Plz put more resume in Desktop folder");
			return false;
		}else
			{
				String query="SELECT email,filename,filepath FROM fusion.rmscandidate where isactive=1 and isblocked=0;";
				int resumeInDB = CMT.getRecordSizeInDB(query);
				System.out.println("Total resume in DB before upload = "+resumeInDB);
			    int count = 0;
				for(int i=resumeInDB;i<availableResume;i++)
				{
					if(doUpload(temp[i][0],source))
					{
						count++;
						System.out.println("Total uploaded till now = "+count);
					}
					if(count==resumeToAdd)
					{
						if(acceptResume(source))
						{
							return verifyUpload(resumeInDB,count);
						}
					}
				}
				System.out.println("total resumes added are = "+count);
			}
		System.out.println("few resumes already existed in the system.plz put more resumes in folder");
		return false;
	}
	
	private boolean doUpload(String temp, String source) throws InterruptedException
	{
		WaitUtil.sleep(2000);
		String resumePath = "/home/psslass11153/Desktop/RMS_Resume/"+temp;
		uploadBtn.clear();
		uploadBtn.sendKeys(resumePath);
		WaitUtil.sleep(1000);
		uploadBtn.submit();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.resumeSourceSelect);
		resumeSourceSelect.click();
		WaitUtil.sleep(1000);
		if(source.equalsIgnoreCase("Referral"))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
				Option1.click();
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.employeeSelect);
				employeeSelect.click();
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.employeeSelectSearch);
				employeeSelectSearch.click();
				employeeSelectSearch.clear();
				employeeSelectSearch.sendKeys("ajay@zz.com");
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
				Option1.click();
			}
		else if(source.equalsIgnoreCase("vendor"))
			{
				//implement vendor side
			}
		else
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option3);
				Option3.click();
			}	
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.uploadResume);
		uploadResume.click();
	    WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.toast);
		String Msg = toast.getText();
		System.out.println(Msg);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.toastCancelBtn);
		toastCancelBtn.click();
		if(Msg.equalsIgnoreCase("1 record(s) uploaded."))
		{
			return true;
		}else
			{
				return false;
			}		
	}
	
	private boolean verifyUpload(int beforeUpload, int Uploaded) throws ClassNotFoundException, SQLException
	{
		String query="SELECT email,filename,filepath FROM fusion.rmscandidate where isactive=1 and isblocked=0;";
		int resumeAfterUpload = CMT.getRecordSizeInDB(query);
		System.out.println("Total resume in DB After upload = "+resumeAfterUpload);
		int total = beforeUpload+Uploaded;
		System.out.println("Total resume should be = "+total);
		if(resumeAfterUpload == total)
		{
			return true;
		}
		return false;
	}

	public boolean acceptResume(String source) throws InterruptedException
	{
		if(CMT.GotoRequiredPage("ajay@zz.com",pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			if(source.equalsIgnoreCase("Referral"))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.EmployeeReferaltab);
				EmployeeReferaltab.click();
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.selectAllReffered);
				selectAllReffered.click();
				WaitUtil.sleep(2000);
			}
			else if(source.equalsIgnoreCase("vendor"))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.vendorTab);
				vendorTab.click();
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.selectAllVendor);
				selectAllVendor.click();
				WaitUtil.sleep(2000);
			}
			else
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.selectAll);
				selectAll.click();
				WaitUtil.sleep(2000);
			}
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.addResumeBtn);
			addResumeBtn.click();
			String Msg = CMT.getToastMsg();
			System.out.println("Resume Accept Msg-->> "+Msg);
			return true;
		}else
			{
				System.out.println("Some error occoured in goto Upload page.");
				return false;
			}		
	}
}
