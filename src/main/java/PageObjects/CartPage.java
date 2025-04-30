package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(xpath="//button[normalize-space()='Checkout']")
    WebElement checkout;

    By cart = By.cssSelector("totalRow button");

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public Boolean VerifyProductDisplay (String prodName) {
        
        return cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(prodName)); 
    }

    public CheckOutPage Checkout() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);", checkout);
        Thread.sleep(3000);
        // Actions actions = new Actions(driver);

        // actions.moveToElement(checkout).click().perform();
        waitForElement(By.xpath("//button[normalize-space()='Checkout']"));
         checkout.click();
        System.out.println("Clicked on checkout");
        CheckOutPage checkoutpage = new CheckOutPage(driver);
        return checkoutpage;

    }
}
