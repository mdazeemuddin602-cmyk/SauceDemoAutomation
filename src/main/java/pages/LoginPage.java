package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By txtUsername = By.id("user-name");
    private By txtPassword = By.id("password");
    private By btnLogin = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String password) {
    	
        driver.findElement(txtUsername).sendKeys(user);

        driver.findElement(txtPassword).sendKeys(password);

        driver.findElement(btnLogin).click();

    }

}