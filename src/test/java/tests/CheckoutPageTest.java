package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.AppConstants;
import utils.AppErrors;

public class CheckoutPageTest extends BaseTest {

    @BeforeClass
    public void accSetup() {
        loginPage.clickOnSignInLink();
        accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        checkoutPage = accountPage.navigateToCheckout();
    }

    @Description("checkout page title test")
    @Test(priority = 1)
    public void checkoutPageTitleTest() {
        String checkoutPageTitle = checkoutPage.getCheckoutPageTitle();
        Assert.assertEquals(checkoutPageTitle, AppConstants.CHECKOUT_PAGE_TITLE, AppErrors.NO_TITLE_MATCHED);
    }

    @Description("verify shipping address selected test")
    @Test(priority = 2)
    public void verifyShippingAddressIsSelected() {
        checkoutPage.verifyShippingAddressIsSelected();
    }

    @Description("verify shipping method selected test")
    @Test(priority = 3)
    public void verifyShippingMethodIsSelected() {
        checkoutPage.verifyShippingMethodIsSelected();
    }

    @Description("verify selected products and place an order")
    @Test(priority = 4)
    public void expandOrderSummaryAndVerifyProductDetailsAndPlaceAnOrderTest() {
        checkoutPage.expandOrderSummary();

        Assert.assertEquals(checkoutPage.getMontanaProductName(), AppConstants.MONTANA_JACKET_NAME);
        Assert.assertEquals(checkoutPage.getMontanaProductPrice(), AppConstants.MONTANA_JACKET_PRICE);

        Assert.assertEquals(checkoutPage.getLandoProductName(), AppConstants.LANDO_JACKET_NAME);
        Assert.assertEquals(checkoutPage.getLandoProductPrice(), AppConstants.LANDO_JACKET_PRICE);

        Assert.assertEquals(checkoutPage.getZeppelinProductName(), AppConstants.ZEPPELIN_JACKET_NAME);
        Assert.assertEquals(checkoutPage.getZeppelinProductPrice(), AppConstants.ZEPPELIN_JACKET_PRICE);

        checkoutPage.clickOnNextAndPlaceOrder();
        String thanksMessage = checkoutPage.verifyOrderIsPlaced();
        Assert.assertTrue(thanksMessage.contains("Thank you for your purchase"));

    }

    @Description("verify order is present in order table")
    @Test(priority = 5)
    public void verifyOrderNumberInOrderTable() {
        String placedOrderNumber = checkoutPage.fetchOrderNumber();
        loginPage.goToMyAccount();
        accountPage.goToMyOrders();
        String orderNumberFromOrderTable = orderPage.verifySubmittedOrder();
        Assert.assertEquals(placedOrderNumber, orderNumberFromOrderTable);
    }
}
