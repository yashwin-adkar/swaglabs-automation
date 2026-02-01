package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Page 1 – Your Information
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");

    // Page 2 – Overview
    private By overviewTitle = By.className("title"); // "Checkout: Overview"
    private By finishBtn = By.id("finish");

    // Success
    private By successMsg = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Step 1: Fill details & continue
    public void enterDetailsAndContinue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys("QA");
        driver.findElement(lastName).sendKeys("Engineer");
        driver.findElement(postalCode).sendKeys("560001");
        driver.findElement(continueBtn).click();
    }

    // Step 2: Wait for overview page
    public void waitForOverviewPage() {
        wait.until(ExpectedConditions.textToBe(overviewTitle, "Checkout: Overview"));
    }

    // Step 3: Finish order
    public void finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public boolean isOrderPlaced() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).isDisplayed();
    }
}
