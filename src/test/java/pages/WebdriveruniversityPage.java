package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;

public class WebdriveruniversityPage extends BasePage {

    public WebdriveruniversityPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'ACTIONS')]")
    WebElement ActionsSection;
    public void openPageInNewTab(String url){
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    public void TakeScreenshotActions() throws IOException {
        scrollToElement(ActionsSection);
        takeScreenshot(ActionsSection.getText());
    }

    public void OpenActionsPage() {
        clickElement(ActionsSection, "Clicked on actions section.");
    }

    public void BackToPreviousPage() {
        driver.navigate().back();
    }

    public void checkWebdriveruniversityActions(String newTabUrl) throws IOException {
        openPageInNewTab(newTabUrl);
        TakeScreenshotActions();
        OpenActionsPage();
    }

    public void navigateWebdriveruniversityPage() throws IOException {
        BackToPreviousPage();
        takeScreenshot(driver.getTitle());
    }
}
