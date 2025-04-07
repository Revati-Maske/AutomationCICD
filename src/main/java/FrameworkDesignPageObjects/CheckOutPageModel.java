package FrameworkDesignPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesignAbstractComponent.AbstractComponent;

public class CheckOutPageModel extends AbstractComponent {
	WebDriver driver;

	public CheckOutPageModel(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		@FindBy(css="[placeholder='Select Country']")
		private WebElement country;
		
		@FindBy(xpath="//button[contains(@class, 'ta-item')]")
		List<WebElement> listOfCountry;
		
		@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
		private WebElement selectCountry;

		@FindBy(css=".action__submit")
		 WebElement submit;
		
		public void addCountryName(String countryIndia) {
			Actions a = new Actions(driver);
			country.click();
			a.sendKeys(country, countryIndia).build().perform();
			waitForElementToAppear(By.cssSelector(".ta-results"));
			/*List<WebElement> options= listOfCountry;		
		       for(WebElement option: options) {
		       	if(option.getText().equalsIgnoreCase(countryIndia)) {
		       		option.click();
		       		break;
		       		
		       	}*/
			//submit.click();
			
			////body//app-root//button[2]
			WebElement element = driver.findElement(By.xpath("//body//app-root//button[2]"));

			Actions actions = new Actions(driver);

			actions.moveToElement(element).click().perform();
		}
		
		/*public void SelectCountryName(String countryName) {
			List<WebElement> options= listOfCountry;		
		       for(WebElement option: options) {
		       	if(option.getText().equalsIgnoreCase(countryName)) {
		       		option.click();
		       		break;
		       		
		       	}
		       }			
		}*/	
		public ConfirmationPage submitOrder() {
			waitForElementToAppear(By.cssSelector(".action__submit"));
			WebElement element = driver.findElement(By.cssSelector(".action__submit"));

			Actions actions = new Actions(driver);

			actions.moveToElement(element).click().perform();
			//submit.click();
			 return new ConfirmationPage(driver);
		}
}
