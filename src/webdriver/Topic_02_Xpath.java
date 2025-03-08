package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath {
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
		driver.get("https://automationfc.github.io/basic-form/");
	}

	@Test
	public void TC_01_Text1() {
	System.out.println("Get texts: " + driver.findElement(By.xpath("//h5[@id='nested']")).getText());
	}
	
	@Test
	public void TC_02_Text2() {
	System.out.println("Get text from nested text: " + driver.findElement(By.xpath("//span[contains(text(),'(Ignore Me)')]")).getText());
	}
	
	@Test
	public void TC_03_Text3() {
	System.out.println("Get text from everywhere: " + driver.findElement(By.xpath("//h5[contains(.,'Michael Jackson')]")).getText());
	System.out.println(driver.findElement(By.xpath("//h5[contains(.,'Michael Jackson')]")).equals(8));
	}
	
	@Test
	public void TC_04_Text4() {
	System.out.println("Get text start with: " + driver.findElement(By.xpath("//p[starts-with(.,'Notice that if you pay by Personal')]")).getText());
	System.out.println(driver.findElement(By.xpath("//p[starts-with(.,'Notice that if you pay by Personal')]")).equals(1));
	}
	
	@Test
	public void TC_05_Text5() {
	System.out.println("Get strings: " + driver.findElement(By.xpath("//h5[@id='hacker']//span[string()]")).getText());
	System.out.println(driver.findElement(By.xpath("//h5[@id='hacker']//span[string()]")).equals(2));
	}
	
	@Test
	public void TC_06_String1() {
	System.out.println("Get specified string: " + driver.findElement(By.xpath("//h5[@id='hacker']//span[contains(text(), ' - 18 years old ')]")).getText());
	System.out.println(driver.findElement(By.xpath("//h5[@id='hacker']//span[string()]")).equals(1));
	}
	
	@Test
	public void TC_07_String2() {
	System.out.println("Get specified string: " + driver.findElement(By.xpath("//h5[@id='hacker']//span[contains(text(), ' - 18 years old ')]")).getText());
	System.out.println(driver.findElement(By.xpath("//h5[@id='hacker']//span[string()]")).equals(1));
	}
	
	@Test
	public void TC_08_Concaternate() {
	System.out.println("Concatenate: " + driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What', \"'s happened?\")]")).getText());
	System.out.println(driver.findElement(By.xpath("//h5[@id='hacker']//span[string()]")).equals(1));
	}
	
	@Test
	public void TC_09_Condition1() {
	System.out.println("Condition - AND: " + driver.findElement(By.xpath("//h5[contains(text(), 'Michael Jackson') and @id='four']")).getText());
	System.out.println(driver.findElement(By.xpath("//h5[contains(text(), 'Michael Jackson') and @id='four']")).equals(1));
	}
	
	@Test
	public void TC_09_Condition2() {
	System.out.println("Condition - OR: " + driver.findElement(By.xpath("//b[starts-with(text(), 'NOP') or contains(text(), 'New York')]")).getText());
	System.out.println(driver.findElement(By.xpath("//b[starts-with(text(), 'NOP') or contains(text(), 'New York')]")).equals(2));
	}

	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}