package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.AuthenticatedBaseTest;
import pages.InventoryPage;
import pages.CartPage;

public class InventoryTest extends AuthenticatedBaseTest {

    @Test
    public void addToCartAndNavigateToCart() {

        InventoryPage inventoryPage = new InventoryPage(driver);

        // wait for inventory page
        inventoryPage.waitForInventoryPage();

        // add product
        inventoryPage.addBackpackToCart();

        // verify cart count
        Assert.assertEquals(inventoryPage.getCartCount(), "1");

        // click cart icon
        inventoryPage.clickCartIcon();

        // verify navigation
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isOnCartPage());
    }
}
