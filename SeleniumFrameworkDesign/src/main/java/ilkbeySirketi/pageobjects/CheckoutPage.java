package ilkbeySirketi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ilkbeySirketi.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By results = By.cssSelector(".ta-results");

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(xpath = "//span[text()=' Turkey']")
	WebElement tuvalu;

	// PageFactory

	public void selectCountry(String word) {
		Actions a = new Actions(driver);
		a.sendKeys(country, word).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		tuvalu.click();

	}

	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
