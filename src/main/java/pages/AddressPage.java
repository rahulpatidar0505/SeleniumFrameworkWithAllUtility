package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtil;
import utils.JavaScriptUtil;

public class AddressPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil jsUtil;
    private final By locator_addressBook = By.xpath("//strong[normalize-space()='Address Book']");
    private final By locator_editAddress = By.xpath("//div[@class='box box-billing-address']//span[contains(text(),'Edit Address')]");
    private final By locator_firstName = By.xpath("//input[@name='firstname']");
    private final By locator_lastName = By.xpath("//input[@name='lastname']");
    private final By locator_company = By.xpath("//input[@name='company']");
    private final By locator_street_0 = By.xpath("//input[@title='Street Address']");
    private final By locator_street_1 = By.xpath("//input[@title=\"Street Address: Line 2\"]");
    private final By locator_city = By.xpath("//input[@name='city']");
    private final By locator_state = By.xpath("//select[@name='region_id']");
    private final By locator_postcode = By.xpath("//input[@name='postcode']");
    private final By locator_bottoms = By.xpath("//select[@name='country_id']");
    private final By locator_country = By.xpath("//select[@id='country']");
    private final By locator_phoneNumber = By.xpath("//input[@name='telephone']");
    private final By locator_saveAddress = By.xpath("//span[normalize-space()='Save Address']");

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public void editAddressDetails(String fName, String lName, String pNumber, String street0,
                                   String country, String state, String city, String postcode) {
        eleUtil.doClick(locator_editAddress);
        eleUtil.doSendKeys(locator_firstName, fName);
        eleUtil.doSendKeys(locator_lastName, lName);
        eleUtil.doSendKeys(locator_phoneNumber, pNumber);
        eleUtil.doSendKeys(locator_street_0, street0);
        eleUtil.doSelectDropDownByVisibleText(locator_country, country);
        eleUtil.doSelectDropDownByVisibleText(locator_state, state);
        eleUtil.doSendKeys(locator_city, city);
        eleUtil.doSendKeys(locator_postcode, postcode);
        eleUtil.doClick(locator_saveAddress);
    }

}
