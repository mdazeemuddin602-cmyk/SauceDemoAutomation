
package pages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private Path downloadDir = Paths.get(
    	    System.getProperty("user.home"), "Downloads"
    	);

    private By pageTitle = By.className("title");

    private By confirmationMessage =
        By.className("complete-header");

    private By confirmationText =
        By.className("complete-text");

    private By generatePDFButton = By.xpath(
        "//button[contains(normalize-space(.), 'Generate PDF')]"
    );

    private By backHomeButton =
        By.id("back-to-products");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );
    }

    public String getPageTitle() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    public String getConfirmationMessage() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                confirmationMessage
            )
        ).getText();
    }

    public String getConfirmationText() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                confirmationText
            )
        ).getText();
    }

    // Prepare download folder and remove old PDF files
    public void deleteExistingPdfFiles() {

        try {
            Files.createDirectories(downloadDir);

            try (Stream<Path> files = Files.list(downloadDir)) {

                files.filter(file ->
                    file.getFileName().toString()
                        .toLowerCase().endsWith(".pdf")
                ).forEach(file -> {

                    try {
                        Files.deleteIfExists(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        } catch (IOException e) {
            throw new RuntimeException(
                "Unable to prepare download folder", e
            );
        }
    }

    // Click Generate PDF Order
    public void clickGeneratePDFOrder() {

        WebElement button = wait.until(
            ExpectedConditions.elementToBeClickable(generatePDFButton)
        );

        System.out.println("PDF Button Text: " + button.getText());

        button.click();

        System.out.println("PDF Button Clicked");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    // Verify actual PDF download
    public boolean waitForPdfDownload() {

        WebDriverWait downloadWait =
            new WebDriverWait(driver, Duration.ofSeconds(20));

        return downloadWait.until(d -> {

            try (Stream<Path> files = Files.list(downloadDir)) {

                boolean pdfExists = files.anyMatch(file -> {

                    try {
                        return file.getFileName().toString()
                            .toLowerCase().endsWith(".pdf")
                            && Files.size(file) > 0;

                    } catch (IOException e) {
                        return false;
                    }
                });

                boolean downloadInProgress;

                try (Stream<Path> currentFiles =
                         Files.list(downloadDir)) {

                    downloadInProgress = currentFiles.anyMatch(file ->
                        file.getFileName().toString()
                            .endsWith(".crdownload")
                    );
                }

                return pdfExists && !downloadInProgress;

            } catch (IOException e) {
                return false;
            }
        });
    }

    // Click Back Home
    public void clickBackHome() {

        wait.until(
            ExpectedConditions.elementToBeClickable(
                backHomeButton
            )
        ).click();
    }
}
