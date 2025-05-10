package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
	public void TC_03_Selected() {}
	
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
