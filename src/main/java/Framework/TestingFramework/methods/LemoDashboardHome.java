package Framework.TestingFramework.methods;

import java.util.ArrayList;
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

public class LemoDashboardHome
{
	CommonMethods CMT;
	CandidateHome can_Home;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String pageName = "Dashboard";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[@ui-sref='main.assessment.dashboard']";
	String pageMenuXpath = "//li[@ng-if='userModules.Assessment']";
	
	public LemoDashboardHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
		can_Home = new CandidateHome(driver);
	}
	
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='okClicked()']"),
		@FindBy(xpath = "//button//span[text()='OK']")
	})
	public WebElement OkBtn;

	public boolean actionOnCandidate(String batch,String candidate,String action) throws InterruptedException
	{
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(handles.get(0));
		WaitUtil.sleep(2000);
		String RMS_UserEmail = "support@polestarllp.com1";
		WebElement actionBtn = null;
		if(CMT.GotoRequiredPage(RMS_UserEmail,pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			List<WebElement> allRows = driver.findElements(By.xpath("//tr[@md-select-id='title']"));
			int rowCount = allRows.size();
			System.out.println("Total Candidates found = "+rowCount);
			for(WebElement rowElement:allRows)
			{
				String batchName = null; 
				String candidateName = null;
				WebElement batchField = rowElement.findElement(By.xpath("td[1]"));
				WebElement candidateField = rowElement.findElement(By.xpath("td[2]"));
				batchName = batchField.getText();
				if(batchField.getAttribute("aria-label") == null)
				{
					candidateName = candidateField.getText();				
				}else
					{
						candidateName = candidateField.getAttribute("aria-label");
					}
				System.out.println("Batch name found---"+batchName);
				System.out.println("Candidate name found---"+candidateName);
				if(action.equalsIgnoreCase("Accepted"))
				{
					actionBtn = rowElement.findElement(By.xpath("td[13]//md-icon[contains(text(),'done')]"));
				}
				else if(action.equalsIgnoreCase("Rejected"))
				{
					actionBtn = rowElement.findElement(By.xpath("td[13]//md-icon[contains(text(),'clear')]"));
				}else
					{
						actionBtn = rowElement.findElement(By.xpath("td[13]//md-icon[contains(text(),'replay')]"));
					}
				if(batchName.equalsIgnoreCase(batch) && candidateName.equalsIgnoreCase(candidate))
				{
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, actionBtn);
					actionBtn.click();
					WaitUtil.sleep(2000);
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.OkBtn);
					OkBtn.click();
					String Msg = CMT.getToastMsg();
					System.out.println("Candidate Action Msg -"+Msg);
					WaitUtil.sleep(5000);
					return can_Home.IsCandidatePresent(candidateName, action);					
				}
		    }// Approve successfully
			// Reject successfully  Candidate records deleted successfully
		}else
			{
				System.out.println("Some error occoured in GOTO Lemonade DashBoard page.");
			}
		return false;
	}
}
