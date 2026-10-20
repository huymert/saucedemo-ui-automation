# SauceDemo Web UI Automation Framework

[![Java](https://img.shields.io/badge/Java-17-orange.svg?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.21.0-43B02A.svg?style=for-the-badge&logo=selenium)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red.svg?style=for-the-badge&logo=testng)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36.svg?style=for-the-badge&logo=apachemaven)](https://maven.apache.org/)
[![Allure Report](https://img.shields.io/badge/Allure%20Report-2.27.0-blueviolet.svg?style=for-the-badge&logo=qameta)](https://qameta.io/)

A modern, production-grade **Web UI Automation Testing Framework** built for the **SauceDemo** e-commerce platform ([saucedemo.com](https://www.saucedemo.com)). Engineered with the **Page Object Model (POM)** design pattern, thread-safe **DriverFactory**, dynamic configuration management, custom test listeners, and interactive **Allure Reports** with automated screenshot capture on test failure.

---

## 📌 Framework Highlights & Architecture

* **Design Pattern**: Page Object Model (POM) separating UI locators and page actions from test verification logic.
* **Driver Management**: Thread-safe `DriverFactory` supporting **Google Chrome**, **Mozilla Firefox**, **Microsoft Edge**, and CI/CD-ready **Headless Mode**.
* **Configuration Management**: Centralized `config.properties` read via `ConfigReader` with fallback options and command-line overrides (`-Dheadless=true`).
* **Test Orchestration**: TestNG suite (`testng.xml`) with structured test groups, dependencies, and execution sequencing.
* **Automated Failure Evidence**: Custom `TestListener` hooked into `AllureManager` to automatically capture and embed full-resolution PNG screenshots on any test failure.
* **CI/CD Integration**: Pre-configured for automated execution in Dockerized CI pipelines with Discord webhook notifications.

---

## 🏗️ Project Structure

```text
saucedemo-ui-automation/
├── .github/workflows/          # GitHub Actions CI pipeline
├── src/
│   ├── main/java/
│   │   ├── pages/              # Page Object Classes
│   │   │   ├── BasePage.java            # Explicit wait helpers & common driver actions
│   │   │   ├── LoginPage.java           # Login locators & authentication flows
│   │   │   ├── InventoryPage.java       # Product catalog, sorting & cart badge actions
│   │   │   ├── CartPage.java            # Shopping cart management & item removal
│   │   │   ├── CheckoutStepOnePage.java # User info input & validation error handling
│   │   │   ├── CheckoutStepTwoPage.java # Order overview, item total & tax calculations
│   │   │   └── CheckoutCompletePage.java# Order confirmation & dispatch verification
│   │   └── utils/
│   │       ├── ConfigReader.java        # Loads configuration from config.properties
│   │       └── DriverFactory.java       # WebDriver initialization & teardown
│   └── test/java/
│       ├── utils/
│       │   ├── AllureManager.java       # Allure attachment utilities (Screenshots)
│       │   └── TestListener.java        # TestNG listener capturing failure screenshots
│       └── web/
│           ├── BaseTest.java            # Test setup/teardown lifecycle (@BeforeMethod, @AfterMethod)
│           ├── LoginTest.java           # Multi-persona authentication & validation tests
│           ├── ProductTest.java         # Product catalog sorting & price verification
│           ├── CartTest.java            # Cart addition, item removal & badge count tests
│           ├── CheckoutTest.java        # Checkout validations, calculations & E2E flows
│           └── LogoutTest.java          # Session termination & safe sign-out tests
├── src/test/resources/
│   ├── config.properties       # Environment URL, browser selection, headless flag
│   └── testng.xml              # TestNG suite runner configuration
├── pom.xml                     # Maven dependencies, AspectJ weaver & Allure plugins
└── README.md
```

---

## 📋 Test Scenarios & Coverage Matrix

| ID | Scenario | Priority | Test Class | Verification |
| :--- | :--- | :---: | :--- | :--- |
| **`WEB-SC-01`** | Login success with `standard_user` | **High** | `LoginTest` | Inventory URL redirect & catalog loaded |
| **`WEB-SC-02`** | Login failure with `locked_out_user` | **High** | `LoginTest` | Error banner: *Sorry, this user has been locked out.* |
| **`WEB-SC-03`** | Form validation on empty credentials | **Medium** | `LoginTest` | Error banner: *Username is required* |
| **`WEB-SC-04`** | Sort products by Price (Low to High) | **Medium** | `ProductTest` | Price ordering mathematically validated |
| **`WEB-SC-05`** | Add product to shopping cart | **High** | `CartTest` | Shopping cart badge counter updates to `1` |
| **`WEB-SC-06`** | Remove product directly from Cart page | **Medium** | `CartTest` | Item deleted & badge counter disappears |
| **`WEB-SC-07`** | Checkout Step 1: Validate postal code | **Medium** | `CheckoutTest` | Error banner: *Postal Code is required* |
| **`WEB-SC-08`** | Checkout Step 2: Total calculation | **High** | `CheckoutTest` | Formula verified: `Item Total + Tax = Total` |
| **`WEB-SC-09`** | Complete E2E Order Flow | **High** | `CheckoutTest` | Header verified: *THANK YOU FOR YOUR ORDER* |
| **`WEB-SC-10`** | Logout via sidebar navigation | **Low** | `LogoutTest` | Session cleared, redirected to Login screen |

---

## 🚀 Getting Started

### Prerequisites
* **Java Development Kit (JDK)**: Version 17 or higher
* **Apache Maven**: Version 3.8+
* **Google Chrome / Mozilla Firefox / Microsoft Edge**: Up-to-date version

### Clone Repository
```bash
git clone https://github.com/huymert/saucedemo-ui-automation.git
cd saucedemo-ui-automation
```

### Execution Commands

```bash
# Run full regression test suite (Headed mode)
mvn clean test

# Run tests in Headless mode (Recommended for CI/CD)
mvn clean test -Dheadless=true

# Run with a specific browser (chrome / firefox / edge)
mvn clean test -Dbrowser=firefox

# Generate and launch Allure Report dashboard
mvn allure:serve
```

---

## 📊 Test Reporting (Allure Report)

Allure produces comprehensive, interactive HTML test reports that include:
* Full test execution timeline and status breakdown (Passed, Failed, Skipped).
* Step-by-step test execution details.
* **Automatic failure screenshots** attached directly to failed test steps.
* Environment details (OS, Java version, browser).

```bash
# Serve report directly in default browser
mvn allure:serve

# Or generate static HTML report into /target/site/allure-maven-plugin
mvn allure:report
```

---

## 👥 Project Team & Contributors

This project was developed by **Team 2** (Capstone Project) with clear ownership and responsibilities:

* **Hà Gia Huy** ([@huymert](https://github.com/huymert)) — **Web UI Module Lead & SDET**:
  * Designed the core Page Object Model (POM) architecture, thread-safe `DriverFactory`, and `BasePage` explicit wait helpers.
  * Implemented test suites: Multi-persona Authentication (`standard_user`, `locked_out_user`, validation errors), Product Catalog browsing & price sorting (`ProductTest.java`), and secure logout (`LogoutTest.java`).
  * Implemented `TestListener` and `AllureManager` for automatic failure screenshot capture.

* **Nguyễn Khánh Linh** — **Web UI Automation Engineer**:
  * Implemented Page Objects and End-to-End test suites for Shopping Cart operations (`CartPage.java`).
  * Implemented multi-step checkout workflows (`CheckoutStepOnePage.java`, `CheckoutStepTwoPage.java`), tax/total calculation verification, and Order Completion (`CheckoutCompletePage.java`).

* **Team Members (API Module)**: Lê Thị Hải Hà ([@muckkho2728](https://github.com/muckkho2728)), Trần Thị Quỳnh Chi ([@quynhchi03](https://github.com/quynhchi03)).
* **Mentor & Project Advisor**: Mr. Vũ Lê Minh.
