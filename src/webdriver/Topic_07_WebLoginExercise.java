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

public class Topic_07_WebLoginExercise {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress;
	String password;
	String firstName;
	String lastName;
	
	//Define reused element into global variable by using 'By' variable	

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
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		websiteUrl();
		
		driver.findElement(By.xpath("//span[@class='label' and contains(text(), 'Account')]")).click();
		var myAccountElement = "//div[@id='header-account']/descendant::a[@title='My Account' and contains(text(), 'My Account')]";
		driver.findElement(By.xpath(myAccountElement)).isDisplayed();
		driver.findElement(By.xpath(myAccountElement)).click();
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSeconds(3);
		
		//Assert
		driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@id='advice-required-entry-email']")).isDisplayed();
		driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[@id='advice-required-entry-pass']")).isDisplayed();
	}
	
	@Test
	public void TC_02_LoginWithWrongEmailFormat() {
		websiteUrl();
		
		driver.findElement(By.xpath("//span[@class='label' and contains(text(), 'Account')]")).click();
		var myAccountElement = "//div[@id='header-account']/descendant::a[@title='My Account' and contains(text(), 'My Account')]";
		driver.findElement(By.xpath(myAccountElement)).isDisplayed();
		driver.findElement(By.xpath(myAccountElement)).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12341234@12312.123123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123567");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_LoginWithPasswordLessThan6Chars() {
		driver.get("https://live.techpanda.org/");
		
		driver.findElement(By.xpath("//span[@class='label' and contains(text(), 'Account')]")).click();
		var myAccountElement = "//div[@id='header-account']/descendant::a[@title='My Account' and contains(text(), 'My Account')]";
		driver.findElement(By.xpath(myAccountElement)).isDisplayed();
		driver.findElement(By.xpath(myAccountElement)).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("jean.tyderman1996@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSeconds(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_LoginWithIncorrectEmail() {
		websiteUrl();
		
		driver.findElement(By.xpath("//span[@class='label' and contains(text(), 'Account')]")).click();
		var myAccountElement = "//div[@id='header-account']/descendant::a[@title='My Account' and contains(text(), 'My Account')]";
		driver.findElement(By.xpath(myAccountElement)).isDisplayed();
		driver.findElement(By.xpath(myAccountElement)).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress); //Enter email not existed in system 
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSeconds(3);
		
		driver.switchTo().alert().accept(); // Handle alert from browser

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05_CreateNewAccount() {
		websiteUrl();
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(3);
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSeconds(3);
		
		driver.findElement(By.id("firstname")).sendKeys("helge");
		driver.findElement(By.id("lastname")).sendKeys("doppler");
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		System.out.println(password);
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		driver.switchTo().alert().accept(); // Handle alert from browser
		sleepInSeconds(3);
		
		//Assert: Register successfully
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformationText);
		Assert.assertTrue(contactInformationText.contains(firstName + " " + lastName));
		System.out.println(firstName + " " + lastName);
		Assert.assertTrue(contactInformationText.contains(emailAddress));
		
		//Log out
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, 'logo.png')]")).isDisplayed());
	}
	
	@Test
	public void TC_06_LoginWithValidAccount() {
		websiteUrl();
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(3);
		
		driver.findElement(By.id("email")).sendKeys("jean.tyderman834@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("NozzaGrande6");
		driver.findElement(By.id("send2")).click();
		
		// Handle alert from browser
		driver.switchTo().alert().accept(); 
		sleepInSeconds(3);
		
		//Assert: Register successfully
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformationText.contains(firstName + " " + lastName));
		System.out.println(firstName + " " + lastName);
		Assert.assertTrue(contactInformationText.contains("jean.tyderman834@gmail.com"));
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
