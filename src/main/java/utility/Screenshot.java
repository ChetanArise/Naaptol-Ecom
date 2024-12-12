package utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Screenshot
{
	public static void clickscreenshot(WebDriver driver,String filename) throws IOException
	{
		File source =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destination = new File ("E:\\QA Batch\\Naaptol_Chetan\\Screenshot\\"+filename+".png");
		FileHandler.copy(source, destination);
		
	}

}
