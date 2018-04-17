/**
 * Created by ksanghb on 05/12/2017.
 */
// Install the Java helper library from twilio.com/docs/java/install

//import com.sun.org.apache.xpath.internal.operations.String;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class NotificationSystem {

    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC2579b05e5698296e94bf417f320658b5";
    public static final String AUTH_TOKEN = "ce497e60ecc308f10bc6ee6aff6a24bc";


    public String sendMessage(TvShow show) throws ApiException,InvalidDefinitionException, NoSuchMethodError
    {
        String userNum;
        String senderNum;
        String notification;
        String showTitle, network;

        userNum =  "+447949249986";
        senderNum = "+441725762039";
        showTitle = show.getName();
        network = show.network;
        notification = "There are 30 minutes until "+ showTitle +" is showing on "+ network;

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try{
            Message message = Message.creator(new PhoneNumber(userNum),
                    new PhoneNumber(senderNum), notification).create();
        }catch(Exception e){
            //e.printStackTrace();
            //Code dangerous.
        }


        return notification;
    }
}
