package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaaptolQuickView extends RemoveComma 
{
	@FindBy(xpath = "//input [@id='header_search_text']")private WebElement searchTab;
	@FindBy(xpath = "(//a [@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement searchButton;
	@FindBy (xpath ="//div [@class='grid_Square']") private List<WebElement> searchedProducts; 
    @FindBy (xpath ="//a [@class='bt_compare icon chat quickFancyBox']") private WebElement quickView;
    @FindBy (xpath = "//a [text()='Dual Sim Foldable Flip Mobile With Camera - Gamma (M2 Mini)']") private WebElement productName;
    @FindBy (xpath = "//h1 [text()='Dual Sim Foldable Flip Mobile With Camera - Gamma (M2 Mini)']") private WebElement quickProductName;
    @FindBy (xpath = "//span[@class='offer-price'] ") private WebElement quickProductPrice;
    @FindBy (xpath = "//ul[@class='sizeBox clearfix']//li") private List<WebElement> color;
    @FindBy (xpath = "//ul[@class='sizeBox clearfix']") private WebElement colorList;
    @FindBy (xpath="//span[text()='Click here to Buy']") private WebElement addToCart;
    @FindBy (xpath = " //button[@type='button']") private WebElement close;
	
	public NaaptolQuickView(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public void clickOnSearchTab(String item)
	{
		searchTab.sendKeys(item);
	}
	public void clickOnSearch()
	{
		searchButton.click();
	}
	public void clickOnQuickView()
	{
		quickView.click();
	}
	
	public String getQuickProductName()
	{
	 return quickProductName.getText();
	}
	 public double getQuickProductPrice() 
	 {
			String p [] =	quickProductPrice.getText().split(" ");
			return Double.parseDouble(removeCommaFromString(p[0]));
	 }
	 public void clickOnAddToCartButton(int index)
	    {
	    	if(colorList.isDisplayed()==true)
	    	{
	    		color.get(index).click();
	    		addToCart.click();
	    	}
	    	else
	    	{
	    		addToCart.click();
	    	}
	    }
	 public void clickOnAddToCart()
	 {
		 addToCart.click();
	 }
	 public void clickOnClose()
	 {
		 close.click();
	 }
}
