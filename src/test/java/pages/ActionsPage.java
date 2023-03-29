package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import selenium.DriverManager;

import java.util.ArrayList;

public class ActionsPage extends BasePage {

    public ActionsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#div-drag-drop-thumbnail>#draggable")
    WebElement DragElement;

    @FindBy(css = "#div-drag-drop-thumbnail>#droppable")
    WebElement DropElement;

    @FindBy(css = "#droppable>p>b")
    WebElement DropMsg;

    @FindBy(xpath = "//button[contains(text(), 'Hover Over Me First!')]/following-sibling::div")
    WebElement LinkVisibility;

    @FindBy(xpath = "//button[contains(text(), 'Hover Over Me First!')]/following-sibling::div/a")
    WebElement LinkText;

    @FindBy(xpath = "//button[contains(text(), 'Hover Over Me First!')]")
    WebElement FirstElement;

    @FindBy(css = "textarea[name='message']")
    WebElement FormTextArea;

    public void switchTabs(int tab){
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(tab));
    }

    public void CheckTitle() {
        Assert.assertTrue(driver.getTitle().contains("Actions"));
    }

    public void DroppedElement(){
        dragAndDrop(DragElement, DropElement);
        Assert.assertEquals(getText(DropMsg), "Dropped!");
    }

    public void CheckLinkInvisibility() {
        Boolean display = LinkVisibility.isDisplayed();
        Assert.assertFalse(display);
    }

    public void CheckLinkVisibility() {
        Actions actions = new Actions(driver);
        actions.moveToElement(FirstElement).perform();

        String linkText = LinkText.getText();
        Assert.assertEquals(linkText, "Link 1");
    }

    public void VerifyPopupError() {
        popupError = driver.switchTo().alert().getText();
        Assert.assertTrue(popupError.contains("Well done you clicked on the link!"));
    }

    public void CloseAlertPopup() {
        driver.switchTo().alert().accept();
    }

    public void CloseTheBrowser() {
        driver.quit();
    }

    public void controlActionsPage(){
        switchTabs(2);
        CheckTitle();
        DroppedElement();
        CheckLinkInvisibility();
        CheckLinkVisibility();
        clickElement(LinkText, "Open Link1");
        VerifyPopupError();
        CloseAlertPopup();
        CloseTheBrowser();
    }

}
