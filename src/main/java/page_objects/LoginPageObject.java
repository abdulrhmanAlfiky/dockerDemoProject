package page_objects;

import generic_keywords.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends WebElementInteractions {


 public LoginPageObject(WebDriver driver){
     super(driver);

 }
    private final By userNameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");
    private final By loginBtn = By.id("login-button");


    public ProductsPageObject userLogin (String userName, String password){
        goToApplication("https://www.saucedemo.com/");
        sendText(userNameTextField,userName);
        sendText(passwordTextField,password);
        clickElement(loginBtn);
        return new ProductsPageObject(driver);
    }

}
