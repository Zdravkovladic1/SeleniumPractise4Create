package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import selenium.DriverManager;
import selenium.DriverManagerFactory;

public class ContactUsPage extends BasePage {

    public ContactUsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void OpenNewPage(String url) throws Exception {
        DriverManager driverManager;
        driverManager = DriverManagerFactory.getDriverManager("CHROME");
        driver = driverManager.getWebDriver();
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    public void InsertText() {
        insertText(driver.findElement(By.cssSelector("textarea[name='message']")), popupError);
    }

    public void contactUsForm(String contactUsUrl) throws Exception {
        OpenNewPage(contactUsUrl);
        InsertText();
    }
}
