package org.quotesms;

import org.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;

public class CurrentWeather {
    
    public static class Weather {
        private String degree;
        private String description;

        public Weather(String degree, String description){
            this.degree = degree;
            this.description = description;
        }

        public String getDegree(){
            return degree;
        }

        public String getDescription(){
            return description;
        }
    }
    public static Weather getWeather(String lat, String lon) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY").trim();
    
        Weather weather = null;
    
        try {
            // Remove quotes from lat and lon
            lat = lat.replaceAll("\"", "");
            lon = lon.replaceAll("\"", "");
    
            String apiUrl = String.format("https://api.openweathermap.org/data/3.0/onecall?lat=%s&lon=%s&appid=%s&units=metric&lang=fr",
                                           lat, lon, apiKey);
            // System.out.println("Constructed URL: " + apiUrl); debugg
    
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
    
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                Scanner scanner = new Scanner(inputStream, "UTF-8");
                StringBuilder json = new StringBuilder();
    
                while (scanner.hasNextLine()) {
                    json.append(scanner.nextLine());
                }
                scanner.close();
                String jsonString = json.toString();
    
                // Parsing JSON
                JSONObject jsonObject = new JSONObject(jsonString);
                if(jsonObject.has("current")) {
                    JSONObject current = jsonObject.getJSONObject("current");
                    double temp = current.getDouble("temp");
                    JSONArray weatherArray = current.getJSONArray("weather");
                    String description = "";
                    if(weatherArray.length() > 0) {
                        description = weatherArray.getJSONObject(0).getString("description");
                    }
                    weather = new Weather(String.valueOf(temp), description);
                    // System.out.println(String.format("Current temperature: %s°C, Weather: %s", temp, description));
                } else {
                    System.err.println("No 'current' data found in the JSON response.");
                }
            } else {
                System.err.println("HTTP error code: " + responseCode);
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }
}