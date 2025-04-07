package FrameworkDesignPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesignAbstractComponent.AbstractComponent;

public class OrdersPageModel extends AbstractComponent {
	WebDriver driver;
	public OrdersPageModel(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(css="tr td:nth-child(3)")
		List<WebElement> productNames;
		
		@FindBy(css=".totalRow button")
		WebElement checkoutEle;
		
		public Boolean verifyOrderDisplayed(String productName) {
			Boolean match= productNames.stream().anyMatch(item-> item.getText().equalsIgnoreCase(productName));
			// applying assertions to check name is found or not
			return match;
		}
		
}
