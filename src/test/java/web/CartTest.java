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
import pages.InventoryPage;
import pages.LoginPage;

@Epic("SauceDemo Web UI")
@Feature("Cart")
public class CartTest extends BaseTest {
    @Test(description = "WEB-SC-05: Thêm sản phẩm vào giỏ hàng thành công")
    @Story("Add product to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that standard_user can add a product to the shopping cart.")
    public void testAddTwoProductsToCart() {

        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();

        Assert.assertTrue(
                inventoryPage.isBackpackRemoveButtonDisplayed(),
                "Backpack button should change to Remove"
        );

        Assert.assertTrue(
                inventoryPage.isBikeLightRemoveButtonDisplayed(),
                "Bike Light button should change to Remove"
        );

        Assert.assertEquals(
                inventoryPage.getCartBadgeCount(),
                "2",
                "Cart badge should be 2"
        );
    }

    @Test(description = "WEB-SC-06: Xóa sản phẩm khỏi giỏ hàng thành công")
    @Story("Remove product from cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that user can remove a product from the shopping cart.")
    public void testRemoveProductFromCart() {


        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");


        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();


        CartPage cartPage = inventoryPage.openCart();

        Assert.assertTrue(
                cartPage.isPageOpened(),
                "Cart page should be opened"
        );

        cartPage.removeBackpack();

        Assert.assertFalse(
                cartPage.isBackpackDisplayed(),
                "Backpack should not be displayed after removal"
        );

        Assert.assertEquals(
                cartPage.getCartBadgeCount(),
                "1",
                "Cart badge should be 1 after removing one product"
        );
    }
}