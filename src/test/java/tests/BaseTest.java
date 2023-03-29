package tests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import selenium.DriverManager;
import selenium.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    DriverManager driverManager;

    public void init() throws Exception {
        driverManager = DriverManagerFactory.getDriverManager("CHROME");
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void openApp(String env) {
        switch (env.toUpperCase()){
            case "PROD" :{
                driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
            }
            break;
            case "QA" :{
                driver.get("https://qa.rahulshettyacademy.com/seleniumPractise/#/");
            }
            break;
            case "DEV" :{
                driver.get("https://dev.rahulshettyacademy.com/seleniumPractise/#/");
            }
            break;
        }

    }

    public void quit(){
        driverManager.quitWebDriver();
    }




}