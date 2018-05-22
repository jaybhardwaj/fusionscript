package Framework.TestingFramework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import Framework.TestingFramework.base.BaseTest;

public class WebDriverListeners extends AbstractWebDriverEventListener {
	/*
	 *  URL NAVIGATION | NAVIGATE() & GET()
	 */
	 // Prints the URL before Navigating to specific URL "get("http://www.google.com");"
	 @Override
	 public void beforeNavigateTo(String url, WebDriver driver) {
//		 if(map.get("consoleOutput").equals("true"))
	//  System.out.println("Before Navigating To : " + url + ", my url was: "
//	    + driver.getCurrentUrl());
	 }

	 // Prints the current URL after Navigating to specific URL "get("http://www.google.com");"
	 @Override
	 public void afterNavigateTo(String url, WebDriver driver) {
		 
//		 if(map.get("consoleOutput").equals("true"))
	//  System.out.println("After Navigating To: " + url + ", my url is: "
//	    + driver.getCurrentUrl());
	 }

	 // Prints the URL before Navigating back "navigate().back()"
	 @Override
	 public void beforeNavigateBack(WebDriver driver) {
		 
//		 if(map.get("consoleOutput").equals("true"))
	//  System.out.println("Before Navigating Back. I was at "
//	    + driver.getCurrentUrl());
	 }

	 // Prints the current URL after Navigating back "navigate().back()"
	 @Override
	 public void afterNavigateBack(WebDriver driver) {
		 
//		 if(map.get("consoleOutput").equals("true"))
	//  System.out.println("After Navigating Back. I'm at "
//	    + driver.getCurrentUrl());
	 }

	 // Prints the URL before Navigating forward "navigate().forward()"
	 @Override
	 public void beforeNavigateForward(WebDriver driver) {
		 
//		 if(map.get("consoleOutput").equals("true"))
	//  System.out.println("Before Navigating Forward. I was at "
//	    + driver.getCurrentUrl());
	 }

	 // Prints the current URL after Navigating forward "navigate().forward()"
	 @Override
	 public void afterNavigateForward(WebDriver driver) {
		 
//		 if(map.get("consoleOutput").equals("true"))
	//  System.out.println("After Navigating Forward. I'm at "
//	    + driver.getCurrentUrl());
	 }


	/*
	 * ON EXCEPTION | SCREENSHOT, THROWING ERROR
	 */
	 // Takes screenshot on any Exception thrown during test execution
	 @Override
	 public void onException(Throwable throwable, WebDriver webdriver) {
		 
//		 if(map.get("consoleOutput").equals("true"))
	//  System.out.println("Caught Exception");
	//  if(map.get("consoleOutput").equals("true"))
	//  System.out.println(throwable.getMessage());
	////  File scrFile = ((TakesScreenshot) webdriver)
////	    .getScreenshotAs(OutputType.FILE);
	////  try {
	////   org.apache.commons.io.FileUtils.copyFile(scrFile, new File(
////	     "C:\\Testfailure.jpeg"));
	////  } catch (Exception e) {
	////   System.out.println("Unable to Save");
	////  }
		 
		 DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		 
		
		 //System.out.println(element.toString().split("->")[1]);
	 }


	/*
	 * FINDING ELEMENTS | FINDELEMENT() & FINDELEMENTS()
	 */
	 // Called before finding Element(s)
	 @Override
	 public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		 
	//  lastFindBy = by;
	//  if(map.get("consoleOutput").equals("true"))
	//  System.out.println("\tTrying to find: '" + lastFindBy + "'.");
	//  //System.out.println("Trying to find: " + by.toString()); // This is optional and an alternate way
	// try{
	//  WebElement e = driver.findElement(by);
	//  for (int i = 0; i < 6; i++) {
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript(
//					"arguments[0].setAttribute('style', arguments[1]);",
//					e, "color: red; border: 3px solid red;");
//			js.executeScript(
//					"arguments[0].setAttribute('style', arguments[1]);",
//					e, "");
//		}
	// }
	// catch(NoSuchElementException e){
//		 
	// }
	       }

