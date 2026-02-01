package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.AuthenticatedBaseTest;
import pages.*;

public class EndToEndTest extends AuthenticatedBaseTest {

    @Test
    public void completePurchaseFlow() {

        InventoryPage inventory = new InventoryPage(driver);
        inventory.waitForInventoryPage();

        inventory.addBackpackToCart();
        Assert.assertEquals(inventory.getCartCount(), "1");

        inventory.clickCartIcon();

        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isOnCartPage());
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterDetailsAndContinue();
        checkout.finishCheckout();
        Assert.assertTrue(checkout.isOrderPlaced());


        Assert.assertTrue(checkout.isOrderPlaced(), "Order not placed!");
    }
}
