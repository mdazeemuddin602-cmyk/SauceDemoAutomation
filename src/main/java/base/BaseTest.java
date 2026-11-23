package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.DriverFactory;
import utilities.PropertyUtility;

public class BaseTest {

    protected WebDriver driver;

    protected PropertyUtility property = new PropertyUtility();

    @BeforeMethod
    public void setup() {

        driver = DriverFactory.initializeDriver(property.getProperty("browser"));

        driver.get(property.getProperty("url"));
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @AfterMethod
    public void tearDown() {

        //driver.quit();

    }

}
