package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends BaseTest {

    @Test
    public void verifyInventoryPageAndAddToCart() {

        // 🔹 Step 1: Login first (PRE-CONDITION)
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // 🔹 Step 2: Inventory validations
        InventoryPage inventoryPage = new InventoryPage(driver);

        Assert.assertTrue(
                inventoryPage.isOnInventoryPage(),
                "User is not on Inventory page"
        );

        Assert.assertEquals(
                inventoryPage.getProductsTitle(),
                "Products"
        );

        Assert.assertTrue(
                inventoryPage.getProductCount() > 0,
                "No products displayed"
        );

        inventoryPage.addBackpackToCart();

        Assert.assertTrue(
                inventoryPage.isRemoveButtonDisplayed(),
                "Remove button not displayed"
        );

        Assert.assertEquals(
                inventoryPage.getCartBadgeCount(),
                "1"
        );
    }
}