	 // Called after finding Element(s)
	 @Override
	 public void afterFindBy(By by, WebElement element, WebDriver driver) {
		 
		 //ystem.out.println("Found: " + by.toString() + "'."); // This is optional and an alternate way
	 }


	/*
	 * CLICK | CLICK()
	 */
	 // Called before clicking an Element
	 @Override
	 public void beforeClickOn(WebElement element, WebDriver driver) {
		 
//		 DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
//		 if(map.get("consoleOutput").equals("true"))	
//		 System.out.println("[ Before Click on]\t"+dateFormat.format(new Date())+"      ["+element.toString().split("->")[1]);

//		 if(map.get("consoleOutput").equals("true"))
	//  System.out.println("\t\tTrying to click: '" + element + "'");
	//  // Highlight Elements before clicking
	// // WebElement e = driver.findElement(by);
	// // JavascriptExecutor js = (JavascriptExecutor)driver;
	//  for (int i = 0; i < 6; i++) {
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript(
//					"arguments[0].setAttribute('style', arguments[1]);",
//					element, "color: red; border: 3px solid red;");
//			js.executeScript(
//					"arguments[0].setAttribute('style', arguments[1]);",
//					element, "");
//		}

	 }

	 // Called after clicking an Element
	 @Override
	 public void afterClickOn(WebElement element, WebDriver driver) {
		 
//		 DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
//		 if(map.get("consoleOutput").equals("true"))	
//		 System.out.println("[Click on]\t"+dateFormat.format(new Date())+"      ["+element.toString().split("->")[1]);

//		 if(map.get("consoleOutput").equals("true"))
	//  System.out.println("\t\tClicked Element with: '" + element + "'");
	 }


	/*
	 * CHANGING VALUES | CLEAR() & SENDKEYS()
	 */
	 // Before Changing values
	/* @Override
	 public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		 
	//  lastElement = element;
	//  originalValue = element.getText();
	//
	//  // What if the element is not visible anymore?
	//  if (originalValue.isEmpty()) {
	//   originalValue = element.getAttribute("value");
	//  }
	 }*/

	 // After Changing values
	 /*@Override
	 public void afterChangeValueOf(WebElement element, WebDriver driver) {
		 
//		 DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			//System.out.println(element.toString().split("->")[1]);
//		 if(map.get("consoleOutput").equals("true"))
//		 System.out.println("[Send Keys to]\t"+dateFormat.format(new Date())+"      ["+element.toString().split("->")[1]);
	//  lastElement = element;
	//  String changedValue = "";
	//  try {
	//   changedValue = element.getText();
	//  } catch (StaleElementReferenceException e) {
//		  if(map.get("consoleOutput").equals("true"))
	//   System.out
//	     .println("\t\tCould not log change of element, because of a stale"
//	       + " element reference exception.");
	//   return;
	//  }
	//  // What if the element is not visible anymore?
	//  if (changedValue.isEmpty()) {
	//   changedValue = element.getAttribute("value");
	//  }
	//  if(map.get("consoleOutput").equals("true"))
	//  System.out.println("\t\tChanging value in element found " + lastElement
//	    + " from '" + originalValue + "' to '" + changedValue + "'");
	 }*/


	/*
	 * SCRIPT - this section will be modified ASAP
	 */
	 // Called before RemoteWebDriver.executeScript(java.lang.String, java.lang.Object[])
	 @Override
	 public void beforeScript(String script, WebDriver driver) {
		 
	 // TODO Auto-generated method stub
	       }

	 // Called before RemoteWebDriver.executeScript(java.lang.String, java.lang.Object[])
	  @Override
	        public void afterScript(String script, WebDriver driver) {
		  
	 // TODO Auto-generated method stub
	       }
}
