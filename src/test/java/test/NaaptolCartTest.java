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
	  driver =Browser.openbrowser();
	 }

	@Test (priority = 1)
	public void VerifyAddProductToCartUsingQuickView()
	{
		test=reports.createTest("VerifyAddProductToCartUsingQuickView");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		 naaptolHomePage.SearchValidProduct("mobile phone");
		 naaptolHomePage.ClickOnSearch();
		 naaptolHomePage.MouseHoverOnProduct(driver,0);
		 naaptolHomePage.ClickOnQuickView();
		 NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);
		 String qpn=naaptolQuickView.GetQuickProductName();
		 double qpp =naaptolQuickView.GetQuickProductPrice();
		
		 naaptolQuickView.ClickOnAddToCartButton(0);
		 NaaptolCartPage naaptolCartPage = new NaaptolCartPage(driver);
		 String cpn= naaptolCartPage.getProductname(0);
		 double cpp= naaptolCartPage.GetProductPrice(1);
			 
		 Assert.assertEquals(cpn,qpn);
		 Assert.assertEquals(cpp,qpp); 
	}
	@Test (priority = 2)
	public void VerifyAddingMultipleProductToCart() throws InterruptedException
	{
		test=reports.createTest("VerifyAddingMultipleProductToCart");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.SearchValidProduct("mobile phone");
		naaptolHomePage.ClickOnSearch();
		String hpn1=naaptolHomePage.getProductname(0);
		double hpp1=naaptolHomePage.getProductprice(0);
		String hpn2=naaptolHomePage.getProductname(2);
		double hpp2=naaptolHomePage.getProductprice(2);
		
		naaptolHomePage.MouseHoverOnProduct(driver,0);
		naaptolHomePage.ClickOnQuickview(0);
		NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);
		naaptolQuickView.ClickOnAddToCartButton(0);
		NaaptolCartPage naaptolCartPage= new NaaptolCartPage(driver);
		String cpn1= naaptolCartPage.getProductname(0);
		double cpp1= naaptolCartPage.GetProductPrice(1);
		naaptolCartPage.ClickOnClose();
		naaptolQuickView.ClickOnClose();
		
		naaptolHomePage.MouseHoverOnProduct(driver,2);
		naaptolHomePage.ClickOnQuickview(2);
		naaptolQuickView.ClickOnAddToCart();
		Thread.sleep(2000);
		String cpn2= naaptolCartPage.getProductname(0);
		double cpp2= naaptolCartPage.GetProductPrice(1);
		naaptolCartPage.ClickOnClose();
		naaptolQuickView.ClickOnClose();
		
		Assert.assertEquals(cpn1,hpn1);
		Assert.assertEquals(cpn2,hpn2); 
		Assert.assertEquals(cpp1,hpp1);
		Assert.assertEquals(cpp2,hpp2);
	}
	@Test (priority = 3)
	public void VerifyRemovingProductFromProduct() throws StaleElementReferenceException,InterruptedException
	{
		test=reports.createTest("VerifyRemovingProductFromProduct");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.SearchValidProduct("mobile phone");
		naaptolHomePage.ClickOnSearch();
		
		naaptolHomePage.MouseHoverOnProduct(driver,0);
		naaptolHomePage.ClickOnQuickview(0);
		NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);  
		naaptolQuickView.ClickOnAddToCartButton(0);
		NaaptolCartPage naaptolCartPage= new NaaptolCartPage(driver);
		naaptolCartPage.ClickOnClose();
		naaptolQuickView.ClickOnClose();
		
		naaptolHomePage.MouseHoverOnProduct(driver,1);
		naaptolHomePage.ClickOnQuickview(1);
		naaptolQuickView.ClickOnAddToCart();
		Thread.sleep(2000);
		int c1=naaptolCartPage.GetCartProductListCount();
		Thread.sleep(2000);
		naaptolCartPage.ClickOnRemove(0);
		Thread.sleep(2000);
		naaptolCartPage.ClickOnRemove(0);
		
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cartItems']//ul")));
		
		Thread.sleep(2000);
		int c2=naaptolCartPage.GetCartProductListCount();
	    
		Assert.assertNotSame(c1,c2);	
	}
	
	@Test (priority = 4)
	public void VerifyOnChangingProductQuantityCorrectAmountIsDisplayed() throws InterruptedException
	{
		test=reports.createTest("VerifyOnChangingProductQuantityCorrectAmountIsDisplayed");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.SearchValidProduct("mobile phone");
		naaptolHomePage.ClickOnSearch();
		
		naaptolHomePage.MouseHoverOnProduct(driver,0);
		naaptolHomePage.ClickOnQuickview(0);
		NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);
		naaptolQuickView.ClickOnAddToCartButton(0);
		
		NaaptolCartPage naaptolCartPage= new NaaptolCartPage(driver);
		Thread.sleep(2000);
		double cpp = naaptolCartPage.GetProductPrice(1);
		Thread.sleep(2000);
		double csp= naaptolCartPage.GetCartShippingPrice(1); 
		Thread.sleep(2000);
		double orderamount= cpp+csp;
		Thread.sleep(2000);
		double coa=naaptolCartPage.GetOrderAmount(1);
		Assert.assertEquals(coa,orderamount);
		
	    naaptolCartPage.ClickOnQuantity(1);
	    Actions act = new Actions(driver);
	    act.keyDown(Keys.DELETE);
	    act.keyUp(Keys.DELETE);
	    act.keyDown(Keys.NUMPAD2);
	    act.keyUp(Keys.NUMPAD2);
	    act.build().perform();
	 
	    Thread.sleep(2000);
	    double orderamount2 =cpp*2+csp; //expected price
	    Thread.sleep(2000);
	    double coa2=naaptolCartPage.GetOrderAmount(1); //actual price
	    Assert.assertEquals(coa2,orderamount2);
		
		naaptolCartPage.ClickOnClose();
		naaptolQuickView.ClickOnClose();
		
	}
	@Test (priority = 5)
	public void VerifyTotalAmountForMultipleProductInCart() throws InterruptedException
	{
		test=reports.createTest("VerifyTotalAmountForMultipleProductInCart");
		NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		naaptolHomePage.SearchValidProduct("mobile phone");
		naaptolHomePage.ClickOnSearch();
		naaptolHomePage.MouseHoverOnProduct(driver,0);
		naaptolHomePage.ClickOnQuickview(0);
		
		NaaptolQuickView naaptolQuickView= new NaaptolQuickView(driver);
		naaptolQuickView.ClickOnAddToCartButton(0);
		NaaptolCartPage naaptolCartPage= new NaaptolCartPage(driver);
		Thread.sleep(2000);
		double cpp = naaptolCartPage.GetProductPrice(1);
		Thread.sleep(2000);
		double csp= naaptolCartPage.GetCartShippingPrice(1); 
		double orderamount= cpp+csp;
		Thread.sleep(2000);
		double coa=naaptolCartPage.GetOrderAmount(1);
		Thread.sleep(2000);
		naaptolCartPage.ClickOnClose();
		naaptolQuickView.ClickOnClose();
		
		naaptolHomePage.MouseHoverOnProduct(driver,1);
		naaptolHomePage.ClickOnQuickview(1);
		
		naaptolQuickView.ClickOnAddToCart();
		Thread.sleep(2000);
		double cpp1 = naaptolCartPage.GetProductPrice(1);
		double csp1= naaptolCartPage.GetCartShippingPrice(1);
		double orderamount1= cpp1+csp1;
		Thread.sleep(2000);
		double coa1=naaptolCartPage.GetOrderAmount(1);
		
		double cta=coa+coa1;// expected total amount
		Thread.sleep(2000);
		double totalamount=naaptolCartPage.GetTotalAmount(); //actual total amount
		
		Assert.assertEquals(totalamount,cta);
		naaptolCartPage.ClickOnClose();
		naaptolQuickView.ClickOnClose();
	}
	

}
