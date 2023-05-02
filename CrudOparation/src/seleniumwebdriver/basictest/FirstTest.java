package seleniumwebdriver.basictest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstTest {
	
	public static void main(String args[]){
	
		//System.setProperty("webdriver.gecko.driver","E:\\Selenium WebDeiver\\lib\\geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver", "E:\\gecko\\geckodriver.exe");
		//FirefoxDriver driver = new FirefoxDriver();   //Invoke the Browser 
		System.setProperty("webdriver.gecko.driver","E:\\Selenium WebDeiver\\lib\\geckodriver.exe");
		//driver= new FirefoxDriver();
		WebDriver driver = new FirefoxDriver();   //Invoke the Browser 
		driver.get("https://www.facebook.com/");  //Open the URL
//driver.findElement(By.id("lst-ib"));
		
/*		WebElement username = driver.findElement(By.name("uername"));
		username.sendKeys("9545303115");
		
		driver.findElement(By.name("password")).sendKeys("95453033115");
	
		driver.findElement(By.name("login")).click();   */
	}
}
