
package AutomationTestingFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import FrameworkDesignPageObjects.CartPageModel;
import FrameworkDesignPageObjects.CheckOutPageModel;
import FrameworkDesignPageObjects.ConfirmationPage;
import FrameworkDesignPageObjects.ProductCataloguPageModel;
import FrameworkDesignPageObjects.TestComponents.BaseTest;
import FrameworkDesignPageObjects.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidatation() throws IOException, InterruptedException {
			String productname= "ZARA COAT 3";
		// launchApplication();	

		 loginPageModel.loginApplication("neetamane11@gmail.com", "Nee@113");
		Assert.assertEquals("Incorrect email or password.", loginPageModel.getErrorMessage());	
	}
	@Test(retryAnalyzer = Retry.class)
	public void productErrorValidatation() throws IOException, InterruptedException {
			String productname= "ZARA COAT 3";
		
		ProductCataloguPageModel proCataloguPageModel = loginPageModel.loginApplication("rahulshettyacademy@gmail.com", "Neeta@113");
		
		List<WebElement> products = proCataloguPageModel.getProductList();
		proCataloguPageModel.addProductTocart(productname);
		
		CartPageModel cartPageModel=  proCataloguPageModel.gotoCartPage();
		
		// catching names apperaing on cart page
		
		Boolean match= cartPageModel.productDisplayed("ZARA COAT 3");
		// applying assertions to check name is found or not
		Assert.assertFalse(match);
}
	
}


