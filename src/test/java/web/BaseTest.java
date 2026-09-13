package web;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverFactory;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", ConfigReader.getProperty("browser"));
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", ConfigReader.getProperty("headless")));
        driver = DriverFactory.initDriver(browser, headless);

        String url = ConfigReader.getProperty("url");
        if (url != null && !url.trim().isEmpty()) {
            driver.get(url);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
