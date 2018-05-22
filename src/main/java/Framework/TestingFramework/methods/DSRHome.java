package Framework.TestingFramework.methods;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;
import Framework.TestingFramework.utils.ExcelRead;
import Framework.TestingFramework.methods.FusionLoginLogout;
public class DSRHome 
{
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	
	@FindAll({
		@FindBy(xpath = "//a[contains(text(),' DSR ')]//i[@class='fa fa-list']")
	})
	public WebElement DsrMenuBtn;
		
	@FindAll({
		@FindBy(xpath = "//a[@href='/fill-dsr']")
	})
	public WebElement FillDsr;
	
	@FindAll({
		@FindBy(xpath = "//a[@href='/view-dsr']")
	})
	public WebElement ViewDsr;
	
	@FindAll({
		@FindBy(xpath = "//div[@class='ng-pristine ng-untouched ng-valid ta-bind ng-empty']")
	})
	public WebElement TextArea;
	@FindAll({
		@FindBy(xpath = "//button[@submit-loader='saveLoading']")
	})
	public WebElement SaveBtn;
	
	@FindAll({
		@FindBy(xpath = "//button[@submit-loader='submitLoading']")
	})
	public WebElement SubmitBtn;
		
	@FindAll({
		@FindBy(xpath = "//div[@class='md-toast-content']//button")
	})
	public WebElement ToastCancelBtn;
	
	public DSRHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
	}
	
	public boolean fillDsr(String username,String password,String DsrText)
	{
		FusionLoginLogout fusionloginlogout= new FusionLoginLogout(driver);
		String TextToFill=null;
		try 
		{
			
			boolean result = fusionloginlogout.LoginToFusion(username,password);
			if(result)
			{
				
				boolean FillDsrPresence = FillDsr.isDisplayed();
				boolean FillDsrEnabled = FillDsr.isEnabled();
				System.out.println(FillDsrPresence);
				System.out.println(FillDsrEnabled);
				
				if (FillDsrPresence==false || FillDsrEnabled==false)
				{
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.DsrMenuBtn);
					DsrMenuBtn.click();
					WaitUtil.sleep(1000);
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.FillDsr);
				    FillDsr.click();
					WaitUtil.sleep(3000);
				}
				if(DsrText==null)
				{
					String[][] textArray= DSRtextData();
					int Max=7;
					int Min=0;
					int randomNum = Min + (int)(Math.random() * ((Max - Min) + 1));
					System.out.println("text for this User-->"+username+"-->>"+textArray[0][randomNum]);
					TextToFill = textArray[0][randomNum];
				}
				else
				{
					TextToFill=DsrText;
				}
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.TextArea);
				TextArea.click();
		    	TextArea.sendKeys(TextToFill);
		       	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.SubmitBtn);
		       	SubmitBtn.click();
		    	WaitUtil.sleep(1000);
		    	WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.ToastCancelBtn);
		    	ToastCancelBtn.click();
		    	WaitUtil.sleep(1000);
		    	return true;
			} 
			else {
				return false;
			}
		}
    	catch (Exception e) 
    	{
			e.printStackTrace();
			return false;
    	}
		finally
		{
	        fusionloginlogout.LogOutFusion();
		}
	}
	
	public String[][] DSRtextData() throws IOException 
	{
		ExcelRead Exl2 = new ExcelRead();
		
		String[][] arrayObject = Exl2.readExcel("/home/psslass11153/Desktop","LoginDetails.xlsx","Sheet4");
		//System.out.println(arrayObject);
		return arrayObject;
	}

}
