package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC04_RegisterWithPasswordLessThan6Characters {
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
	public void TC_04_RegisterWithPasswordLessThan6Characters() {
		//Variables
		var passwordWith5Characters = "Nozza";
		var fullName = "Helge Doppler";
		var email = "helgedoppler211224@gmail.com";
		var phone = "0333774461";
		var expectedErrorMessage = "Mật khẩu phải có ít nhất 6 ký tự";
		
		//Test
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys(fullName);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(passwordWith5Characters);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys(passwordWith5Characters);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys(phone);	
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Assert
		var actualErrorMessage = driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
		var actualErrorCMessage = driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		Assert.assertEquals(actualErrorCMessage, expectedErrorMessage);
	}
	
	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}