package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class SeleniumPracticeTests extends BaseTest {

    @BeforeMethod
    public void setup() throws Exception {
        init();
        openApp("Prod");
    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        quit();
    }


    @Parameters({"newTabUrl", "contactUsUrl"})
    @Test()
    public void AlloverTest(String newTabUrl, String contactUsUrl) throws Exception
    {
        new HomePage(driver).completePurchase();
        new ShoppingCartPage(driver).ShoppingCartProcess();
        new TermsConditionsPage(driver).SelectCountryProcess();
        WebdriveruniversityPage universityPage = new WebdriveruniversityPage(driver);
        universityPage.checkWebdriveruniversityActions(newTabUrl);
        universityPage.navigateWebdriveruniversityPage();
        new ActionsPage(driver).controlActionsPage();
        new ContactUsPage(driver).contactUsForm(contactUsUrl);
    }


}
