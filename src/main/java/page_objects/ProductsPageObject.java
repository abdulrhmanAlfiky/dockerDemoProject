package page_objects;

import generic_keywords.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPageObject extends WebElementInteractions {


    private final By titleOfProductPage = By.xpath("//span[contains(text(), 'Products')]");
    private final By firstItem = By.id("item_4_title_link");
    private final By secondItem = By.id("item_0_title_link");

    public ProductsPageObject(WebDriver driver) {
        super(driver);
    }

    public String getTitleOfPage(){
        return retrieveTextData(titleOfProductPage);
    }

    public String getFirstItemText(){
        return retrieveTextData(firstItem);
    }
    public String getSecondItemText(){return retrieveTextData(secondItem);}
}
