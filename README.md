# SauceDemo Web UI Automation Framework

> **Module 1: Web UI Automation Testing - Capstone Project (Team 2 - Rikkei Edu)**

---

## 👥 Thành viên nhóm & Phân công nhiệm vụ (Team 2)
* **Hà Gia Huy (Scrum Master Sprint 0)**: Setup Base Framework, DriverFactory, Page Objects & Test cases: Login đa persona (standard, locked, problem user), Catalog & Sắp xếp sản phẩm.
* **Nguyễn Khánh Linh**: Page Objects & Test cases: Quản lý giỏ hàng (Add/Remove), Quy trình Checkout Step 1 & 2, Hoàn tất đơn hàng E2E.
* **Lê Thị Hải Hà**: Thành viên Team 2 / Phụ trách Module API Restful-Booker.
* **Trần Thị Quỳnh Chi**: Thành viên Team 2 / Phụ trách Module API Restful-Booker.
* **Mentor / Product Owner**: Thầy Vũ Lê Minh

---

## 🌐 Hệ thống kiểm thử
* **Hệ thống Web E2E**: SauceDemo
* **URL**: https://www.saucedemo.com
* **Công nghệ**: Java 17, Selenium WebDriver, TestNG, Maven, Page Object Model (POM), Allure Report, GitLab CI.
* **GitLab Repository**: https://gitlab.com/capstone_yah/saucedemo-ui-automation.git

---

## 📋 10 Test Scenarios Web UI (SauceDemo)
1. `[WEB-SC-01]` **[High]** Login thành công với `standard_user`.
2. `[WEB-SC-02]` **[High]** Login thất bại hiển thị đúng thông báo với `locked_out_user`.
3. `[WEB-SC-03]` **[Medium]** Login validation: Báo lỗi khi bỏ trống username hoặc password.
4. `[WEB-SC-04]` **[Medium]** Lọc & sắp xếp danh sách sản phẩm theo giá tăng dần (Price: low to high).
5. `[WEB-SC-05]` **[High]** Thêm sản phẩm vào giỏ hàng và kiểm tra badge số lượng cập nhật đúng.
6. `[WEB-SC-06]` **[Medium]** Xóa sản phẩm khỏi giỏ hàng trực tiếp từ trang Cart.
7. `[WEB-SC-07]` **[Medium]** Checkout Step 1: Kiểm tra validate bắt buộc nhập Postal Code.
8. `[WEB-SC-08]` **[High]** Checkout Step 2: Kiểm tra tổng tiền thanh toán (Item total + Tax = Total).
9. `[WEB-SC-09]` **[High]** Hoàn tất đơn hàng E2E (Order Completion) hiển thị màn hình *THANK YOU FOR YOUR ORDER*.
10. `[WEB-SC-10]` **[Low]** Đăng xuất an toàn khỏi hệ thống qua sidebar menu.

---

## 🌿 Git Workflow & Hướng dẫn Clone
```bash
# 1. Clone repository về máy
git clone https://gitlab.com/capstone_yah/saucedemo-ui-automation.git
cd saucedemo-ui-automation

# 2. Chuyển sang nhánh develop
git checkout develop

# 3. Tạo nhánh feature từ develop để làm bài
git checkout -b feature/<ten-tinh-nang>-<ten-ban>
```
* **Nhánh chính**: `main` (Production/Demo), `develop` (Tích hợp phát triển).
* **Nhánh tính năng gợi ý**: `feature/login-huy`, `feature/checkout-linh`.

---

## 🛠️ Hướng dẫn chạy Test
```bash
# Chạy có giao diện
mvn clean test

# Chạy Headless (Docker/CI)
mvn clean test -Dheadless=true

# Xem Allure Report
mvn allure:serve
```
