
package AutomationTestingFrameworkDesign;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import FrameworkDesignPageObjects.LoginPageModel;

public class StandAloneApplication {

	public static void main(String[] args) throws InterruptedException  {
		String productname= "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("neetamane11@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Neeta@113");
		driver.findElement(By.id("login")).click();
		

		// Explicit wait until all items are loaded
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod1= products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		System.out.println(prod1);
		//click on add to cart button
		prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		// Explicit wait until "product added to cart" msg display
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		// catching names apperaing on cart page
		List<WebElement> cartItems= driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match= cartItems.stream().anyMatch(item-> item.getText().equalsIgnoreCase(productname));
		// applying assertions to check name is found or not
		Assert.assertTrue(match);
	   // driver.findElement(By.cssSelector(".totalRow button")).click();
/*
 *Using JavaScript to click on an element via executeScript() is often used as a workaround in Selenium WebDriver when the standard click() method doesn't work as expected. There are a few reasons why you might resort to using JavaScript for clicking:

Element Not Interactable: Sometimes, Selenium's click() method fails to click on an element because the element is not considered "interactable" by WebDriver. This can happen if the element is covered by another element, not visible, or not in the expected state for clicking.

JavaScript Event Handlers: If the element has JavaScript event handlers attached to it (such as onclick), using JavaScript to trigger the click event can ensure that those handlers are executed.

Stale Element Reference: If the element becomes stale after it is located, attempting to click on it with Selenium's click() method may result in a StaleElementReferenceException. Using JavaScript to click can bypass this issue in some cases.

Animations or Transitions: If there are animations or transitions on the page that affect the element's visibility or clickability, using JavaScript to click can ensure that the click is executed regardless of the animation state.

Performance: In some cases, using JavaScript to click on an element can be faster than the standard Selenium click() method, especially if the page contains complex JavaScript or dynamic content.
 */
		WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", checkout);
		
		
		
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//Section[@class='ta-results list-group ng-star-inserted']/button //span[text()=' "+"India"+"']")).click();
		js.executeScript("window.scrollBy(0,100)");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		String msg=  driver.findElement(By.cssSelector(".hero-primary")).getText();
		//Assert.assertEquals(msg.equalsIgnoreCase("Thankyou for the order."), msg);
		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
		
	}
}


