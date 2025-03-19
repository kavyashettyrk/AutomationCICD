package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstactComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(css=".action__submit")
	WebElement Submit;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement SelectCountry;
	
	By Results=By.cssSelector("section[class *=ta-results]");
	
		
	public void SelectCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(Country,countryName).build().perform();
		waitForElementToAppear(Results);

		SelectCountry.click();
		a.scrollByAmount(0, 500).build().perform();
	
	}
	
	public ConfirmationPage SubmitOrder()
	{
		Submit.click();
		return new ConfirmationPage(driver);
	}
	
}
