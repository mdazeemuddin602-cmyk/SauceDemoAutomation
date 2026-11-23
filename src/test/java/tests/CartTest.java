
package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;

public class CartTest extends BaseTest {

    @Test
    public void verifyCartAndRemoveProduct() {

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

        Assert.assertEquals(
            cartPage.getPageTitle(),
            "Your Cart"
        );

        Assert.assertEquals(
            cartPage.getCartItemCount(),
            2,
            "Cart should contain 2 products"
        );

        List<String> names = cartPage.getProductNames();
        System.out.println("Cart Products: " + names);

        List<String> prices = cartPage.getProductPrices();
        System.out.println("Cart Prices: " + prices);

        Assert.assertTrue(names.contains("Sauce Labs Backpack"));
        Assert.assertTrue(names.contains("Sauce Labs Bike Light"));

        cartPage.removeProduct("Sauce Labs Bike Light");

        Assert.assertEquals(
            cartPage.getCartItemCount(),
            1,
            "Product was not removed successfully"
        );

        Assert.assertTrue(
            cartPage.getProductNames().contains("Sauce Labs Backpack")
        );

        Assert.assertFalse(
            cartPage.getProductNames().contains("Sauce Labs Bike Light")
        );

        cartPage.clickCheckout();

        System.out.println("PASS: Cart verification and product removal completed.");
    }
}
