package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private final By pageTitle = By.cssSelector(".title");
    private final By backpackRemoveButton =
            By.id("remove-sauce-labs-backpack");
    private final By backpackItemName =
            By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']");
    private final By cartBadge =
            By.cssSelector(".shopping_cart_badge");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.urlContains("cart.html"));
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        pageTitle,
                        "Your Cart"
                )
        );
        return true;
    }

    public void removeBackpack() {
        click(backpackRemoveButton);
    }

    public boolean isBackpackDisplayed() {
        return isDisplayed(backpackItemName);
    }

    public String getCartBadgeCount() {
        return getText(cartBadge);
    }
    public CheckoutStepOnePage clickCheckout() {
        click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }
}