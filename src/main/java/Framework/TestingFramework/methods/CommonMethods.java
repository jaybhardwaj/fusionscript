package Framework.TestingFramework.methods;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.DatabaseUtil;
import Framework.TestingFramework.utils.WaitUtil;

public class CommonMethods 
{
	FusionLoginLogout fusionloginlogout;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	String supervisor=null;
	String UserFullName=null;
	
	@FindAll({
		@FindBy(xpath = "//a[@aria-label='HR']"),
		@FindBy(xpath = "//a/i [@class='fa fa-users']")		
	})
	public WebElement HRSection;
	@FindAll({
		@FindBy(xpath = "//a[contains(text(),'FEEDBACK')]"),
		@FindBy(xpath ="//a[@href='/feedback']")
	})
	public WebElement FeedBackMenuBtn;
	@FindAll({
		@FindBy(xpath = "//div[@class='nav_menu']//p[@class='ng-binding']"),
		@FindBy(xpath = "//div[@class='x_title clearfix layout-xs-column']/h2")
	})
	public WebElement PageHeading;
	@FindAll({
		@FindBy(xpath = "//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")
	})
	public WebElement ToggleMenuBarBtn;	
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
        fusionloginlogout= new FusionLoginLogout(driver);
	}
	
	public Boolean GotoFeedbackPage(String user) throws InterruptedException
	{
		//FusionLoginLogout fusionloginlogout= new FusionLoginLogout(driver);
		if(driver.findElements(By.xpath("//button[@ng-click='$mdMenu.open($event)']/span")).size() != 0)
		{
			fusionloginlogout.LogOutFusion();
			WaitUtil.sleep(3000);
		}
		
		boolean Loginresult = fusionloginlogout.LoginToFusion(user,"Qwerty@1");
		if(Loginresult)
		{
			System.out.println("Login Successful");
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.PageHeading);
			String Heading= PageHeading.getText();
			System.out.println("We are at page-->"+Heading);
			if(Heading.equalsIgnoreCase("Feedback"))
			{
				System.out.println("Already at Feedback Page.");
			}else
				{
					if(driver.findElements(By.xpath("//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")).size() != 0)
						{
						 System.out.println("Inside ToggleNav Bar");
							if(ToggleMenuBarBtn.isDisplayed() && ToggleMenuBarBtn.isEnabled())
								{  
									ToggleMenuBarBtn.click();
									WaitUtil.sleep(1000);
									System.out.println("Inside ToggleNav Bar Clicked");
								}
						}
					if(HRSection.isDisplayed() && HRSection.isEnabled())
						{
							System.out.println("Inside HR Section");
							WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.HRSection);
							HRSection.click();	
						}
					if(driver.findElements(By.xpath("//a[contains(text(),'FEEDBACK')]")).size() != 0)
						{
							WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FeedBackMenuBtn);
							FeedBackMenuBtn.click();
							WaitUtil.sleep(5000);
						}else
							{
								System.out.println("FeedBack Module is not given to this user");
								return false;
							}	
				}
		 }else{
			System.out.println("Login Unsuccessful");
			return false;
		      }	   
		return true;
	}
	
	public String[] findSupervisor(String user) throws ClassNotFoundException, SQLException
	{
		String query=null;
		String[] Result = new String[2];
		query="Select m.useremail,concat(e.firstname, ' ', e.lastname) as EmployeeName from mstemployee e left join mstemployee m on e.managerid=m.userid and m.isactive=1 where e.useremail='"+user+"' and e.isactive=1;";
		DatabaseUtil db = new DatabaseUtil();
			db.makeConnection();
			ResultSet result=db.runQuery(query);
			result.beforeFirst();
			if(result.next())
			{
				
				Result[0] = result.getString(1);
				Result[1]= result.getString(2);
			}
			//System. out.println(Result[0]);
			//System. out.println(Result[1]);
			db.ConnectionClose();
		return Result;
	}

}
