package page_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;

public class ProductsPageTests extends BaseTest {

    LoginPageObject loginPageObject;
    ProductsPageObject productsPageObject;
    private static final Logger logger = LogManager.getLogger(LoginPageTests.class);


    @Test
    public void testItemName() throws InterruptedException {
        String username = "standard_user";
        String password = "secret_sauce";
        loginPageObject = new LoginPageObject(driver);
        productsPageObject = loginPageObject.userLogin(username,password);
        Thread.sleep(10000);
        Assert.assertEquals(productsPageObject.getFirstItemText(),"Sauce Labs Backpack");
    }

    @Test
    public void testItemName2() throws InterruptedException {
        String username = "standard_user";
        String password = "secret_sauce";
        loginPageObject = new LoginPageObject(driver);
        productsPageObject = loginPageObject.userLogin(username,password);
        logger.info("Username is: "+username+"Password is: "+password);
        Thread.sleep(10000);
        Assert.assertEquals(productsPageObject.getSecondItemText(),"Sauce Labs Bike Light");
    }
}
