package FrameworkDesignPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesignAbstractComponent.AbstractComponent;

public class LoginPageModel extends AbstractComponent {
	WebDriver driver;
	public LoginPageModel(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail=  driver.findElement(By.id("userEmail"));
	
	
	//Page Factory design pattern
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword"));

	@FindBy(id="userPassword")
	WebElement password;
	
	//driver.findElement(By.id("login"))
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		 return errorMessage.getText();
	}
public ProductCataloguPageModel loginApplication(String email, String pass) {
	userEmail.sendKeys(email);
	password.sendKeys(pass);
	submit.click();
	ProductCataloguPageModel proCataloguPageModel = new ProductCataloguPageModel(driver);
	return proCataloguPageModel;
}
public void goToLink() {
	driver.get("https://rahulshettyacademy.com/client");
}

}
