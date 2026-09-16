package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        wait.until(d -> {
            try {
                WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(locator));
                elem.click();
                return true;
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    protected void clickWithJs(By locator) {
        wait.until(d -> {
            try {
                WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elem);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    protected void type(By locator, String text) {
        wait.until(d -> {
            try {
                WebElement element = waitForVisibility(locator);
                element.clear();
                element.sendKeys(text);
                return true;
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    protected String getText(By locator) {
        return wait.until(d -> {
            try {
                WebElement elem = d.findElement(locator);
                if (elem.isDisplayed()) {
                    String text = elem.getText();
                    return (text != null) ? text : "";
                }
                return null;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void selectByValue(By locator, String value) {
        wait.until(d -> {
            try {
                WebElement selectElement = waitForVisibility(locator);
                Select select = new Select(selectElement);
                select.selectByValue(value);
                return true;
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    protected void selectByVisibleText(By locator, String text) {
        wait.until(d -> {
            try {
                WebElement selectElement = waitForVisibility(locator);
                Select select = new Select(selectElement);
                select.selectByVisibleText(text);
                return true;
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    protected List<WebElement> findElements(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElements(locator);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
