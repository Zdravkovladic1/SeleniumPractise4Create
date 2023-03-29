package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }


    @FindBy(css = ".products .totAmt")
    WebElement TotalAmount;

    @FindBy(css = ".promoCode")
    WebElement PromoCode;

    @FindBy(css = ".promoBtn")
    WebElement ApplayButton;

    @FindBy(css = ".promoInfo")
    WebElement errorMessage;

    @FindBy(css = ".products .discountAmt ~  button")
    WebElement PlaceOrderButton;

    @FindBy(css = ".wrapperTwo :nth-child(1)")
    WebElement ChooseCountryLabel;


    public void enterPromoCode() {
        String totalAmount = getText(TotalAmount);
        insertText(PromoCode, totalAmount);
    }

    public void ApplyOrder() {
        clickElement(ApplayButton, "Applied promo code.");
        Assert.assertEquals(getText(errorMessage), "Invalid code ..!");
    }

    public void PlaceOrder() {
        clickElement(PlaceOrderButton, "Clicked place order button.");
        Assert.assertEquals(getText(ChooseCountryLabel), "Choose Country");
    }


    public void ShoppingCartProcess(){
        enterPromoCode();
        ApplyOrder();
        PlaceOrder();
    }

}
