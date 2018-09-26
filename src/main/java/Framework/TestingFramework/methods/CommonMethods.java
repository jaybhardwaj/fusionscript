package Framework.TestingFramework.methods;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import Framework.TestingFramework.base.BaseTest;
import Framework.TestingFramework.utils.DatabaseUtil;
import Framework.TestingFramework.utils.ExcelRead;
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
		@FindBy(xpath = "//button[@ng-click='$mdMenu.open($event)']/img")
	})
	public WebElement profileMenuBtn;
	@FindAll({
		@FindBy(xpath = "//a[@href='/profileView']"),
		@FindBy(xpath = "//a[@ui-sref='main.profile.myprofile']")
	})
	public WebElement profile;
	@FindAll({
		@FindBy(xpath = "//p[@ng-if='userInfo.useremail']")
	})
	public WebElement profileEmail;
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
		@FindBy(xpath = "//div[@class='nav_menu']//li[contains(@class,'flex')]//p")
	})
	public WebElement PageHeading;
	@FindAll({
		@FindBy(xpath = "//div[@ng-if='!toggleNavbarScreen']//i[@class='fa fa-bars']")
	})
	public WebElement ToggleMenuBarBtn;
	@FindAll({
		@FindBy(xpath = "//div[@ id='cube-close']//md-icon[text()='clear']")
	})
	public WebElement cubeClose;
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor)driver;
        fusionloginlogout= new FusionLoginLogout(driver);
	}
	@FindAll({
		@FindBy(xpath = "//span[@class='md-toast-text ng-binding flex']")
	})
	public WebElement toast;
	@FindAll({
		@FindBy(xpath = "//div[@class='md-toast-content']//button")
	})
	public WebElement toastCancelBtn;
//This method logins for parameter-user and leads to the page required.
	public Boolean GotoRequiredPage(String user,String SectionXpath,String pageMenuXpath,String pageName,String pageXpath) throws InterruptedException
	{
		WaitUtil.sleep(5000);
		boolean Loginresult = false;
		if(driver.findElements(By.xpath("//button[@ng-click='$mdMenu.open($event)']/img")).size() != 0)
		{
			System.out.println("inside profile section");
//if same user is already logined then do not log-out.
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.profileMenuBtn);
			profileMenuBtn.click();
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.profile);
			profile.click();
			WaitUtil.sleep(2000);
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.profileEmail);
			String loginedUser = profileEmail.getText();
			if(loginedUser.equalsIgnoreCase(user))
			{
				Loginresult = true;
				driver.navigate().back();
				WaitUtil.sleep(5000);
			}else
				{
//if different user is logined then do log-out.
					fusionloginlogout.LogOutFusion();
					WaitUtil.sleep(3000);
					Loginresult = fusionloginlogout.LoginToFusion(user,"Qwerty@1");
				}	
		}else
			{
				Loginresult = fusionloginlogout.LoginToFusion(user,"Qwerty@1");
			}
		if(Loginresult)
		{
			System.out.println("Login Successful");
			if(cubeClose.isDisplayed() && cubeClose.isEnabled())
			{
				WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.cubeClose);
				cubeClose.click();
				WaitUtil.sleep(2000);			
			}
			WebElement RequiredSection = driver.findElement(By.xpath(SectionXpath));
			WebElement RequiredPage = driver.findElement(By.xpath(pageXpath));
			WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.PageHeading);
			String Heading= PageHeading.getAttribute("innerHTML");
			System.out.println("We are at page-->"+Heading);
			if(Heading.equalsIgnoreCase(pageName))
			{
				System.out.println("Already at "+pageName+"  Page.");
			}else
				{
//This block handles left side toggle menu bar
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
								WaitUtil.sleep(1000);
								WaitUtil.explicitWaitByVisibilityOfElement(driver, time, RequiredPage);
								RequiredPage.click();								
							}
