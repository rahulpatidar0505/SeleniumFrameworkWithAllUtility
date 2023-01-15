package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.AppConstants;
import utils.ElementUtil;
import utils.JavaScriptUtil;
import utils.TimeUtil;

import java.util.List;

public class CheckoutPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	private final By locator_orderSummary = By.xpath("//div[@class='title']");
	private final By locator_selectShippingAddress = By.cssSelector(".shipping-address-item.selected-item");
	private final By locator_selectShippingMethod = By.cssSelector("input[value='flatrate_flatrate']");
	private final By locator_nextButton = By.xpath("//span[normalize-space()='Next']");
	private final By locator_placeOrderButton = By.xpath("//span[normalize-space()='Place Order']");
	private final By locator_thanksMsg = By.xpath("//span[contains(normalize-space(),'Thank you for your purchase!')]");
	private final By locator_orderNumber = By.xpath("//main[@id='maincontent']//p[1]");
	private final By locator_shippingMethod = By.xpath("//div[normalize-space()='Shipping Methods']");
	private final By locator_productDisplayList = By.xpath("//div[normalize-space()='Shipping Methods']");
	private final By locator_displayProductSummary = By.xpath("//div[@class='content minicart-items' and @aria-hidden='false'] ");
	private final By locator_montana_product_name = By.xpath("//strong[normalize-space()='Montana Wind Jacket']");
	private final By locator_montana_product_price = By.xpath("//strong[normalize-space()='Montana Wind Jacket']/parent::div/following-sibling::div[@class='subtotal']");
	private final By locator_landoProductName = By.xpath("//strong[@class='product-item-name'][contains(text(),'Lando Gym Jacket')]");
	private final By locator_landoProductPrice = By.xpath("//strong[normalize-space()='Lando Gym Jacket']/parent::div/following-sibling::div[@class='subtotal']");
	private final By locator_zeppelinProductName = By.xpath("//strong[@class='product-item-name'][contains(text(),'Zeppelin Yoga Pant')]");
	private final By locator_zeppelinProductPrice = By.xpath("//strong[normalize-space()='Zeppelin Yoga Pant']/parent::div/following-sibling::div[@class='subtotal']");
	private final By lc_productDisplayList = By.xpath("//div[@aria-expanded='false']//strong");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	@Step("getting login page title...")
	public String getCheckoutPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.CHECKOUT_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}
	public String getMontanaProductName() {
		return eleUtil.doGetElementText(locator_montana_product_name);
	}
	public String getMontanaProductPrice() {
		return eleUtil.doGetElementText(locator_montana_product_price);
	}
	public String getLandoProductName() {
		return eleUtil.doGetElementText(locator_landoProductName);
	}
	public String getLandoProductPrice() {
		return eleUtil.doGetElementText(locator_landoProductPrice);
	}
	public String getZeppelinProductName() {
		return eleUtil.doGetElementText(locator_zeppelinProductName);
	}
	public String getZeppelinProductPrice() {
		return eleUtil.doGetElementText(locator_zeppelinProductPrice);
	}
	public void expandOrderSummary(){
		eleUtil.waitForElementPresence(locator_orderSummary, 5);

		if(eleUtil.getElements(lc_productDisplayList).size()>0){
			eleUtil.doClick(locator_orderSummary);
			eleUtil.waitForElementPresence(locator_displayProductSummary, 5);
		} else {
			eleUtil.waitForElementPresence(locator_displayProductSummary, 5);
		}
	}
	public void clickOnNextAndPlaceOrder(){
		eleUtil.waitForElementPresence(locator_nextButton, 5);
		eleUtil.doClick(locator_nextButton);
		eleUtil.waitForElementPresence(locator_placeOrderButton, 5);
		eleUtil.doClick(locator_placeOrderButton);
	}
	public String verifyOrderIsPlaced() {
		eleUtil.waitForElementPresence(locator_thanksMsg, 5);
		return eleUtil.doGetElementText(locator_thanksMsg);
	}
	public void verifyShippingAddressIsSelected(){
		eleUtil.waitForElementPresence(locator_selectShippingAddress, 5);
		eleUtil.getElement(locator_selectShippingAddress).isSelected();
	}

	public void verifyShippingMethodIsSelected(){
		eleUtil.waitForElementPresence(locator_selectShippingMethod, 5);
		eleUtil.getElement(locator_selectShippingMethod).isSelected();
	}
	public String fetchOrderNumber(){
		String orderNumberMessage = eleUtil.doGetElementText(locator_orderNumber);
		int colonIndex = orderNumberMessage.indexOf(':');
		String orderNumberText = orderNumberMessage.substring(colonIndex + 2);
		String orderNumber = orderNumberText.replace(".", "");
		return orderNumber;
	}
}
