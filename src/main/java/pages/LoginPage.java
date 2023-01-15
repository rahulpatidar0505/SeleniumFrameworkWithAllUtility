package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AppConstants;
import utils.ElementUtil;
import utils.TimeUtil;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private final By locator_signInLink = By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]");
    private final By locator_email = By.id("email");
    private final By locator_password = By.id("pass");
    private final By locator_signInButton = By.xpath("//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]");
    private final By forgotPwdLink = By.xpath("//a[@class='action remind']//span[contains(text(),'Forgot Your Password?')]");
    private final By locator_accountPanelArrow = By.xpath("//div[@class='panel header']//button[@type='button']");
    private final By locator_myAccount = By.xpath("//div[@aria-hidden='false']//a[normalize-space()='My Account']");
    private final By locator_errorMsg = By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    @Step("getting login page title...")
    public String getLoginPageTitle() {
        return eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
    }

    @Step("get Login Page Url")
    public String getLoginPageUrl() {
        return eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
    }

    @Step("checking forgot pwd link exist")
    public boolean isforgotPWdLinkExist() {
        return eleUtil.doIsDisplayed(forgotPwdLink);
    }

    public void clickOnSignInLink() {
        eleUtil.doClick(locator_signInLink);
    }

    public boolean verifyInvalidLoginErrorMsg() {
        eleUtil.staleElementRefExceptionSolution(eleUtil.getElement(locator_errorMsg));
        eleUtil.waitForElementPresence(locator_errorMsg, 10);
        return eleUtil.getElement(locator_errorMsg).isDisplayed();
    }

    public void customerLogin(String emailId, String passowrd) {
        eleUtil.waitForElementVisible(locator_email, TimeUtil.DEFAULT_TIME_OUT).sendKeys(emailId);
        eleUtil.doSendKeys(locator_password, passowrd);
        eleUtil.doClick(locator_signInButton);
    }

    public void goToMyAccount() {
        eleUtil.waitForElementPresence(locator_accountPanelArrow, 5);
        eleUtil.doClick(locator_accountPanelArrow);
        eleUtil.doClick(locator_myAccount);
    }

    @Step("login with username : {0} and password: {1}")
    public AccountPage doLogin(String un, String pwd) {
        customerLogin(un, pwd);
        return new AccountPage(driver);
    }
}
