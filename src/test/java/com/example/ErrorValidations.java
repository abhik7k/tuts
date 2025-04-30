package com.example;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.ProductCatalogue;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ErrorValidations extends BaseTest{

    @Test(groups= {"ErrorHandling"})
    public void loginErrorValidation() throws IOException {
    
    loginpage.login("abhik77k@gmail.com", "D2e@fa7");
    Assert.assertEquals(loginpage.getErrorMessage(), "Incorrect email password.");    
}
    @Test
    public void productErrorValidation() throws IOException, InterruptedException{

        String prodName="ZARA COAT 3";
        
        ProductCatalogue productCatalogue = loginpage.login("abhik77k@gmail.com", "D2e@fam7"); 
        List<WebElement> products = productCatalogue.productList();
        productCatalogue.addProductToCart(prodName);
        CartPage cartPage = productCatalogue.goToCart();
        System.out.println("now in cartPage ");
        Boolean match = cartPage.VerifyProductDisplay("ZARA3 COAT 3");
        Assert.assertFalse(match);
       System.out.println("going to checkout page");  

    }
}

