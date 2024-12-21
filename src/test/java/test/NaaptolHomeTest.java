package test;


import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pojo.Browser;
import pom.NaaptolHomePage;
import pom.NaaptolQuickView;
import pom.ProductDetailPage;
@Listeners (test.Listeners.class)
public class NaaptolHomeTest extends BaseTest
{
	
	 @BeforeMethod
	 public void LaunchApplication() 
	 {
	  driver =Browser.openBrowser("Chrome");
	 }
	 @Test (priority = 1)
	 public void verifyOnClickingShoppingCategories()
	 {
		 test=reports.createTest("verifyOnClickingShoppingCategories");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		 naaptolHomePage.verifyShoppingCategories(driver);
		 Assert.assertTrue(naaptolHomePage.shoppingCategoryListDisplayed());
	 }
	 
	 @Test (priority = 2)
	 public void verifyProductDetailsInQuickView()
	 {
		 test=reports.createTest("verifyProductDetailsInQuickView");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver); 
		 naaptolHomePage.searchValidProduct("mobile phone");
		 naaptolHomePage.clickOnSearch();
		 naaptolHomePage.mouseHoverOnProduct(driver,0);
		 String hpn =naaptolHomePage.getProductName();
		 
		 double hpp=naaptolHomePage.getProductPrice();
		 System.out.println(hpp);
		 		 
		 NaaptolQuickView naaptolQuickView = new NaaptolQuickView(driver);
		 naaptolQuickView.clickOnQuickView();
		 String qpn=naaptolQuickView.getQuickProductName();
		 double qpp =naaptolQuickView.getQuickProductPrice();
		 
		 Assert.assertEquals(qpn,hpn);
		 Assert.assertEquals(qpp,hpp);
	 }
	 
	 @Test (priority = 3)
	 public void verifyProductDetailsInNewTab()
	 {
		 test=reports.createTest("verifyProductDetailsInNewTab");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver); 
		 naaptolHomePage.searchValidProduct("mobile phone");
		 naaptolHomePage.clickOnSearch();
		 naaptolHomePage.mouseHoverOnProduct(driver,0);
		 String hpn =naaptolHomePage.getProductName();
		 double hpp=naaptolHomePage.getProductPrice();
		 
		 naaptolHomePage.clickOnProduct();
		 
		 Set<String> s= driver.getWindowHandles();
		 
		 Iterator<String> i=s.iterator();
		 
		 while(i.hasNext())
		 {
			 String s1=i.next();
			 driver.switchTo().window(s1);
			 String title =driver.getTitle();
			 
			 if(title.contains("Buy Dual Sim"))
			 {
				 ProductDetailPage productDetailPage= new ProductDetailPage(driver);
				 String dpn = productDetailPage.getProductName();
				 double dpp = productDetailPage.getProductPrice();
				Assert.assertEquals(dpn,hpn);
				Assert.assertEquals(hpp,dpp);
				break;
			 }
		 }	 
	 }
	 
	 @Test (priority = 4)
	 public void verifySortFeature() throws InterruptedException
	 {
		 test=reports.createTest("verifySortFeature");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver); 
		 naaptolHomePage.searchValidProduct("mobile phone");
		 naaptolHomePage.clickOnSearch();
		 naaptolHomePage.clickOnSort();
		 Thread.sleep(2000);
		 String hpn1 =naaptolHomePage.getProductname(0);
		 String hpn2 =naaptolHomePage.getProductname(1);
		 String hpn3 =naaptolHomePage.getProductname(2);
		 naaptolHomePage.selectSortOption("rated");
		 String spn1=naaptolHomePage.getSortedProductName(0);
		 String spn2=naaptolHomePage.getSortedProductName(1);
		 String spn3=naaptolHomePage.getSortedProductName(2);
		 
		 Assert.assertNotEquals(spn1,hpn1);
		 Assert.assertNotEquals(spn2,hpn2);
		 Assert.assertNotEquals(spn3,hpn3);
		 
	 }
}
