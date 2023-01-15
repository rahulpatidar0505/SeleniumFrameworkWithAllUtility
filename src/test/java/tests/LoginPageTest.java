package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;
import utils.AppConstants;
import utils.AppErrors;

public class LoginPageTest extends BaseTest {
    @Description("login page title test")
    @Severity(SeverityLevel.TRIVIAL)
    @Test()
    public void loginPageTitleTest() {
        String loginTitle = loginPage.getLoginPageTitle();
        System.out.println("login page title : " + loginTitle);
        Assert.assertEquals(loginTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrors.NO_TITLE_MATCHED);
    }

    @Description("login page url test")
    @Test()
    public void loginPageURLTest() {
        String actURL = loginPage.getLoginPageUrl();
        System.out.println("login page url : " + actURL);
        Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppErrors.NO_URL_MATCHED);
    }

    @Description("forgot password link test")
    @Test
    public void forgotPwdLinkExistTest() {
        loginPage.clickOnSignInLink();
        Assert.assertTrue(loginPage.isforgotPWdLinkExist());
    }

//    @Description("invalid login test")
//    @Test
//    public void inValidLoginTest() {
//        loginPage.clickOnSignInLink();
//        loginPage.customerLogin(RandomStringUtils.randomAlphanumeric(5)+"@gmail.com", RandomStringUtils.randomAlphanumeric(5));
//        Assert.assertTrue(loginPage.verifyInvalidLoginErrorMsg());
//    }

    @Description("valid login test")
    @Test
    public void validLoginTest() {
        loginPage.clickOnSignInLink();
        loginPage.customerLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

}
