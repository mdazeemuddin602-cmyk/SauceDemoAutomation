package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCheckoutInformation() {

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            property.getProperty("username"),
            property.getProperty("password")
        );

        // Add products
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");

        // Cart
        productsPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);

        cartPage.removeProduct("Sauce Labs Bike Light");
        cartPage.clickCheckout();

        // Checkout Information
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        Assert.assertEquals(
            checkoutPage.getPageTitle(),
            "Checkout: Your Information"
        );

        // Scenario 1: All fields empty
        checkoutPage.clickContinue();

        Assert.assertTrue(
            checkoutPage.getFirstNameValidationMessage()
                .contains("First Name is required")
        );

        // Scenario 2: First name only
        checkoutPage.enterFirstName("Mohd");
        checkoutPage.clickContinue();

        Assert.assertTrue(
            checkoutPage.getFirstNameValidationMessage()
                .contains("Last Name is required")
        );

        // Scenario 3: First name + Last name
        checkoutPage.enterLastName("Azeem");
        checkoutPage.clickContinue();

        Assert.assertTrue(
            checkoutPage.getFirstNameValidationMessage()
                .contains("Postal Code is required")
        );

        // Scenario 4: All fields filled
        checkoutPage.enterPostalCode("500018");
        checkoutPage.clickContinue();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-step-two.html"),
            "Checkout Overview page was not displayed"
        );

        System.out.println("PASS: Checkout information and validations completed.");
    }
}
