package com.example;
import org.openqa.selenium.WebElement;
// Removed conflicting File import
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import PageObjects.CartPage;
import PageObjects.CheckOutPage;
import PageObjects.ConfirmationPage;
import PageObjects.OrderPage;
import PageObjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class AppTest extends BaseTest
{
    String prodName="ZARA COAT 3";

    @Test(dataProvider = "getData")
    public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException{  
        
        ProductCatalogue productCatalogue = loginpage.login(input.get("email"), input.get("password")); 
        List<WebElement> products = productCatalogue.productList();
        productCatalogue.addProductToCart(prodName);
        CartPage cartPage = productCatalogue.goToCart();
        System.out.println("now in cartPage ");
        Boolean match = cartPage.VerifyProductDisplay(prodName);
        Assert.assertTrue(match);
       
        CheckOutPage checkOutPage = cartPage.Checkout();
        System.out.println("now in checkOutPage ");
        ConfirmationPage confirmationPage = checkOutPage.selectCountry("India");
        String msg = confirmationPage.getSuccessMessage();
        System.out.println(msg);
        Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    // @Test(dependsOnMethods = {"SubmitOrder"})
    // public void OrderHistory () {

    //     ProductCatalogue productCatalogue = loginpage.login("abhik77k@gmail.com", "D2e@fam7");
    //     OrderPage orderPage = productCatalogue.goToOrderHistory();
    //     Assert.assertTrue(orderPage.VerifyOrderDisplay(prodName));
       
    // } 

    






    
   
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap("C://Users//abhinav//tuts//src//test//java//com//example//data.json");
        if (data == null || data.size() < 2) {
            throw new RuntimeException("Insufficient test data in JSON file");
        }
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
  
}
