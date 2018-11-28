package selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.MarionetteDriverManager;


public class WebDriverBuilder {

    private final WebDriverConfig webDriverConfig;

    public WebDriverBuilder(WebDriverConfig webDriverConfig) {
        this.webDriverConfig = webDriverConfig;    }


    public WebDriver toWebDriver() {
        DesiredCapabilitiesFactory desiredCapabilitiesFactory = new DesiredCapabilitiesFactory();
        DesiredCapabilities capabilities = desiredCapabilitiesFactory.initDesiredCapabilities(webDriverConfig);
        String browser = webDriverConfig.getBrowserName();

        switch (browser) {
            case "chrome":
                ChromeDriverManager.getInstance().setup();
                final ChromeDriver chromeDriver = new ChromeDriver(capabilities);
                chromeDriver.manage().window().maximize();
                return chromeDriver;
            case "internetexplorer":
                InternetExplorerDriverManager.getInstance().setup();
                final InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver(capabilities);
                internetExplorerDriver.manage().window().maximize();
                return internetExplorerDriver;
            default:
                MarionetteDriverManager.getInstance().setup();
                FirefoxDriver firefoxDriver = new FirefoxDriver(capabilities);
                firefoxDriver.manage().window().maximize();
                return firefoxDriver;
        }
    }
}
