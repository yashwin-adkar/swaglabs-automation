package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {

    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By productsTitle = By.className("title");
    private By inventoryItems = By.className("inventory_item");
    private By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By removeBackpack = By.id("remove-sauce-labs-backpack");
    private By cartBadge = By.className("shopping_cart_badge");

    // Actions
    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public String getProductsTitle() {
        return driver.findElement(productsTitle).getText();
    }

    public int getProductCount() {
        List<WebElement> items = driver.findElements(inventoryItems);
        return items.size();
    }

    public void addBackpackToCart() {
        driver.findElement(addToCartBackpack).click();
    }

    public boolean isRemoveButtonDisplayed() {
        return driver.findElement(removeBackpack).isDisplayed();
    }

    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }
}