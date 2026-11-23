package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

	
	  private WebDriverWait wait;
	  
	  public WaitUtility(WebDriver driver) { wait = new WebDriverWait(driver,
	  Duration.ofSeconds(10)); }
	  
	  public void waitForElementToBeVisible(WebElement element) {
	  wait.until(ExpectedConditions.visibilityOf(element)); }
	  
	  public void waitForElementToBeClickable(WebElement element) {
	  wait.until(ExpectedConditions.elementToBeClickable(element)); }
	  
	  public void waitForElementToBePresent(By locator) {
	  wait.until(ExpectedConditions.presenceOfElementLocated(locator)); }
	  
	  public void waitForElementToDisappear(WebElement element) {
	  wait.until(ExpectedConditions.invisibilityOf(element)); }
	  
	  public void waitForTitleContains(String title) {
	  wait.until(ExpectedConditions.titleContains(title)); }
	  
	  public void waitForUrlContains(String url) {
	  wait.until(ExpectedConditions.urlContains(url)); }
	 

}
