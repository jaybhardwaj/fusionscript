package Framework.TestingFramework.utils;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	
	public static void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebDriverWait explicitWaitByVisibilityOfElement(WebDriver driver, int seconds, WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(el));
		return wait;
	}

	public static WebDriverWait explicitWaitByVisibilityOfAllElement(WebDriver driver, int seconds, List<WebElement> el) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOfAllElements((el)));
		return wait;
	}

}
