package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By checkoutTitle = By.className("title"); // Checkout: Your Information
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");

    private By overviewTitle = By.className("title"); // Checkout: Overview
    private By finishBtn = By.id("finish");

    private By successMsg = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public void enterDetailsAndContinue() {

        // 🔥 CI CRITICAL: wait for page
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
        wait.until(ExpectedConditions.textToBe(
                checkoutTitle, "Checkout: Your Information"));

        WebElement fn = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        fn.clear();
        fn.sendKeys("QA");

        WebElement ln = driver.findElement(lastName);
        ln.clear();
        ln.sendKeys("Engineer");

        WebElement zip = driver.findElement(postalCode);
        zip.clear();
        zip.sendKeys("560001");

        // CI stability
        try { Thread.sleep(1000); } catch (Exception e) {}

        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void finishCheckout() {

        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        wait.until(ExpectedConditions.textToBe(
                overviewTitle, "Checkout: Overview"));

        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public boolean isOrderPlaced() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).isDisplayed();
    }
}
