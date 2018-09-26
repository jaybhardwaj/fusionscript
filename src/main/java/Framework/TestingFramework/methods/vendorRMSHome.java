package Framework.TestingFramework.methods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;

import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;

public class vendorRMSHome 
{
	CommonMethods CMT;
	VendorHome Ven_Home;
	ArrayList<String> vendorCredential = new ArrayList<String>();
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String pageName = "Vendor";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[@ui-sref='main.rms.vendor']";
	String pageMenuXpath = "//li[@ng-if='userModules.RMS']";
	
	public vendorRMSHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
		Ven_Home = new VendorHome(driver);
	}
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Pending')]")
	})
	public WebElement pendingTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Accepted')]")
	})
	public WebElement acceptedTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Rejected')]")
	})
	public WebElement rejectedTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Re Submit')]")
	})
	public WebElement resubmitTab;
	@FindAll({
		@FindBy(xpath = "//md-tab-content[contains(@class,'md-active')]//button[text()='Approve']")
	})
	public WebElement approveBtn;
	@FindAll({
		@FindBy(xpath = "//md-tab-content[contains(@class,'md-active')]//button[text()='Reject']")
	})
	public WebElement rejectBtn;
	@FindAll({
		@FindBy(xpath = "//md-tab-content[contains(@class,'md-active')]//button//span[text()='Re Submit' ]")
	})
	public WebElement resubmitBtn;
	@FindAll({
		@FindBy(xpath = "//md-tab-content[contains(@class,'md-active')]//textarea[@name='levelonecomment']")
	})
	public WebElement HRUserComment;
	@FindAll({
		@FindBy(xpath = "//md-tab-content[contains(@class,'md-active')]//textarea[@name='leveltwocomment']")
	})
	public WebElement HRAdminComment;
	
	
