package ilkbeySirketi;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ilkbeySirketi.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("semmame@gmail.com");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Au1234kurt");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(pro -> pro.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		System.out.println("aaaa");
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		System.out.println(match);
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "tu").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//span[text()=' Tuvalu']")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();

	}

}