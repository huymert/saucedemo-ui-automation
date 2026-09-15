package web;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutStepOnePage;
import pages.CheckoutStepTwoPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.CheckoutCompletePage;

public class CheckoutTest extends BaseTest {

    // WEB-SC-07
    @Test
    public void testCheckoutWithoutPostalCode() {

        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        // 2. Add product
        inventoryPage.addBackpackToCart();

        // 3. Open Cart
        CartPage cartPage = inventoryPage.openCart();

        // 4. Click Checkout
        CheckoutStepOnePage checkoutStepOnePage =
                cartPage.clickCheckout();

        Assert.assertTrue(
                checkoutStepOnePage.isPageOpened(),
                "Checkout Step One page should be opened"
        );

        // 5. Nhập First Name + Last Name
        //    và cố tình bỏ trống Postal Code
        checkoutStepOnePage.enterFirstName("Linh");
        checkoutStepOnePage.enterLastName("Nguyen");

        // 6. Click Continue
        checkoutStepOnePage.clickContinue();

        // 7. Verify validation message
        Assert.assertEquals(
                checkoutStepOnePage.getErrorMessage(),
                "Error: Postal Code is required",
                "Postal Code required error should be displayed"
        );
    }


    // WEB-SC-08
    @Test
    public void testVerifyCheckoutTotal() {

        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        // 2. Add product
        inventoryPage.addBackpackToCart();

        // 3. Open Cart
        CartPage cartPage = inventoryPage.openCart();

        // 4. Go to Checkout Step 1
        CheckoutStepOnePage checkoutStepOnePage =
                cartPage.clickCheckout();

        // 5. Điền thông tin hợp lệ
        checkoutStepOnePage.enterFirstName("Linh");
        checkoutStepOnePage.enterLastName("Nguyen");
        checkoutStepOnePage.enterPostalCode("100000");

        // 6. Continue sang Step 2
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

        // 7. Lấy các giá trị tiền
        double itemTotal = checkoutStepTwoPage.getItemTotal();
        double tax = checkoutStepTwoPage.getTax();
        double total = checkoutStepTwoPage.getTotal();

        // 8. Verify Total = Item Total + Tax
        Assert.assertEquals(
                total,
                itemTotal + tax,
                0.01,
                "Total should equal Item Total + Tax"
        );
    }
    @Test
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