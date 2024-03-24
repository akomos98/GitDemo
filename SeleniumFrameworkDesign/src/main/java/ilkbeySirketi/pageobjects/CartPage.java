package ilkbeySirketi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ilkbeySirketi.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cardProducts;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory

	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cardProducts.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage goToCheckout() {
		// TODO Auto-generated method stub
		checkoutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}

}
