package Framework.TestingFramework.methods;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;


public class GoogleHomePage {
	
	JavascriptExecutor jse ;
	WebDriver driver;
	
	@FindAll({
		@FindBy(xpath = "//span[contains(text(), 'Contact Us')]"),
		@FindBy(xpath = "//div[contains(text(), 'Contact Us')]"),
		@FindBy(xpath = "//div[contains(text(), 'Customer Care')]"),
		@FindBy(xpath = "//a[contains(text(), 'Customer Care')]"),
		@FindBy(xpath = "//span[contains(text(), 'Customer Care')]"),
		@FindBy(xpath = "//div[@data-reactid='5']//span[contains(text(), 'Customer Care')]"),
		@FindBy(xpath = "//a[@ng-click='checkHeaderElementType(item,item.clickaction)']//div[text()='Customer Care']"),
	})
	public WebElement customercarelink;
	                  
	@FindAll({
		@FindBy(xpath = "//label[text()='Mobile Number']/../input"),
		@FindBy(xpath = "//div[text()='Enter Prepaid Mobile Number']/../input")
	})
	public WebElement numberinputfield;
	
	public GoogleHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		//jse.executeScript("arguments[0].scrollIntoView();", this.scroll);
		//WaitUtil.sleep(5000);
		//jse.executeScript("scroll(0, 150)");
	}
	
	public boolean verifyGooglePageTittle() {
		driver.navigate().to(BaseTest.inputs.get("baseUrl"));
		String getTitle = driver.getTitle();
		if(getTitle.equalsIgnoreCase("Google")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyPaytm1() {
		driver.get("https://paytm.com/");
		WaitUtil.sleep(3000);
		int time = Integer.parseInt(BaseTest.env.get("waitTime"));
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.customercarelink);
		WaitUtil.sleep(3000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.numberinputfield);
		this.numberinputfield.click();
		WaitUtil.sleep(1000);
		this.numberinputfield.sendKeys("@#$%^&*@#$");
		WaitUtil.sleep(1000);
		String text = this.numberinputfield.getAttribute("value");
		if(text.equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
