package rahulshettyacademy.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidation extends BaseTest{
	
	@Test(groups = {"ErrorHandling"})
	public void errorValidation()
	{
		landingpage.loginApplication("kavyashetty@abc.com", "Kavya@38");
		Assert.assertEquals("Incorrect email or password.",  landingpage.getErrorMessage());	
		}
	
	
	@Test
	public void errorValidationForProductName()
	{
		String productName = "ZARA COAT 3";
	    ProductCatalogue productCatalogue = landingpage.loginApplication("kavyashetty@abc.com", "Kavya@37");

		List<WebElement> products = productCatalogue.getproductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductsDisplay("ZARA COAT 33");

		Assert.assertFalse(match);
	}

    
    

}
