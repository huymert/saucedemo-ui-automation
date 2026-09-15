package web;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest extends BaseTest {

    @Test
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

    @Test
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