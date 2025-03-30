package webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_05_WebBrowser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	//Tương tác vs Browser thì thông qua biến driver
	//Tương tác vs Element thì thông qua biến WebElement
	
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/chromedriver");
		}
		//Tip1: Cứ cái hàm nào action thì ko trả về (click/nhập/chọn/accept/hover/refresh/back/forward/...) -> void
		//Tip2: Cứ cái hàm vào lấy dữ liệu ra thì cần trả về (get Url/title/ID/Window/text/attribute/css/value/...) -> return
	}
	
	@Test
	public void TC_01() {
		// >=2: Nó sẽ đóng tab/window mà nó đang đứng
		// =1: Nó sẽ đóng Brower
		driver.close();
		
		// Ko quan tâm bao nhiêu tabs/windows -> Đóng Browser
		driver.quit();
		
		// Có thể lưu vào 1 biến để sử dụng cho các step sau -> Dùng lại nhiều lần
		WebElement emailTextbox = driver.findElement(By.xpath(""));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		
		// Có thể sử dụng luôn, ko cần tạo biến -> Dùng cho những action ko lặp lại
		driver.findElement(By.xpath("//button[@id='login']")).click();
		
		//Tìm nhiều elements
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		//Mở ra 1 Url bất kỳ
		driver.get("https://www.youtube.com");
		driver.get("https://www.facebook.com");
		
		//Trả về Url của page hiện tại -> Dùng để compare navigate page: Verify with expected Url
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(), "https://about.instagram.com/");
	}
}