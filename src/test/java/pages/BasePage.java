package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasePage {

    WebDriver driver;
    int waitTime = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(WebElement element, String log) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime);

        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            new Actions(driver).moveToElement(element).click().perform();
            System.out.println("Clicked element: " + log);
        } catch (Exception e) {
            e.printStackTrace();
            element.click();
            System.out.println("Clicked element: " + log);
        }
    }

    public int RandomNumber(int ItemsSize){
        Random random = new Random();
        return random.nextInt(ItemsSize);
    }

    public List<WebElement> GetRandomItems(List<WebElement> productList, int totlaItems) {
        Random random = new Random();
        List<WebElement> itemsList = new ArrayList<>();

        for (int i = 0; i < totlaItems; i++) {
            int randomIndex = random.nextInt(productList.size());
            itemsList.add(productList.remove(randomIndex));
        }
        return itemsList;
    }

    public void clickOnAddtocartMultiple(WebElement element, int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            clickElement(element, "Add to cart one item multiple times.");
        }
    }

    public String getText(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime);

        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        String Text = element.getText();
        return Text;
    }

    public void insertText(WebElement element, String text) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public void selectCheckbox(WebElement element){
        if(!element.isSelected()){
            element.click();
        }
    }

    public void dragAndDrop(WebElement sourcelocator, WebElement destinationlocator){
        Actions action = new Actions(driver);
        action.dragAndDrop(sourcelocator, destinationlocator).build().perform();
    }

    public void takeScreenshot(String name) throws IOException {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("src/results/screenshots/" + name + ".png"));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -60);", element);
    }

    static String popupError;
}