//	incase of resubmit-Please Re Submit your Application. and reason
//	 Your Application is Rejected in case of rejected.
	//div[@ng-if='vendorDetail.vendorstatus']//span status at vendor side 
	//div//strong[text()='Reason: ']/..//div  reason at vendor side
	public boolean checkVendorFunctionality(String vendorID, String Password, String role, String Action) throws InterruptedException, FindFailed, ClassNotFoundException, IOException, SQLException
	{
		if(Ven_Home.CompleteRegistration(vendorID,Password,"submit"))
		{
				switchTab();
				WaitUtil.sleep(5000);
				switch(Action)
				{
					case "Approve":
						if(role.equalsIgnoreCase("HR Admin"))
						{
							if(doTableAction(vendorID,Action,"HR User"))
							{
								return doTableAction(vendorID,Action,role);
							}							
						}else
							{
								return doTableAction(vendorID,Action,role);
							}
						return false;
					case "Reject":
						if(role.equalsIgnoreCase("HR Admin"))
						{
							if(doTableAction(vendorID,"Approve","HR User"))
							{
								System.out.println("*****calling HR admin to reject vendor******");
								return doTableAction(vendorID,Action,role);
							}							
						}
						else
							{
								System.out.println("*****calling HR User to reject vendor******");
								return doTableAction(vendorID,Action,role);
							}
						return false;
					case "Resubmit":
							return doTableAction(vendorID,Action,"HR User");
					case "default":
						System.out.println("*****calling default function to reject vendor******");
						return doTableAction(vendorID,Action,role);
				}
		}else
			{
				System.out.println("Some Error Occuered in registering vendor.");
				return false;
			}		
		return false;		
	}
	
	private boolean doTableAction(String vendorID, String action,String role) throws InterruptedException
	{
		String RMS_UserEmail = null;
		WebElement commentArea;
		if(role.equalsIgnoreCase("HR User"))
		{
			RMS_UserEmail = "nancy@zz.com";
			commentArea = HRUserComment;
		}else
			{
				RMS_UserEmail = "ajay@zz.com";
				commentArea = HRAdminComment;
			}
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			List<WebElement> allRows = driver.findElements(By.xpath("//md-tab-content[contains(@class,'md-active')]//tr[@md-select-id='title']"));
			int rowCount = allRows.size();
			System.out.println("Total pending vendor applications = "+rowCount);
			for(WebElement rowElement:allRows)
			{
				String vendorEmail = null; 
				WebElement vendorIDField = rowElement.findElement(By.xpath("td[2]//div"));
				if(vendorIDField.getAttribute("aria-label") == null)
				{
					vendorEmail = vendorIDField.getText();				
				}else
					{
						vendorEmail = vendorIDField.getAttribute("aria-label");
					}
				System.out.println("pending vendor's email---"+vendorEmail);
				if(vendorEmail.equalsIgnoreCase(vendorID))
				{
					switch (action)
					{
						case "Approve":
	                       System.out.println("Switch Case-Approve");
	                       WebElement approve = rowElement.findElement(By.xpath("td//md-icon[contains(text(),'done')]"));
			   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, approve);
			        	   approve.click();
			        	   WaitUtil.sleep(3000);
			   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.HRUserComment);
			   			   commentArea.click();
			   			   commentArea.clear();
			   			   commentArea.sendKeys("This is to test functionality of - "+action+" for "+role);
			        	   WaitUtil.sleep(1000);
			   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.approveBtn);
			   			   approveBtn.click();				        	   
			       		   String ApproveMsg=CMT.getToastMsg();
			       		   System.out.println("Approving Message--> "+ApproveMsg);
			       		   if(ApproveMsg.equalsIgnoreCase("Vendor is Accepted successfully."))
			       		   {
			       			if(verifyStatusRMS( RMS_UserEmail, vendorID, action, role))
			       			   {
			       				   switchTab();
			       				   WaitUtil.sleep(2000);
			       				   boolean s = Ven_Home.verifyStatusVendor(vendorID, role, action);
			       				   System.out.println("Verify vendor return boolean ============ "+s);
			       				   return s;
			       			   }
			       		   }
						case "Reject":
	                       System.out.println("Switch Case-Reject");
	                       WebElement reject = rowElement.findElement(By.xpath("td//md-icon[contains(text(),'clear')]"));
			   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, reject);
			   			   reject.click();
			   			   WaitUtil.sleep(3000);
			   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.HRUserComment);
			   			   commentArea.click();
			   			   commentArea.clear();
			   			   commentArea.sendKeys("This is to test functionality of - "+action+" for "+role);
			        	   WaitUtil.sleep(1000);
			   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.rejectBtn);
			   			   rejectBtn.click();
			       		   String RejectMsg=CMT.getToastMsg();
			       		   System.out.println("Requisition-->"+RejectMsg);
			       		   if(RejectMsg.equalsIgnoreCase("Vendor is Rejected successfully."))
			       		   {
			       			   if(verifyStatusRMS( RMS_UserEmail, vendorID, action, role))
			       			   	{
			       				   switchTab();
			       				   WaitUtil.sleep(2000);
			       				   return Ven_Home.verifyStatusVendor(vendorID, role, action);
			       			   	}
			       		   }
						case "Resubmit":
	                       System.out.println("Switch Case-Resubmit");
	                       WebElement reSubmit = rowElement.findElement(By.xpath("td//md-icon[contains(text(),'save')]"));
			   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, reSubmit);
			   			   reSubmit.click();
			   			   WaitUtil.sleep(3000);
			   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.HRUserComment);
			   			   commentArea.click();
			   			   commentArea.clear();
			   			   commentArea.sendKeys("This is to test functionality of - "+action+" for "+role);
			        	   WaitUtil.sleep(1000);
			   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.resubmitBtn);
			   			   resubmitBtn.click();
			       		   String ReSubmitMsg=CMT.getToastMsg();
			       		   System.out.println("Requisition-->"+ReSubmitMsg);
			       		   if(ReSubmitMsg.equalsIgnoreCase("Status has been changed to Resubmit."))
			       		   {
			       			   if(verifyStatusRMS( RMS_UserEmail, vendorID, action, role))
			       			   {
			       				   switchTab();
			       				   WaitUtil.sleep(2000);
			       				   return Ven_Home.verifyStatusVendor(vendorID, role, action);
			       			   }			       			   	
			       		   }
						default:
		                       System.out.println("no matched Action");						
					}
				}
			}
		}else
			{
				System.out.println("Some error occoured in goto vendor page.");
			}
		return false;
	}
	
	public boolean verifyStatusRMS(String RMS_UserEmail,String VendorID,String action, String role) throws InterruptedException
	{
		System.out.println("Verify Status RMS is called.");
		WebElement Tab = null;
		if(action.equalsIgnoreCase("Approve"))
		{
			if(role.equalsIgnoreCase("HR User"))
			{
				Tab = pendingTab;				
			}else
				{
					Tab = acceptedTab;
				}
		}
		else if(action.equalsIgnoreCase("Reject"))
		{
			Tab = rejectedTab;
		}else
			{
			    Tab = resubmitTab;
			}
		
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, Tab);
			Tab.click();
			WaitUtil.sleep(3000);
			List<WebElement> allRows = driver.findElements(By.xpath("//md-tab-content[contains(@class,'md-active')]//tr[@md-select-id='title']"));
			int rowCount = allRows.size();
			System.out.println("Total rows found = "+rowCount);
			for(WebElement rowElement:allRows)
			{
				String vendorEmail = null; 
				WebElement vendorIDField = rowElement.findElement(By.xpath("td[2]//div"));
				if(vendorIDField.getAttribute("aria-label") == null)
				{
					vendorEmail = vendorIDField.getText();				
				}else
					{
						vendorEmail = vendorIDField.getAttribute("aria-label");
					}
				System.out.println("vendor's email found---"+vendorEmail);
				if(vendorEmail.equalsIgnoreCase(VendorID))
				{
					switch(action)
					{
						case "Approve":
							System.out.println("Switch Case verify Approve.");
							if(role.equalsIgnoreCase("HR Admin"))
							{
								System.out.println("Requested vendoriD is found in Accepted Tab for Role -- "+role);
								return true;
							}else
								{
									System.out.println("Requested vendorID is found in pending Tab for Role -- "+role);
									String status = null;
									WebElement statusField = rowElement.findElement(By.xpath("td[8]//label"));
						   			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, statusField);
						   			status = statusField.getText();
						   			if(status.equalsIgnoreCase("Pending with HR Head"))
									{
						   				return true;				
									}								
								}													
						case "Reject":
							System.out.println("Switch Case verify Reject.");
							System.out.println("Requested vendoriD is found in Rejceted Tab for Role -- "+role);
							return true;							
						case "Re Submit":
							System.out.println("Switch Case verify Re Submit.");
							System.out.println("Requested vendoriD is found in Re Submit Tab for Role -- "+role);
							return true;							
						default:
							System.out.println("No action Matched.");
					}
				}
			}			
		}else
			{
				System.out.println("Some error occoured in GOTO Vendor page.");
			}
		return false;
	}
	
	private boolean switchTab()
	{
		System.out.println("switch Tab method Is called.");
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("total tabs opened = "+handles.size());
		String  CurrentHandle= driver.getWindowHandle();//Return a string of alphanumeric current window handle
		for(int i=0;i<2;i++)
		{
			if(!CurrentHandle.equalsIgnoreCase(handles.get(i)))
			{
				driver.switchTo().window(handles.get(i));
				System.out.println("tab is finally switched..");
			}
		}
		return true;
	}
}
