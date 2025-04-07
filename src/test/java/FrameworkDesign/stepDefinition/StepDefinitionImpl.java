package FrameworkDesign.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import FrameworkDesignPageObjects.CartPageModel;
import FrameworkDesignPageObjects.CheckOutPageModel;
import FrameworkDesignPageObjects.ConfirmationPage;
import FrameworkDesignPageObjects.LoginPageModel;
import FrameworkDesignPageObjects.ProductCataloguPageModel;
import FrameworkDesignPageObjects.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	ProductCataloguPageModel proCataloguPageModel;
	LoginPageModel	loginPageModel;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException  {
			loginPageModel= launchApplication();
	}
	
	@Given("^Logged in with username(.+) and password(.+)$")
	public void logged_in_with_username_and_password(String userName, String password) throws IOException {
		proCataloguPageModel= loginPageModel.loginApplication(userName, password);
	}
	@When("^add product(.+) to cart$")
	public void add_product_to_cart(String prodName) throws InterruptedException {
		List<WebElement> products = proCataloguPageModel.getProductList();
		proCataloguPageModel.addProductTocart(prodName);
		}
	
	@When("^Checkout(.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		CartPageModel cartPageModel=  proCataloguPageModel.gotoCartPage();
		// catching names apperaing on cart page		
		Boolean match= cartPageModel.productDisplayed(productName);
		// applying assertions to check name is found or not
		Assert.assertTrue(match);
		CheckOutPageModel checkOutPageModel=  cartPageModel.gotocheckeout();
		checkOutPageModel.addCountryName("India");
		 confirmationPage= checkOutPageModel.submitOrder();
	}
	
	//for message data coming from feature file written in string and coming from examples written using regular expression.
	@Then("{string} message should display on confirmation page")
	public void message_should_display_on_confirmation_page(String expectedMessage) {
		String msg=  confirmationPage.getSuccessMsg();
		Assert.assertTrue(msg.equalsIgnoreCase(expectedMessage));
	}
	
	@When("^Logged in with (.+) and (.+)$")
	public void logged_in_with_username_and_password1(String userName, String password) throws IOException {
	    proCataloguPageModel = loginPageModel.loginApplication(userName, password);
	}
	@Then("{string} message should displayed")
	public void message_should_displayed(String expectedMessage) {
		Assert.assertEquals(expectedMessage, loginPageModel.getErrorMessage());	

	}


}
