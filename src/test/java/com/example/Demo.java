package com.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {
    public static void main(String args[]) {
     WebDriverManager.chromedriver().setup();
     WebDriver driver = new ChromeDriver();
     
     driver.get("https://www.atlassian.com/");
     driver.manage().window().maximize();
  
     List<WebElement> elems = driver.findElements(By.xpath("//a"));
  
      for (WebElement we : elems) {
          if(we.getText().equals("Enterprise")){
              we.click(); 
              break;
          }
    }
  }}