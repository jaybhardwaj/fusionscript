package Framework.TestingFramework.methods;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.DatabaseUtil;
import Framework.TestingFramework.utils.WaitUtil;

public class RequisitionHome 
{
	CommonMethods CMT ;
	UploadHome Up_Home;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String RMS_UserEmail=null;
	String RMS_UserFullName=null;
	String pageName = "Requisition";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[@ui-sref='main.rms.requisition']";
	String pageMenuXpath = "//li[@ng-if='userModules.RMS']";
	
	public RequisitionHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
		Up_Home =new UploadHome(driver);
	}
	
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[1]")//applicable to ALL dropdowns
	})
	public WebElement Option1;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[2]")//applicable to ALL dropdowns
	})
	public WebElement Option2;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[3]")//applicable to ALL dropdowns
	})
	public WebElement Option3;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='updateRequisitionInfo({id:null},$event)']"),
		@FindBy(xpath = "//button[@ng-if='userModules.RMS.role!='HR User' && !selectedTab ']")
	})
	public WebElement addReqBtn;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.jobtitle']"),
		@FindBy(xpath = "//input[@name='Jobtitle']")
	})
	public WebElement jobTitle;
	@FindAll({
		@FindBy(xpath = "//md-select[@aria-label='Designation']"),
		@FindBy(xpath = "//md-select[@name='designation']"),
		@FindBy(xpath = "//md-select[@ng-model='editInfo.designationid']")
	})
	public WebElement designationSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='searchDesignation']"),
		@FindBy(xpath = "//input[@name='Jobtitle']")
	})
	public WebElement searchDesignation;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-change='editInfo.skillsName = changeHoverFields(technologyList,editInfo.skills)']"),
		@FindBy(xpath = "//md-select[contains(@class,'ng-valid-parse') and @name='skills']")
	})
	public WebElement skillSelect;
	@FindAll({
		@FindBy(xpath = "//md-select[@aria-label='Location']"),
		@FindBy(xpath = "//md-select[@name='location']"),
		@FindBy(xpath = "//md-select[@ng-model='editInfo.locationid']")
	})
	public WebElement locationSelect;
	@FindAll({
		@FindBy(xpath = "//div[@ id='cube-close']//md-icon[text()='clear']")
	})
	public WebElement cubeClose;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='editInfo.positions']"),
		@FindBy(xpath = "//input[@name='positions']")
	})
	public WebElement position;
	@FindAll({
		@FindBy(xpath = "//input[@name='minexp']"),
		@FindBy(xpath = "//input[@ng-model='editInfo.minexperience']")
	})
	public WebElement minExp;
	@FindAll({
		@FindBy(xpath = "//input[@name='maxexp']"),
		@FindBy(xpath = "//input[@ng-model='editInfo.maxexperience']")
	})
	public WebElement maxExp;
	@FindAll({
		@FindBy(xpath = "//form[@name='saveRequisitionForm']//input[@class ='md-datepicker-input md-input']")
	})
	public WebElement Calender;
	@FindAll({
		@FindBy(xpath = "//td[@class='md-calendar-date md-calendar-date-today md-focus']/span"),
		@FindBy(xpath = "//div[@class='md-virtual-repeat-offsetter']//span[text()='10']")
	})
	public WebElement date;
	@FindAll({
		@FindBy(xpath = "//md-select[@aria-label='Priority']"),
		@FindBy(xpath = "//md-select[@name='priority']"),
		@FindBy(xpath = "//md-select[@ng-model='editInfo.priorityid']")
	})
	public WebElement prioritySelect;
	@FindAll({
		@FindBy(xpath = "//input[@name='minimumsalary']"),
		@FindBy(xpath = "//input[@ng-model='editInfo.minimumsalary']")
	})
	public WebElement minSalary;
	@FindAll({
		@FindBy(xpath = "//input[@name='maximumsalary']"),
		@FindBy(xpath = "//input[@ng-model='editInfo.maximumsalary']")
	})
	public WebElement maxSalary;
	@FindAll({
		@FindBy(xpath = "//md-select[@aria-label='Type']"),
		@FindBy(xpath = "//md-select[@name='jobType']"),
		@FindBy(xpath = "//md-select[@ng-model='editInfo.jobtypeid']")
	})
	public WebElement jobTypeSelect;
	@FindAll({
		@FindBy(xpath = "//div[@ng-model='html']")
	})
	public WebElement jobDesc;
	@FindAll({
		@FindBy(xpath = "//form[@name='saveRequisitionForm']//button[@submit-button='Save']")
	})
	public WebElement saveButton;
	@FindAll({
		@FindBy(xpath = "//form[@name='saveRequisitionForm']//button[@ng-click='hideDialog('saveRequisitionForm')']")
	})
	public WebElement CancelButton;
	@FindAll({
		@FindBy(xpath = "//div[@table-search='tableSearchActive']//input[@ng-model='tableSearch']")
	})
	public WebElement tableSearch;
	@FindAll({
		@FindBy(xpath = "//tr[@ng-repeat='item in requisitionData | filterBy: tableSearchRequisition : tableSearchActive | orderBy: query.order | limitTo: query.limit : (query.page -1) * query.limit']")
	})
	public WebElement activeTab_Tablerow;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='okClicked()']"),
		@FindBy(xpath = "//button//span[text()='OK']")
	})
	public WebElement OkBtn;
	@FindAll({
		@FindBy(xpath = "//md-select[@aria-label='Hr Users']"),
		@FindBy(xpath = "//md-select[@name='assignedtoHrVal']"),
		@FindBy(xpath = "//md-select[@ng-model='rmsUserDataForHR.hrId']")
	})
	public WebElement HrUserSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='searchHrUsers']"),
		@FindBy(xpath = "//input[@placeholder='Search for a Hr Users']")
	})
	public WebElement searchHrUser;
	@FindAll({
		@FindBy(xpath = "//md-select[@aria-label='Open for Referral']"),
		@FindBy(xpath = "//md-select[@name='referral']"),
		@FindBy(xpath = "//md-select[@ng-model='rmsUserDataForHR.referralstatus']")
	})
	public WebElement openForReferralSelect;
	@FindAll({
		@FindBy(xpath = "//md-option//div[text()='Yes']")
	})
	public WebElement ReferralOptionYes;
	@FindAll({
		@FindBy(xpath = "//md-option//div[text()='No']")
	})
	public WebElement ReferralOptionNo;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='rmsUserDataForHR.publishtoweb']"),
		@FindBy(xpath = "//md-select[@aria-label='Publish to Web']"),
		@FindBy(xpath = "//md-select[@name='publishtoweb']")		
	})
	public WebElement publishWebSelect;
	@FindAll({
		@FindBy(xpath = "//button[@submit-button='Assign']")
	})
	public WebElement submitButton;
	@FindAll({
		@FindBy(xpath = "//md-tab-item[contains(text(),'Expired/Rejected')]")
	})
	public WebElement ExpiryRejcted_Tab;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='vm.valueAsText']")
	})
	public WebElement filterDateRange;
	@FindAll({
		@FindBy(xpath = "//button[@aria-label='Year To Date']")
	})
	public WebElement filterDateRangeOpstn;
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='tagCandidateInfo(untagCandidateData,$event)']")
	})
	public WebElement addCandidateBtn;
	@FindAll({
		@FindBy(xpath = "//a[@aria-label='Back']")
	})
	public WebElement backBtn;
	@FindAll({
		@FindBy(xpath = "//div[@id='taggedcandidateListDialog']//input[@ng-model='tableSearch']")
	})
	public WebElement SearchCandidateTable;
	@FindAll({
		@FindBy(xpath = "//div[@class='nav_menu']//li[@class='flex']/p")
	})
	public WebElement PageHeading;
	@FindAll({
		@FindBy(xpath = "//md-checkbox[@ng-model='editInfo.prescreeningrequired']")
	})
	public WebElement PreScreenCheckBox;

