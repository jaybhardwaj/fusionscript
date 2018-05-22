package Framework.TestingFramework.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;

public class FusionTheme 
{
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	
	@FindAll({
		@FindBy(xpath = "//div[@id='style-switcher']//a")
	})
	public WebElement ThemeMenuBtn;
	
	@FindAll({
		@FindBy(xpath = "//div[@class='style-switcher-wrap']//md-tab-item[text()='Nav-Bar']")
	})
	public WebElement NavBarTab;
	
	@FindAll({
		@FindBy(xpath = "//md-checkbox[@aria-label='collapsed Navbar']")
	})
	public WebElement CheckOption;
	
	@FindAll({
		@FindBy(xpath = "//button[@ng-click='updateTheme()']")
	})
	public WebElement ApplyBtn;
	
	@FindAll({
		@FindBy(xpath = "//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")
	})
	public WebElement ToggleMenuBarBtn;
	
	public FusionTheme(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
	}
	
	public String CheckCollapsabilityOfNavBar(String username,String password,String ReqAction) throws InterruptedException
	{
		String Status=null;
		FusionLoginLogout fusionloginlogout= new FusionLoginLogout(driver);
		if(driver.findElements(By.xpath("//span[@class='fa fa-angle-down ng-scope']")).size() != 0)
		{
			fusionloginlogout.LogOutFusion();
		}
		boolean result = fusionloginlogout.LoginToFusion(username,password);
		if(result)
		{
			System.out.println("Login Successful");
			WaitUtil.sleep(5000);
			if(driver.findElements(By.xpath("//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")).size() != 0)
			{Status="Enabled";}
			else{Status="Disabled";}
			System.out.println("Present Status-->"+Status);
			if(ReqAction.equalsIgnoreCase("Enable") && Status.equalsIgnoreCase("Disabled"))
				{
					Status=enableDisableCollapseMenuBar();
					System.out.println("wants to do-->"+Status);
				}
			else if(ReqAction.equalsIgnoreCase("Disable") && Status.equalsIgnoreCase("Enabled"))
				{
					Status=enableDisableCollapseMenuBar();
					System.out.println("wants to do-->"+Status);
				}
			else
				{
					Status="Invalid Request";
					System.out.println("Req-"+Status);
				}
		}
		else
		{
			System.out.println("Login Unsucessful");
			return Status;
		}
		return Status;
		
	}
	
	public String enableDisableCollapseMenuBar()
	{
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ThemeMenuBtn);
		ThemeMenuBtn.click();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.NavBarTab);
		NavBarTab.click();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.CheckOption);
		CheckOption.click();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ApplyBtn);
		ApplyBtn.click();
		WaitUtil.sleep(5000);
		if(driver.findElements(By.xpath("//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")).size() != 0)
		 { return "Enabled"; }
		else
		 { return "Disabled"; }

		
		
	}

}