//If user is at some other page of required module.
							else if(RequiredPage.isDisplayed() && RequiredPage.isEnabled())
							{
								WaitUtil.explicitWaitByVisibilityOfElement(driver, time, RequiredPage);
								RequiredPage.click();
								System.out.println("we are at your required page named as "+pageName);								
							}
//If user is at some other module.							
							else
								{
								    WebElement RequiredPageMenu = driver.findElement(By.xpath(pageMenuXpath));
								    WaitUtil.explicitWaitByVisibilityOfElement(driver, time, RequiredPageMenu);
									RequiredPageMenu.click();
									WaitUtil.sleep(2500);
									WaitUtil.explicitWaitByVisibilityOfElement(driver, time, RequiredPage);
									RequiredPage.click();
								}
							WaitUtil.sleep(5000);
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
				if(result.getString(1).equalsIgnoreCase("support@polestarllp.com"))
				{ Result[0] = result.getString(1)+"1"; }
				else{ Result[0] = result.getString(1); }
				
				Result[1]= result.getString(2);
			}
			//System. out.println(Result[0]);
			//System. out.println(Result[1]);
			db.ConnectionClose();
		return Result;
	}
	
//This method runs query and and returns result in array (size = rowSize*colSize).
	public String[] runQuery(String temp_query,int rowSize,int colSize) throws ClassNotFoundException, SQLException 
	{
		String query=temp_query;
		DatabaseUtil db = new DatabaseUtil();
	    db.makeConnection();
		ResultSet result=db.runQuery(query);
		int size = 0;
	    if (result.last()) 
	     {
	       size = result.getRow();
	     }
		System.out.println("size of result set="+size);
		if(size<rowSize)
		{
			System.out.println("Insuffiecient records in DB to cater your request");
			return null;
		}
		size = rowSize*colSize;
		String[] Result = new String[size];
		System.out.println("size of returning result set final="+Result.length);
		result.beforeFirst();
		int c=0;
		while(result.next())
		  {
				for(int i=c,j=1;j<=colSize;j++)
				{
					Result[i]= result.getString(j);
					i=i+1;
				}
				c=c+colSize;
		  }
	   for(int i=0;i<Result.length;i++)
		{
			System.out.println(Result[i]);
		}
		db.ConnectionClose();
		return Result;
	}

