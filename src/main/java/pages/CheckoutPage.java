package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.className("title");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By cancelButton = By.id("cancel");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    public void enterFirstName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName))
            .sendKeys(name);
    }

    public void enterLastName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName))
            .sendKeys(name);
    }

    public void enterPostalCode(String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode))
            .sendKeys(code);
    }

    public void clickContinue() {
        wait.until(
            ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }

    public void clickCancel() {
        wait.until(
            ExpectedConditions.elementToBeClickable(cancelButton)
        ).click();
    }

    public String getFirstNameValidationMessage() {
        return driver.findElement(By.cssSelector("[data-test='error']")).getText();
    }
}
