package selenium.testcases;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

public class PostPolicyApproveAll {

    private static final char SLASH = '/';
    private static final String SPOT = "spot";
    private static final String POST = "post";
    private static final String TOKEN = "?token=";
    private static final Logger log = Logger.getLogger(PostPolicyApproveAll.class);
    private static final String URL = "https://open-api.spot.im/conversation-policy/";
    private StringBuilder urlToSend;

    @Before
    public void buildUrl() {
        log.info("POST URL should be as: https://open-api.spot.im/conversation-policy/spot/{spot_id}/post/{post_id}/{new_policy}?token={token}");
        System.out.println("Testing - Send API POST request");
        final String spot_id = "sp_1FaiP6Ry";
        final String post_id = "post2";
        final String token = "03180904byiKAG";
        final String new_policy = "approve_all";
        urlToSend = getStringBuilder(spot_id, post_id, token, new_policy);
    }

    @Test
    public void postUrl() throws Exception {
        log.info("send valid api call");
        final int responseCode = sendApiRequest(urlToSend);
        log.info("check the response code");
        checkResponseIsValid(responseCode);
    }

    /**
     * check the response code if it's valid
     *
     * @param response - response code
     */
    private void checkResponseIsValid(final int response) {
        if (response == 200) {
            System.out.println("The response is valid - approve all succeed");
        } else {
            System.out.println("The response isn't valid - Test Failed");
        }
    }

    /**
     * sending API call request within provided inputs
     *
     * @param urlToSend - URL which we are going to send
     * @throws Exception - throw exception in case we have
     */
    private int sendApiRequest(final StringBuilder urlToSend) throws Exception {
        System.out.println("URL to send : " + urlToSend.toString());
        URL obj = new URL(urlToSend.toString());
        HttpURLConnection con;
        log.info("here we are going to send the request");
        con = (HttpURLConnection) obj.openConnection();
        log.info("getting the response code");
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);
        return responseCode;
    }

    /**
     * build the url to send
     *
     * @param spot_id    - spot ID
     * @param post_id    - post ID
     * @param token      - token ID
     * @param new_policy - policy status
     * @return - String builder with the parameters
     */
    private static StringBuilder getStringBuilder(final String spot_id, final String post_id, final String token, final String new_policy) {
        StringBuilder urlToSend = new StringBuilder();
        urlToSend.append(URL);
        urlToSend.append(SPOT);
        urlToSend.append(SLASH);
        urlToSend.append(spot_id);
        urlToSend.append(SLASH);
        urlToSend.append(POST);
        urlToSend.append(SLASH);
        urlToSend.append(post_id);
        urlToSend.append(SLASH);
        urlToSend.append(new_policy);
        urlToSend.append(TOKEN);
        urlToSend.append(token);
        return urlToSend;
    }
}
