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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Epic("SauceDemo Web UI")
@Feature("Product Catalog")
public class ProductTest extends BaseTest {

    @Test(description = "WEB-SC-04: Sắp xếp sản phẩm theo giá từ thấp đến cao (Price low to high)")
    @Story("Sort Products")
    @Severity(SeverityLevel.NORMAL)
    @Description("Xác minh danh sách sản phẩm được sắp xếp tăng dần theo giá chính xác khi chọn Price (low to high)")
    public void testSortProductsByPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isPageOpened(), "Trang Inventory phải hiển thị");

        inventoryPage.selectSortOption("lohi");

        List<Double> actualPrices = inventoryPage.getAllItemPrices();
        Assert.assertFalse(actualPrices.isEmpty(), "Danh sách sản phẩm không được rỗng");

        List<Double> expectedSortedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedSortedPrices);

        Assert.assertEquals(actualPrices, expectedSortedPrices,
                "Danh sách giá thực tế phải khớp với danh sách được sắp xếp tăng dần");
    }
}
