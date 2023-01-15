package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.AppConstants;
import utils.ElementUtil;
import utils.TimeUtil;

public class AccountPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    String productName;
    String productPrice;

    private final By locator_men = By.xpath("//span[normalize-space()='Men']");
    private final By locator_tops = By.xpath("//a[contains(@href,'tops-men.html')]//span[contains(text(),'Tops')]");
    private final By locator_jackets = By.xpath("//a[contains(@href,'jackets-men.html')]//span[contains(text(),'Jackets')]");
    private final By locator_bottoms = By.xpath("//a[contains(@href,'bottoms-men.html')]//span[contains(text(),'Bottoms')]");
    private final By locator_pants = By.xpath("//a[contains(@href,'pants-men.html')]//span[contains(text(),'Pants')]");
    private final By locator_productAddedMsg = By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
    private final By productList = By.cssSelector(".product.name.product-item-name");

    private final By productSizeList = By.xpath("//div[@class='swatch-option text'][@role='option']");

    private final By productColorList = By.xpath("//div[@class='swatch-option color']");

    private final By locator_addToCartButton = By.xpath("//span[normalize-space()='Add to Cart']");

    private final By locator_cartLink = By.xpath("//a[@class='action showcart']");

    private final By locator_checkout = By.xpath("//button[@id='top-cart-btn-checkout']");

    private final By locator_myAccountText = By.xpath("//span[normalize-space()='My Account']");
    private final By locator_myOrder = By.xpath("//a[normalize-space()='My Orders']");
    private final By locator_montanaProductName = By.xpath("//span[contains(text(),'Montana Wind Jacket')]");
    private final By locator_montanaProductPrice = By.xpath("//span[@itemprop='offers']//span[@class='price-label'][normalize-space()='As low as']/following-sibling::span//span[@class='price']");
    private final By locator_landoProductName = By.xpath("//span[contains(text(),'Lando Gym Jacket')]");
    private final By locator_landoProductPrice = By.xpath("//span[@itemprop='offers']//span[@class='price-label'][normalize-space()='As low as']/following-sibling::span//span[@class='price']");
    private final By locator_zeppelinProductName = By.xpath("//span[contains(text(),'Zeppelin Yoga Pant')]");

    private final By locator_zeppelinProductPrice = By.xpath("//span[@class='price-label'][normalize-space()='As low as']/following-sibling::span//span[@class='price']");

    private final By lc_productName = By.xpath("//h1[@class = 'page-title']//span[@itemprop=\"name\"]");
    private final By lc_productPrice = By.xpath("//div[contains(@class,'product-info-price')]//span[contains(@id,'product-price')]//span[@class='price']");


    public AccountPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }


    @Step("get Account Page Url")
    public String getAccPageURL() {
        return eleUtil.waitForUrlContains(AppConstants.ACC_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
    }

    public String getProductName() {
        return eleUtil.doGetElementText(lc_productName);
    }

    public String getProductPrice() {
        return eleUtil.doGetElementText(lc_productPrice);
    }

    public void selectJacket() {
        eleUtil.moveToElement(locator_men);
        eleUtil.moveToElement(locator_tops);
        eleUtil.doClick(locator_jackets);
//        jsUtil.scrollIntoView(eleUtil.getElement(locator_tops));
//        jsUtil.clickElementByJS(eleUtil.getElement(locator_jackets));
    }

    public void selectPant() {
        eleUtil.moveToElement(locator_men);
        eleUtil.moveToElement(locator_bottoms);
        eleUtil.doClick(locator_pants);
//        jsUtil.scrollIntoView(eleUtil.getElement(locator_bottoms));
//        jsUtil.clickElementByJS(eleUtil.getElement(locator_pants));
    }

    public void selectProductName(String productName) {
        for (WebElement product : eleUtil.getElements(productList)) {
            if (product.getText().contains(productName)) {
                product.click();
                break;
            }
        }
    }

    //			List<WebElement> metaDataList = eleUti.getElements(productMetaData);
    public String getMontanaProductName() {
        return eleUtil.doGetElementText(locator_montanaProductName);
    }

    public String getMontanaProductPrice() {
        return eleUtil.doGetElementText(locator_montanaProductPrice);
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

    public String getProductAddedMsg() {
        eleUtil.waitForElementPresence(locator_productAddedMsg, 5);
        return eleUtil.doGetElementText(locator_productAddedMsg);
    }

    public void selectProductSize(String productSize) {
        for (WebElement size : eleUtil.getElements(productSizeList)) {
            if (size.getText().contains(productSize)) {
                size.click();
                break;
            }
        }
    }

    public void selectProductColor(String productColor) {
        for (WebElement color : eleUtil.getElements(productColorList)) {
            if (color.getAttribute("aria-label").contains(productColor)) {
                color.click();
                break;
            }
        }
    }

    public void selectSpecificProduct(String productName, String productSize, String productColor) {
        selectProductName(productName);
        selectProductSize(productSize);
        selectProductColor(productColor);
        eleUtil.doClick(locator_addToCartButton);
    }

    public void goToCartAndCheckout() {
        eleUtil.waitForElementPresence(locator_cartLink, 5);
        eleUtil.doClick(locator_cartLink);
        eleUtil.doClick(locator_checkout);
    }

    public void goToMyOrders() {
        eleUtil.waitForElementPresence(locator_myOrder, 5);
        eleUtil.doClick(locator_myOrder);
    }

    @Step("Go to checkout page")
    public CheckoutPage navigateToCheckout() {
        driver.navigate().to("https://magento.softwaretestingboard.com/checkout/#shipping");
        eleUtil.waitForTitleIs(AppConstants.CHECKOUT_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
        return new CheckoutPage(driver);
    }
}

