package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

    @FindBy(css="tr td:nth-of-type(2)")
    List<WebElement> prodNames;

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver=driver; 
        PageFactory.initElements(driver, this);
    }

    public Boolean VerifyOrderDisplay (String prodName) {
        
        return prodNames.stream().anyMatch(cartProduct->cartProduct.getText().equals(prodName)); 
    }
    
}
