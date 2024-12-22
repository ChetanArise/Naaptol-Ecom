package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaaptolCartPage extends RemoveComma
{

	@FindBy (xpath = "//div[@class='cart_info']//h2") private List<WebElement> cartProductName; 
     @FindBy (xpath = "//li[@class='head_UPrice']") private List<WebElement> cartProductPrice;
     @FindBy (xpath = " (//button[@type='button'])[2]") private WebElement close;
     @FindBy (xpath = "//a[text()='Remove']") private List<WebElement> remove;
     @FindBy (xpath = "//div[@id='cartItems']//ul") private List<WebElement> cartProductList;
     @FindBy (xpath = "//li[@class='head_ship']") private List<WebElement> cartShippingPrice;
     @FindBy (xpath = "//li[@class='head_Amount']") private List<WebElement> orderAmount;
     @FindBy (xpath = "//li[@class='head_qty']") private List<WebElement> quantity;
     @FindBy (xpath = "//span[@id='totalPayableAmount']") private WebElement totalAmount;

    public NaaptolCartPage(WebDriver driver)
    {
       PageFactory.initElements(driver,this);
    }  
    public String getProductName(int index)
    {
       return cartProductName.get(index).getText();
    }
    public double getProductPrice(int index) 
    {
       String [] price =cartProductPrice.get(index).getText().substring(3).split(" ");
       return Double.parseDouble(removeCommaFromString(price[0]));
    }
    public void clickOnClose()
    {
	   close.click();
    }
    public void clickOnRemove(int index)
    {
	   remove.get(index).click();
    }
    public int getCartProductListCount()
    {
	   return cartProductList.size();
	}
    public double getCartShippingPrice(int index)
    {
	   String [] sPrice= cartShippingPrice.get(index).getText().substring(3).split(" ");
	   return Double.parseDouble(removeCommaFromString(sPrice[0]));
    }
    public double getOrderAmount(int index)
    {
	   String [] oAmount=orderAmount.get(index).getText().split(" ");
	   return Double.parseDouble(removeCommaFromString(oAmount[0]));
    }
    public void clickOnQuantity(int index)
    {
	   quantity.get(index).click();
    }
    public void enterQuantity(int index,String Quantity)
    {
	   quantity.get(index).sendKeys(Quantity);;
    }
    public double getTotalAmount()
    {
	   String tAmount[]=totalAmount.getText().split(" ");
	   return Double.parseDouble(removeCommaFromString(tAmount[0]));
    }
}
