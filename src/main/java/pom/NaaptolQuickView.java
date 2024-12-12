package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaaptolQuickView extends RemoveComma 
{
	@FindBy(xpath = "//input [@id='header_search_text']")private WebElement SearchTab;
	@FindBy(xpath = "(//a [@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement SearchButton;
	@FindBy (xpath ="//div [@class='grid_Square']") private List<WebElement> SearchedProducts; 
    @FindBy (xpath ="//a [@class='bt_compare icon chat quickFancyBox']") private WebElement QuickView;
    @FindBy (xpath = "//a [text()='Dual Sim Foldable Flip Mobile With Camera - Gamma (M2 Mini)']") private WebElement ProductName;
    @FindBy (xpath = "//h1 [text()='Dual Sim Foldable Flip Mobile With Camera - Gamma (M2 Mini)']") private WebElement QuickProductName;
    @FindBy (xpath = "//span[@class='offer-price'] ") private WebElement QuickProductPrice;
    @FindBy (xpath = "//a[text()='Gold']") private WebElement color;
    @FindBy (xpath = "//ul[@class='sizeBox clearfix']//li") private List<WebElement> Color;
    @FindBy (xpath = "//ul[@class='sizeBox clearfix']") private WebElement ColorList;
    @FindBy (xpath="//span[text()='Click here to Buy']") private WebElement AddToCart;
    @FindBy (xpath = " //button[@type='button']") private WebElement Close;
	
	public NaaptolQuickView(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public void ClickOnSearchTab(String item)
	{
		SearchTab.sendKeys(item);
	}
	public void ClickOnSearch()
	{
		SearchButton.click();
	}
	public void ClickOnQuickView()
	{
		QuickView.click();
	}
	
	public String GetQuickProductName()
	{
	 return QuickProductName.getText();
	}
	 public double GetQuickProductPrice() 
	 {
			String p [] =	QuickProductPrice.getText().split(" ");
			return Double.parseDouble(removeCommaFromString(p[0]));
	 }
	 public void ClickOnAddToCartButton(int index)
	    {
	    	if(ColorList.isDisplayed()==true)
	    	{
	    		Color.get(index).click();
	    		AddToCart.click();
	    	}
	    	else
	    	{
	    		AddToCart.click();
	    	}
	    }
	 public void ClickOnAddToCart()
	 {
		 AddToCart.click();
	 }
	 public void ClickOnClose()
	 {
		 Close.click();
	 }
}
