package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;
import PageObjects.OrderPage;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        //code to initialize the driver
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }   

    @FindBy(css="[routerlink*= 'cart']")
    WebElement cartIcon;

    @FindBy(css="[routerlink*= 'myorders']")
    WebElement orderHeader;

    public void waitForElement(By findBy) {
        //code to wait for element
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForWebElement(WebElement e) {
        //code to wait for element
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForElementToDisappear(WebElement element) {
        //code to wait for element to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public CartPage goToCart() {
        cartIcon.click();
        CartPage cartpage = new CartPage(driver);
        return cartpage;
    }

    public OrderPage goToOrderHistory () {
        orderHeader.click();
        OrderPage orderpage = new OrderPage(driver);
        return orderpage;
    }


}