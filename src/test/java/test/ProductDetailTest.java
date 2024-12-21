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
	 public void launchApplication() 
	 {
	  driver =Browser.openBrowser("Chrome");
	 }
	 
	 @Test (priority =1)
	 public void verifyAddToCartUsingProductDetailPage()
	 {
		test=reports.createTest("verifyAddToCartUsingProductDetailPage");
		NaaptolHomePage naaptolHomePage= new NaaptolHomePage(driver);
		naaptolHomePage.searchValidProduct("mobile phone");
		naaptolHomePage.clickOnSearch();
		naaptolHomePage.clickOnProduct();
		
	    Set<String> s= driver.getWindowHandles();
	    Iterator<String> i=s.iterator();
	 
	    while(i.hasNext())
	    {
		 String s1=i.next();
		 driver.switchTo().window(s1);
	    }
	    ProductDetailPage productDetailPage = new ProductDetailPage(driver);
	    String dpn = productDetailPage.getProductName();
	    double dpp = productDetailPage.getProductPrice();
	    productDetailPage.clickOnAddToCartButton(0);
	 
	    NaaptolCartPage naaptolCartPage = new NaaptolCartPage(driver);
	    String cpn=naaptolCartPage.getProductName(0);
	    double cpp=naaptolCartPage.getProductPrice(1);
	 
	    Assert.assertEquals(cpn, dpn);
	    Assert.assertEquals(cpp, dpp);	
     }
}
