package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;

public class Topic_08_WebTextBoxAndTextAreaExercise {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	//Define reused element into global variable by using 'By' variable	
	String emailAddress;
	String password;
	String firstName;
	String lastName;
	
	/* Textbox ko thể xuống dòng 
	<input type='text'> <br> 
	<input type='number'> <br>
	<input type='email'> <br>
	<input type='checkbox'> <br>
	<input type='file'> <br>
	<input type='radio'> <br>
	<input type='button'> <br>
	<input type='date'> <br>
	<input type='time'> <br>
	
	//Có thể xuống dòng vs textbox 
	<textarea id="edu" name="user_edu"></textarea> */

	@BeforeTest
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		
		rand = new Random();
		emailAddress = "jean.tyderman" + rand.nextInt(1000) + "@gmail.com";
		password = "NozzaGrande" + rand.nextInt(10);
		firstName = "helge";
		lastName = "doppler";
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC() {
		
	}
	
	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} //1000ms = 1s
	}
	
	public void websiteUrl() {
		driver.get("https://live.techpanda.org/");
		sleepInSeconds(3);
	}
	
	@AfterTest
	public void afterClass() {
		driver.quit();
	}

}
