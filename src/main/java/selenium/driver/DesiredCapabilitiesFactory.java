package selenium.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

class DesiredCapabilitiesFactory {

	public DesiredCapabilities initDesiredCapabilities(WebDriverConfig config) {

		// DesireCapabilities variable
		final DesiredCapabilities capabilities = new DesiredCapabilities();

		// Get browser from config and set browser.
		final String browser = config.getBrowserName();
		capabilities.setBrowserName(browser);

		// Set specific settings for firefox.
		if (browser.equals("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		}

		// Set specific settings for chrome.
		else if (browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		}

		return capabilities;
	}

}
