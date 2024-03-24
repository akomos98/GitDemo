package ilkbeySirketi.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ilkbeySirketi.pageobjects.CartPage;
import ilkbeySirketi.pageobjects.OrderPage;

public class AbstractComponent {
	// By.cssSelector(".ng-animating")
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	public void waitForWebElementToAppear(WebElement elem) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(elem));

	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {

//		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.invisibilityOf(ele));

		Thread.sleep(2000);

	}

	public CartPage goToCartPage() {

		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrderPage() {

		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
