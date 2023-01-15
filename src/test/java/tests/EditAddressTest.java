package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AppConstants;
import utils.ExcelUtil;

public class EditAddressTest extends BaseTest {

    @BeforeTest
    public void loginTest() {
        loginPage.clickOnSignInLink();
        loginPage.customerLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] getEditAddressTestData() {
        Object addressData[][] = ExcelUtil.getTestData(AppConstants.ADDRESS_DETAILS_SHEET_NAME);
        return addressData;
    }

    @Description("edit address test")
    @Test(dataProvider = "getEditAddressTestData")
    public void editAddressTest(String firstName, String lastName, String phoneNumber, String streetAddress, String country, String state,
                                String city, String postcode) {
        loginPage.goToMyAccount();
        addressPage.editAddressDetails(firstName, lastName, phoneNumber, streetAddress, country, state, city, postcode);
    }

}
