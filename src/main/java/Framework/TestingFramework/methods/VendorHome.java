package Framework.TestingFramework.methods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import com.github.javafaker.Faker;
import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.WaitUtil;

public class VendorHome 
{
	CommonMethods CMT ;
	WebDriver driver;
	JavascriptExecutor jse ;
	int time = Integer.parseInt(BaseTest.env.get("waitTime"));
	
	public VendorHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
		CMT = new CommonMethods(driver);
	}
//  ******************common xpath to select option 1 and 2 in any select/multiSelect drop down**************
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[1]")
	})
	public WebElement Option1;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[2]")
	})
	public WebElement Option2;
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,' md-active')]//md-option[3]")
	})
	public WebElement Option3;
//	****************  Vendor Screen-profile menu Xpaths  ****************************
	@FindAll({
		@FindBy(xpath = "//li[@class='dropdown']//i")
	})
	public WebElement logOutMenu;
	@FindAll({
		@FindBy(xpath = "//a[@ng-click='vendorlogout()']")
	})
	public WebElement logOutBtn;
	@FindAll({
		@FindBy(xpath = "//a[text()='Change Password']")
	})
	public WebElement changePassBtn;
//	****************  Vendor Screen-Login Xpaths  ****************************
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorLoginForm']//input[@ng-model='editInfo.username']"),
		@FindBy(xpath = "//form[@name='vendorLoginForm']//input[@name='username']")
	})
	public WebElement loginEmail;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorLoginForm']//input[@ng-model='editInfo.password']"),
		@FindBy(xpath = "//form[@name='vendorLoginForm']//input[@name='password']")
	})
	public WebElement loginPassword;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorLoginForm']//button[@ng-click='loginVendor()']"),
		@FindBy(xpath = "//form[@name='vendorLoginForm']//button[@submit-button='Login']")
	})
	public WebElement loginBtn;
	@FindAll({
		@FindBy(xpath = "//a[@ng-click='forgetPassword()']")
	})
	public WebElement helpBtn;
//	****************  Vendor Screen-Registration Xpaths  ****************************
	@FindAll({
		@FindBy(xpath = "//a[@ng-click='newVendor()']")
	})
	public WebElement registerBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorSignUpForm']//input[@ng-model='editInfo.email_id']"),
		@FindBy(xpath = "//form[@name='vendorSignUpForm']//input[@name='email_id']")
	})
	public WebElement registerEmail;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorSignUpForm']//button[@ng-click='signUpVendor()']"),
		@FindBy(xpath = "//form[@name='vendorSignUpForm']//button[@submit-button='Start Registration']")
	})
	public WebElement startRegistrationBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorSignUpForm']//a[@ng-click='backToLogin()']")
	})
	public WebElement backToLoginBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='forgetForm']//input[@name='email_id']"),
		@FindBy(xpath = "//form[@name='forgetForm']//input[@ng-model='editInfo.email_id']")
	})
	public WebElement forgetPasswordEmail;
	@FindAll({
		@FindBy(xpath = "//form[@name='forgetForm']//button[@ng-click='forgetVendor()']"),
		@FindBy(xpath = "//form[@name='forgetForm']//button[@submit-button='Request OTP']")
	})
	public WebElement requestOTP;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorOTPForm']//input[@ng-model='editInfo.otp']"),
		@FindBy(xpath = "//form[@name='vendorOTPForm']//button[@ng-readonly='editInfo.otp']")
	})
	public WebElement enterOTP;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorOTPForm']//button[@ng-click='submitOTP()']"),
		@FindBy(xpath = "//input[@name='password']")
	})
	public WebElement submitOtpBtn;
	@FindAll({
		@FindBy(xpath = "//form[@name='createPasswordForm']//input[@ng-model='editInfo.n_password']"),
		@FindBy(xpath = "//form[@name='createPasswordForm']//input[@name='n_password']")
	})
	public WebElement password1;
	@FindAll({
		@FindBy(xpath = "//form[@name='createPasswordForm']//input[@ng-model='editInfo.c_password']"),
		@FindBy(xpath = "//form[@name='createPasswordForm']//input[@name='c_password']")
	})
	public WebElement password2;
	@FindAll({
		@FindBy(xpath = "//form[@name='createPasswordForm']//button[@ng-click='createPassword()']")
	})
	public WebElement createPassBtn;
