package ilkbeySirketi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ilkbeySirketi.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver){
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By produsctsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(produsctsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		
		WebElement prod =getProductList().stream().filter(pro->
		pro.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		return prod;
		
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	
	
	
	}
	
	
	
}
