package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends RemoveComma  
{
    @FindBy (xpath = "//h1[text()='Dual Sim Foldable Flip Mobile With Camera - Gamma (M2 Mini)']") private WebElement productName;
    @FindBy (xpath = "//span[@class='offer-price']") private WebElement productPrice;
    @FindBy (xpath = "//ul[@class='sizeBox clearfix']") private WebElement colorList;
    @FindBy (xpath = "//ul[@class='sizeBox clearfix']//li") private List<WebElement> color;
    @FindBy (xpath = "//span[text()='Click here to Buy']") private WebElement addToCartButton;
 
    public ProductDetailPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
    public String getProductName()
	{
	    return productName.getText();
  	}
    public double getProductPrice() 
    {
 		String [] price =	productPrice.getText().split(" ");
		return Double.parseDouble(removeCommaFromString(price[0]));
	}
    public void clickOnAddToCartButton(int index)
    {
    	if(colorList.isDisplayed()==true)
    	{
    		color.get(index).click();
    		addToCartButton.click();
    	}
    	else
    	{
    		addToCartButton.click();
    	}
    }
}
