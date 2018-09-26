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

public class LemonadeHome
{
	LemoBatchHome LBH;
	LemoDashboardHome LDH;
	WebDriver driver;
	JavascriptExecutor jse ;
	ArrayList<String> batchDetails = new ArrayList<String>();
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String pageName = "Vendor";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[@ui-sref='main.assessment.home']";
	String pageMenuXpath = "//li[@ng-if='userModules.Assessment']";
	
	public LemonadeHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		LBH = new LemoBatchHome(driver);
		LDH = new LemoDashboardHome(driver);
	}
	
	public boolean lemonadeFlow(String CanStatus) throws InterruptedException, FindFailed, ClassNotFoundException, SQLException, IOException
	{
		String totalQuestion = "10";
		String cutOff = "5";
		String testDuration = "10";
		String expiryAfter = "30";
		batchDetails.clear();
		if(LBH.CreateBatch(totalQuestion,cutOff,testDuration,expiryAfter,batchDetails))
		{
			WaitUtil.sleep(3000);
			if(LBH.ConductTestFor(batchDetails.get(0),"Active"))
			{
				String canName = LBH.fillCandidateDetails();
				if(LBH.completeTestSikuli(totalQuestion,"submit"))
				{
					System.out.println("Batch Name = "+batchDetails.get(0));
					System.out.println("Candidate Name = "+canName);
					return LDH.actionOnCandidate(batchDetails.get(0),canName,CanStatus);
				}
			}
		}else
			{
				System.out.println("Some error occourred in creating lemonade batch.");
			}
		return false;		
	}

	public boolean selfSubmit() throws InterruptedException, ClassNotFoundException, FindFailed, SQLException, IOException
	{
		String totalQuestion = "10";
		String cutOff = "5";
		String testDuration = "2";
		String expiryAfter = "30";
		batchDetails.clear();
		if(LBH.CreateBatch(totalQuestion,cutOff,testDuration,expiryAfter,batchDetails))
		{
			WaitUtil.sleep(2000);
			if(LBH.ConductTestFor(batchDetails.get(0),"Active"))
			{
				String canName = LBH.fillCandidateDetails();
				if(LBH.completeTestSikuli(totalQuestion,"Self"))
				{
					System.out.println("Batch Name = "+batchDetails.get(0));
					System.out.println("Candidate Name = "+canName);
					return LDH.actionOnCandidate(batchDetails.get(0),canName,"Accepted");
				}
			}
		}else
			{
				System.out.println("Some error occourred in creating lemonade batch.");
			}
		return false;
	}

	public boolean ExpiryBatchflow() throws ClassNotFoundException, InterruptedException, SQLException, IOException
	{
		String batchName = null;
		batchDetails.clear();
		if(LBH.startTestFor("Expired", batchDetails))
		{
			batchName = batchDetails.get(0);
			return LBH.StartTest("Expired",batchName);
		}else
			{
				if(LBH.CreateBatch("10","5","5","1",batchDetails))
				{
					batchName = batchDetails.get(0);
					WaitUtil.sleep(240000);
					if(LBH.ConductTestFor(batchName,"Expired"))
					{
						return true;
					}
				}
			}
		return false;
	}

	public boolean RetestCandidate() throws ClassNotFoundException, InterruptedException, SQLException, IOException, FindFailed
	{
		String batchName = null;
		batchDetails.clear();
		if(LBH.startTestFor("Active", batchDetails))
		{
			batchName = batchDetails.get(0);
			LBH.StartTest("Active",batchName);
		}else
			{
				if(LBH.CreateBatch("10","5","5","1",batchDetails))
				{
					batchName = batchDetails.get(0);
					WaitUtil.sleep(240000);
					LBH.ConductTestFor(batchName,"Active");
				}
			}
		String canName = LBH.fillCandidateDetails();
		return false;
	}

}
