package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC07_DemoAxes {
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
		driver.get("https://www.packtpub.com/en-us/search?q=java&format=eBook");
	}
	
	@Test
	public void TC_07_DemoAxes() {
		//Pick a random book and get its published date
		System.out.println("Get published date: " + driver.findElement(By.xpath
				("//div[contains(text(),'Java Memory Management') and @class='ellipsis']/parent::div/following-sibling::div[1]/span")).getText());
	}
	
	@Test
	public void TC_07_DemoAxes2() {
		//Pick a random book and get its description
		System.out.println("Get description: " + driver.findElement(By.xpath
				("//div[contains(text(), 'The Complete Coding Interview Guide in Java') and @class='ellipsis']/parent::div/following-sibling::div/child::div[@class='ellipsis']")).getText());
	}
	
	@Test
	public void TC_07_DemoAxes3() {
		//Pick a random book and get its price before discount
		System.out.println("Get price: " + driver.findElement(By.xpath
				("//div[contains(text(), 'Learning Java by Building Android Games') and @class='ellipsis']/ancestor::a/following-sibling::div/child::div/descendant::div[@class='product-card-v2-content-footer-pricing-price']/descendant::del")).getText());
	}
	
	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}