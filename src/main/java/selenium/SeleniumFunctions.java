package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.configurations.TypedProperties;

public class SeleniumFunctions {

    public WebDriver driver;


    public SeleniumFunctions(final WebDriver driver) {
        this.driver = driver;
    }

    private TypedProperties testData = new TypedProperties("/test_data.properties");

    public String getTestData(String key) {
        return testData.getValue(key);
    }

    private final int TIMEOUT = Integer.parseInt(getTestData("timeout"));

    public void waitForElementToBeClickable(final String locator) {
        waitForElementToBeClickable(buildByUsingLocator(locator), TIMEOUT);
    }

    public void waitForElementAndClick(final String locator){
            waitForElementToBeClickable(locator);
            findElementByLocator(locator).click();
            waitForPageLoad();
    }

    public void waitForElementAndType(final String locator, final String text){
        waitForElementToBeClickable(locator);
        findElementByLocator(locator).sendKeys(text);
        waitForPageLoad();
    }

    public void waitForElementToBeClickable(final By by, final int maximumSeconds) {
        WebDriverWait wait = new WebDriverWait(this.driver, maximumSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForFrameToLoad( final String frameName){
        WebDriverWait wait = new WebDriverWait(this.driver,10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
    }

    public void waitForPageLoad() {
        waitForPageLoad(TIMEOUT);
    }

    public void waitForPageLoad(int timeout) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

        Wait<WebDriver> wait = new WebDriverWait(this.driver, timeout);
        try {
            wait.until(expectation);
        } catch (Throwable error) {
            throw error;
        }
    }

    public String getTitleName() {
        return this.driver.getTitle();
    }

    public void clickAndWait(final String locator) {
        final WebElement element = findElementByLocator(locator);
        element.click();
        waitForPageLoad();
    }

    public void clickOnElement(final String locator) {
        clickAndWait(locator);
    }

    public String clickAndSwitchToNewWindow(String triggerButton) {
        String parentHandle = this.driver.getWindowHandle(); // get the current window handle
        clickAndWait(triggerButton); // click the button and open the new window
        for (String winHandle : this.driver.getWindowHandles()) {
            this.driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
        return parentHandle;
    }

    public void waitUntilNumOfWindowsToBe(int numOfWindowsToBe) {
        WebDriverWait wait = new WebDriverWait(this.driver, TIMEOUT);
        wait.until(ExpectedConditions.numberOfWindowsToBe(numOfWindowsToBe));
    }

    public WebElement findElementByLocator(final String locator) {
        return this.driver.findElement(buildByUsingLocator(locator));
    }

    public By buildByUsingLocator(String locator) {
        By by;
        locator = locator.trim();
        if (locator.startsWith("id=")) {
            by = By.id(locator.replace("id=", ""));
        } else if (locator.startsWith("//") || locator.startsWith("xpath=")) {
            by = By.xpath(locator.replace("xpath=", ""));
        } else if (locator.startsWith("css=#") || locator.startsWith("img")) {
            by = By.cssSelector(locator.replace("css=", ""));
        } else if (locator.startsWith("css=") || locator.startsWith("img")) {
            by = By.cssSelector(locator.replace("css=", ""));
        } else if (locator.startsWith("link=")) {
            by = By.linkText(locator.replace("link=", ""));
        } else if (locator.startsWith("name=")) {
            by = By.name(locator.replace("name=", ""));
        } else if (locator.startsWith("class=")) {
            by = By.className(locator.replace("class=", ""));
        } else if (locator.startsWith("tagName=")) {
            by = By.tagName(locator.replace("tagName=", ""));
        } else {
            by = By.id(locator);
        }
        return by;
    }


    public void waitForElementIsInvisible(final String locator) {
        final WebDriverWait wait = new WebDriverWait(this.driver, TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buildByUsingLocator(locator)));
    }

    public void waitForElementIsVisible(final String locator) {
        final WebDriverWait wait = new WebDriverWait(this.driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(buildByUsingLocator(locator)));
    }

    public void waitForTextToAppear(final String textToAppear, final WebElement element) {
        final WebDriverWait wait = new WebDriverWait(this.driver, TIMEOUT);
        wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
    }

    public void waitForUrlPart(final String urlPart) {
        final WebDriverWait wait = new WebDriverWait(this.driver, TIMEOUT);
        wait.until(ExpectedConditions.urlContains(urlPart));
    }


    public void mouseover(final By by) {
        final WebElement element = this.driver.findElement(by);
        final Actions builder = new Actions(this.driver);
        final Action mouseover = builder.moveToElement(element).build();
        mouseover.perform();
    }

    public void mouseover(final WebElement element) {
        final Actions builder = new Actions(this.driver);
        final Action mouseover = builder.moveToElement(element).build();
        mouseover.perform();
    }

    public void moveToElement(final By by) {
        mouseover(by);
    }

    public void moveToElement(final WebElement element) {
        mouseover(element);
    }
}
