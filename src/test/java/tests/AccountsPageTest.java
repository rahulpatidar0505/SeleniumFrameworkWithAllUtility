package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.AppConstants;
import utils.AppErrors;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		loginPage.clickOnSignInLink();
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("account page title test")
	@Test
	public void accPageURLTest() {
		String actURL = accountPage.getAccPageURL();
		System.out.println("Acc page url : " + actURL);
		Assert.assertTrue(actURL.contains(AppConstants.ACC_PAGE_FRACTION_URL), AppErrors.NO_URL_MATCHED);
	}

	@Description("select products from list, add to cart and checkout test")
	@Test
	public void selectProductsAndCheckoutTest() {
		accountPage.selectJacket();
		accountPage.selectSpecificProduct(AppConstants.MONTANA_JACKET_NAME, "M", "Green");
		Assert.assertEquals(accountPage.getProductAddedMsg(),
				"You added Montana Wind Jacket to your shopping cart.");
		Assert.assertEquals(accountPage.getProductName(), AppConstants.MONTANA_JACKET_NAME);
		Assert.assertEquals(accountPage.getProductPrice(), AppConstants.MONTANA_JACKET_PRICE);

		accountPage.selectJacket();
		accountPage.selectSpecificProduct(AppConstants.LANDO_JACKET_NAME, "L", "Blue");
		Assert.assertEquals(accountPage.getProductAddedMsg(),
				"You added Lando Gym Jacket to your shopping cart.");
		Assert.assertEquals(accountPage.getProductName(), AppConstants.LANDO_JACKET_NAME);
		Assert.assertEquals(accountPage.getProductPrice(), AppConstants.LANDO_JACKET_PRICE);

		accountPage.selectPant();
		accountPage.selectSpecificProduct(AppConstants.ZEPPELIN_JACKET_NAME, "32", "Red");
		Assert.assertEquals(accountPage.getProductAddedMsg(),
				"You added Zeppelin Yoga Pant to your shopping cart.");
		Assert.assertEquals(accountPage.getProductName(), AppConstants.ZEPPELIN_JACKET_NAME);
		Assert.assertEquals(accountPage.getProductPrice(), AppConstants.ZEPPELIN_JACKET_PRICE);

		accountPage.goToCartAndCheckout();
	}

}
