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
	
//This method logins for parameter-user and leads to the page required.
	public Boolean GotoFeedbackPage(String user,String SectionXpath,String pageMenuXpath,String pageName,String pageXpath) throws InterruptedException
	{
		if(driver.findElements(By.xpath("//button[@ng-click='$mdMenu.open($event)']/span")).size() != 0)
		{
			//if already logined then do log-out.
			fusionloginlogout.LogOutFusion();
			WaitUtil.sleep(3000);
		}
		boolean Loginresult = fusionloginlogout.LoginToFusion(user,"Qwerty@1");
		if(Loginresult)
		{
			WebElement RequiredSection = driver.findElement(By.xpath(SectionXpath));
			//WebElement RequiredPageMenu=null;
			WebElement RequiredPage = driver.findElement(By.xpath(pageXpath));
			/*if(pageMenuXpath != null)
			{
				RequiredPageMenu = driver.findElement(By.xpath(pageMenuXpath));				
			}*/
			System.out.println("Login Successful");
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.PageHeading);
			String Heading= PageHeading.getText();
			System.out.println("We are at page-->"+Heading);
			if(Heading.equalsIgnoreCase(pageName))
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
					if(RequiredSection.isDisplayed() && RequiredSection.isEnabled())
						{
							WaitUtil.explicitWaitByVisibilityOfElement(driver, time, RequiredSection);
							RequiredSection.click();
							System.out.println("Inside RequiredSection");
						}
					if(driver.findElements(By.xpath(pageXpath)).size() != 0)
						{
							if(pageMenuXpath.equalsIgnoreCase("NotApplicable"))
							{
								WaitUtil.explicitWaitByVisibilityOfElement(driver, time, RequiredPage);
								RequiredPage.click();
								WaitUtil.sleep(5000);
							}else
								{
								    WebElement RequiredPageMenu = driver.findElement(By.xpath(pageMenuXpath));
								    WaitUtil.explicitWaitByVisibilityOfElement(driver, time, RequiredPageMenu);
									RequiredPageMenu.click();
									WaitUtil.sleep(1500);
									WaitUtil.explicitWaitByVisibilityOfElement(driver, time, RequiredPage);
									RequiredPage.click();
									WaitUtil.sleep(5000);
								}    
						}else
							{
								System.out.println(pageName+" Module is not given to this user");
								return false;
							}	
				}
		 }else{
			System.out.println("Login Unsuccessful");
			return false;
		      }	   
		return true;
	}
	
//This method finds supervisor (emailId-in resultSet[0]) of the parameter-user and user's full name(in resultSet[1]).	
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
