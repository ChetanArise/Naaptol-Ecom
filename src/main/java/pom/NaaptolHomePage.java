package pom;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NaaptolHomePage extends RemoveComma 
{
	@FindBy(xpath = "//input [@id='header_search_text']")private WebElement searchTab;
	@FindBy(xpath = "(//a [@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement searchButton;
	@FindBy (xpath ="//div[@class='item_title']") private List<WebElement> searchedProducts;  
	@FindBy (xpath="//a [text()='Log In / Register']") private WebElement login;
	@FindBy (xpath="//input [@placeholder='Enter mobile number']") private WebElement mobileNo;
	@FindBy (xpath="//input [@value='Continue']") private WebElement continueButton;
	@FindBy (xpath="//input [@maxlength='6']") private WebElement otp;
	@FindBy (xpath="//input [@value='Submit']") private WebElement submit; 
	@FindBy (xpath = "//div[@class='cate_head']") private WebElement shoppingCategory;
	@FindBy (xpath = "//a [text()='Dual Sim Foldable Flip Mobile With Camera - Gamma (M2 Mini)']") private WebElement productName;
	@FindBy (xpath = "//div [@class='item_title']") private List<WebElement> productNameList;
	@FindBy (xpath = "//div[@id='mainMenuContent']") private WebElement shoppingCategoryList;
	@FindBy (xpath = "//span[@class='offer-price']") private WebElement productPrice;
	@FindBy (xpath = "//span[@class='offer-price']") private List<WebElement> productPriceList;
	@FindBy (xpath ="//a [@class='bt_compare icon chat quickFancyBox']") private WebElement quickView;
	@FindBy (xpath="//span[text()='Quick View']")private List<WebElement> quickviewList;
	@FindBy (xpath = "//select[@id='sortByFilter']") private WebElement sort;
	@FindBy (xpath = "//select[@id='sortByFilter']//option") List<WebElement> sortOptions;
	
	public NaaptolHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
    
	public void searchValidProduct(String item)
	{
		searchTab.sendKeys(item);
	}
	public void clickOnSearch()
	{
		searchButton.click();
	}
	public void searchInvalidProduct(String item)
	{
		searchTab.sendKeys(item);	
	}
	public int listOfSearchedProducts()
	{
		return searchedProducts.size();
	}
	public void clickOnLogin()
	{
		login.click();
	}
    public void enterMobileNumber(String number)
    {
    	mobileNo.sendKeys(number);
    }
	public void clickOnContinue()
	{
		continueButton.click();
	}
    public void enterOtp(String Otp)
    {
    	otp.sendKeys(Otp);
    }
    public void clickOnSubmit()
	{
		submit.click();
	}
    public void verifyShoppingCategories(WebDriver driver)
    {
    	Actions act = new Actions(driver);
    	act.moveToElement(shoppingCategory);
    	act.perform();
    }
    public String getProductName()
	{
		return productName.getText();
	}
    public void mouseHoverOnProduct(WebDriver driver,int index) 
    {
		Actions act = new Actions(driver);
		act.moveToElement(productNameList.get(index));
		act.perform();
	}
    public boolean shoppingCategoryListDisplayed()
    {
    	return shoppingCategoryList.isDisplayed();
    }
    public double getProductPrice() 
    {
		String [] p =	productPrice.getText().split(" ");
		return Double.parseDouble(removeCommaFromString(p[0]));
	}
    public void clickOnProduct()
    {
	    productName.click();
    }
    public void clickOnQuickView()
	{
		quickView.click();
	}
    public String getProductname(int index)
    {
	   return productNameList.get(index).getText();
    }
    public double getProductprice(int index)
    {
	   String [] p =productPriceList.get(index).getText().split(" ");
	   return Double.parseDouble(removeCommaFromString(p[0]));
    }
	public void clickOnQuickview(int index)
	{
		quickviewList.get(index).click();
	}
	public void clickOnSort()
	{
		sort.click();
	}
	public void selectSortOption(String value)
	{
		Select s = new Select(sort);
		s.selectByValue(value);	
		for (int i = 0; i <sortOptions.size(); i++) 
		{
	      if (sortOptions.get(i).isSelected()) 
	      {
	       System.out.println(sortOptions.get(i).getText()+" Option Selected");
	      }
		}
	}
	public String getSortedProductName(int index)
	{
		return searchedProducts.get(index).getText();
	}
}
