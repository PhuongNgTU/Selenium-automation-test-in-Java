package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_05_WebBrowserExercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeTest
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Wait này chỉ áp dụng cho việc tìm element, ko áp dụng cho việc chuyển trang
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Url() {
		driver.get("https://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
		sleepInSeconds(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_Title() {
		driver.get("https://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(3);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
		sleepInSeconds(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigate() {
		driver.get("https://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(2);
		
		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
		sleepInSeconds(2);
		
		driver.navigate().back();
		sleepInSeconds(2);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.navigate().forward();
		sleepInSeconds(2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_04_PageSourceHTML() {
		driver.get("https://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(2);
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
		sleepInSeconds(2);
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
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
