package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC01_RegisterWithEmptyData {
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
	public void TC_01_RegisterWithInvalidEmail() {
		//Variables
		var expectedFirstnameErrorMsg = "Vui lòng nhập họ tên";
		var expectedEmailErrorMsg = "Vui lòng nhập email";
		var expectedCEmailErrorMsg = "Vui lòng nhập lại địa chỉ email";
		var expectedPasswordErrorMsg = "Vui lòng nhập mật khẩu";
		var expectedCPasswordErrorMsg = "Vui lòng nhập lại mật khẩu";
		var expectedPhoneErrorMsg = "Vui lòng nhập số điện thoại.";
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		var actualFirstnameErrorMsg = driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText();
		var actualEmailErrorMsg = driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
		var actualCEmailErrorMsg = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
		var actualPasswordErrorMsg = driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
		var actualCPasswordErrorMsg = driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
		var actualPhoneErrorMsg = driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
		
		//Assert
		Assert.assertEquals(expectedFirstnameErrorMsg, actualFirstnameErrorMsg);
		Assert.assertEquals(expectedEmailErrorMsg, actualEmailErrorMsg);
		Assert.assertEquals(expectedCEmailErrorMsg, actualCEmailErrorMsg);
		Assert.assertEquals(expectedPasswordErrorMsg, actualPasswordErrorMsg);
		Assert.assertEquals(expectedCPasswordErrorMsg, actualCPasswordErrorMsg);
		Assert.assertEquals(expectedPhoneErrorMsg, actualPhoneErrorMsg);
	}
	
	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}