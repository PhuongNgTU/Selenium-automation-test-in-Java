package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TCs_Register {
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
	}
	
	@Test
	public void TC_01_RegisterWithEmtyData() {
		//Act
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Assert
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}
	
	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		//Variables
		var fullName = "Jean Tyderman";
		var invalidEmail = "123@456@789";
		var password = "NozzaGrande@128096";
		var phone = "0333774461";
		var expectedMessage = "Vui lòng nhập email hợp lệ";
		
		//Act
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys(fullName);
		driver.findElement(By.id("txtFirstname")).sendKeys(fullName);
		driver.findElement(By.id("txtEmail")).sendKeys(invalidEmail);
		driver.findElement(By.id("txtCEmail")).sendKeys(invalidEmail);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("txtCPassword")).sendKeys(password);
		driver.findElement(By.id("txtPhone")).sendKeys(phone);
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Assert
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), expectedMessage);
	}
	
	@Test
	public void TC_03_RegisterWithIncorrectConfirmEmail() {
		//Variables
		var fullName = "Helge Doppler";
		var email = "helgedoppler@yahoo.com";
		var password = "NozzaGrande@128096";
		var phone = "0333774461";
		var expectedMessage = "Email nhập lại không đúng";
		
		//Pre-act
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).clear();
		
		//Act
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys(fullName);
		driver.findElement(By.id("txtFirstname")).sendKeys(fullName);
		driver.findElement(By.id("txtEmail")).sendKeys(email);
		driver.findElement(By.id("txtCEmail")).sendKeys("helgedoppler@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("txtCPassword")).sendKeys(password);
		driver.findElement(By.id("txtPhone")).sendKeys(phone);
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Assert
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), expectedMessage);
	}
	
	@Test
	public void TC_04_RegisterWithPasswordLessThan6Characters() {
		//Variables
		var passwordWith5Characters = "Nozza";
		var fullName = "Helge Doppler";
		var email = "helgedoppler211224@gmail.com";
		var phone = "0333774461";
		var expectedErrorMessage = "Mật khẩu phải có ít nhất 6 ký tự";
		
		//Pre-act
		driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).clear();
		
		//Act
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys(fullName);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(passwordWith5Characters);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys(passwordWith5Characters);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys(phone);	
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Assert
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), expectedErrorMessage);
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), expectedErrorMessage);
	}
	
	@Test
	public void TC_05_RegisterWithIncorrectConfirmPassword() {
		//Variables
		var password = "NozzaGrande@1280";
		var fullName = "Helge Doppler";
		var email = "helgedoppler211224@gmail.com";
		var phone = "0333774461";
		var expectedErrorMessage = "Mật khẩu bạn nhập không khớp";
		
		//Pre-act
		driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).clear();
		
		//Act
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys(fullName);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys(password+"96");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys(phone);	
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Assert
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), expectedErrorMessage);
	}
	
	@Test
	public void TC_06_RegisterWithInvalidPhoneNumber() {
		//Variables
		var password = "NozzaGrande@1280";
		var fullName = "Helge Doppler";
		var email = "helgedoppler211224@gmail.com";
		var phone = "096767676767";
		var expectedErrorMessage = "Số điện thoại phải từ 10-11 số.";
		
		//Pre-act
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		
		//Act
		driver.findElement(By.id("txtFirstname")).sendKeys(fullName);
		driver.findElement(By.id("txtEmail")).sendKeys(email);
		driver.findElement(By.id("txtCEmail")).sendKeys(email);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("txtCPassword")).sendKeys(password);
		driver.findElement(By.id("txtPhone")).sendKeys(phone);	
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Assert
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), expectedErrorMessage);
	}
	
	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}