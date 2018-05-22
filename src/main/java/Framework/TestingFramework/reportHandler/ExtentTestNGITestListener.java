package Framework.TestingFramework.reportHandler;

import java.io.File;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework.TestingFramework.base.BaseTest;

public class ExtentTestNGITestListener implements ITestListener {
	
	private static ExtentReports extent = ExtentManager.createInstance("Reports/TestReport.html");
	private static ThreadLocal parentTest = new ThreadLocal();
    private static ThreadLocal test = new ThreadLocal();
    public Vector<String> clasNames = new Vector<String>();
    public Vector<ExtentTest> parents = new Vector<ExtentTest>();
    EventFiringWebDriver driver;
    
   	public synchronized void onStart(ITestContext context) {
      /* 	ExtentTest parent = extent.createTest("Thread"+Thread.currentThread().getId());
       	System.out.println(getClass().getName());
           parentTest.set(parent);*/
   	}
   	
   	
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
	}
	
	
	public synchronized void onTestStart(ITestResult result) {
		if(!clasNames.contains(result.getMethod().getRealClass().getSimpleName())){
			clasNames.add(result.getMethod().getRealClass().getSimpleName());
			parents.add(clasNames.indexOf(result.getMethod().getRealClass().getSimpleName()), extent.createTest(result.getMethod().getRealClass().getSimpleName()));
		}
		ExtentTest parent = parents.get(clasNames.indexOf(result.getMethod().getRealClass().getSimpleName()));
		parentTest.set(parent);
		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(result.getMethod().getMethodName()+"("+result.getMethod().getDescription()+")");
        test.set(child);
	}

	
	public synchronized void onTestSuccess(ITestResult result) {
		String inputs = BaseTest.multipleInput.get(Thread.currentThread().getId());
		((ExtentTest) test.get()).pass("Test passed");
		if(inputs != null) {
			((ExtentTest) test.get()).log(Status.INFO, "Multiple Case Inputs: "+inputs);
		}
	}

	
	public synchronized void onTestFailure(ITestResult result) {
		driver = BaseTest.findMyDriver();
		String inputs = BaseTest.multipleInput.get(Thread.currentThread().getId());
		((ExtentTest) test.get()).fail(result.getThrowable());
		if(inputs != null) {
			((ExtentTest) test.get()).log(Status.INFO, "Multiple Case Inputs: "+inputs);
		}
		try {
			if(inputs != null) {
				TakesScreenshot ts = (TakesScreenshot)driver;
		        File source = ts.getScreenshotAs(OutputType.FILE);
		        String dest = System.getProperty("user.dir") +"/../Images/"+result.getMethod().getMethodName()+inputs.trim()+".png";
		        File destination = new File(dest);
		        FileUtils.copyFile(source, destination);
		        String path = dest;
		        ((ExtentTest) test.get()).addScreenCaptureFromPath(path);
			} else {
				TakesScreenshot ts = (TakesScreenshot)driver;
		        File source = ts.getScreenshotAs(OutputType.FILE);
		        String dest = System.getProperty("user.dir") +"/../Images/"+result.getMethod().getMethodName()+".png";
		        File destination = new File(dest);
		        FileUtils.copyFile(source, destination);
		        String path = dest;
		        ((ExtentTest) test.get()).addScreenCaptureFromPath(path);
			}
		} catch(Exception e) {
			
		}
	}

	
	public synchronized void onTestSkipped(ITestResult result) {
		((ExtentTest) test.get()).skip(result.getThrowable());
	}

	
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

}
