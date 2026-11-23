package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.className("title");
    private By productName = By.className("inventory_item_name");
    private By productPrice = By.className("inventory_item_price");

    private By paymentInfo = By.className("summary_value_label");
    private By shippingInfo = By.className("summary_value_label");

    private By itemTotal = By.className("summary_subtotal_label");
    private By tax = By.className("summary_tax_label");
    private By total = By.className("summary_total_label");

    private By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public String getPaymentInformation() {
        return driver.findElements(paymentInfo).get(0).getText();
    }

    public String getShippingInformation() {
        return driver.findElements(shippingInfo).get(1).getText();
    }

    public String getItemTotal() {
        return driver.findElement(itemTotal).getText();
    }

    public String getTax() {
        return driver.findElement(tax).getText();
    }

    public String getTotal() {
        return driver.findElement(total).getText();
    }

    public void clickFinish() {
        wait.until(
            ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }
}