//	This method first creates requisition then do Delete,Tag,Assign,Copy and if nothing comes in action parameter then approves raised requisition.
//	This method logins as Ajay@zz.com who is HR Admin.
	public boolean actionOnRequisition(String role, String action, ArrayList<String> ReqCred, String PreScreeningStatus) throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		String query = null;
		String Action = "Approve";
		ReqCred.clear();
		/*query="SELECT e.useremail,concat(e.firstname,' ',e.lastname) FROM fusion.mstmapusermodule as m join mstportalconfig c on c.id=m.roleid join mstemployee as e on e.userid=m.userid and e.isactive=1 where c.configcode='RMSUserRoles' and c.configvalue1 = '"+role+"' ORDER BY RAND() LIMIT 1;";
		String[] temp = get_RMSUser(query);
		RMS_UserEmail=temp[0];
		RMS_UserFullName=temp[1];*/
		if(CMT.GotoRequiredPage("ajay@zz.com",pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			String ranJobtitle = getRandomJobTitle();
			String ranDesignation = getRandomDesignation();
			if(addReq(ranJobtitle,ranDesignation,PreScreeningStatus))
			{
				switch(action)
				{
					case "Delete":
						return doTableAction("ajay@zz.com",ranJobtitle,action,null);
					case "Tag":
						if(doTableAction("ajay@zz.com",ranJobtitle,Action,null))
						{
							return doTableAction("ajay@zz.com",ranJobtitle,action,ReqCred);
						}
					case "Assign":
						if(doTableAction("ajay@zz.com",ranJobtitle,Action,null))
						{
							return doTableAction("ajay@zz.com",ranJobtitle,"Assign,No,Yes,No",ReqCred);
						}
					case "Copy":
						if(doTableAction("ajay@zz.com",ranJobtitle,Action,null))
						{
							return doTableAction("ajay@zz.com",ranJobtitle,action,null);
						}
					default:
						return doTableAction("ajay@zz.com",ranJobtitle,Action,null);
				}
			}
			else
				{
					System.out.println("Some Error Occuered in raising Requisition.");
					return false;
				}	
		}
		else{
			System.out.println("Some Error Occuered in GoTo Requisition page Process.");
			return false;
		    }
	}
	
	//	This method creates requisition by different valid RMS users (ex-HOD,HR Admin,Hiring Manager) and got it approved/rejected based on parameter received.
	public boolean addRequisition(String role, String Action) throws ClassNotFoundException, SQLException, InterruptedException, IOException
	{
		System.out.println("*************Add Requisition method called for-->"+role+"*************");
		String query = null;
		query="SELECT e.useremail,concat(e.firstname,' ',e.lastname) FROM fusion.mstmapusermodule as m join mstportalconfig c on c.id=m.roleid join mstemployee as e on e.userid=m.userid and e.isactive=1 where c.configcode='RMSUserRoles' and c.configvalue1 = '"+role+"' ORDER BY RAND() LIMIT 1;";
		String[] temp = get_RMSUser(query);
		RMS_UserEmail=temp[0];
		RMS_UserFullName=temp[1];
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			String ranJobtitle = getRandomJobTitle();
			String ranDesignation = getRandomDesignation();
			if(addReq(ranJobtitle,ranDesignation,"true"))
			{
				return approveRequisition(RMS_UserEmail,role,ranJobtitle,Action);
			}
			else
				{
					System.out.println("Method AddRequisition--Some Error Occuered in raising Requisition.");
					return false;
				}			
		}
		else{
			System.out.println("Method AddRequisition--Some Error Occuered in GoTo Requisition page Process.");
			return false;
		    }
	}

