package selenium.utils;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

import selenium.driver.WebDriverBuilder;
import selenium.driver.WebDriverConfig;

public class WebDriverProvider extends TestWatcher {
    private final WebDriverBuilder webDriverBuilder;
    private WebDriver driver;

    public WebDriverProvider(final WebDriverConfig webDriverConfig) {
        this.webDriverBuilder = new WebDriverBuilder(webDriverConfig);
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = webDriverBuilder.toWebDriver();
        }
        return driver;
    }

    @Override
    protected void finished(final Description description) {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}