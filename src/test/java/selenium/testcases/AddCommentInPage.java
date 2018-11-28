package selenium.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.apache.log4j.Logger;
import selenium.SeleniumFunctions;
import selenium.StringUtils;
import selenium.driver.WebDriverConfig;
import selenium.pageobjects.*;
import selenium.utils.CommonConstants;
import selenium.utils.WebDriverProvider;

public class AddCommentInPage {

    private static final Logger log = Logger.getLogger(AddCommentInPage.class);
    private static final WebDriverConfig webDriverConfig = new WebDriverConfig();
    private final WebDriverProvider webDriverProvider = new WebDriverProvider(webDriverConfig);

    private WebDriver getDriver() {
        return this.webDriverProvider.getDriver();
    }

    private StartPage startPage = PageFactory.initElements(getDriver(), StartPage.class);
    private SeleniumFunctions seleniumFunctions = PageFactory.initElements(getDriver(), SeleniumFunctions.class);

    @Before
    public void setup() {
        startPage.open();
    }

    @Test
    public void testFlow()  {
        seleniumFunctions.waitForElementAndClick(CommonConstants.A_TEXT_POST2_HTML);
        seleniumFunctions.waitForElementToBeClickable(CommonConstants.CLASS_SPPRE_FRAME_CONTAINER);
        switchToFrame();
        seleniumFunctions.waitForElementIsVisible(CommonConstants.CSS_SPPRE_LOGIN_BUTTON);
        seleniumFunctions.waitForElementAndClick(CommonConstants.CSS_SPPRE_LOGIN_BUTTON);
        getDriver().switchTo().defaultContent();
        seleniumFunctions.waitForFrameToLoad(CommonConstants.SPOT_IM_FRAME_MODAL_IFRAME);
        seleniumFunctions.waitForElementAndClick(CommonConstants.CSS_X_LARGE_EMAIL_CONNECT_SOCIAL_CONNECT_BUTTON_CONNECT_BUTTON);
        seleniumFunctions.waitForElementAndType(CommonConstants.INPUT_PLACEHOLDER_EMAIL, CommonConstants.USER_NAME_STRING);
        seleniumFunctions.waitForElementAndType(CommonConstants.INPUT_PLACEHOLDER_PASSWORD, CommonConstants.PASSWORD_STRING);
        seleniumFunctions.waitForElementAndClick(CommonConstants.CSS_BUTTON_ACTION_REGULAR);
        seleniumFunctions.waitForElementIsInvisible(CommonConstants.CSS_BUTTON_ACTION_REGULAR);
        getDriver().switchTo().defaultContent();
        switchToFrame();
        final String userName = seleniumFunctions.findElementByLocator(
                CommonConstants.CSS_SPPRE_CURRENT_USER_MENU_SPPRE_TEXT_TRIGGER_SPPRE_CONVERSATION_SPPRE_MEDIUM).getText();
        final String commentToAdd = userName + CommonConstants.IS_USING_UI_AUTOMATION_COMMENT;
        seleniumFunctions.waitForElementAndType(CommonConstants.CSS_QL_EDITOR_QL_BLANK, commentToAdd);
        seleniumFunctions.waitForElementAndClick(CommonConstants.CSS_SPPRE_SEND_BUTTON_BRAND_BG_COLOR_BRAND_BG_COLOR_HOVER);
        final String firstComment = seleniumFunctions.findElementByLocator(
                CommonConstants.CSS_SPPRE_MESSAGE_ENTITIES_SPPRE_DEPTH_0_SPPRE_PAD_MESSAGE).getText();
        if (firstComment.equals(commentToAdd)) {
            log.info(CommonConstants.THE_COMMENT_ADDED_AS_EXPECTED_MSG);
        }
        else {
            log.error(CommonConstants.THE_FIRST_COMMENT_ISN_T_THE_SAME_AS_THE_USER_ENTERED_MSG);
        }
    }

    private void switchToFrame() {
        final String frame = seleniumFunctions.findElementByLocator(
                CommonConstants.CLASS_SPPRE_FRAME_CONTAINER).getAttribute(CommonConstants.ID_STRING);
        final String frameId = frame + CommonConstants.IFRAME;
        getDriver().switchTo().frame(seleniumFunctions.findElementByLocator(StringUtils.cssStringToFrameId(frameId)));
    }

    @After
    public void closeBrowser() {
        getDriver().quit();
    }
}