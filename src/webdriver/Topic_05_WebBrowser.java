package webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
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
		//Note: '//**' Dùng nhiều, '//*' Dùng ít, 'không *' ko dùng
		// >=2: Nó sẽ đóng tab/window mà nó đang đứng
		// =1: Nó sẽ đóng Brower
		driver.close();
		
		//Ko quan tâm bao nhiêu tabs/windows -> Đóng Browser
		driver.quit(); //**
		
		//Có thể lưu vào 1 biến để sử dụng cho các step sau -> Dùng lại nhiều lần
		WebElement emailTextbox = driver.findElement(By.xpath("")); //**
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		
		//Có thể sử dụng luôn, ko cần tạo biến -> Dùng cho những action ko lặp lại
		driver.findElement(By.xpath("//button[@id='login']")).click();
		
		//Tìm nhiều elements
		//List<> cho phép lưu trùng
		List<WebElement> checkboxes = driver.findElements(By.xpath("")); //*
		
		//Mở ra 1 Url bất kỳ
		driver.get("https://www.youtube.com"); //**
		driver.get("https://www.facebook.com");
		
		//Trả về Url của page hiện tại -> Dùng để compare navigate page: Verify with expected Url
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(), "https://about.instagram.com/");
		
		//Trả về Source Code HTML của page hiện tại 
		//Verify tương đối 
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Create, share, and watch short, entertaining videos on Instagram."));
		
		//Trả về title của page hiện tại
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "(67) Phép Màu (Đàn Cá Gỗ OST) - MAYDAYs ft. Minh Tốc | Official MV - YouTube");
		
		//WebDriver API - Window/Tabs
		//Lấy ra được ID của Window/Tav mà driver đang đứng (active)	
		String loginWindowID = driver.getWindowHandle(); //**
		
		//Lẩy ra ID cả tất cả Windows/Tabs
		//Set<> không chấp nhận lưu trùng, chỉ lưu những giá trị không trùng nhau
		Set<String> allIDs = driver.getWindowHandles(); //*
		
		//Cookie: Lưu các lựa chọn của user (VD: Đã login thành công, thì lần sau sẽ không yc login lại), lưu ở trên server và broswer
		//Cache: Lưu content của website để sử dụng sau này và chỉ lưu ở browser 
		Options opt = driver.manage();
		
		//Login thành công -> Lưu lại
		opt.getCookies(); //* Sẽ học trong bài framework 
		
		//Test case khác -> Set cookie vào lại -> Không cần phải login
		opt.logs(); //Sẽ học trong bài framework
		opt.timeouts().implicitlyWait(5, TimeUnit.SECONDS); //**Setup time chờ 1 element xuất hiện, 5s = 5000ms, liên quan tới bài học WebDriver API - Wait in Selenium
		opt.timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //Setup time chờ 1 page load xong
		opt.timeouts().setScriptTimeout(5, TimeUnit.SECONDS); //Setup time chờ 1 script thực thi xong, liên quan tới bài học WebDriver API - Javascript Executor (JavascriptExecutor Library) 
		
		opt.window().fullscreen(); //Full screen = Ko viền trên, ko viền dưới, ko task bar
		opt.window().maximize(); //**
		
		//Test GUI: Font/Size/Color/Position/Location -> Thường ít auto
		opt.window().setPosition(null);
		opt.window().setSize(null);
		opt.window().getSize();
		opt.window().getPosition();
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.forward();
		nav.refresh();
		nav.to("https://www.youtube.com"); //Giống với driver.get()
		
		TargetLocator tar = driver.switchTo();
		tar.alert(); //*
		tar.frame(""); //*
		tar.window(""); //*
	}
}