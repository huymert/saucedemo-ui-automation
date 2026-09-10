package web;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Khởi tạo WebDriver sẽ hoàn thiện ở Sprint 1
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
