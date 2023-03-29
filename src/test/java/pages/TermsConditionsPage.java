package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class TermsConditionsPage extends BasePage {

    public TermsConditionsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".products>.wrapperTwo select")
    WebElement CountryDropdown;

    @FindBy(css = ".products>.wrapperTwo select :nth-child(1)")
    WebElement SelectDropdownElement;

    @FindBy(css = ".products>.wrapperTwo select option")
    List<WebElement> Countries;

    @FindBy(css = ".products>.wrapperTwo button")
    WebElement ProceedButton;

    @FindBy(css = ".erroralert")
    WebElement AgreementErrorMsg;

    @FindBy(css = ".chkAgree")
    WebElement TermsConditionsCheckbox;

    @FindBy(css = ".wrapperTwo > span")
    WebElement SuccessMsg;


    public void SelectDisabled() {
        clickElement(CountryDropdown, "Clicked on country drop down.");
        boolean isEnabledSelectDropdown = SelectDropdownElement.isEnabled();
        Assert.assertFalse(isEnabledSelectDropdown);
    }

    public void ChooseRandomCountry() {
        Select se = new Select(CountryDropdown);
        se.selectByIndex(RandomNumber(Countries.size()));
    }

    public void ProceedWithoutAgreement(){
        clickElement(ProceedButton, "Clicked on the proceed button without checking agreement.");
        Assert.assertEquals(getText(AgreementErrorMsg), "Please accept Terms & Conditions - Required");
    }

    public void ProceedSuccessfully(){
        clickElement(ProceedButton, "Clicked on the proceed button after checking agreement.");
        Assert.assertTrue(getText(SuccessMsg).contains("Thank you"));
    }

    public void SelectCountryProcess(){
        SelectDisabled();
        ChooseRandomCountry();
        ProceedWithoutAgreement();
        selectCheckbox(TermsConditionsCheckbox);
        ProceedSuccessfully();
    }

}
