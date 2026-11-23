package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.CheckoutOverviewPage;

public class CheckoutOverviewTest extends BaseTest {

    @Test
    public void verifyCheckoutOverviewAndFinishOrder() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            property.getProperty("username"),
            property.getProperty("password")
        );

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeProduct("Sauce Labs Bike Light");
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName("Mohd");
        checkoutPage.enterLastName("Azeem");
        checkoutPage.enterPostalCode("500018");
        checkoutPage.clickContinue();

        CheckoutOverviewPage overviewPage =
            new CheckoutOverviewPage(driver);

        Assert.assertEquals(
            overviewPage.getPageTitle(),
            "Checkout: Overview"
        );

        Assert.assertEquals(
            overviewPage.getProductName(),
            "Sauce Labs Backpack"
        );

        Assert.assertEquals(
            overviewPage.getProductPrice(),
            "$29.99"
        );

        Assert.assertTrue(
            overviewPage.getPaymentInformation().contains("SauceCard")
        );

        Assert.assertTrue(
            overviewPage.getShippingInformation().contains("Pony Express")
        );

        Assert.assertEquals(
            overviewPage.getItemTotal(),
            "Item total: $29.99"
        );

        Assert.assertEquals(
            overviewPage.getTax(),
            "Tax: $2.40"
        );

        Assert.assertEquals(
            overviewPage.getTotal(),
            "Total: $32.39"
        );

        System.out.println("PASS: Checkout overview calculations verified.");

        overviewPage.clickFinish();

        System.out.println("Order Finish button clicked.");
    }
}
