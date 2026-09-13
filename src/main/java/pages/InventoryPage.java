package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {
    private final By pageTitle = By.cssSelector(".title");
    private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private final By itemPrices = By.cssSelector(".inventory_item_price");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(pageTitle, "Products"));
        return true;
    }

    public void selectSortOption(String visibleTextOrValue) {
        selectByValue(sortDropdown, visibleTextOrValue);
    }

    public List<Double> getAllItemPrices() {
        List<WebElement> priceElements = findElements(itemPrices);
        List<Double> prices = new ArrayList<>();
        for (WebElement elem : priceElements) {
            String priceText = elem.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public void openMenu() {
        click(menuButton);
    }

    public LoginPage logout() {
        openMenu();
        click(logoutLink);
        return new LoginPage(driver);
    }
}
