package Framework.TestingFramework.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Framework.TestingFramework.annotationHandler.AnnotationTest;
import Framework.TestingFramework.reportHandler.ExtentTestNGITestListener;
import junit.framework.Assert;

@Listeners({AnnotationTest.class, ExtentTestNGITestListener.class})
public class DataProviderTest 
{
	@Test()
	public void test1()
	{
		System.out.println("Running test1");
//		Assert.fail();
	}
	@Test()
	public void test2()
	{
		System.out.println("Running test2");
	}
	@Test(dependsOnMethods = { "test1","test2" })
	public void test3()
	{
		System.out.println("Running test3");
	}
	@Test(enabled = false)
	public void test4()
	{
		System.out.println("Running test4");
	}

}
