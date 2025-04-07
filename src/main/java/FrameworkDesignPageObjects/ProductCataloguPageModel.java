package FrameworkDesignPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesignAbstractComponent.AbstractComponent;

public class ProductCataloguPageModel extends AbstractComponent {
	WebDriver driver;

	public ProductCataloguPageModel(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

		
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By productToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");

	public  List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productname) {
		
		 List<WebElement> productList = getProductList();
	      
		WebElement prod1= getProductList().stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname.trim())).findFirst().orElse(null);
		return prod1;
	}
	public void addProductTocart(String productname) throws InterruptedException {
		WebElement prod2= getProductByName(productname);
		  if (prod2 == null) {
		        throw new RuntimeException("Product with name " + productname + " not found");
		    }
		prod2.findElement(productToCart).click();	
		// Explicit wait until "product added to cart" msg display
		waitForElementToAppear(toastMsg);
		//OR wait until element disappear
		waitForElementToDisppear(spinner);
		


	}
}
