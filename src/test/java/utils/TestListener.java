package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case [" + result.getName() + " bị FAIL - chụp màn hình...]");

        // Gọi hàm chụp màn hình đính vào Allure
        AllureManager.saveScreenshotPNG();
    }
}
