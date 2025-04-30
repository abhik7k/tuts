package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.devtools.v126.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
    
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".col-lg-4")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement animating;

    By prods = By.cssSelector(".col-lg-4");
    By addToCart = By.xpath("//div[@class='card-body']/button[contains(text(), 'Add To Cart')]");
    By toastMsg = By.cssSelector("#toast-container");
    //By cartIcon = By.cssSelector("[routerlink*= 'cart']");

    public List<WebElement> productList () {
        
        waitForElement(prods);
        return products;
    }

    public WebElement getProductByName (String prodName) {
       
        WebElement prod =  products.stream().filter(product-> //or we can use productList() also
        product.findElement(By.cssSelector("b")).getText().
        equals(prodName)).findFirst().orElse(null);

        return prod;
    }

    public void addProductToCart (String prodName) {

       WebElement prod = getProductByName(prodName);
       prod.findElement(addToCart).click();
       waitForElement(toastMsg);
       waitForElementToDisappear(animating);
    }

  
   

     


    

}