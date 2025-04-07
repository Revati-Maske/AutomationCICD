package FrameworkDesignPageObjects.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FrameworkDesignPageObjects.LoginPageModel;

public class BaseTest {
	public WebDriver driver;
	public LoginPageModel loginPageModel;
	public WebDriver initializeDriver() throws IOException {
		
		//To parse and extract GlobalData.properties file use below class from util package
		
		Properties prop = new Properties();
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\FrameworkDesign\\resources\\GlobalData.properties");
		prop.load(fi);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
	 //String browserName= prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			ChromeOptions option= new ChromeOptions();
			
			if(browserName.contains("headless")) {
			option.addArguments("headless");
		}
		 
	 driver = new ChromeDriver();
	
	 }
	 else if(browserName.equalsIgnoreCase("firefox")) {
		 //Firefox driver code
	 }
	 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	    @BeforeMethod(alwaysRun=true)
		public LoginPageModel launchApplication() throws IOException {
			driver =initializeDriver();
			 loginPageModel= new LoginPageModel(driver);
			loginPageModel.goToLink();
			return loginPageModel;
			
		}
	    
	    public String getScreenshot(String str, WebDriver driver) throws IOException {
	    	TakesScreenshot ts = (TakesScreenshot)driver;
	    	File source= ts.getScreenshotAs(OutputType.FILE);
	    	File dest = new File(System.getProperty("user.dir")+"//reports//"+ str+".png");
	    	FileUtils.copyFile(source, dest);
	    	return System.getProperty("user.dir")+"//reports//"+ str+".png";
	    	
	    	
	    }
	    
	    @AfterMethod(alwaysRun=true)
		public void tearDown() {
			driver.quit();
			
		}
	    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
			
			//read json to String 
			String jsonContent= FileUtils.readFileToString(new File(filePath));
			
			//convert string to hashmap
			ObjectMapper objectMapper = new ObjectMapper();
			List<HashMap<String, String>> data= objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
				
			});
			return data;
		}
}

	


