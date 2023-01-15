package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtil;

public class OrderPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    // 1. private By locators:
    private By locator_myOrderText = By.xpath("//span[normalize-space()='My Orders']");
    private By orderRowList = By.xpath("//table[@id=\"my-orders-table\"]//tr");

    // 2. page constructor:
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String verifySubmittedOrder() {
        String beforeXpath = "//table[@id=\"my-orders-table\"]//tr[";
        String afterXpath = "]/td[1]";
        String text = null;
        for (int i = 1; i <= eleUtil.getElements(orderRowList).size(); i++) {
            String finalXpath = beforeXpath + i + afterXpath;
            text = driver.findElement(By.xpath(finalXpath)).getText();
            break;
        }
        return text;
    }

}