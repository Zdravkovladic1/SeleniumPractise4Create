package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import selenium.DriverManager;
import selenium.DriverManagerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".products-wrapper .products>:nth-child(7) button")
    WebElement OneItem;

    @FindBy(css = ".products-wrapper .products>.product>.product-action")
    List<WebElement> Products;

    @FindBy(css = ".cart>.cart-icon")
    WebElement BasketIcon;

    @FindBy(css = ".cart-preview .action-block :nth-child(1)")
    WebElement ProceedToCheckout;

    @FindBy(xpath = "//button[contains(text(), 'Hover Over Me First!')]/following-sibling::div/a")
    WebElement LinkText;

    @FindBy(css = "tbody >tr >td:nth-child(3)>.quantity")
    List<WebElement> QuantityItemsSelect;


    public void VerifyOpenHomePage() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    public void AddOneItemToBasket() {
        clickOnAddtocartMultiple(OneItem, 4);
    }

    public void AddThreeRandomItemsToBasket() throws InterruptedException {
        List<WebElement> list = GetRandomItems(Products, 3);
        for (WebElement item :
                list) {
            clickElement(item, "Add random item to cart.");
        }
    }

    public void QuantityItems(List<WebElement> productList) {
        for (WebElement element :
                productList) {
            if (Integer.parseInt(element.getText()) == 4) {
                Assert.assertEquals(element.getText(), "4");
            } else {
                Assert.assertEquals(element.getText(), "1");
            }
        }

    }

    public void completePurchase() throws Exception {
        VerifyOpenHomePage();
        AddOneItemToBasket();
        AddThreeRandomItemsToBasket();
        BasketIcon.click();
        ProceedToCheckout.click();
        QuantityItems(QuantityItemsSelect);

    }



}