//	****************  Vendor Screen-Basic Details Xpaths  ***************************
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorProfileForm']//input[@ng-model='vendorDetail.name']"),
		@FindBy(xpath = "//form[@name='vendorProfileForm']//input[@name='name']")
	})
	public WebElement vendorName;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorProfileForm']//input[@ng-model='vendorDetail.emailid']"),
		@FindBy(xpath = "//form[@name='vendorProfileForm']//input[@name='emailid']")
	})
	public WebElement vendorEmail;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorProfileForm']//input[@ng-model='vendorDetail.contactnumber']"),
		@FindBy(xpath = "//form[@name='vendorProfileForm']//input[@name='contactnumber']")
	})
	public WebElement contactNo;
	@FindAll({
		@FindBy(xpath = "//form[@name='vendorProfileForm']//textarea[@ng-model='vendorDetail.address']"),
		@FindBy(xpath = "//form[@name='vendorProfileForm']//textarea[@name='address']")
	})
	public WebElement address;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='vendorDetail.city']"),
		@FindBy(xpath = "//md-select[@name='city']")
	})
	public WebElement citySelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='vendorDetail.pincode']"),
		@FindBy(xpath = "//input[@name='pincode']")
	})
	public WebElement pinCode;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='vendorDetail.skills']"),
		@FindBy(xpath = "//md-select[@name='skills']")
	})
	public WebElement skillSelect;
	@FindAll({
		@FindBy(xpath = "//input[@name='save']")//common for all pages
	})
	public WebElement saveBtn;
	@FindAll({
		@FindBy(xpath = "//button[@name='submit']")//common for all pages
	})
	public WebElement submitBtn;
	@FindAll({
		@FindBy(xpath = "//input[@name='next']")
	})
	public WebElement nextBtn;
//	****************   Vendor Screen-commercial Details Xpaths  *********************
	@FindAll({
		@FindBy(xpath = "//a[@href='#commercial']")
	})
	public WebElement commercialTab;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='vendorDetail.pan']"),
		@FindBy(xpath = "//input[@name='pan']")
	})
	public WebElement panNo;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='vendorDetail.servicetaxrno']"),
		@FindBy(xpath = "//input[@name='servicetaxrno']")
	})
	public WebElement comRegNo;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='vendorDetail.cstno']"),
		@FindBy(xpath = "//input[@name='cstno']")
	})
	public WebElement gstNo;
//	****************   Vendor Screen-Bank Details Xpaths  ***************************
	@FindAll({
		@FindBy(xpath = "//a[@href='#bank']")
	})
	public WebElement bankTab;
	@FindAll({
		@FindBy(xpath = "//md-select[@ng-model='vendorDetail.bankname']"),
		@FindBy(xpath = "//md-select[@name='bankname']")
	})
	public WebElement bankSelect;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='vendorDetail.bankaccountno']"),
		@FindBy(xpath = "//input[@name='bankaccountno']")
	})
	public WebElement bankAccNo;
	@FindAll({
		@FindBy(xpath = "//input[@ng-model='vendorDetail.bankifsccode']"),
		@FindBy(xpath = "//input[@name='bankifsccode']")
	})
	public WebElement IfscCode;
	@FindAll({
		@FindBy(xpath = "//textarea[@ng-model='vendorDetail.bankbranch']")
	})
	public WebElement bankBranchAddress;
//	****************   Vendor Screen-Document Details Xpaths  ***************************
	@FindAll({
		@FindBy(xpath = "//a[@href='#documents']")
	})
	public WebElement documentTab;
	@FindAll({
		@FindBy(xpath = "//h2[text()='PAN Card']/..//button")
	})
	public WebElement uploadPanBtn;
	@FindAll({
		@FindBy(xpath = "//h2[text()='Registration No']/..//button")
	})
	public WebElement uploadRegBtn;
	@FindAll({
		@FindBy(xpath = "//h2[text()='Company Certificate']/..//button")
	})
	public WebElement uploadCompBtn;
	@FindAll({
		@FindBy(xpath = "//h2[text()='Cancelled Cheque']/..//button")
	})
	public WebElement uploadCancelledChequeBtn;
	@FindAll({
		@FindBy(xpath = "//h2[text()='VAT/CST Registration']/..//button")
	})
	public WebElement uploadVatBtn;
	@FindAll({
		@FindBy(xpath = "//div[@ng-if='vendorDetail.vendorstatus']//span")
	})
	public WebElement statusMsg;
	@FindAll({
		@FindBy(xpath = "//strong/..//div")
	})
	public WebElement reasonMsg;
