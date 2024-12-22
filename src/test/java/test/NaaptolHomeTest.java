package test;


import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
		 String hpn =naaptolHomePage.getProductname(0);
		 double hpp=naaptolHomePage.getProductprice(0);
		 naaptolHomePage.clickOnQuickview(0); 
		 NaaptolQuickView naaptolQuickView = new NaaptolQuickView(driver);
		 String qpn=naaptolQuickView.getQuickProductName();
		 double qpp =naaptolQuickView.getQuickProductPrice();
		 
		 Assert.assertEquals(qpn,hpn);
		 Assert.assertEquals(qpp,hpp);
	 }
	
	 @Test (priority = 3)
	 public void verifyProductDetailsInNewTab() throws InterruptedException
	 {
		 test=reports.createTest("verifyProductDetailsInNewTab");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver); 
		 naaptolHomePage.searchValidProduct("mobile phone");
		 naaptolHomePage.clickOnSearch();
		 naaptolHomePage.mouseHoverOnProduct(driver,0);
		 String hpn =naaptolHomePage.getProductname(0);
		 double hpp=naaptolHomePage.getProductprice(0);
		 
		 naaptolHomePage.clickOnProduct();
		 naaptolHomePage.launchChildBrowser(driver);
		 ProductDetailPage productDetailPage= new ProductDetailPage(driver);
		 String dpn = productDetailPage.getProductName();
		 double dpp = productDetailPage.getProductPrice();
		 Thread.sleep(3000);
		 Assert.assertEquals(dpn,hpn);
		 Assert.assertEquals(hpp,dpp);
		 
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
