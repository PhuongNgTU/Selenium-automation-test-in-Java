package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC06_RegisterWithInvalidPhoneNumber {
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
	public void TC_06_RegisterWithInvalidPhoneNumber() {
		//Variables
		var password = "NozzaGrande@1280";
		var fullName = "Helge Doppler";
		var email = "helgedoppler211224@gmail.com";
		var phone = "096767676767";
		var expectedErrorMessage = "Số điện thoại phải từ 10-11 số.";
		
		//Test
		driver.findElement(By.id("txtFirstname")).sendKeys(fullName);
		driver.findElement(By.id("txtEmail")).sendKeys(email);
		driver.findElement(By.id("txtCEmail")).sendKeys(email);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("txtCPassword")).sendKeys(password);
		driver.findElement(By.id("txtPhone")).sendKeys(phone);	
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Assert
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), expectedErrorMessage);
	}
	
	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}