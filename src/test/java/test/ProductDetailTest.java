package test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pojo.Browser;
import pom.NaaptolCartPage;
import pom.NaaptolHomePage;
import pom.ProductDetailPage;
@Listeners (test.Listeners.class)
public class ProductDetailTest extends BaseTest
{
	@BeforeMethod
	 public void LaunchApplication() 
	 {
	  driver =Browser.openbrowser();
	 }
	 
	@Test (priority =1)
	public void VerifyAddToCartUsingProductDetailPage()
	{
		test=reports.createTest("VerifyAddToCartUsingProductDetailPage");
		NaaptolHomePage naaptolHomePage= new NaaptolHomePage(driver);
		naaptolHomePage.SearchValidProduct("mobile phone");
		naaptolHomePage.ClickOnSearch();
		naaptolHomePage.ClickOnProduct();
		
	 Set<String> s= driver.getWindowHandles();
	 Iterator<String> i=s.iterator();
	 
	 while(i.hasNext())
	 {
		 String s1=i.next();
		 driver.switchTo().window(s1);
	 }
	 ProductDetailPage productDetailPage = new ProductDetailPage(driver);
	 String dpn = productDetailPage.GetProductName();
	 double dpp = productDetailPage.getProductPrice();
	 productDetailPage.ClickOnAddToCartButton(0);
	 
	 NaaptolCartPage naaptolCartPage = new NaaptolCartPage(driver);
	 String cpn=naaptolCartPage.getProductname(0);
	 double cpp=naaptolCartPage.GetProductPrice(1);
	 
	 Assert.assertEquals(cpn, dpn);
	 Assert.assertEquals(cpp, dpp);	
	}
	
}
