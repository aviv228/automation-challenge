package selenium.driver;

import selenium.configurations.TypedProperties;

public class WebDriverConfig {
	private final TypedProperties typedProperties = new TypedProperties("/test_data.properties");

	public String getBrowserName() {
        return typedProperties.getValue("browser.name");
	}
}
