package org.quotesms;

public class SmsScheduler  {
    public static void main(String[] args) {
        try {
            QuoteGenerator.getQuote();
            GetCoordonateCity.getCoordonate();
            MessageSender.sender();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
