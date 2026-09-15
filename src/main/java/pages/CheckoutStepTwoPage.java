package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepTwoPage extends BasePage {

    private final By pageTitle = By.cssSelector(".title");
    private final By itemTotalLabel = By.cssSelector(".summary_subtotal_label");
    private final By taxLabel = By.cssSelector(".summary_tax_label");
    private final By totalLabel = By.cssSelector(".summary_total_label");
    private final By finishButton = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        pageTitle,
                        "Checkout: Overview"
                )
        );
        return true;
    }

    public double getItemTotal() {
        return parseAmount(getText(itemTotalLabel));
    }

    public double getTax() {
        return parseAmount(getText(taxLabel));
    }

    public double getTotal() {
        return parseAmount(getText(totalLabel));
    }

    public CheckoutCompletePage clickFinish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    private double parseAmount(String text) {
        String number = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(number);
    }
}