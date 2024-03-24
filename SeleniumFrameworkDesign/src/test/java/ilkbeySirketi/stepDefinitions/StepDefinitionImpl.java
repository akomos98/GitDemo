package ilkbeySirketi.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ilkbeySirketi.TestComponents.BaseTest;
import ilkbeySirketi.pageobjects.CartPage;
import ilkbeySirketi.pageobjects.CheckoutPage;
import ilkbeySirketi.pageobjects.ConfirmationPage;
import ilkbeySirketi.pageobjects.LandingPage;
import ilkbeySirketi.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalouge;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {

		landingPage = lounchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {

		productCatalouge = landingPage.loginApplication(username, password);

	}

	@When("^I add the product (.+) from Cart$")
	public void i_add_the_product_from_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	private void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalouge.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("turk");
		confirmationPage = checkoutPage.submitOrder();

	}

	@Then("^(.+) message is displayed on ConfirmationPage$")
	private void message_displayed_on_confirmationPage(String productName) {
		String confirmMassage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMassage.equalsIgnoreCase(productName));
		driver.close();
	}

	@Then("^(.+) message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}

}
