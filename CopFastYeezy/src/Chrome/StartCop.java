package Chrome;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartCop {
	public static void main(String args[]) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","/Users/jay/Desktop/selenium/chromedriver");
		for(int i=1;i<2;i++) {
				String profile = getProfile(i);
				String cartLink = getCartLink(i);
				openSelectedProfile(profile,cartLink);
		}
		

		
	}
	public static void openSelectedProfile(String profile,String cartLink) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments(profile);
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://yeezysupply.com");
		while(!(driver.findElements(By.id("SIZE")).size() > 0)) {
			Thread.sleep(8000);
			driver.get("https://yeezysupply.com");
		}
		

		Select size_dropdown1 = new Select(driver.findElement(By.xpath("//*[@id=\"SIZE\"]")));
		size_dropdown1.selectByVisibleText("5.0");
		
		try {
			driver.findElement(By.name("add")).click();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open();");
		ArrayList<String> tab = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tab.get(1)); 
		
		driver.get(cartLink);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait = new WebDriverWait(driver,10);
		WebElement checkOut;
		checkOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("checkout")));
		try {
			checkOut.click();
		}catch(Exception e) {
			System.out.println(e); 
		}
		driver.findElement(By.name("checkout[email]")).sendKeys("JayPatel10673@gmail.com");
		driver.findElement(By.name("checkout[shipping_address][first_name]")).sendKeys("Jay");
		driver.findElement(By.name("checkout[shipping_address][last_name]")).sendKeys("Patel");
		driver.findElement(By.name("checkout[shipping_address][address1]")).sendKeys("552 springridge rd");
		driver.findElement(By.name("checkout[shipping_address][address2]")).sendKeys("A8");
		driver.findElement(By.name("checkout[shipping_address][city]")).sendKeys("Clinton");
		driver.findElement(By.name("checkout[shipping_address][zip]")).sendKeys("39056");
		driver.findElement(By.name("checkout[shipping_address][phone]")).sendKeys("6015191839");
			
		try {
			driver.findElement(By.name("button")).click();
		}catch(Exception e) {
			System.out.println(e);
		}
		try {
			driver.findElement(By.name("button")).click();
		}catch(Exception e) {
			System.out.println(e);
		}
		driver.findElement(By.id("checkout_different_billing_address_false"));
		driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Jay Patel");
		driver.findElement(By.id("expiry")).sendKeys("07/23");
		driver.findElement(By.id("verification_value")).sendKeys("6798");
		
		
	}
	public static String getProfile(int proNum) {
		String pro="";
		switch(proNum){
			case 0:
				pro = "/Users/jay/Library/Application Support/Google/Chrome/Profile 92";
				break;
			case 1:
				pro = "/Users/jay/Library/Application Support/Google/Chrome/Profile 93";
				break;
		}
		return pro;
	}
	public static String getCartLink(int cartNum) {
		String cart="";
		switch(cartNum) {
		case 0: 
			cart = "https://yeezysupply.com/17655971/checkouts/d40fd32a68e58f449b907fa86665b734?previous_step=shipping_method&step=payment_method";
			break;
		case 1:
			cart = "https://yeezysupply.com/17655971/checkouts/1bc755deca9a2cb4cb6c749a6f4dd774?previous_step=shipping_method&step=payment_method";
			break;
		}
		return cart;
	}
}
