package org.quotesms;

public class App {
    public static void main(String[] args) {
        try {
            // Votre code existant
            QuoteGenerator.getQuote();
            System.out.println("quote texte: " + QuoteGenerator.getQuote().getQuoteText());
            System.out.println("quote auteur: " + QuoteGenerator.getQuote().getQuoteAuthor());
            GetCoordonateCity.getCoordonate();

            CurrentWeather.Weather weather = CurrentWeather.getWeather(GetCoordonateCity.getCoordonate().getLatitude(), GetCoordonateCity.getCoordonate().getLongitude());

            if (weather != null) {
                String temp = weather.getDegree();
                String description = weather.getDescription();

                System.out.println(String.format("Current temperature: %s°C", temp));
                System.out.println("Weather: " + description);
            } else {
                System.err.println("Failed to retrieve weather data.");
            }
            MessageSender.sender();
        } catch (Exception e) {
            // Gérer l'exception ici
            e.printStackTrace();
        }
    }
}
