package webdriver;
import org.testng.annotations.Test;

public class TC09_ReverseString {
	@Test
	public void TC_09() {
		String sentence = "Enter a string to reverse";
		String reverseString = "";
		char stringToArray[] = sentence.toCharArray();
		
		for (int i = sentence.length()-1; i >= 0; i--) {
			reverseString += stringToArray[i];
			System.out.println(reverseString);
		}
	}
}