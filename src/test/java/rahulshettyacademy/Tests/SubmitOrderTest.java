package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData",groups = {"Purchase"})
		public void SubmitOrder(HashMap<String, String> input)
	
	{
		

	    ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));

		List<WebElement> products = productCatalogue.getproductsList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductsDisplay(input.get("productName"));

		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.SelectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.SubmitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	

	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingpage.loginApplication("kavyashetty@abc.com", "Kavya@37");
		OrderPage orderspage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(productName)); 
	}
	
	

	
	@DataProvider
	
	public Object getData()
	{
		HashMap<String, String> map= new HashMap();
		map.put("email", "kavyashetty@abc.com");
		map.put("password", "Kavya@37");
		map.put("productName", "ZARA COAT 3");
		
		map.put("email", "kavyashetty@abc.com");
		map.put("password", "Kavya@37");
		map.put("productName", "ADIDAS ORIGINAL");
		
		
		return new Object[][] {{map},{"map1"}};
	}
	
	
	
}
