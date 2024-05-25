package org.quotesms;

public class App 
{
    public static void main( String[] args )
    {
        QuoteGenerator.getQuote();
        System.out.println("quote texte: " + QuoteGenerator.getQuote().getQuoteText());
        System.out.println("quote auteur: " + QuoteGenerator.getQuote().getQuoteAuthor());
    }
}
