
package AutomationTestingFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameworkDesignPageObjects.CartPageModel;
import FrameworkDesignPageObjects.CheckOutPageModel;
import FrameworkDesignPageObjects.ConfirmationPage;
import FrameworkDesignPageObjects.OrdersPageModel;
import FrameworkDesignPageObjects.ProductCataloguPageModel;
import FrameworkDesignPageObjects.TestComponents.BaseTest;

public class PracticeStandAloneApplication extends BaseTest {
	String productname= "ZARA COAT 3";
// for less data 
	//public void submitOrder(String email, String password)
	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// launchApplication();	

		ProductCataloguPageModel proCataloguPageModel = loginPageModel.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = proCataloguPageModel.getProductList();
		proCataloguPageModel.addProductTocart(input.get("prodname"));
		
		//click on add to cart button
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		CartPageModel cartPageModel=  proCataloguPageModel.gotoCartPage();
		
		// catching names apperaing on cart page
		
		Boolean match= cartPageModel.productDisplayed(input.get("prodname"));
		// applying assertions to check name is found or not
		Assert.assertTrue(match);
	   // driver.findElement(By.cssSelector(".totalRow button")).click();

		CheckOutPageModel checkOutPageModel=  cartPageModel.gotocheckeout();
		checkOutPageModel.addCountryName("India");
		//checkOutPageModel.SelectCountryName("India");
		ConfirmationPage confirmationPage= checkOutPageModel.submitOrder();
				
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		String msg=  confirmationPage.getSuccessMsg();
		//Assert.assertEquals(msg.equalsIgnoreCase("Thankyou for the order."), msg);
		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));	
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCataloguPageModel proCataloguPageModel = loginPageModel.loginApplication("neetamane11@gmail.com", "Neeta@113");
		OrdersPageModel ordersPageModel= proCataloguPageModel.goToOrdersPage();
		Assert.assertTrue(ordersPageModel.verifyOrderDisplayed(productname));

	}
	
	// If you have large amount of data then use HashMap
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> has = new HashMap<String, String>();
//		has.put("email", "neetamane11@gmail.com");
//		has.put("password", "Neeta@113");
//		has.put("prodname","ZARA COAT 3");
//		
//		HashMap<String, String> has1 =new HashMap<String, String>();
//		has1.put("email", "shetty@gmail.com");
//		has1.put("password", "Iamking@000");
//		has1.put("prodname","ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\FrameworkDesign\\data\\PurchaseOrderData.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	
	// if you have less data
	/*@DataProvider
	public Object[][] getData() {
	return new Object[][] {{"neetamane11@gmail.com","Neeta@113","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}
	
	
	*/
	
}


