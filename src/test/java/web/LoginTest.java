package web;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Epic("SauceDemo Web UI")
@Feature("Authentication")
public class LoginTest extends BaseTest {

    @Test(description = "WEB-SC-01: Đăng nhập thành công với tài khoản standard_user")
    @Story("User Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Xác minh người dùng hợp lệ standard_user đăng nhập thành công vào trang Inventory")
    public void testLoginSuccessStandardUser() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(inventoryPage.isPageOpened(), "Trang Inventory phải hiển thị sau khi đăng nhập thành công");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "URL phải chứa inventory.html");
    }

    @Test(description = "WEB-SC-02: Đăng nhập thất bại với tài khoản bị khóa locked_out_user")
    @Story("User Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Xác minh thông báo lỗi xuất hiện khi đăng nhập bằng tài khoản locked_out_user")
    public void testLoginLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Thông báo lỗi phải hiển thị");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Sorry, this user has been locked out"),
                "Nội dung thông báo lỗi phải khớp thông tin tài khoản bị khóa");
    }

    @Test(description = "WEB-SC-03: Đăng nhập thất bại khi bỏ trống Username hoặc Password")
    @Story("User Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Xác minh thông báo lỗi validate khi người dùng bỏ trống thông tin đăng nhập")
    public void testLoginEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Thông báo lỗi phải hiển thị");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Thông báo lỗi phải yêu cầu nhập Username");
    }
}
