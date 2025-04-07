package FrameworkDesignAbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkDesignPageObjects.CartPageModel;
import FrameworkDesignPageObjects.OrdersPageModel;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	// cart button is showing at header and on all pages so it is common for all
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findby) {
		// Explicit wait until all items are loaded

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForWebElementToAppear(WebElement findby) {
		// Explicit wait until all items are loaded

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public void waitForElementToDisppear(WebElement ele) throws InterruptedException {
		// Explicit wait until element to disappear
		// To reduce the waiting time due to workload use thread.sleep
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public CartPageModel gotoCartPage() {
		cartButton.click();
		CartPageModel cartPageModel = new CartPageModel(driver);
		return cartPageModel;

	}
	
	public OrdersPageModel goToOrdersPage() {
		orderHeader.click();
		OrdersPageModel orderPageModel = new OrdersPageModel(driver);
		return orderPageModel;

	}
	
	

}
