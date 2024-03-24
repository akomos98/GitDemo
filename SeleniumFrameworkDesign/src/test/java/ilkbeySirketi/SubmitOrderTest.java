package ilkbeySirketi;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ilkbeySirketi.TestComponents.BaseTest;
import ilkbeySirketi.pageobjects.CartPage;
import ilkbeySirketi.pageobjects.CheckoutPage;
import ilkbeySirketi.pageobjects.ConfirmationPage;
import ilkbeySirketi.pageobjects.LandingPage;
import ilkbeySirketi.pageobjects.OrderPage;
import ilkbeySirketi.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	 String productName="ZARA COAT 3";
	 
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		

		//LandingPage landingPage = lounchApplication();
		//("semmame@gmail.com", "Au1234kurt")
		//"ahmet@gmail.com","Ahmet1234kurt"
		ProductCatalogue productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(input.get("productNames"));

		CartPage cartPage = productCatalouge.goToCartPage();

		System.out.println("aaaa");

		Boolean match = cartPage.VerifyProductDisplay(input.get("productNames"));
		System.out.println(match);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("turk");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMassage = confirmationPage.getConfirmationMessage();
		System.out.println(confirmMassage);

		Assert.assertTrue(confirmMassage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("bitiyooom");
		
		System.out.println("benden sonra fenerbahçe yazmalı");

	}
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
	//to verify ZARA COAT 3 is displaying in orders page
		System.out.println("fenerbahçe");
		ProductCatalogue productCatalouge = landingPage.loginApplication("semmame@gmail.com", "Au1234kurt");
		OrderPage ordersPage=productCatalouge.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
		
	}
	
	//Extent Reports-->
	
	public String getScreenshot(String testCaseName) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map= new HashMap<String, String>();
//		map.put("email", "semmame@gmail.com");
//		map.put("password","Au1234kurt");
//		map.put("productNames", "ZARA COAT 3");
//		
//		HashMap<String, String> map1= new HashMap<String, String>();
//		map1.put("email", "ahmet@gmail.com");
//		map1.put("password","Ahmet1234kurt");
//		map1.put("productNames", "ADIDAS ORIGINAL");		
		List<HashMap<String,String>>data=getJsonDataToMap("C://Users//ahmet//eclipse-workspace//SeleniumFrameworkDesign//src//main//java//ilkbeySirketi//resources//GlobalData.properties");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}