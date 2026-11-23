
package utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initializeDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            // Chrome Options
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--disable-notifications");

            // Disable Google Password Manager popup
            Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);

            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);
            
            String downloadPath =
            	    System.getProperty("user.home") + "/Downloads";

            prefs.put("download.default_directory", downloadPath);
            prefs.put("download.prompt_for_download", false);
            prefs.put("plugins.always_open_pdf_externally", true);


            // Initialize ChromeDriver with options
            driver = new ChromeDriver(options);
            
            
        }

        else if (browser.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();

            driver = new EdgeDriver();

        }

        else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();

            driver = new FirefoxDriver();

        }

        else {

            throw new IllegalArgumentException(
                "Browser not supported: " + browser
            );

        }

        driver.manage().window().maximize();

        return driver;
    }

    public static WebDriver getDriver() {

        return driver;
    }
}
