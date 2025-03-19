package rahulshettyacademy.pageobjects;





import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstactComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElements(By.cssSelector("div[class=cartSection] h3"));
	
	
	@FindBy(css="div[class=cartSection] h3")
	List<WebElement> cartProducts;
	
	@FindBy(css="li button[type=button]")
	WebElement checkOut;
	
		
	public Boolean VerifyProductsDisplay(String productName)
	{
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut()
	{
		checkOut.click();
		CheckOutPage checkOutPage=new CheckOutPage(driver);
		return new CheckOutPage(driver);
		
	}


	
	
}