//  This method finds approver( other than creator ) and do approve/reject by calling doTableAction
	private boolean approveRequisition(String RMS_UserEmail, String role, String ranJobtitle, String action) throws ClassNotFoundException, SQLException, InterruptedException, IOException 
	{
		System.out.println("************ApproveRequisition method is called.***********");
		String query="SELECT e.useremail,concat(e.firstname,' ',e.lastname) FROM fusion.mstmapusermodule as m join mstportalconfig c on c.id=m.roleid join mstemployee as e on e.userid=m.userid and e.isactive=1 where c.configcode='RMSUserRoles' and e.useremail != '"+RMS_UserEmail+"' and c.configvalue1='HOD' or c.configvalue1='HR Admin' ORDER BY RAND() LIMIT 1;";
		String[] temp = get_RMSUser(query);
		RMS_UserEmail=temp[0];
		RMS_UserFullName=temp[1];
		System.out.println("Requisition's Approver email-->"+RMS_UserEmail);
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			return doTableAction(RMS_UserEmail,ranJobtitle,action,null);			
		}else{
			   System.out.println("Method ApproveRequisition--Some Error Occuered in GoTo Requisition page Process.");
			   return false;
		     }
	}

//	This method finds requisition in table and  performs various actions on raised requisition
	private boolean doTableAction(String user,String ranJobtitle, String action, ArrayList<String> reqCred) throws ClassNotFoundException, SQLException, InterruptedException, IOException
	{
		List<WebElement> allRows = driver.findElements(By.xpath("//tr[@ng-repeat='item in requisitionData | filterBy: tableSearchRequisition : tableSearchActive | orderBy: query.order | limitTo: query.limit : (query.page -1) * query.limit']"));
//		int rowCount = allRows.size();
//		System.out.println("Total rows-->>"+rowCount);
		int RowIndex=1;
		for(WebElement rowElement:allRows)
		{
		      List<WebElement> AllColumns=rowElement.findElements(By.xpath("td"));
		      int ColumnIndex=1;
		      int status = 0;
		      for(WebElement colElement:AllColumns)
		      {
		           String temp1 = colElement.getText();
		           if(temp1.equalsIgnoreCase(ranJobtitle))
		           {
		        	   String str = action;
		       		   String [] arrOfAction = str.split(",");
		       		   WebElement actionTray;
		               switch(arrOfAction[0])
		               {
		                   case "Approve":
		                       System.out.println("*********Switch Case-Approve*********");
		                       WebElement approveBtn = rowElement.findElement(By.xpath("td//md-icon[contains(text(),'done')]"));
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, approveBtn);
				        	   approveBtn.click();
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OkBtn);
				        	   OkBtn.click();				        	   
				       		   String ApproveMsg=CMT.getToastMsg();
				       		   System.out.println("Toast Msg On Approve-->"+ApproveMsg);
				        	   return verifyStatus("Approved",user,ranJobtitle,null);
		                   case "Reject":
		                       System.out.println("*********Switch Case-Reject*********");
		                       WebElement rejectBtn = rowElement.findElement(By.xpath("td//md-icon[contains(text(),'clear')]"));
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, rejectBtn);
			        		   rejectBtn.click();
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OkBtn);
				        	   OkBtn.click();
				       		   String RejectMsg=CMT.getToastMsg();
				       		   System.out.println("Toast Msg On Reject-->"+RejectMsg);
				        	   return verifyStatus("Rejected",user,ranJobtitle,null);
					       case "Assign":
		                       System.out.println("*********Switch Case-Assign*********");
		                       WebElement assignBtn = rowElement.findElement(By.xpath("td//md-icon[contains(text(),'people')]"));
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, assignBtn);
		        		       assignBtn.click();
		        		       String assignedHr_user = doAssignment(arrOfAction,ranJobtitle);
		        		       System.out.println("Switch Case-Assign HR User-->"+assignedHr_user);
		        		       WaitUtil.sleep(2000);
		        		       reqCred.add(ranJobtitle);
		        		       return verifyStatus("Assigned",assignedHr_user,ranJobtitle,null);
		                   case "Delete":
		                       System.out.println("*********Switch Case-Delete*********");
		                       actionTray =  rowElement.findElement(By.xpath("td//md-icon[contains(text(),'menu')]"));
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, actionTray);
				   			   actionTray.click();
				   			   WaitUtil.sleep(1000);
				   			   WebElement deleteBtn =  rowElement.findElement(By.xpath("//button[@ng-click='deleteRequisition(item.id,$event)']"));
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, deleteBtn);
				   			   deleteBtn.click();
				   			   WaitUtil.sleep(1000);
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OkBtn);
				        	   OkBtn.click();
				       		   String deleteMsg=CMT.getToastMsg();
				       		   System.out.println("Requisition-->"+deleteMsg);
					    	   return verifyStatus("Deleted",user,ranJobtitle,null);
		                   case "Tag":
		                	   System.out.println("*********Switch Case-Tag*********");	                
	                		   actionTray =  rowElement.findElement(By.xpath("td//md-icon[contains(text(),'menu')]"));
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, actionTray);
				   			   actionTray.click();
				   			   WaitUtil.sleep(1000);
				   			   WebElement tagBtn =  rowElement.findElement(By.xpath("//button[@ng-click='showRequisition(item.id)']"));
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, tagBtn);
				   			   tagBtn.click();
				   			   WaitUtil.sleep(5000);
				   			   String taggedCandidate []= doTagCandidate(2);
				   			   if(taggedCandidate == null)
				   			   {
				   				   return false;
				   			   }else
					   			   {
				   				   	  reqCred.add(ranJobtitle);
				   				      reqCred.add(taggedCandidate[0]);
				   				      return true;
//						   				  return verifyStatus("Tagged",user,ranJobtitle,taggedCandidate);
					   			   }
		                   case "Copy":
		                	   System.out.println("*********Switch case Copy*********");
		                	   actionTray =  rowElement.findElement(By.xpath("td//md-icon[contains(text(),'menu')]"));
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, actionTray);
				   			   actionTray.click();
				   			   WaitUtil.sleep(1000);
				   			   WebElement copyBtn =  rowElement.findElement(By.xpath("//button[@ng-click='copyRequisitionInfo(item,$event)']"));
				   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, copyBtn);
				   			   copyBtn.click();
				   			   WaitUtil.sleep(2000);
				   			   String newJobtitle = getRandomJobTitle();
				   			   if(doCopy_verify(newJobtitle))
				   			   {
				   				   if(doTableAction(user,newJobtitle,"Approve",null))
				   				   {
				   					return verifyStatus("Approved",user,newJobtitle,null);
				   				   }
				   			   }
				   			   WaitUtil.sleep(5000);
		                   default:
		                       System.out.println("no matched Action");
		               }
		       		   status=1;
		        	   break;
		           }else{
			        	   ColumnIndex=ColumnIndex+1;		        	   
		                }
		           
		       }
		      if(status==1)
		      {  break;}
		      else
		      {  RowIndex=RowIndex+1;}      
		 }
		return false;
	}
	
	private boolean doCopy_verify(String newJobtitle) 
	{
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.jobTitle);
		jobTitle.click();
		jobTitle.clear();
		jobTitle.sendKeys(newJobtitle);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Calender);
		Calender.click();
		WaitUtil.sleep(2000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.date);
		date.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.jobTitle);
		saveButton.click();
		String copyMsg=CMT.getToastMsg();
		System.out.println("Copy Requisition message-->"+copyMsg);
 	   	if(copyMsg.equalsIgnoreCase("Requisition successfully added"))
 	   	{
 	   		return true;
 	   	}else
	 	   	{
	 	   		return false;
	 	   	}	
	}

	private String [] doTagCandidate(int num) throws ClassNotFoundException, SQLException, IOException, InterruptedException 
	{
		System.out.println("dotagcandidate is called");
		String query="SELECT email,filename,filepath FROM fusion.rmscandidate where isactive=1 and isblocked=0;";
		int resumeInDB = CMT.getRecordSizeInDB(query);
		System.out.println("Total resume in DB = "+resumeInDB);
		int resumeToAdd = num-resumeInDB;
		if(resumeInDB<num)
		{
			if(Up_Home.uploadCandidate(resumeToAdd,"self"))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.PageHeading);
				String Heading= PageHeading.getAttribute("innerHTML");
				System.out.println("We are at page-->"+Heading);
				if(Heading.equalsIgnoreCase("Resume Upload"))
				{
					driver.navigate().back();
					System.out.println("coming back to tag candidate page");
					WaitUtil.sleep(5000);
				}			
			}else
				{
				   System.out.println("Some error occoured in uploading candidates");
				   return null;
				}
			
		}else
		  { 
			System.out.println("Required num of resumes are already present in DB.");
		  }
		String temp[] = new String[num];
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.filterDateRange);
		filterDateRange.click();
		WaitUtil.sleep(500);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.filterDateRangeOpstn);
		filterDateRangeOpstn.click();
		WaitUtil.sleep(3000);
		for(int i=1;i<=num;i++)
		{
			String Xpath1 = "//tr[@md-select-id='name']["+i+"]/td[1]";
			WebElement rowCheck = driver.findElement(By.xpath(Xpath1));
			rowCheck.click();
			WaitUtil.sleep(1000);
			String Xpath2 = "//tr[@md-select-id='name']["+i+"]/td[2]/div";
			WebElement CanEmail = driver.findElement(By.xpath(Xpath2));
			if(CanEmail.getAttribute("aria-label") == null)
			{
				temp[i-1] = CanEmail.getText();				
			}else
				{
					temp[i-1] = CanEmail.getAttribute("aria-label");
				}
			System.out.println("Tagged Candidate---"+temp[i-1]);
		}
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.addCandidateBtn);
		addCandidateBtn.click();
		String TagMsg=CMT.getToastMsg();
		System.out.println("Requisition tag candidate-->"+TagMsg);
 	   	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.backBtn);
 	   	backBtn.click();
 	   	WaitUtil.sleep(5000);
		return temp;
		
	}

	private String doAssignment(String[] arrOfAction,String ranJobtitle) throws ClassNotFoundException, SQLException, InterruptedException 
	{
		String [] temp = arrOfAction;
		if(temp[0].equalsIgnoreCase("Assign"))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.HrUserSelect);
			HrUserSelect.click();
			String query = "SELECT e.useremail,concat(e.firstname,' ',e.lastname) FROM fusion.mstmapusermodule as m join mstportalconfig c on c.id=m.roleid join mstemployee as e on e.userid=m.userid and e.isactive=1 where c.configcode='RMSUserRoles' and c.configvalue1='HR User' ORDER BY RAND() LIMIT 1;";
			String[] Querytemp = get_RMSUser(query);
			RMS_UserEmail=Querytemp[0];
			RMS_UserFullName=Querytemp[1];
			System.out.println("HR user to whome req is being assigned-->"+RMS_UserFullName);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.searchHrUser);
			searchHrUser.click();
			searchHrUser.clear();
			searchHrUser.sendKeys(RMS_UserFullName);
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
			Option1.click();
			Option1.sendKeys(Keys.TAB);
			WaitUtil.sleep(1000);
			if(temp[1].equalsIgnoreCase("Yes"))
			{
				// for implementing vendor side
			}
			if(temp[2].equalsIgnoreCase("Yes"))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.openForReferralSelect);
				openForReferralSelect.click();
				WaitUtil.sleep(1000);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
				Option1.click();
			}else
				{
					WaitUtil.sleep(1000);
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option2);
					Option2.click();
				}
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.publishWebSelect);
			publishWebSelect.click();
			WaitUtil.sleep(1000);
			if(temp[3].equalsIgnoreCase("Yes"))
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
				Option1.click();
			}else
				{
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option2);
					Option2.click();
				}
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.submitButton);
			submitButton.click();
    		String Msg=CMT.getToastMsg();
    		System.out.println("Toast Msg on Req Assignment to HR User-->"+Msg);
	    	if(Msg.equalsIgnoreCase("Requisition assigned successfully"))
	    	{
	    		return RMS_UserEmail;
	    	}
    		WaitUtil.sleep(2000);
		}				
		return null;
	}

	private boolean verifyStatus(String status, String rMS_UserEmail2, String ranJobtitle , String temp[]) throws InterruptedException 
	{
		if(CMT.GotoRequiredPage(rMS_UserEmail2,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			System.out.println("verify Status method is called");
			List<WebElement> allRows;
			if(status.equalsIgnoreCase("Rejected"))
			{
				System.out.println("verify status Called For a rejected Requisition to check status--->"+status);
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, ExpiryRejcted_Tab);
				ExpiryRejcted_Tab.click();
				System.out.println("expiry Tab clicked");
				WaitUtil.sleep(5000);
				allRows = driver.findElements(By.xpath("//tr[@ng-repeat='item in requisitionData | filterBy: tableSearchRequisition : tableSearchExpired | orderBy: query.order | limitTo: query.limit : (query.page -1) * query.limit']"));
			}else
				{
					System.out.println("verify Status Called for Active Requisition tab");
					allRows = driver.findElements(By.xpath("//tr[@ng-repeat='item in requisitionData | filterBy: tableSearchRequisition : tableSearchActive | orderBy: query.order | limitTo: query.limit : (query.page -1) * query.limit']"));
				} 
			int RowIndex=1;
			for(WebElement rowElement:allRows)
			{
				List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
			      int ColumnIndex=1;
			      for(WebElement colElement:TotalColumnCount)
			      {
			           String temp1 = colElement.getText();
			           if(temp1.equalsIgnoreCase(ranJobtitle))
			           {
			        	   System.out.println("Required requisition--->"+ranJobtitle+" is found.");
			        	   switch(status)
			               {
			                   case "Approved":
					        	   WebElement Status = rowElement.findElement(By.xpath("td//label[contains(text(),'Approved')]"));
					   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, Status);
					        	   if(Status.isDisplayed())
					        	   { return true; }
					        	   else
					        	   { return false; }
			                   case "Rejected":
					        	   WebElement rejectStatus = rowElement.findElement(By.xpath("td//label[contains(text(),'Rejected')]"));
					   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, rejectStatus);
					        	   if(rejectStatus.isDisplayed())
					        	   { return true; }
					        	   else
					        	   { return false; }
						       case "Assigned":
			                       return true;
						       case "Deleted":
						    	   System.out.println("Requisition is not deleted");
						    	   return false;
						       case "Tagged":
						    	   System.out.println("Verify Status-Switch Case-Tag");
			                	   WebElement actionTray1 =  rowElement.findElement(By.xpath("td//md-icon[contains(text(),'menu')]"));
					   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, actionTray1);
					   			   actionTray1.click();
					   			   WaitUtil.sleep(1000);
					   			   WebElement viewTaggedCandidateBtn =  rowElement.findElement(By.xpath("//button[@ng-click='TagCandidateList(item,$event)']"));
					   			   WaitUtil.explicitWaitByVisibilityOfElement(driver, time, viewTaggedCandidateBtn);
					   			   viewTaggedCandidateBtn.click();
					   			   WaitUtil.sleep(3000);
					   			   int size = temp.length;   
					   			   for(int i=1;i<=size;i++)
					   			   {
						   			  WaitUtil.explicitWaitByVisibilityOfElement(driver, time, SearchCandidateTable);
						   			  SearchCandidateTable.click();
							   		  SearchCandidateTable.clear();
							   		  SearchCandidateTable.sendKeys(temp[i-1]);
							   		  WaitUtil.sleep(60000);
							   		  WebElement TaggedCandidate=driver.findElement(By.xpath("//tr[@md-select-id='name'][1]/td[3]/div"));
							   		  WaitUtil.explicitWaitByVisibilityOfElement(driver, time, TaggedCandidate);
							   		  String candidateEmail = TaggedCandidate.getAttribute("aria-label");
							   		  System.out.println("matching first tagged candidate"+i+"--"+candidateEmail);
							   		  if(temp[i-1].equalsIgnoreCase(candidateEmail))
							   		  {
							   			  System.out.println("Matched");
							   		  }else
								   		  {
							   			  	  System.out.println("Not Matched");
								   			  return false;
								   		  }
					   			   }
					   			   return true;
			                   default:
			                       System.out.println("no matched Action");
			               }
			           }else{
			        	   ColumnIndex=ColumnIndex+1;		        	   
		                }
			      }
			    RowIndex=RowIndex+1; 
			}
			
		}else{
			   System.out.println("Some Error Occuered in GoTo Requisition page Process.");
		     }
		return true;
	}

	private boolean addReq(String JobTitle, String designation,String PreScreeningStatus) 
	{
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.addReqBtn);
		addReqBtn.click();
		WaitUtil.sleep(5000);
		if(!PreScreenCheckBox.getAttribute("aria-checked").equalsIgnoreCase(PreScreeningStatus))
		{
			PreScreenCheckBox.click();
			WaitUtil.sleep(1000);
		}
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.jobTitle);
		jobTitle.click();
		jobTitle.clear();
		jobTitle.sendKeys(JobTitle);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.designationSelect);
		designationSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.searchDesignation);
		searchDesignation.click();
		searchDesignation.clear();
		searchDesignation.sendKeys(designation);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.skillSelect);
		skillSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(500);
		Option2.click();
		WaitUtil.sleep(500);
		Option3.click();
		WaitUtil.sleep(1000);
		Option3.sendKeys(Keys.TAB);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.locationSelect);
		locationSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.position);
		position.click();
		position.clear();
		position.sendKeys("5");
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.minExp);
		minExp.click();
		minExp.sendKeys("3");
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.maxExp);
		maxExp.click();
		maxExp.sendKeys("10");
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Calender);
		Calender.click();
		WaitUtil.sleep(1500);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.date);
		date.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.prioritySelect);
		prioritySelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.minSalary);
		minSalary.click();
		minSalary.clear();
		minSalary.sendKeys("50000");
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.maxSalary);
		maxSalary.click();
		maxSalary.clear();
		maxSalary.sendKeys("500000");
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.jobTypeSelect);
		jobTypeSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.jobDesc);
		jobDesc.click();
		jobDesc.clear();
		jobDesc.sendKeys("Please find suitable employee as soon as possible.");
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveButton);
		saveButton.click();
		String Msg=CMT.getToastMsg();
		System.out.println("Add Requisition-->"+Msg);
		if(Msg.equalsIgnoreCase("Requisition successfully added"))
		{		
			return true;
		}
		else if(Msg.equalsIgnoreCase("Requisition already exist with this job title."))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.CancelButton);
			CancelButton.click();
			return false;
		}
		return false;
	}

	public String[] get_RMSUser(String temp_query) throws ClassNotFoundException, SQLException 
	{
		String query=temp_query;
		String[] Result = new String[2];
		DatabaseUtil db = new DatabaseUtil();
			db.makeConnection();
			ResultSet result=db.runQuery(query);
			result.beforeFirst();
			if(result.next())
			{
				if(result.getString(1).equalsIgnoreCase("support@polestarllp.com"))
				{ Result[0] = result.getString(1)+"1"; }
				else{ Result[0] = result.getString(1); }
				
				Result[1]= result.getString(2);
			}else
				{
					System.out.println("No record found in Db w.r.t your query.");
				}
			System.out.println(Result[0]);
			System.out.println(Result[1]);
			db.ConnectionClose();
		return Result;
	}
	
	private String getRandomDesignation() throws SQLException, ClassNotFoundException 
	{
		String query=null;
		String RanDesignation=null;
		query="SELECT configvalue1 FROM fusion.mstportalconfig where configcode='designation' and isactive =1 ORDER BY RAND() LIMIT 1";
		DatabaseUtil db = new DatabaseUtil();
		db.makeConnection();
		ResultSet result=db.runQuery(query);
		result.beforeFirst();
		if(result.next())
		{
			RanDesignation = result.getString(1);
		}
		db.ConnectionClose();
		return RanDesignation;
	}

	private String getRandomJobTitle() 
	{
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) 
        { 
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String randomJobTitle = salt.toString();
		return randomJobTitle;
	}	
}
