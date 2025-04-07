package FrameworkDesignPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkDesignAbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent  {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement successmsg;
	
	public String getSuccessMsg()
	{
		CheckOutPageModel cp = new CheckOutPageModel(driver);
		String msg = successmsg.getText();
		return msg;
	}
}
