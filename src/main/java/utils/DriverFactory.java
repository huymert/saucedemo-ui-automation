package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static WebDriver initDriver(String browser, boolean headless) {
        if (browser == null || browser.trim().isEmpty()) {
            browser = ConfigReader.getProperty("browser");
            if (browser == null) {
                browser = "chrome";
            }
        }

        WebDriver webDriver;
        try {
            webDriver = createDriverInstance(browser, headless);
        } catch (Exception e) {
            webDriver = createDriverInstance("edge", headless);
        }

        if (!headless) {
            try {
                webDriver.manage().window().maximize();
            } catch (Exception ignored) {}
        }

        int implicitWait = 10;
        try {
            String waitProp = ConfigReader.getProperty("timeout.implicit");
            if (waitProp != null) {
                implicitWait = Integer.parseInt(waitProp);
            }
        } catch (Exception ignored) {}

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        setDriver(webDriver);
        return webDriver;
    }

    private static WebDriver createDriverInstance(String browser, boolean headless) {
        switch (browser.toLowerCase().trim()) {
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.addArguments("--disable-notifications");
                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--window-size=1920,1080");
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                }
                return new EdgeDriver(edgeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                }
                return new FirefoxDriver(firefoxOptions);

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-notifications");

                // Disable Chrome Password Manager / password leak warning
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);

                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.addArguments("--disable-features=PasswordLeakDetection");

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                }

                return new ChromeDriver(chromeOptions);
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
            } catch (Exception ignored) {
            } finally {
                driver.remove();
            }
        }
    }
}
