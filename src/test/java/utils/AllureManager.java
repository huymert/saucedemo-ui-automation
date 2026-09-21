package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureManager {

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }
}
