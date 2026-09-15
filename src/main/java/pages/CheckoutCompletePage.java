package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {

    private final By pageTitle = By.cssSelector(".title");
    private final By completeHeader =
            By.cssSelector(".complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        wait.until(
                ExpectedConditions.urlContains("checkout-complete.html")
        );

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        pageTitle,
                        "Checkout: Complete!"
                )
        );

        return true;
    }

    public String getCompleteMessage() {
        return getText(completeHeader);
    }
}