//	This method returns records i.e-num of records present in DB w.r.t received query.
	public int getRecordSizeInDB(String temp_query) throws ClassNotFoundException, SQLException 
	{
		String query=temp_query;
		DatabaseUtil db = new DatabaseUtil();
	    db.makeConnection();
		ResultSet result=db.runQuery(query);
		int size = 0;
		result.beforeFirst();
	    if (result.last()) 
	     {
	       size = result.getRow();
	     }
		System.out.println("size of result set="+size);
		db.ConnectionClose();
		return size;
	}

	public String[][] get_ExcelData(String excelName, String sheetName) throws IOException
	{
		ExcelRead Exl2 = new ExcelRead();
		String[][] arrayObject = Exl2.readExcel("src//main//java//Framework//TestingFramework//testInput//",excelName,sheetName);
		return arrayObject;
	}
	
	public String getToastMsg()
	{
		WaitUtil.sleep(1000);
		WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.toast);
        String Msg=toast.getText();
        WaitUtil.explicitWaitByVisibilityOfElement(driver, time, this.toastCancelBtn);
 	   	toastCancelBtn.click();
 	   	WaitUtil.sleep(2000);
		return Msg;
		
	}
  
    public String getEmailText(String userName,String password, final String subjectKeyword, final String fromEmail, final String bodySearchText) throws IOException 
    {
        Properties properties = new Properties();
        String OTP = null;
        // server setting
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", 993);
        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port",String.valueOf(993));
        Session session = Session.getDefaultInstance(properties);
    try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);
            System.out.println("Connected to Email server….");
            // opening the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);
            //creating a search term for all “unseen” messages
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, true);
            //creating a search term for all recent messages
            Flags recent = new Flags(Flags.Flag.RECENT);
            FlagTerm recentFlagTerm = new FlagTerm(recent, false);
            SearchTerm searchTerm = new OrTerm(unseenFlagTerm,recentFlagTerm);
            // performing search through the folder
            Message[] foundMessages = folderInbox.search(searchTerm);
            // traversing first 10 mails received
            for (int i=foundMessages.length-1; i>=foundMessages.length-10;i--) 
            {
                Message message = foundMessages[i];
                Address[] froms = message.getFrom();
                String email = froms == null ? null : ((InternetAddress)froms[0]).getAddress();
                if(message.getSubject()==null)
                {
                	continue;
                }
                Date date = new Date();//Getting Present date from the system
				long diff = date.getTime()-message.getReceivedDate().getTime();//Get The difference between two dates
				long diffMinutes = diff / (60 * 1000) % 60; //Fetching the difference of minute
				try {
						if(message.getSubject().contains(subjectKeyword) &&email.equals(fromEmail) && getTextFromMessage(message).contains(bodySearchText) && diffMinutes<=5)
						{
							/*System.out.println(getTextFromMessage(message));
							System.out.println("Found message #" + i + ": ");
							System.out.println("At "+ i + " :"+ "Subject:"+ subject);
							System.out.println("From: "+ email +" on : "+message.getReceivedDate());*/
							if(getTextFromMessage(message).contains(bodySearchText)== true)
							{
								String[] temp = getTextFromMessage(message).split(":");
								String msgText = temp[1].trim();
								OTP=msgText;
							}
							else{
									OTP=null;
								}
							break;
						}
					} 
					catch (NullPointerException expected) 
					{
						expected.printStackTrace();
					}
            }
            // disconnecting
            folderInbox.close(false);
            store.close();
        } 
    	catch (NoSuchProviderException ex) 
        	{
            	System.out.println("No provider.");
            	ex.printStackTrace();
        	}
      	catch (MessagingException ex) 
        	{
        	  	System.out.println("Could not connect to the message store.");
        	  	ex.printStackTrace();
        	}
        return OTP;
    }

//	This method is used by getEmailText-Method
    private String getTextFromMessage(Message message) throws MessagingException, IOException
    {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }
 
//	This method is used by getTextFromMessage(Message message)-Method
    private String getTextFromMimeMultipart( MimeMultipart mimeMultipart )  throws MessagingException, IOException
    {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) 
        {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) 
            {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } 
            else if (bodyPart.isMimeType("text/html")) 
            {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } 
            else if (bodyPart.getContent() instanceof MimeMultipart)
            {
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }

//	This method is used to upload file ( using button ) using sikuli
    public boolean doUploadSikuli(String filePath) throws FindFailed
	{
		WaitUtil.sleep(2000);
		System.out.println("doUploadSikuli is called with resumePath = "+filePath);
		String imagePath = "/home/psslass11153/Pictures/sikuli/";
        Screen sikuliObj = new Screen();
        Pattern openSearchBtn = new Pattern(imagePath + "openSearch.png");
        Pattern fileInputTextBox = new Pattern(imagePath + "locationInput.png");
        Pattern openBtn = new Pattern(imagePath + "openFile.png");
        if(sikuliObj.exists(fileInputTextBox) != null)
        {
        	System.out.println("inside Sikuli IF-search box present");
        	sikuliObj.type(fileInputTextBox, filePath);
        }else
	        {
	        	System.out.println("inside Sikuli ElSE-search box not present");
	        	sikuliObj.click(openSearchBtn);
	            WaitUtil.sleep(2000);
	            sikuliObj.type(fileInputTextBox, filePath);
	        }
        WaitUtil.sleep(1000);
        sikuliObj.click(openBtn);
        WaitUtil.sleep(2000);
        return true;
	}

    public boolean switchTab()
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

    public String getRandomNum(int n) 
	{
		String SALTCHARS = "123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < n) 
        { 
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String randomNum = salt.toString();
        System.out.println("***********Your random num is  = "+randomNum+" *************");
		return randomNum;
	}
    
}


