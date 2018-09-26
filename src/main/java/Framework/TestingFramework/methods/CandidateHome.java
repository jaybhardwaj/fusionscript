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

public class CandidateHome
{
	CommonMethods CMT;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String pageName = "Candidates";
	String pageSectionXpath = "//a[@aria-label='HR']";
	String PageXpath = "//a[@ui-sref='main.rms.candidate']";
	String pageMenuXpath = "//li[@ng-if='userModules.RMS']";
	
	public CandidateHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
	}
	
	@FindAll({
		@FindBy(xpath = "//md-tab-item[text()='Active']")
	})
	public WebElement activeTab;
	
	public boolean IsCandidatePresent(String candidate, String canStatus) throws InterruptedException
	{
		if(CMT.GotoRequiredPage("support@polestarllp.com1",pageSectionXpath,pageMenuXpath,pageName,PageXpath))
		{
			activeTab.click();
			WaitUtil.sleep(5000);
			List<WebElement> allRows = driver.findElements(By.xpath("//tr[@ng-repeat='item in candidateData']"));
			int rowCount = allRows.size();
			System.out.println("Total Candidates found = "+rowCount);
			for(WebElement rowElement:allRows)
			{
				WebElement candidateField = rowElement.findElement(By.xpath("td[2]"));
				WebElement SourceField = rowElement.findElement(By.xpath("td[8]/div"));
				WebElement StatusField = rowElement.findElement(By.xpath("td[11]"));
				String candidateName = candidateField.getText();
				String Source = SourceField.getText();
				String Status = StatusField.getText();
				System.out.println("candidate name found---"+candidateName);
				System.out.println("source name found---"+Source);
				System.out.println("candidate Status found---"+Status);
				if(candidateName.equalsIgnoreCase(candidate) && Source.equalsIgnoreCase("Campus Placement") && Status.equalsIgnoreCase(canStatus))
				{
					return true;
				}
			}
		}else
			{
				System.out.println("Some error occourred in GOTO Candidate Page.");
			}
		return false;			
	}

}