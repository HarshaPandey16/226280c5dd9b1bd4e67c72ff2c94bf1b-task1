package testingAssignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class LoginFunctionality {
	public String baseUrl="https://thepromoapp.com/#!/login";
	public String expectedUrl="https://thepromoapp.com/#!/location";
	public WebDriver driver=null;
	public WebElement usrname;
	public WebElement pswrd;
	public WebElement loginbutton;
	
	@BeforeMethod
	public void elementLocations() {
		driver=new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();  
		usrname=driver.findElement(By.name("userName"));
		pswrd=driver.findElement(By.name("password"));
		loginbutton=driver.findElement(By.xpath(".//button[@type='submit']"));
	}
	
	@Parameters({"validusername","validpassword"})
	@Test (priority=4)
	public void validusrnamevalidpaswrd(String validusername, String validpassword) {
		usrname.sendKeys(validusername);
		pswrd.sendKeys(validpassword);
		loginbutton.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		String actualUrl=driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Parameters({"validusername","invalidpassword"})
	@Test (priority=1)
	public void validUsrnameInvalidPswrd(String validusername, String invalidpassword) {
		usrname.sendKeys(validusername);
		pswrd.sendKeys(invalidpassword);
		loginbutton.click();
		String actualUrl=driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);	
	}
	
	@Parameters({"invalidusername","validpassword"})
	@Test (priority=2)
	public void invalidUsrnamevalidPswrd(String invalidusername, String validpassword) {
		usrname.sendKeys(invalidusername);
		pswrd.sendKeys(validpassword);
		loginbutton.click();
		String actualUrl=driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Parameters({"invalidusername","invalidpassword"})
	@Test (priority=3)
	public void invalidUsrnameinvalidPswrd(String invalidusername, String invalidpassword) {
		usrname.sendKeys(invalidusername);
		pswrd.sendKeys(invalidpassword);
		loginbutton.click();
		String actualUrl=driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);	
	}
	
	@AfterMethod
	public void loginPage() {
		driver.close();
	}
}
