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
	@FindBy(xpath = "//input [@id='header_search_text']")private WebElement SearchTab;
	@FindBy(xpath = "(//a [@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement SearchButton;
	@FindBy (xpath ="//div[@class='item_title']") private List<WebElement> SearchedProducts;  
	@FindBy (xpath="//a [text()='Log In / Register']") private WebElement Login;
	@FindBy (xpath="//input [@placeholder='Enter mobile number']") private WebElement MobileNo;
	@FindBy (xpath="//input [@value='Continue']") private WebElement Continue; 
	@FindBy (xpath="//input [@maxlength='6']") private WebElement Otp;
	@FindBy (xpath="//input [@value='Submit']") private WebElement Submit; 
	@FindBy (xpath = "//span [text()='Shopping Categories']") private WebElement ShoppingCategory;
	@FindBy (xpath = "//a [text()='Dual Sim Foldable Flip Mobile With Camera - Gamma (M2 Mini)']") private WebElement ProductName;
	@FindBy (xpath = "//div [@class='item_title']") private List<WebElement> ProductNameList;
	@FindBy (xpath = "//div[@id='mainMenuContent']") private WebElement ShoppingCategoryList;
	@FindBy (xpath = "//span[@class='offer-price']") private WebElement ProductPrice;
	@FindBy (xpath = "//span[@class='offer-price']") private List<WebElement> ProductPriceList;
	@FindBy (xpath ="//a [@class='bt_compare icon chat quickFancyBox']") private WebElement QuickView;
	@FindBy (xpath="//span[text()='Quick View']")private List<WebElement> QuickviewList;
	@FindBy (xpath = "//select[@id='sortByFilter']") private WebElement Sort;
	@FindBy (xpath = "//select[@id='sortByFilter']//option") List<WebElement> SortOptions;
	
	public NaaptolHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
    
	public void SearchValidProduct(String item)
	{
		SearchTab.sendKeys(item);
	}
	public void ClickOnSearch()
	{
		SearchButton.click();
	}
	public void SearchInvalidProduct(String item)
	{
		SearchTab.sendKeys(item);	
	}
	public int ListOfSearchedProducts()
	{
		return SearchedProducts.size();
	}
	public void ClickOnLogin()
	{
		Login.click();
	}
    public void EnterMobileNumber(String number)
    {
    	MobileNo.sendKeys(number);
    }
	public void ClickOnContinue()
	{
		Continue.click();
	}
    public void EnterOtp(String otp)
    {
    	Otp.sendKeys(otp);
    }
    public void ClickOnSubmit()
	{
		Submit.click();
	}
    public void VerifyShoppingCategories(WebDriver driver)
    {
    	Actions act = new Actions(driver);
    	act.moveToElement(ShoppingCategory);
    	act.perform();
    }
    public String GetProductName()
	{
	 return ProductName.getText();
	}
    public void MouseHoverOnProduct(WebDriver driver,int index) 
    {
		Actions act = new Actions(driver);
		act.moveToElement(ProductNameList.get(index));
		act.perform();
	}
    public boolean ShoppingCategoryListDisplayed()
    {
    	return ShoppingCategoryList.isDisplayed();
    }
    public double getProductPrice() {
		String [] p =	ProductPrice.getText().split(" ");
		return Double.parseDouble(removeCommaFromString(p[0]));
	}
   public void ClickOnProduct()
   {
	   ProductName.click();
   }
   public void ClickOnQuickView()
	{
		QuickView.click();
	}
   public String getProductname(int index)
   {
	  return ProductNameList.get(index).getText();
   }
   public double getProductprice(int index)
   {
	   String [] p =ProductPriceList.get(index).getText().split(" ");
		return Double.parseDouble(removeCommaFromString(p[0]));
   }
	public void ClickOnQuickview(int index)
	{
		QuickviewList.get(index).click();
	}
	public void ClickOnSort()
	{
		Sort.click();
	}
	public void SelectSortOption(String value)
	{
		Select s = new Select(Sort);
		s.selectByValue(value);	
		for (int i = 0; i <SortOptions.size(); i++) 
		{
	        if (SortOptions.get(i).isSelected()) 
	        {
	           System.out.println(SortOptions.get(i).getText()+" Option Selected");
	        }
		}
	}
	public String GetSortedProductName(int index)
	{
		return SearchedProducts.get(index).getText();
	}
}
