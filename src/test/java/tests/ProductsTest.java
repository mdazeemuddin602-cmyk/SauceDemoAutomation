package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest extends BaseTest {

    @Test
    public void verifyProductsPageAndAddTwoProducts() {

        // Step 1: Login to Sauce Demo
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            property.getProperty("username"),
            property.getProperty("password")
        );


        // Step 2: Create Products Page Object
        ProductsPage productsPage = new ProductsPage(driver);


        // Step 3: Verify Products Page
        Assert.assertEquals(
            productsPage.getPageTitle(),
            "Products",
            "Products page is not displayed"
        );


        // Step 4: Verify Total Products
        Assert.assertEquals(
            productsPage.getTotalProducts(),
            6,
            "Expected 6 products on Products page"
        );


        // Step 5: Get Product Names
        List<String> productNames =
                productsPage.getProductNames();

        System.out.println("Available Products: " + productNames);


        // Step 6: Get Product Prices
        List<String> productPrices =
                productsPage.getProductPrices();

        System.out.println("Product Prices: " + productPrices);


        // Step 7: Sort Products by Price Low to High
        productsPage.sortPriceLowToHigh();

        System.out.println("Products sorted by Price Low to High");


        // Step 8: Add First Product to Cart
        productsPage.addProductToCart("Sauce Labs Backpack");


        // Step 9: Add Second Product to Cart
        productsPage.addProductToCart("Sauce Labs Bike Light");


        // Step 10: Verify Cart Badge
        Assert.assertTrue(
            productsPage.isCartBadgeDisplayed(),
            "Cart badge is not displayed"
        );

        Assert.assertEquals(
            productsPage.getCartBadgeCount(),
            "2",
            "Cart badge count is incorrect"
        );


        // Step 11: Open Shopping Cart
        productsPage.clickCartIcon();


        // Step 12: Print Success Message
        System.out.println(
            "PASS: Two products added successfully and cart opened."
        );

    }
}
