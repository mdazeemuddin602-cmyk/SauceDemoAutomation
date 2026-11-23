package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    // WebDriver
    private WebDriver driver;

    // Locators
    private By pageTitle = By.className("title");

    private By productItems = By.className("inventory_item");

    private By productNames = By.className("inventory_item_name");

    private By productPrices = By.className("inventory_item_price");

    private By sortDropdown = By.className("product_sort_container");

    private By cartBadge = By.className("shopping_cart_badge");

    private By cartIcon = By.className("shopping_cart_link");


    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }


    // 1. Get Products Page Title
    public String getPageTitle() {

        return driver.findElement(pageTitle).getText();
    }


    // 2. Get Total Number of Products
    public int getTotalProducts() {

        return driver.findElements(productItems).size();
    }


    // 3. Get All Product Names
    public List<String> getProductNames() {

        List<String> names = new ArrayList<>();

        List<WebElement> products =
                driver.findElements(productNames);

        for (WebElement product : products) {

            names.add(product.getText());
        }

        return names;
    }


    // 4. Get All Product Prices
    public List<String> getProductPrices() {

        List<String> prices = new ArrayList<>();

        List<WebElement> productPriceList =
                driver.findElements(productPrices);

        for (WebElement price : productPriceList) {

            prices.add(price.getText());
        }

        return prices;
    }


    // 5. Sort Products - Name A to Z
    public void sortNameAtoZ() {

        Select select =
                new Select(driver.findElement(sortDropdown));

        select.selectByValue("az");
    }


    // 6. Sort Products - Name Z to A
    public void sortNameZtoA() {

        Select select =
                new Select(driver.findElement(sortDropdown));

        select.selectByValue("za");
    }


    // 7. Sort Products - Price Low to High
    public void sortPriceLowToHigh() {

        Select select =
                new Select(driver.findElement(sortDropdown));

        select.selectByValue("lohi");
    }


    // 8. Sort Products - Price High to Low
    public void sortPriceHighToLow() {

        Select select =
                new Select(driver.findElement(sortDropdown));

        select.selectByValue("hilo");
    }


    // 9. Add Product to Cart using Product Name

    public void addProductToCart(String productName) {

        String productId = productName.toLowerCase()
                .replace(" ", "-");

        By addButton = By.id("add-to-cart-" + productId);

        By removeButton = By.id("remove-" + productId);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        );

        System.out.println("Adding product: " + productName);

        button.click();

        // Verify Add to Cart button changes to Remove
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(removeButton)
        );

        System.out.println(productName + " added successfully");
    }


    // 10. Get Cart Badge Count
    public String getCartBadgeCount() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).getText();
    }


    // 11. Verify Cart Badge is Displayed
    public boolean isCartBadgeDisplayed() {

        return !driver.findElements(cartBadge).isEmpty();
    }


    // 12. Open Shopping Cart
    public void clickCartIcon() {

        driver.findElement(cartIcon).click();
    }

}
