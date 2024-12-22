package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pojo.Browser;
import pom.NaaptolHomePage;
@Listeners (test.Listeners.class)
public class NaaptolSearchTest extends BaseTest
{
	 @BeforeMethod
	 public void LaunchApplication() 
	 {
	  driver =Browser.openBrowser("Chrome");
	 }
	 
	 
     @Test (priority = 1)
     public void verifyIfUserIsAbleToPerformValidSearch() throws InterruptedException
     {
    	test=reports.createTest("verifyIfUserIsAbleToPerformValidSearch");
    	NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
    	naaptolHomePage.searchValidProduct("Mobile Phone");
    	naaptolHomePage.clickOnSearch();
    	Thread.sleep(5000);
    	Assert.assertTrue(naaptolHomePage.listOfSearchedProducts()>0);
    	Thread.sleep(5000);
    	Assert.assertTrue(driver.getTitle().contains("Mobile Phone"));
    	System.out.println(naaptolHomePage.listOfSearchedProducts());
    	Assert.assertTrue(naaptolHomePage.listOfSearchedProducts()>0);
     }
    
      @Test (priority = 2)
      public void verifyIfUserIsAbleToPerformInValidSearch()
      {
    	test=reports.createTest("verifyIfUserIsAbleToPerformInValidSearch");
    	NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
    	naaptolHomePage.searchInvalidProduct("iPhone");
    	naaptolHomePage.clickOnSearch();
    	Assert.assertTrue(driver.getTitle().contains("iPhone"));
    	Assert.assertEquals(naaptolHomePage.listOfSearchedProducts(),0);
      }
}
