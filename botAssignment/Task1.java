package botAssignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class InputParameters
{
	public String hostname;
	public String uname;
	public String pass;
	public String from_email;
	public String to_email;
	public String logfile;
	
	public InputParameters(String hostname,String uname,String pass,String from_email,String to_email,String logfile)
	{
		this.hostname = hostname;
		this.uname =uname;
		this.pass =pass;
		this.from_email=from_email;
		this.to_email=to_email;
		this.logfile=logfile;
	}
}

public class Task1 {
	
	public static void main(String[] args) 
	{
		String fileName="C:\\Users\\DELL\\Desktop\\JAVA Coding\\Automate.io\\src\\botAssignment\\Inputs.tsv";
		InputParameters ip = null ;
		try  
		{  
		File file=new File(fileName);    //creates a new file instance  
		FileReader fr=new FileReader(file);   //reads the file  
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
		br.readLine();
		String inputs =br.readLine();
		String[] parameters =inputs.split(",") ;
		ip = new InputParameters(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5]);
		fr.close();      
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}  
		
		System.out.println(ip.from_email);
		System.out.println(ip.uname);
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(ip.hostname);
		
		//Applying Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Click on Login and again click on Login
		driver.findElement(By.cssSelector("a.c-btn.c-btn--border")).click();
		driver.findElement(By.cssSelector("a[tabindex='8']")).click();
		
		//Now Login with Username and Password
		driver.findElement(By.id("email")).sendKeys(ip.uname);
		driver.findElement(By.id("password")).sendKeys(ip.pass);
		driver.findElement(By.xpath(".//button[@type='submit']")).click();
		
		//Click to Create a Bot
		driver.findElement(By.linkText("Create a Bot")).click();
		
		//Click to Select Trigger app and selecting Gmail
		WebElement leftDrpdwn=driver.findElement(By.cssSelector("button.btn.btn-white2.btn-block.dropdown-toggle"));
		leftDrpdwn.click();
		WebElement fromGmail=driver.findElement(By.cssSelector("div.ellipsed.help-block.identifier"));
		String from_uname=fromGmail.getText();
		
		// Log Error If not Authenticated
		if(!from_uname.equalsIgnoreCase(ip.from_email))
		{
		
			try{    
				String currentDirectory = System.getProperty("user.dir");
				System.out.println(currentDirectory);
		           FileWriter fw=new FileWriter(currentDirectory+"\\"+ip.logfile);    
		           fw.write("Gmail Not Autheticated");    
		           fw.close();    
		          }
			catch(Exception e)
			{
				System.out.println(e);
			}
			driver.close();
			System.exit(0);
		}
		
		fromGmail.click();
		
		//Click to select New Email option
		driver.findElement(By.cssSelector("div.heading.ellipsed.no-padding[title='New Email']")).click();
		
		//Click on Label and type Inbox
		driver.findElements(By.cssSelector("button.btn.btn-block.dropdown-toggle.flex-row-align-center.btn-default")).get(1).click();
	    driver.findElement(By.xpath("//*[starts-with(@id, 'aio_w_')]/div[2]/div[3]/div[1]/aio-content-group/div/aio-content-input/aio-content-dropdown/div/aio-btn-group-dropdown/div/ul/aio-searchable-dropdown/form/input")).sendKeys("Inbox");
	    //Click on Inbox option 
	    driver.findElement(By.xpath("//*[starts-with(@id, 'aio_w_')]/div[2]/div[3]/div[1]/aio-content-group/div/aio-content-input/aio-content-dropdown/div/aio-btn-group-dropdown/div/ul/aio-searchable-dropdown/div/li/div[1]/span[1]")).click();
	    
	    //Move to right
	    WebElement rightDrpdwn=driver.findElements(By.cssSelector("button.btn.btn-white2.btn-block.dropdown-toggle")).get(1);
	    rightDrpdwn.click();
	    //Select Gmail Option and select Send an Email option
	    driver.findElements(By.cssSelector("div.ellipsed.help-block.identifier")).get(1).click();
	    driver.findElement(By.cssSelector("div.heading.ellipsed.no-padding[title='Send an Email']")).click();
	    
	    //Get all clickable image elements on Right to click on Dropdowns
	    List<WebElement> inputFields=driver.findElements(By.cssSelector("i.icon-circle_plus"));
	    
	    //Updating From Name 
	    inputFields.get(1).click();
	    driver.findElement(By.cssSelector("div.heading.ellipsed[title='From Name']")).click();
	    //Updating From Address
	    inputFields.get(2).click();
	    driver.findElements(By.cssSelector("div.heading.ellipsed[title='From Email']")).get(1).click();
	    //Updating To Name and To Address
	    driver.findElements(By.cssSelector("div.content-editable.form-control.buttoned")).get(3).sendKeys("Dummy");
	    driver.findElements(By.cssSelector("div.content-editable.form-control.buttoned")).get(4).sendKeys(ip.to_email);
	    //Udating Subject and Email Body
	    inputFields.get(8).click();
	    driver.findElements(By.cssSelector("div.heading.ellipsed[title='Subject']")).get(7).click();
	    inputFields.get(9).click();
	    inputFields.get(9).click();
	    driver.findElements(By.cssSelector("div.heading.ellipsed[title='Body']")).get(8).click();
	    
	    //Save the Bot
	    driver.findElement(By.cssSelector("a.btn.btn-cta1.mw6.pull-right")).click();
	   
	    //Enable the Bot
	    driver.findElement(By.cssSelector("span.disc")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-cta2.fixed-size-button")).click();
	    
	    //Logout
	    driver.findElement(By.cssSelector("span.text-uppercase.user-name")).click();
	    driver.findElement(By.linkText("Logout")).click();  
	    
	}
}
