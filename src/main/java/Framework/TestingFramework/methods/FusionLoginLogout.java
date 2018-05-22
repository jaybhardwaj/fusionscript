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

public class FusionLoginLogout {
	
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	
	@FindAll({
		@FindBy(xpath = "//*[@id='nav-lg-login-btn']/button")
	})
	public WebElement SignInBtn;
	
	@FindAll({
		@FindBy(xpath = "//input[@type='email' and @ng-model='loginInfo.useremail']"),
		@FindBy(xpath = "//input[@type='email']")
	})
	public WebElement UserId;
	
	@FindAll({
		@FindBy(xpath = "//input[@type='password ']"),
		@FindBy(xpath = "//input[@type='password' and @ng-model='loginInfo.userpassword']")
	})
	public WebElement Pass;
	
	@FindAll({
		@FindBy(xpath = "//button[@type='submit']")
	})
	public WebElement LoginBtn;
	
	@FindAll({
		@FindBy(xpath = "//span[@class='fa fa-angle-down ng-scope']")
	})
	public WebElement LogOutMenuBtn;
	
	@FindAll({
		@FindBy(xpath = "//i[@class='fa fa-sign-out ng-scope']")
	})
	public WebElement LogOutBtn;
		
	public FusionLoginLogout(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
	}
	
	public boolean LoginToFusion(String emailId, String Password) throws InterruptedException
    {
			driver.navigate().to(BaseTest.inputs.get("baseUrl"));
			WaitUtil.sleep(2000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SignInBtn);
	       	SignInBtn.click();
	       	WaitUtil.sleep(2000);
	    	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.UserId);
	    	UserId.click();
	    	UserId.clear();
	    	UserId.sendKeys(emailId);
	    	WaitUtil.sleep(1000);
	    	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Pass);
	    	Pass.click();
	    	Pass.clear();
	    	Pass.sendKeys(Password);
	    	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.LoginBtn);
	    	LoginBtn.click(); 	
	    	WaitUtil.sleep(5000);
	    	if(driver.findElements(By.xpath("//span[@class='fa fa-angle-down ng-scope']")).size() != 0)
			{
	    		return true;
			}
    		return false;
		
    }
	public void LogOutFusion()
	{
		WaitUtil.sleep(2000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.LogOutMenuBtn);
		LogOutMenuBtn.click();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.LogOutBtn);
		LogOutBtn.click();
	}

}
