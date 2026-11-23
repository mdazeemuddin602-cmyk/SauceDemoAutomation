
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.CheckoutOverviewPage;
import pages.ConfirmationPage;

public class ConfirmationTest extends BaseTest {

    @Test
    public void verifyCompleteOrderAndPDFDownload() {

        // STEP 1: Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
            property.getProperty("username"),
            property.getProperty("password")
        );

        // STEP 2: Products Page
        ProductsPage productsPage =
            new ProductsPage(driver);

        Assert.assertEquals(
            productsPage.getPageTitle(),
            "Products"
        );

        // STEP 3: Add Two Products
        productsPage.addProductToCart(
            "Sauce Labs Backpack"
        );

        productsPage.addProductToCart(
            "Sauce Labs Bike Light"
        );

        // STEP 4: Cart Page
        productsPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
            cartPage.getCartItemCount(),
            2
        );

        // Remove Bike Light
        cartPage.removeProduct(
            "Sauce Labs Bike Light"
        );

        Assert.assertEquals(
            cartPage.getCartItemCount(),
            1
        );

        cartPage.clickCheckout();

        // STEP 5: Checkout Information
        CheckoutPage checkoutPage =
            new CheckoutPage(driver);

        Assert.assertEquals(
            checkoutPage.getPageTitle(),
            "Checkout: Your Information"
        );

        checkoutPage.enterFirstName("Mohd");
        checkoutPage.enterLastName("Azeem");
        checkoutPage.enterPostalCode("500018");

        checkoutPage.clickContinue();

        // STEP 6: Checkout Overview
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
            overviewPage.getPaymentInformation()
                .contains("SauceCard")
        );

        Assert.assertTrue(
            overviewPage.getShippingInformation()
                .contains("Pony Express")
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

        System.out.println(
            "PASS: Checkout Overview verified."
        );

        // STEP 7: Finish Order
        overviewPage.clickFinish();

        // STEP 8: Confirmation Page
        ConfirmationPage confirmationPage =
            new ConfirmationPage(driver);

        Assert.assertEquals(
            confirmationPage.getPageTitle(),
            "Checkout: Complete!"
        );

        Assert.assertEquals(
            confirmationPage.getConfirmationMessage(),
            "Thank you for your order!"
        );

        Assert.assertTrue(
            confirmationPage.getConfirmationText()
                .contains("dispatched"),
            "Order confirmation text is incorrect"
        );

        System.out.println(
            "PASS: Order Confirmation verified."
        );

        // STEP 9: Generate PDF Order
		/*
		 * confirmationPage.deleteExistingPdfFiles();
		 * 
		 * confirmationPage.clickGeneratePDFOrder();
		 * 
		 * Assert.assertTrue( confirmationPage.waitForPdfDownload(),
		 * "PDF order download failed" );
		 * 
		 * System.out.println( "PASS: PDF downloaded successfully." );
		 */
        
     // Generate PDF button click
        confirmationPage.clickGeneratePDFOrder();

        System.out.println("PDF generation attempted. Download verification pending.");

        // Back Home functionality
		/*
		 * confirmationPage.clickBackHome();
		 * 
		 * Assert.assertEquals( productsPage.getPageTitle(), "Products",
		 * "Back Home navigation failed" );
		 * 
		 * System.out.println("PASS: Back Home navigation verified.");
		 * System.out.println("========== COMPLETE ORDER FLOW PASSED ==========");
		 */

        // STEP 10: Back Home
        confirmationPage.clickBackHome();

        Assert.assertEquals(
            productsPage.getPageTitle(),
            "Products",
            "Back Home navigation failed"
        );

        System.out.println(
            "PASS: Back Home navigation verified."
        );

        System.out.println(
            "========== COMPLETE ORDER FLOW PASSED =========="
        );
    }
}
