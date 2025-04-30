package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;


public class LoginPage extends AbstractComponent{

    WebDriver driver;

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement login;

    @FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
    WebElement errorMessage;
   
    public LoginPage (WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void goTo(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public String getErrorMessage() {
        System.out.println("Is error message displayed: " + errorMessage.isDisplayed());
        waitForWebElement(errorMessage);
        return errorMessage.getText();
    }

    public ProductCatalogue login(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        login.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;

    }


}