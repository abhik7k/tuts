package com.example;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public WebDriver driver;
    public LoginPage loginpage;

    public WebDriver setUp() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src\\main\\java\\Resources\\res.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
            WebDriverManager.chromedriver().setup();
              
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            //code to initialize firefox driver
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            //code to initialize ie driver
            WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

     public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {} );
        return data;
    }   
    public static String getScreenshot (String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        FileUtils.copyFile(source, new java.io.File(destinationFile));
        return destinationFile;
    }


    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApplication() throws IOException {
        driver = setUp();
        loginpage = new LoginPage(driver);
        loginpage.goTo( "https://rahulshettyacademy.com/client/");
        return loginpage;
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
    
}
