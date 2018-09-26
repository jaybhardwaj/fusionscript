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
		@FindBy(xpath = "//a[@ng-click='openLoginDialog($event)']")
	})
	public WebElement SignInBtn;
	
	@FindAll({
		@FindBy(xpath = "//form[@name='loginForm']//input[@ng-model='loginInfo.useremail']"),
		@FindBy(xpath = "//form[@name='loginForm']//input[@name='useremail']")
	})
	public WebElement UserId;
	
	@FindAll({
		@FindBy(xpath = "//form[@name='loginForm']//input[@name='userpassword']"),
		@FindBy(xpath = "//form[@name='loginForm']//input[@ng-model='loginInfo.userpassword']")
	})
	public WebElement Pass;
	
	@FindAll({
		@FindBy(xpath = "//form[@name='loginForm']//button[@submit-button='Log In']")
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
	    	WaitUtil.sleep(10000);
	    	if(driver.findElements(By.xpath("//span[@class='fa fa-angle-down ng-scope']")).size() != 0)
			{
	    		return true;
			}
	    	System.out.println("May be Session TimeOut.");
    		return false;
		
    }
	public void LogOutFusion()
	{
		WaitUtil.sleep(2000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.LogOutMenuBtn);
		LogOutMenuBtn.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.LogOutBtn);
		LogOutBtn.click();
	}

}
