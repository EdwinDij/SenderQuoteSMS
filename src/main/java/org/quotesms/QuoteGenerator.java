package org.quotesms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.XML;


public class QuoteGenerator {
    public static class Quote {
        private String quoteText;
        private String quoteAuthor;

        public Quote(String quoteText, String quoteAuthor) {
            this.quoteText = quoteText;
            this.quoteAuthor = quoteAuthor;
        }

        public String getQuoteText() {
            return quoteText;
        }

        public String getQuoteAuthor() {
            return quoteAuthor;
        }
    }

    public static Quote getQuote(){
    String quoteText = "";
    String quoteAuthor = "";
    try {
    String url = "http://api.forismatic.com/api/1.0/?method=getQuote&key=457653&format=xml&lang=en";
    URL obj = new URL(url);
    HttpURLConnection connection =(HttpURLConnection) obj.openConnection();
    
    //j'établie la connexion
    connection.setRequestMethod("GET");
    connection.setRequestProperty("User-Agent", "Mozilla/5.0");
    
    //réponse de l'api
    int resStatusCode = connection.getResponseCode();
    // System.out.println("Res code: " + resStatusCode);

    //lire la réponse
    BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = input.readLine()) != null) {
        response.append(inputLine);
    }
    input.close();
    // System.out.println(response.toString());

    String xmlResponse = response.toString();
    JSONObject jsonResponse = XML.toJSONObject(xmlResponse);

    JSONObject quote = jsonResponse.getJSONObject("forismatic").getJSONObject("quote");
    quoteText = quote.getString("quoteText");
    quoteAuthor = quote.getString("quoteAuthor");

    } catch(Exception e) {
    e.printStackTrace();
    }
    return new Quote(quoteText, quoteAuthor);
}
}
