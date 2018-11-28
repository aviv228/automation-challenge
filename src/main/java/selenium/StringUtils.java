package selenium;

public class StringUtils {


    public static String cssStringToFrameId(final String frameId) {
        return "css=iframe[id='" + frameId + "']";
    }
}
