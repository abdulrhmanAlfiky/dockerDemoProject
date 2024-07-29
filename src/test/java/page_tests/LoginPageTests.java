package page_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;

public class LoginPageTests extends BaseTest{

    LoginPageObject loginPageObject;
    ProductsPageObject productsPageObject;
    private static final Logger logger = LogManager.getLogger(LoginPageTests.class);


    @Test
    public void userLoginTest() throws InterruptedException {
        String username = "standard_user";
        String password = "secret_sauce";
        loginPageObject = new LoginPageObject(driver);
        productsPageObject = loginPageObject.userLogin(username,password);
        logger.info("Username is: "+username+"Password is: "+password);
        Assert.assertEquals(productsPageObject.getTitleOfPage(),"Products");
        Thread.sleep(10000);

    }
}
