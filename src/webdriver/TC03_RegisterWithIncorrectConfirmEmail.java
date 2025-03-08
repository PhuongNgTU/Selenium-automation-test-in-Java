package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC03_RegisterWithIncorrectConfirmEmail {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			String url = System.setProperty("webdriver.gecko.driver",
					projectPath + "\\browserDrivers\\geckodriver.exe");
			System.out.println(url);
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test
	public void TC_03_RegisterWithIncorrectConfirmEmail() {
		var fullName = "Helge Doppler";
		var email = "helgedoppler@yahoo.com";
		var password = "NozzaGrande@128096";
		var phone = "0333774461";
		var expectedMessage = "Email nhập lại không đúng";
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys(fullName);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("helgedoppler@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys(phone);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Assert
		var cEmailErrorMessage = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
		Assert.assertEquals(cEmailErrorMessage, expectedMessage);
	}
	
	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}