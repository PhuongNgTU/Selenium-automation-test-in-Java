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

public class Topic_06_WebElementExercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	//Define reused element into global variable by using 'By' variable	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By eduTextArea = By.cssSelector("#edu");
	By user5Text = By.xpath("//h5[text()='Name: User5']");
	By pwdTextbox = By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By developmentCheckbox = By.cssSelector("#development");
	
	@BeforeTest
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Text box
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("selenium webdriver");
			System.out.println("emailTextbox is displayed");
		} else {
			System.out.println("emailTextbox is not displayed");
		}
		
		//Text area
		if(driver.findElement(eduTextArea).isDisplayed()) {
			driver.findElement(eduTextArea).sendKeys("selenium grid");
			System.out.println("eduTextArea is displayed");
		} else {
			System.out.println("eduTextArea is not displayed");
		}
		
		//Radio button
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("ageUnder18Radio is displayed");
		} else {
			System.out.println("ageUnder18Radio is not displayed");
		}
		
		//Hover
		if(driver.findElement(user5Text).isDisplayed()) {
			System.out.println("user5Text is displayed");
		} else {
			System.out.println("user5Text is not displayed");
		}
	}
	
	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(driver.findElement(pwdTextbox).isEnabled()) {
			System.out.println("password textbox is enabled");
		} else {
			System.out.println("password textbox is disabled");
		}
		
		if(driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("biography text area is enabled");
		} else {
			System.out.println("biography text area is disabled");
		}
		
		if(driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("email textbox is enabled");
		} else {
			System.out.println("email textbox is disabled");
		}
	}
	
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Verify checkbox/radio button hasn't been selected
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
		
		//Click to checkbox/ridio button
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckbox).click();
		sleepInSeconds(3);
		
		//Verify checkbox/radio button has been selected
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());
	}
	
	@Test
	public void TC_04_MailChimp_AssertPartialTrue() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("jean.tyderman2025@gmail.com");
		
		By passwordTextbox = By.id("new_password");
		By signupButton = By.id("create-account-enabled");
		driver.findElement(passwordTextbox).sendKeys("abc");
		driver.findElement(By.id("onetrust-close-btn-container")).click();
		sleepInSeconds(3);
		driver.findElement(By.xpath("//input[@class='dijitReset dijitCheckBoxInput']")).click();
		driver.findElement(signupButton).click();
		sleepInSeconds(3);
		
		//Assert
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='dijitReset dijitCheckBoxInput']")).isSelected());
	}
	
	@Test
	public void TC_05_MailChimp_SendUpperKey() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("jean.tyderman2025@gmail.com");
		By passwordTextbox = By.id("new_password");
		By signupButton = By.id("create-account-enabled");
		String str = "abc";
		driver.findElement(passwordTextbox).sendKeys(str.concat(str.toUpperCase()));
		System.out.println(str.concat(str.toUpperCase()));
		driver.findElement(By.id("onetrust-close-btn-container")).click();
		sleepInSeconds(3);
		driver.findElement(By.xpath("//input[@class='dijitReset dijitCheckBoxInput']")).click();
		driver.findElement(signupButton).click();
		sleepInSeconds(3);
		
		//Assert
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='dijitReset dijitCheckBoxInput']")).isSelected());
	}
	
	@Test
	public void TC_06_MailChimp_AssertAllTrue() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("jean.tyderman2025");
		By passwordTextbox = By.id("new_password");
		By signupButton = By.id("create-account-enabled");
		String str = "abc";
		driver.findElement(passwordTextbox).sendKeys(str.concat(str.toUpperCase()).concat("2810").concat("@"));
		System.out.println(str.concat(str.toUpperCase()).concat("2810").concat("@"));
		driver.findElement(By.id("onetrust-close-btn-container")).click();
		sleepInSeconds(3);
		driver.findElement(By.xpath("//input[@class='dijitReset dijitCheckBoxInput']")).click();
		driver.findElement(signupButton).click();
		sleepInSeconds(3);

		//Assert
		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='rounded-corners-4  av-password success-check']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='dijitReset dijitCheckBoxInput']")).isSelected());
	}
	
	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} //1000ms = 1s
	}
	
	@AfterTest
	public void afterClass() {
		driver.quit();
	}

}
