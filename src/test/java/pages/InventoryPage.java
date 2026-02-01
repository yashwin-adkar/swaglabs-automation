package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By inventoryTitle = By.className("title");
    private By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForInventoryPage() {
        wait.until(ExpectedConditions.textToBe(inventoryTitle, "Products"));
    }

    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addBackpackBtn)).click();
    }

    public String getCartCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();

        // 🔥 wait for cart page
        wait.until(ExpectedConditions.urlContains("cart"));
    }

}
