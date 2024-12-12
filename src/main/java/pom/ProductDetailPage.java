package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends RemoveComma  
{
 @FindBy (xpath = "//h1[text()='Dual Sim Foldable Flip Mobile With Camera - Gamma (M2 Mini)']") private WebElement ProductName;
 @FindBy (xpath = "//span[@class='offer-price']") private WebElement ProductPrice;
 @FindBy (xpath = "//ul[@class='sizeBox clearfix']") private WebElement ColorList;
 @FindBy (xpath = "//ul[@class='sizeBox clearfix']//li") private List<WebElement> Color;
 @FindBy (xpath = "//span[text()='Click here to Buy']") private WebElement AddToCartButton;
 
    public ProductDetailPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
    public String GetProductName()
	{
	 return ProductName.getText();
  	}
    public double getProductPrice() {
 		String [] p =	ProductPrice.getText().split(" ");
		return Double.parseDouble(removeCommaFromString(p[0]));
	}
    public void ClickOnAddToCartButton(int index)
    {
    	if(ColorList.isDisplayed()==true)
    	{
    		Color.get(index).click();
    		AddToCartButton.click();
    	}
    	else
    	{
    		AddToCartButton.click();
    	}
    }
 
}
