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
public class LogoutTest extends BaseTest {

    @Test(description = "WEB-SC-10: Đăng xuất tài khoản an toàn qua Burger Sidebar Menu")
    @Story("User Logout")
    @Severity(SeverityLevel.NORMAL)
    @Description("Xác minh người dùng có thể mở sidebar menu và đăng xuất về trang đăng nhập thành công")
    public void testLogoutFromSidebar() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isPageOpened(), "Trang Inventory phải hiển thị");

        LoginPage postLogoutPage = inventoryPage.logout();
        Assert.assertTrue(postLogoutPage.isLoginFormDisplayed(), "Form đăng nhập phải hiển thị sau khi đăng xuất");
        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"),
                "URL không còn ở trang inventory.html sau khi đăng xuất");
    }
}