//		*********************************************************************************
	
	private boolean openVendorScreen()
	{	
		if(driver.findElements(By.xpath("//h4[text()='Login as Vendor']")).size() != 0)
		    {
		    	System.out.println("Vendor Login Screen is already opened in new tab.");
		    	return true;
		    }
		else if(driver.findElements(By.xpath("//li[@class='dropdown']//i")).size() != 0)
			{
				System.out.println("Vendor is logined.");
				return logOut();
			}
		else
		    {
				driver.navigate().to(BaseTest.inputs.get("baseUrl"));
				System.out.println("Main Fusion tab is opened.");
				WaitUtil.sleep(10000);
		    	jse.executeScript("window.open();");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
			    driver.get("http://139.162.47.185:1337/vendor");
			    System.out.println("Fusion vendor tab is opened.");
			    WaitUtil.sleep(10000);
			    if(driver.findElements(By.xpath("//h4[text()='Login as Vendor']")).size() != 0)
			    {
			    	System.out.println("Vendor Login Screen is opened in new tab.");
			    	return true;
			    }	    	
		    }	
	    return false;	    
	}

	public boolean loginAsVendor(String vendorId) throws InterruptedException
	{
		if(driver.findElements(By.xpath("//li[@class='dropdown']//i")).size() != 0)
		{
			System.out.println("Already logined.So loging it Out.");
			logOut();
		}
		if(openVendorScreen())
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.loginEmail);
			loginEmail.click();
			loginEmail.clear();
			loginEmail.sendKeys(vendorId);
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.loginPassword);
			loginPassword.click();
			loginPassword.clear();
			WaitUtil.sleep(1000);
			loginPassword.sendKeys("Qwerty@1");
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.loginPassword);
			loginBtn.click();
			WaitUtil.sleep(7000);
			if(driver.findElements(By.xpath("//i[text()='content_paste']")).size() != 0)
		    {
		    	System.out.println("Vendor Login is successful.");
		    	return true;
		    }else
			    {
			    	System.out.println("vendor login took too long more than 10 seconds.");
			    	return false;
			    }
		}else
			{
				System.out.println("******** Vendor Login failed.Some error occourred in opening vendor Screen.*******");
				return false;
			}
	}
	
	public boolean fillBasicDetails(String vendorID) throws IOException
	{
		Faker faker = new Faker();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.vendorName);
		vendorName.click();
		vendorName.clear();
		vendorName.sendKeys(faker.name().fullName());
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.vendorEmail);
		vendorEmail.click();
		vendorEmail.clear();
		vendorEmail.sendKeys(vendorID);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.contactNo);
		contactNo.click();
		contactNo.clear();
		contactNo.sendKeys(CMT.getRandomNum(10));
		WaitUtil.sleep(1000);
		String adrress =faker.address().streetAddress()+", "+faker.address().state()+", "+faker.address().country();
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.address);
		address.click();
		address.clear();
		address.sendKeys(adrress);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.citySelect);
		citySelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.pinCode);
		pinCode.click();
		pinCode.clear();
		pinCode.sendKeys("201301");
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.skillSelect);
		skillSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(1000);
		Option2.click();
		WaitUtil.sleep(1000);
		Option3.click();
		WaitUtil.sleep(1000);
		Option3.sendKeys(Keys.TAB);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveBtn);
		saveBtn.click();
		String Msg = CMT.getToastMsg();
		System.out.println("vendor save Msg -"+Msg);
		if(Msg.equalsIgnoreCase("User information saved successfully."))
		{ 
			WaitUtil.sleep(2000);
			return true; 
		}
		return false;
	}

	private boolean fillCommercialDetails()
	{
		if(commercialTab.isDisplayed() && commercialTab.isEnabled())
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.commercialTab);
			commercialTab.click();
			WaitUtil.sleep(2000);
		}
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.panNo);
		panNo.click();
		panNo.clear();
		panNo.sendKeys("AAAPL1234C");
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.comRegNo);
		comRegNo.click();
		comRegNo.clear();
		comRegNo.sendKeys("test2018");
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.gstNo);
		gstNo.click();
		gstNo.clear();
		gstNo.sendKeys("22AAAAA0000A1Z5");
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveBtn);
		saveBtn.click();
		String Msg = CMT.getToastMsg();
		System.out.println("vendor save Msg -"+Msg);
		if(Msg.equalsIgnoreCase("User information saved successfully."))
		{ 
			WaitUtil.sleep(2000);
			return true; 
		}
		return false;
	}

	private boolean fillBankDetails()
	{
		if(bankTab.isDisplayed() && bankTab.isEnabled())
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.bankTab);
			bankTab.click();
			WaitUtil.sleep(2000);
		}
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.bankSelect);
		bankSelect.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.Option1);
		Option1.click();
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.bankAccNo);
		bankAccNo.click();
		bankAccNo.clear();
		bankAccNo.sendKeys(CMT.getRandomNum(15));
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.IfscCode);
		IfscCode.click();
		IfscCode.clear();
		IfscCode.sendKeys("SBIN0000001");
		WaitUtil.sleep(1000);
		Faker faker = new Faker();
		String adrress =faker.address().streetAddress()+", "+faker.address().state()+", "+faker.address().country();
		bankBranchAddress.click();
		bankBranchAddress.clear();
		bankBranchAddress.sendKeys(adrress);
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveBtn);
		saveBtn.click();
		String Msg = CMT.getToastMsg();
		System.out.println("vendor save Msg -"+Msg);
		if(Msg.equalsIgnoreCase("User information saved successfully."))
		{ 
			WaitUtil.sleep(2000);
			return true; 
		}
		return false;		
	}

	private boolean uploadDocuments(String action) throws FindFailed, ClassNotFoundException, SQLException, IOException
	{
		String resumepath = "/home/psslass11153/Pictures/sikuli/Bank Letter.docx";
		if(documentTab.isDisplayed() && documentTab.isEnabled())
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.documentTab);
			documentTab.click();
			WaitUtil.sleep(2000);
		}
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.uploadPanBtn);
		uploadPanBtn.click();
		CMT.doUploadSikuli(resumepath);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.uploadRegBtn);
		uploadRegBtn.click();
		CMT.doUploadSikuli(resumepath);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.uploadCompBtn);
		uploadCompBtn.click();
		CMT.doUploadSikuli(resumepath);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.uploadCancelledChequeBtn);
		uploadCancelledChequeBtn.click();
		CMT.doUploadSikuli(resumepath);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.uploadVatBtn);
		uploadVatBtn.click();
		CMT.doUploadSikuli(resumepath);
		WaitUtil.sleep(1000);
		if(action.equalsIgnoreCase("submit"))
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.submitBtn);
			submitBtn.click();
		}else
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.saveBtn);
				saveBtn.click();
			}
		String Msg = CMT.getToastMsg();
		System.out.println("vendor save Msg -"+Msg);
		if(action.equalsIgnoreCase("submit") && Msg.equalsIgnoreCase("User information submitted successfully."))
		{ 
			WaitUtil.sleep(2000);
			if(driver.findElements(By.xpath("//div[@ng-if='vendorDetail.vendorstatus']//span")).size() != 0)
		    {
				String status = statusMsg.getText();
		    	System.out.println("Vendor application status--> "+status);
		    	if(status.equalsIgnoreCase("Your Application is Pending"))
		    	{
		    		return logOut();
		    	}		    	
		    } 
		}
		else if(action.equalsIgnoreCase("save") && Msg.equalsIgnoreCase("User information saved successfully."))
			{
				WaitUtil.sleep(1000);
				return true;
			}
		return false;	
	}

	public boolean register(String vendorId,String password) throws IOException, InterruptedException 
	{
		if(openVendorScreen())
		{
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.registerBtn);
			registerBtn.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.registerEmail);
			registerEmail.click();
			registerEmail.clear();
			registerEmail.sendKeys(vendorId);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.startRegistrationBtn);
			startRegistrationBtn.click();
			String Msg1 = CMT.getToastMsg();
			System.out.println("vendor Registration Msg after submitting EmailID-"+Msg1);
			if(Msg1.equalsIgnoreCase("OTP has been send successfully"))
			{
				String mailText = CMT.getEmailText(vendorId,password, "OTP For Login", "support@polestarllp.com", "Your OTP is:");
				System.out.println("mailText receieved is  = "+mailText);
				String[] temp = mailText.split(":");
				String OTP = temp[1].trim();
				if(OTP != null)
				{
					WaitUtil.sleep(1000);
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.enterOTP);
					enterOTP.click();
					enterOTP.clear();
					enterOTP.sendKeys(OTP);
					WaitUtil.sleep(1000);
					WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.submitOtpBtn);
					submitOtpBtn.click();
					String Msg2 = CMT.getToastMsg();
					System.out.println("vendor Registration Msg after submitting OTP--> "+Msg2);
					if(Msg2.equalsIgnoreCase("OTP verified successfully"))
					{
						WaitUtil.sleep(2000);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.password1);
						password1.click();
						password1.clear();
						password1.sendKeys("Qwerty@1");
						WaitUtil.sleep(1000);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.password2);
						password2.click();
						password2.clear();
						password2.sendKeys("Qwerty@1");
						WaitUtil.sleep(1000);
						WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.createPassBtn);
						createPassBtn.click();
						String Msg3 = CMT.getToastMsg();
						System.out.println("vendor password Reset Msg-->> "+Msg3);
						if(Msg3.equalsIgnoreCase("Password updated successfully"))
						{
							System.out.println("Vendor is registered successfully.Now you can login as vendor.");
							WaitUtil.sleep(2000);
							if(loginAsVendor(vendorId))
							{
								System.out.println("Vendor successfully logined with resetted password.");
								WaitUtil.sleep(3000);
								return true;
							}
						}
					}else
						{
							System.out.println("OTP verification failed.Wrong OTP received.");
						}
				}else
					{
						System.out.println("OTP is not found at registering EmailID.");
						return false;
					}
			}else
				{
					System.out.println("OTP is not sent by system.");
				}
		}else
			{
				System.out.println("There was some error in opening Vendor Screen in new TAB.");
				return false;
			}
		return false;
	}
	
	private boolean logOut()
	{
		if(driver.findElements(By.xpath("//li[@class='dropdown']//i")).size() != 0)
	    {
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.logOutMenu);
			logOutMenu.click();
			WaitUtil.sleep(1000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.logOutBtn);
			logOutBtn.click();
	    	WaitUtil.sleep(3000);
	    	if(driver.findElements(By.xpath("//h4[text()='Login as Vendor']")).size() != 0)
		    {
		    	System.out.println("Vendor is logout successfully.");
		    	return true;
		    }
	    }else
		    {
		    	System.out.println("LogOut menu is not visible.vendor is not logined.");
		    }
		return false;
	}
	

	public boolean CompleteRegistration(String vendorId, String Password,String action) throws IOException, FindFailed, ClassNotFoundException, SQLException, InterruptedException
	{
		if(register(vendorId,Password))
		{
			if(fillBasicDetails(vendorId))
			{
				if(fillCommercialDetails())
				{
					if(fillBankDetails())
					{
						System.out.println("Vendor is registered and info is filled Upto bank Details.");
						return uploadDocuments(action);
					}
				}
			}
		}
		return false;		
	}

	public boolean verifyStatusVendor(String VendorID, String role, String action) throws InterruptedException
	{
		System.out.println("VerifyStatusVendor Method is called.");
		if(loginAsVendor(VendorID))
		{
			if(driver.findElements(By.xpath("//div[@ng-if='vendorDetail.vendorstatus']//span")).size() != 0)
		    {
				String status = statusMsg.getText();
		    	System.out.println("For Verification---Vendor application status--> "+status);
		    	switch (action)
		    	{
		    		case "Approve":
		    			if(role.equalsIgnoreCase("HR User") && status.equalsIgnoreCase("Your Application is Pending"))
			    		{
		    				switchTab();
			    			return true;
			    		}
			    		else if(role.equalsIgnoreCase("HR Admin") && status.equalsIgnoreCase("Your Application is Accepted"))
			    		{
			    			switchTab();
			    			return true;
			    		}
		    		case "Reject":
		    			if(status.equalsIgnoreCase("Your Application is Rejected"))
				    	{
		    				switchTab();
				    		return true;
				    	}
		    		case "Re Submit":
		    			String Reason = reasonMsg.getText();
		    			if(status.equalsIgnoreCase("Please Re Submit your Application.") && Reason.equalsIgnoreCase("This is to test functionality of - "+action+" for "+role))
				    	{
		    				switchTab();
				    		return true;
				    	}
		    		case "default":
		    			System.out.println("Please send right action parameter.");
		    			switchTab();
		    			return false;
		    	}		    	
		    }
			else
			    {
			    	System.out.println("Verification failed as No Status MSg is visible to Vendor.");
			    	switchTab();
			    	return false;
			    }
		}
		switchTab();
		return false;
	}
	
	private boolean switchTab()
	{
		System.out.println("switch Tab method Is called.");
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("total tabs opened = "+handles.size());
		String  CurrentHandle= driver.getWindowHandle();//Return a string of alphanumeric current window handle
		for(int i=0;i<2;i++)
		{
			if(!CurrentHandle.equalsIgnoreCase(handles.get(i)))
			{
				driver.switchTo().window(handles.get(i));
				System.out.println("tab is finally switched..");
			}
		}
		return true;
	}
}
