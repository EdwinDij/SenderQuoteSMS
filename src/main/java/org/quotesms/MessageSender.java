package org.quotesms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
// import io.github.cdimascio.dotenv.Dotenv;

public class MessageSender {
    // private static Dotenv dotenv = Dotenv.load();
    private static TwilioConfig twilioConfig = new TwilioConfig(
        System.getenv("ACCOUNT_SID"),
        System.getenv("ACCOUND_SID_AUTH_TOKEN"),
        System.getenv("TWILIO_PHONE"),
        System.getenv("MYPHONE")
    );

    public static void sender() throws Exception {
        CurrentWeather.Weather weather = CurrentWeather.getWeather(GetCoordonateCity.getCoordonate().getLatitude(), GetCoordonateCity.getCoordonate().getLongitude());
    
        if(weather != null) {
            try {
                Twilio.init(twilioConfig.getAccoundSid(), twilioConfig.getAuthToken()); // Correction ici
                Message message = Message.creator(
                        new PhoneNumber(twilioConfig.getMyPhone()),
                        new PhoneNumber(twilioConfig.getTwilioPhone()),
                        String.format("Hey 👋, il fait %s°C et le ciel est %s.\nPhrase inspirante du jour: %s ---%s",weather.getDegree(), weather.getDescription(), QuoteGenerator.getQuote().getQuoteText(), QuoteGenerator.getQuote().getQuoteAuthor())
                ).create();
        
                // System.out.println("Message SID: " + message.getSid());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }
}
