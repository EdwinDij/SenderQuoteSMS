package org.quotesms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

public class MessageSender {

    public static Dotenv dotenv = Dotenv.load();
    public static String accountSid = dotenv.get("ACCOUNT_SID");
    public static String authToken = dotenv.get("AUTH_TOKEN");
    public static String twilioPhone = dotenv.get("TWILIO_PHONE");
    public static String myPhone = dotenv.get("MY_PHONE");
    public static void sender() {
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                new PhoneNumber(myPhone),
                new PhoneNumber(twilioPhone),
                "Hey, Actuellement il fait:\n"+ CurrentWeather.getWeather(GetCoordonateCity.getCoordonate().getLattitude(), GetCoordonateCity.getCoordonate().getLongitude()) 
        ).create();

        System.out.println("Message SID: " + message.getSid());
    }
}
