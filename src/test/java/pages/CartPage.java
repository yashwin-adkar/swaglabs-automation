package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartTitle = By.className("title"); // Your Cart
    private By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isOnCartPage() {
        return wait.until(ExpectedConditions.textToBe(cartTitle, "Your Cart"));
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();

        // 🔥 CI-SAFE WAIT
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }
}
