package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepOnePage extends BasePage {

    private final By pageTitle = By.cssSelector(".title");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        pageTitle,
                        "Checkout: Your Information"
                )
        );
        return true;
    }

    public void enterFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeInput, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public String getErrorMessage() {
        return waitForVisibility(errorMessage).getText();
    }
    public String getFirstNameValue() {
        return driver.findElement(firstNameInput).getAttribute("value");
    }

    public String getLastNameValue() {
        return driver.findElement(lastNameInput).getAttribute("value");
    }

    public String getPostalCodeValue() {
        return driver.findElement(postalCodeInput).getAttribute("value");
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }
}