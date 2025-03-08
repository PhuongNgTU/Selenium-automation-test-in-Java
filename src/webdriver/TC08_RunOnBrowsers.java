package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TC08_RunOnBrowsers {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Test
	public void TC_01_Run_Firefox() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		
		driver.get("https://demo.nopcommerce.com/");
		
		driver.quit();
	}
	
	@Test
	public void TC_02_Run_Edge() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		}
		
		driver = new EdgeDriver();
		
		driver.get("https://demo.nopcommerce.com/");
		
		driver.quit();
	}
}