package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Driver;

import pojo.Browser;
import pom.NaaptolCartPage;
import pom.NaaptolHomePage;
import pom.NaaptolQuickView;
import pom.RemoveComma;

@Listeners (test.Listeners.class)
public class NaaptolCartTest extends BaseTest
{
	 
	 @BeforeMethod
	 public void LaunchApplication() 
	 {
	  driver =Browser.openBrowser("Chrome");
	 }	

	 @Test (priority = 1)
	 public void verifyAddProductToCartUsingQuickView()
     {
	   	 test=reports.createTest("verifyAddProductToCartUsingQuickView");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		 naaptolHomePage.searchValidProduct("mobile phone");
		 naaptolHomePage.clickOnSearch();
		 naaptolHomePage.mouseHoverOnProduct(driver,0);
		 naaptolHomePage.clickOnQuickView();
		 NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);
		 String qpn=naaptolQuickView.getQuickProductName();
		 double qpp =naaptolQuickView.getQuickProductPrice();
		
		 naaptolQuickView.clickOnAddToCartButton(0);
		 NaaptolCartPage naaptolCartPage = new NaaptolCartPage(driver);
		 String cpn= naaptolCartPage.getProductName(0);
		 double cpp= naaptolCartPage.getProductPrice(1);
			 
		 Assert.assertEquals(cpn,qpn);
		 Assert.assertEquals(cpp,qpp); 
     }
	 @Test (priority = 2)
	 public void verifyAddingMultipleProductToCart() throws InterruptedException
	 {
		test=reports.createTest("verifyAddingMultipleProductToCart");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.searchValidProduct("mobile phone");
		naaptolHomePage.clickOnSearch();
		String hpn1=naaptolHomePage.getProductname(0);
		double hpp1=naaptolHomePage.getProductprice(0);
		String hpn2=naaptolHomePage.getProductname(2);
		double hpp2=naaptolHomePage.getProductprice(2);
		
		naaptolHomePage.mouseHoverOnProduct(driver,0);
		naaptolHomePage.clickOnQuickview(0);
		NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);
		naaptolQuickView.clickOnAddToCartButton(0);
		NaaptolCartPage naaptolCartPage= new NaaptolCartPage(driver);
		String cpn1= naaptolCartPage.getProductName(0);
		double cpp1= naaptolCartPage.getProductPrice(1);
		naaptolCartPage.clickOnClose();
		naaptolQuickView.clickOnClose();
		
		naaptolHomePage.mouseHoverOnProduct(driver,2);
		naaptolHomePage.clickOnQuickview(2);
		naaptolQuickView.clickOnAddToCart();
		Thread.sleep(2000);
		String cpn2= naaptolCartPage.getProductName(0);
		double cpp2= naaptolCartPage.getProductPrice(1);
		naaptolCartPage.clickOnClose();
		naaptolQuickView.clickOnClose();
		
		Assert.assertEquals(cpn1,hpn1);
		Assert.assertEquals(cpn2,hpn2); 
		Assert.assertEquals(cpp1,hpp1);
		Assert.assertEquals(cpp2,hpp2);
     }
     @Test (priority = 3)
	 public void verifyRemovingProductFromProduct() throws StaleElementReferenceException,InterruptedException
	 {
		test=reports.createTest("verifyRemovingProductFromProduct");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.searchValidProduct("mobile phone");
		naaptolHomePage.clickOnSearch();
		
		naaptolHomePage.mouseHoverOnProduct(driver,0);
		naaptolHomePage.clickOnQuickview(0);
		NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);  
		naaptolQuickView.clickOnAddToCartButton(0);
		NaaptolCartPage naaptolCartPage= new NaaptolCartPage(driver);
		naaptolCartPage.clickOnClose();
		naaptolQuickView.clickOnClose();
		
		naaptolHomePage.mouseHoverOnProduct(driver,1);
		naaptolHomePage.clickOnQuickview(1);
		naaptolQuickView.clickOnAddToCart();
		Thread.sleep(2000);
		int c1=naaptolCartPage.getCartProductListCount();
		Thread.sleep(2000);
		naaptolCartPage.clickOnRemove(0);
		Thread.sleep(2000);
		naaptolCartPage.clickOnRemove(0);
		
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cartItems']//ul")));
		
		Thread.sleep(2000);
		int c2=naaptolCartPage.getCartProductListCount();
	    
		Assert.assertNotSame(c1,c2);	
     }	
	 @Test (priority = 4)
	 public void verifyOnChangingProductQuantityCorrectAmountIsDisplayed() throws InterruptedException
	 {
		test=reports.createTest("verifyOnChangingProductQuantityCorrectAmountIsDisplayed");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.searchValidProduct("mobile phone");
		naaptolHomePage.clickOnSearch();
		
		naaptolHomePage.mouseHoverOnProduct(driver,0);
		naaptolHomePage.clickOnQuickview(0);
		NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);
		naaptolQuickView.clickOnAddToCartButton(0);
		
		NaaptolCartPage naaptolCartPage= new NaaptolCartPage(driver);
		Thread.sleep(2000);
		double cpp = naaptolCartPage.getProductPrice(1);
		Thread.sleep(2000);
		double csp= naaptolCartPage.getCartShippingPrice(1); 
		Thread.sleep(2000);
		double orderamount= cpp+csp;
		Thread.sleep(2000);
		double coa=naaptolCartPage.getOrderAmount(1);
		Assert.assertEquals(coa,orderamount);
		
	    naaptolCartPage.clickOnQuantity(1);
	    Actions act = new Actions(driver);
	    act.keyDown(Keys.DELETE);
	    act.keyUp(Keys.DELETE);
	    act.keyDown(Keys.NUMPAD2);
	    act.keyUp(Keys.NUMPAD2);
	    act.build().perform();
	 
	    Thread.sleep(2000);
	    double orderamount2 =cpp*2+csp; //expected price
	    Thread.sleep(2000);
	    double coa2=naaptolCartPage.getOrderAmount(1); //actual price
	    Assert.assertEquals(coa2,orderamount2);
		
		naaptolCartPage.clickOnClose();
		naaptolQuickView.clickOnClose();
		
     }
	 @Test (priority = 5)
	 public void verifyTotalAmountForMultipleProductInCart() throws InterruptedException
	 {
	   	test=reports.createTest("VerifyTotalAmountForMultipleProductInCart");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.searchValidProduct("mobile phone");
		naaptolHomePage.clickOnSearch();
		naaptolHomePage.mouseHoverOnProduct(driver,0);
		naaptolHomePage.clickOnQuickview(0);
		
		NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);
		naaptolQuickView.clickOnAddToCartButton(0);
		NaaptolCartPage naaptolCartPage= new NaaptolCartPage(driver);
		Thread.sleep(2000);
		double cpp = naaptolCartPage.getProductPrice(1);
		Thread.sleep(2000);
		double csp= naaptolCartPage.getCartShippingPrice(1); 
		double orderamount= cpp+csp;
		Thread.sleep(2000);
		double coa=naaptolCartPage.getOrderAmount(1);
		Thread.sleep(2000);
		naaptolCartPage.clickOnClose();
		naaptolQuickView.clickOnClose();
		
		naaptolHomePage.mouseHoverOnProduct(driver,1);
		naaptolHomePage.clickOnQuickview(1);
		
		naaptolQuickView.clickOnAddToCart();
		Thread.sleep(2000);
		double cpp1 = naaptolCartPage.getProductPrice(1);
		double csp1= naaptolCartPage.getCartShippingPrice(1);
		double orderamount1= cpp1+csp1;
		Thread.sleep(2000);
		double coa1=naaptolCartPage.getOrderAmount(1);
		
		double cta=coa+coa1;// expected total amount
		Thread.sleep(2000);
		double totalamount=naaptolCartPage.getTotalAmount(); //actual total amount
		
		Assert.assertEquals(totalamount,cta);
		naaptolCartPage.clickOnClose();
		naaptolQuickView.clickOnClose();
	}
}
