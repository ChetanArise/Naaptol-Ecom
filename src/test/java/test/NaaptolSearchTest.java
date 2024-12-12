package test;

import java.io.IOException;

import org.testng.Assert;
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
	  driver =Browser.openbrowser();
	 }
    @Test (priority = 1)
    public void VerifyIfUserIsAbleToPerformValidSearch() throws InterruptedException
    {
    	test=reports.createTest("VerifyIfUserIsAbleToPerformValidSearch");
    	NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
    	naaptolHomePage.SearchValidProduct("Mobile Phone");
    	naaptolHomePage.ClickOnSearch();
    	Thread.sleep(5000);
    	Assert.assertTrue(naaptolHomePage.ListOfSearchedProducts()>0);
    	Thread.sleep(5000);
    	Assert.assertTrue(driver.getTitle().contains("Mobile Phone"));
    	System.out.println(naaptolHomePage.ListOfSearchedProducts());
//    	Assert.assertTrue(naaptolHomePage.ListOfSearchedProducts()>0);
    }
    
    @Test (priority = 2)
    public void VerifyIfUserIsAbleToPerformInValidSearch()
    {
    	test=reports.createTest("VerifyIfUserIsAbleToPerformInValidSearch");
    	NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
    	naaptolHomePage.SearchInvalidProduct("iPhone");
    	naaptolHomePage.ClickOnSearch();
    	Assert.assertTrue(driver.getTitle().contains("iPhone"));
    	Assert.assertEquals(naaptolHomePage.ListOfSearchedProducts(),0);
    }

}
