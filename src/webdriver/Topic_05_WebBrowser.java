package webdriver;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class Topic_05_WebBrowser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	//Tương tác vs Browser thì thông qua biến driver
	//Tương tác vs Element thì thông qua biến WebElement
	
	//Tip1: Cứ cái hàm nào action thì ko trả về (click/nhập/chọn/accept/hover/refresh/back/forward/...) -> void
	//Tip2: Cứ cái hàm vào lấy dữ liệu ra thì cần trả về (get Url/title/ID/Window/text/attribute/css/value/...) -> return
	
	@Test
	public void TC_01() {
		// >=2: Nó sẽ đóng tab/window mà nó đang đứng
		driver.close();
	}
}