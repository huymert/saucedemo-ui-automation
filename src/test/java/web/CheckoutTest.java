package web;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutStepOnePage;
import pages.CheckoutStepTwoPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.CheckoutCompletePage;

@Epic("SauceDemo Web UI")
@Feature("Checkout")
public class CheckoutTest extends BaseTest {


    @Test(description = "WEB-SC-07: Kiểm tra validation khi bỏ trống Postal Code")
    @Story("Checkout validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify validation when Postal Code is empty.")
    public void testCheckoutWithoutPostalCode() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.openCart();
        CheckoutStepOnePage checkoutStepOnePage =
                cartPage.clickCheckout();
        Assert.assertTrue(
                checkoutStepOnePage.isPageOpened(),
                "Checkout Step One page should be opened"
        );
        checkoutStepOnePage.enterFirstName("Linh");
        checkoutStepOnePage.enterLastName("Nguyen");
        checkoutStepOnePage.clickContinue();
        Assert.assertEquals(
                checkoutStepOnePage.getErrorMessage(),
                "Error: Postal Code is required",
                "Postal Code required error should be displayed"
        );
    }



    @Test(description = "WEB-SC-08: Xác nhận tổng tiền thanh toán và thuế phí tại Checkout Overview")
    @Story("Checkout overview")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify total price and tax calculation.")
    public void testVerifyCheckoutTotal() {

        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.openCart();
        CheckoutStepOnePage checkoutStepOnePage =
                cartPage.clickCheckout();
        checkoutStepOnePage.enterFirstName("Linh");
        checkoutStepOnePage.enterLastName("Nguyen");
        checkoutStepOnePage.enterPostalCode("100000");
        System.out.println("First name = " + checkoutStepOnePage.getFirstNameValue());
        System.out.println("Last name = " + checkoutStepOnePage.getLastNameValue());
        System.out.println("Postal code = " + checkoutStepOnePage.getPostalCodeValue());
        checkoutStepOnePage.clickContinue();
        System.out.println("URL after Continue = " + driver.getCurrentUrl());
        if (checkoutStepOnePage.isErrorDisplayed()) {
            System.out.println("Error = " + checkoutStepOnePage.getErrorMessage());
        }

        CheckoutStepTwoPage checkoutStepTwoPage =
                new CheckoutStepTwoPage(driver);

        Assert.assertTrue(
                checkoutStepTwoPage.isPageOpened(),
                "Checkout Step Two page should be opened"
        );

        double itemTotal = checkoutStepTwoPage.getItemTotal();
        double tax = checkoutStepTwoPage.getTax();
        double total = checkoutStepTwoPage.getTotal();

        Assert.assertEquals(
                total,
                itemTotal + tax,
                0.01,
                "Total should equal Item Total + Tax"
        );
    }

    @Test(description = "WEB-SC-09: Hoàn tất đơn hàng mua sắm E2E")
    @Story("Order completion")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify successful order completion.")
    public void testCompleteOrderSuccessfully() {

        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addBackpackToCart();

        CartPage cartPage = inventoryPage.openCart();

        CheckoutStepOnePage checkoutStepOnePage =
                cartPage.clickCheckout();

        checkoutStepOnePage.enterFirstName("Linh");
        checkoutStepOnePage.enterLastName("Nguyen");
        checkoutStepOnePage.enterPostalCode("100000");
        checkoutStepOnePage.clickContinue();

        CheckoutStepTwoPage checkoutStepTwoPage =
                new CheckoutStepTwoPage(driver);

        Assert.assertTrue(
                checkoutStepTwoPage.isPageOpened(),
                "Checkout Step Two page should be opened"
        );

        CheckoutCompletePage checkoutCompletePage =
                checkoutStepTwoPage.clickFinish();

        Assert.assertTrue(
                checkoutCompletePage.isPageOpened(),
                "Checkout Complete page should be opened"
        );

        Assert.assertEquals(
                checkoutCompletePage.getCompleteMessage(),
                "Thank you for your order!",
                "Order completion message should be correct"
        );
    }
}