import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.twilio.exception.ApiException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ksanghb on 05/12/2017.
 */
public class NotificationSystemTest {
    TvShow show;
    ArrayList<String> genres;

    NotificationSystem notificationSystem;
    @Before
    public void setUp() throws Exception {

        genres = new ArrayList<String>(Arrays.asList("genre1", "genre2"));
        show = new TvShow("show 1", genres, "network1", "imgLink", "description", "8", "150", "2017-11-05T11:00:00+00:00" );
        notificationSystem = new NotificationSystem();

    }

    //The test will not pass but the functionality of the SMS alert works as intended. Proven by recieved text messages.
    @Test
    public void sendMessageTest() throws ApiException,InvalidDefinitionException, NoSuchMethodError  {
        String result = "";
        result = notificationSystem.sendMessage(show);
        Assert.assertTrue(result.equals("There are 30 minutes until show 1 is showing on network1"));
        System.out.println(result);

    }
}