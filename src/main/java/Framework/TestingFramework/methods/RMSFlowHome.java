package Framework.TestingFramework.methods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;

import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;

public class RMSFlowHome 
{
	PipelineHome Pipe_Home;
	SchedulingHome Sc_Home;
	InterviewCalendarHome Interview;
	RequisitionHome Req_Home;
	ReferralHome Ref_Home;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String RMS_UserEmail=null;
	String RMS_UserFullName=null;
	String pageName = "Schedule Interview";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[contains(text(),'Scheduling')]";
	String pageMenuXpath = "//a[contains(text(),'RMS')]";
	ArrayList<String> ReqCan = new ArrayList<String>();
	
	public RMSFlowHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		Req_Home = new RequisitionHome(driver);
		Sc_Home = new SchedulingHome(driver);
		Interview = new InterviewCalendarHome(driver);
		Ref_Home = new ReferralHome(driver);
		Pipe_Home = new PipelineHome(driver);
	}
	public boolean PreScreening(String jobTitle, String Candidate, String PreScreeningStatus) throws ClassNotFoundException, InterruptedException, SQLException
	{
		if(PreScreeningStatus.equalsIgnoreCase("false"))
		{
			System.out.println("PreScreening is not mandatory for this requisition.");
			return true;
		}
		System.out.println("Schedule and Approve PreScreening is called.");
		return Sc_Home.scheduler(jobTitle,"Pre Screening",Candidate,null,PreScreeningStatus);	
	}
	
	public boolean Screening(String jobTitle, String Candidate, String action, String PreScreeningStatus) throws ClassNotFoundException, InterruptedException, SQLException
	{
		System.out.println("Schedule Screening is called.");
//		String jobTitle, String stage, String Candidate, String Interviewer, String PreScreenStatus
		if(Sc_Home.scheduler(jobTitle,"Screening",Candidate,null,PreScreeningStatus))
		{
			System.out.println("Approve Screening is called.");
			return Interview.approveStage(jobTitle,"Screening",Candidate,"ajay@zz.com",action);
		}
		return false;
	}
	
	public boolean TechnicalInterview(String jobTitle, String Candidate, String action, String PreScreeningStatus) throws ClassNotFoundException, InterruptedException, SQLException
	{
		if(Sc_Home.scheduler(jobTitle,"Technical Interview",Candidate,null,PreScreeningStatus))
		{
			System.out.println("Approve Techincal Interview is called.");
			return Interview.approveStage(jobTitle,"Technical Interview",Candidate,"ajay@zz.com",action);
		}
		return false;					
	}
	
	public boolean HRInterview(String jobTitle, String Candidate, String action, String PreScreeningStatus) throws ClassNotFoundException, InterruptedException, SQLException
	{
		if(Sc_Home.scheduler(jobTitle,"HR Interview",Candidate,null,PreScreeningStatus))
		{
			System.out.println("Approve HR Interview is called.");
			return Interview.approveStage(jobTitle,"HR Interview",Candidate,"ajay@zz.com",action);
		}
		return false;			
	}
	
	public boolean completeInterviewStage(String stage, String action, String type,String PreScreeningStatus) throws ClassNotFoundException, InterruptedException, SQLException, IOException, FindFailed
	{
		/*System.out.println("completeInterviewStage is called.");
		if(Pipe_Home.actionOnPipelineTable("Manendra","IFZGWUQUHM","Offered","sendLink"))
		{
			return true;
		}*/
		String Jobtitle = null;
		String Candidate = null;
		if(type.equalsIgnoreCase("Referral"))
		{
			if(Req_Home.actionOnRequisition("HR Admin","Assign",ReqCan,PreScreeningStatus))
			{
				Jobtitle = ReqCan.get(0);
				String query = null;
				query="SELECT e.useremail,concat(e.firstname,' ',e.lastname) FROM fusion.mstmapusermodule as m join mstportalconfig c on c.id=m.roleid join mstemployee as e on e.userid=m.userid and e.isactive=1 where c.configcode='RMSUserRoles' ORDER BY RAND() LIMIT 1;";
				String[] temp = Req_Home.get_RMSUser(query);
				RMS_UserEmail=temp[0];
				RMS_UserFullName=temp[1];
				System.out.println("Referrer id--> "+RMS_UserEmail);
				System.out.println("Referring Requisition--> "+Jobtitle);
				WaitUtil.sleep(2000);
				if(Ref_Home.referToRequisition(RMS_UserEmail, Jobtitle,ReqCan))
				{
					System.out.println("Req content is--> "+ReqCan);
					Candidate = ReqCan.get(1);
					System.out.println("Reffered Candidate-->>"+Candidate);
				}else
					{
						return false;
					}
				
				WaitUtil.sleep(3000);
//				System.out.println("requisition for referral is--> "+ReqCan.get(1));
			}else
				{
					System.out.println("Some error occoured in create & Assign requisition process.");
					return false;
				}
			
		}else
			{
				if(Req_Home.actionOnRequisition("HR Admin","Tag",ReqCan,PreScreeningStatus))
				{
					Jobtitle = ReqCan.get(0);
					Candidate = ReqCan.get(1);
					System.out.println("Required Requisition = "+Jobtitle);
					System.out.println("Required Candidate = "+Candidate);	
				}else
					{
						System.out.println("There is some error while creating Requisition and tagging candidate");
						return false;
					}
			}
		
		switch (stage)
		{
			case "Pre Screening":
				return PreScreening(Jobtitle,Candidate,PreScreeningStatus);
			case "Screening":
				if(PreScreening(Jobtitle,Candidate,PreScreeningStatus))
				{
					return Screening(Jobtitle,Candidate,action,PreScreeningStatus);
				}else
					{
						System.out.println("Screening can't be done because preScreening is not completed");
						return false;
					}
			case "Technical Interview":
				if(PreScreening(Jobtitle,Candidate,PreScreeningStatus))
				{
					if(Screening(Jobtitle,Candidate,"Approve",PreScreeningStatus))
					{
						return TechnicalInterview(Jobtitle,Candidate,action,PreScreeningStatus);
					}else
						{
							System.out.println("Technical Interview can't be done because Screening is not completed");
							return false;
						}
				}else
					{
						System.out.println("Screening can't be done because preScreening is not completed");
						return false;
					}
			case "HR Interview":
				if(PreScreening(Jobtitle,Candidate,PreScreeningStatus))
				{
					if(Screening(Jobtitle,Candidate,"Approve",PreScreeningStatus))
					{
						if(TechnicalInterview(Jobtitle,Candidate,"Approve",PreScreeningStatus))
						{
							return HRInterview(Jobtitle,Candidate,action,PreScreeningStatus);
						}else
							{
								System.out.println("HR interview can't be done because techincal is no completed.");
								return false;
							}
					}else
						{
							System.out.println("Technical Interview can't be done because Screening is not completed.");
							return false;
						}
				}else
					{
						System.out.println("Screening can't be done because preScreening is not completed.");
						return false;
					}
		
		}
		return false;
	}

}
