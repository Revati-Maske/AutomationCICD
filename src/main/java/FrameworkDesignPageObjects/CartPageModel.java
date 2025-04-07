package FrameworkDesignPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesignAbstractComponent.AbstractComponent;

public class CartPageModel extends AbstractComponent {
	WebDriver driver;
	public CartPageModel(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(css=".cartSection h3")
		List<WebElement> productNames;
		
		@FindBy(css=".totalRow button")
		WebElement checkoutEle;
		
		public Boolean productDisplayed(String productName) {
			Boolean match= productNames.stream().anyMatch(item-> item.getText().equalsIgnoreCase(productName.trim()));
			// applying assertions to check name is found or not
			return match;
		}
		public CheckOutPageModel gotocheckeout() {
			//checkoutEle.click();
			WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].click();", checkout);
			
			//CheckOutPageModel checkOutPageModel = new CheckOutPageModel(driver);
			return new CheckOutPageModel(driver);
			
		}
}
	

	
		


	

