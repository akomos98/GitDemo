package ilkbeySirketi;

import java.io.IOException;
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

import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ilkbeySirketi.TestComponents.BaseTest;
import ilkbeySirketi.TestComponents.Retry;
import ilkbeySirketi.pageobjects.CartPage;
import ilkbeySirketi.pageobjects.CheckoutPage;
import ilkbeySirketi.pageobjects.ConfirmationPage;
import ilkbeySirketi.pageobjects.LandingPage;
import ilkbeySirketi.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling","Purchase"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		//LandingPage landingPage = lounchApplication();
		landingPage.loginApplication("semmame@gmail.com", "Au12kurt");

		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		

	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";

		//LandingPage landingPage = lounchApplication();
		ProductCatalogue productCatalouge = landingPage.loginApplication("semmame@gmail.com", "Au1234kurt");

		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);

		CartPage cartPage = productCatalouge.goToCartPage();

		System.out.println("aaaa");

		Boolean match = cartPage.VerifyProductDisplay(productName);
		System.out.println(match);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("turk");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMassage = confirmationPage.getConfirmationMessage();
		System.out.println(confirmMassage);

		Assert.assertTrue(confirmMassage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("bitiyooom");

		

	}
}