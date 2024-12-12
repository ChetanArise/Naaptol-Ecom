package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaaptolCartPage extends RemoveComma
{
 @FindBy (xpath = "//div[@class='cart_info']//h2") private List<WebElement> CartProductName; 
 @FindBy (xpath = "//li[@class='head_UPrice']") private List<WebElement> CartProductPrice;
 @FindBy (xpath = " (//button[@type='button'])[2]") private WebElement Close;
 @FindBy (xpath = "//a[text()='Remove']") private List<WebElement> Remove;
 @FindBy (xpath = "//div[@id='cartItems']//ul") private List<WebElement> CartProductList;
 @FindBy (xpath = "//li[@class='head_ship']") private List<WebElement> CartShippingPrice;
 @FindBy (xpath = "//li[@class='head_Amount']") private List<WebElement> OrderAmount;
 @FindBy (xpath = "//li[@class='head_qty']") private List<WebElement> Quantity;
 @FindBy (xpath = "//span[@id='totalPayableAmount']") private WebElement TotalAmount;

 public NaaptolCartPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
 public String getProductname(int index)
 {
	  return CartProductName.get(index).getText();
 }
 public double GetProductPrice(int index) {
		String [] p =	CartProductPrice.get(index).getText().substring(3).split(" ");
		return Double.parseDouble(removeCommaFromString(p[0]));
	}
 public void ClickOnClose()
 {
	 Close.click();
 }
 public void ClickOnRemove(int index)
 {
	 Remove.get(index).click();
 }
 public int GetCartProductListCount()
 {
	return CartProductList.size();
	 
 }
 public double GetCartShippingPrice(int index)
 {
	 String [] s= CartShippingPrice.get(index).getText().substring(3).split(" ");
	 return Double.parseDouble(removeCommaFromString(s[0]));
 }
 public double GetOrderAmount(int index)
 {
	 String [] o=OrderAmount.get(index).getText().split(" ");
	 return Double.parseDouble(removeCommaFromString(o[0]));
 }
 public void ClickOnQuantity(int index)
 {
	 Quantity.get(index).click();
 }
 public void EnterQuantity(int index,String quantity)
 {
	 Quantity.get(index).sendKeys(quantity);;
 }
 public double GetTotalAmount()
 {
	 String t[]=TotalAmount.getText().split(" ");
	 return Double.parseDouble(removeCommaFromString(t[0]));
 }
 
}
