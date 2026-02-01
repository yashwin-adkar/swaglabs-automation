package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void invalidUsernameTest() {
        LoginPage login = new LoginPage(driver);
        login.login("invalid_user", "secret_sauce");
        Assert.assertTrue(login.getErrorMessage().contains("Username and password"));
    }

    @Test
    public void sqlInjectionTest() {
        LoginPage login = new LoginPage(driver);
        login.login("' OR '1'='1", "' OR '1'='1");
        Assert.assertTrue(login.getErrorMessage().length() > 0);
    }
}
