package Framework.TestingFramework.methods;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

public class MiFlashSale 
{
	@Test
	public void flashSale() throws FindFailed
	{
		System.out.println("jay");
		getDealSikuli();
	}
	

	public void getDealSikuli() throws FindFailed
	{
		
		String imagePath = "/home/psslass11153/Pictures/mi/";
        Screen sikuliObj = new Screen();
        Pattern openChrome = new Pattern(imagePath + "chromeIcon.png");
        Pattern RefreshBtn = new Pattern(imagePath + "RefreshBtn.png");
        Pattern BuyBtn = new Pattern(imagePath + "BuyBtn.png");
        Pattern finalBuyBtn = new Pattern(imagePath + "buyNow2.png");
        if(sikuliObj.exists(openChrome) != null)
        {
        	System.out.println("Chrome is present");
        	sikuliObj.click(openChrome);
        }
        sikuliObj.click(RefreshBtn);
        while(true)
        {
        	if(sikuliObj.exists(BuyBtn) != null)
            {
            	sikuliObj.click(BuyBtn);
            	if(sikuliObj.exists(finalBuyBtn) != null)
                {
            		if(sikuliObj.exists(finalBuyBtn) != null)
            		{
            			sikuliObj.click(finalBuyBtn);
            		}
                }
            	break;
            }else
            {
            	sikuliObj.click(RefreshBtn);
            }
        }
        

	}
}
