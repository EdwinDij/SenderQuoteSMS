package org.quotesms;

public class App 
{
    public static void main( String[] args )
    {
        QuoteGenerator.getQuote();
        System.out.println("quote texte: " + QuoteGenerator.getQuote().getQuoteText());
        System.out.println("quote auteur: " + QuoteGenerator.getQuote().getQuoteAuthor());
        GetCoordonateCity.getCoordonate();
        // System.out.println("latitude: " + GetCoordonateCity.getCoordonate().getLattitude());
        // System.out.println("longitude: " + GetCoordonateCity.getCoordonate().getLongitude());
        
        System.out.println(CurrentWeather.getWeather(GetCoordonateCity.getCoordonate().getLattitude(), GetCoordonateCity.getCoordonate().getLongitude()));
    }
}
