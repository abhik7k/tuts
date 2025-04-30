package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css=".form-group input")
    WebElement autoSuggestive;

    @FindBy(css= ".btnn")
    WebElement submitButton;

    @FindBy(css= ".ta-item:nth-of-type(2)")
    WebElement selectCountry;

    @FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
    WebElement submitButton1;

    By visible = By.cssSelector(".ta-results");

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }


    public ConfirmationPage selectCountry(String countryName) throws InterruptedException {
        System.out.println("trying to enter text ind");
        Actions a = new Actions(driver);
        a.sendKeys(autoSuggestive, countryName).build().perform();
        waitForElement(visible); 
        selectCountry.click();//selecting india here
        System.out.println("Placing the order");
        submitButton.click();
        

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }

    







    
}
