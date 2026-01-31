package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Valid login failed");
    }

    @Test
    public void invalidUsernameTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "secret_sauce");

        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Username and password"),
            "Error message not displayed"
        );
    }

    @Test
    public void invalidPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Username and password"),
            "Error message not displayed"
        );
    }

    @Test
    public void emptyUsernameTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");

        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Username is required"),
            "Username required message missing"
        );
    }

    @Test
    public void emptyPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");

        Assert.assertTrue(
            loginPage.getErrorMessage().contains("Password is required"),
            "Password required message missing"
        );
    }

    @Test
    public void lockedOutUserTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(
            loginPage.getErrorMessage().contains("locked out"),
            "Locked user message missing"
        );
    }
    @Test
    public void sqlInjectionUsernameTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("' OR '1'='1", "secret_sauce");

        Assert.assertTrue(
            loginPage.getErrorMessage().length() > 0,
            "SQL injection vulnerability!"
        );
    }

    @Test
    public void sqlInjectionPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "' OR '1'='1");

        Assert.assertTrue(
            loginPage.getErrorMessage().length() > 0,
            "SQL injection vulnerability!"
        );
    }

    @Test
    public void sqlInjectionBothFieldsTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("' OR '1'='1", "' OR '1'='1");

        Assert.assertTrue(
            loginPage.getErrorMessage().length() > 0,
            "SQL injection vulnerability!"
        );
    }

}
