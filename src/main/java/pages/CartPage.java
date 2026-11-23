
package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    private WebDriver driver;

    private By pageTitle = By.className("title");
    private By cartItems = By.className("cart_item");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();

        List<WebElement> products = driver.findElements(productNames);

        for (WebElement product : products) {
            names.add(product.getText());
        }

        return names;
    }

    public List<String> getProductPrices() {
        List<String> prices = new ArrayList<>();

        List<WebElement> productPriceList = driver.findElements(productPrices);

        for (WebElement price : productPriceList) {
            prices.add(price.getText());
        }

        return prices;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeProduct(String productName) {
        String productId = productName.toLowerCase().replace(" ", "-");

        By removeButton = By.id("remove-" + productId);

        driver.findElement(removeButton).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
