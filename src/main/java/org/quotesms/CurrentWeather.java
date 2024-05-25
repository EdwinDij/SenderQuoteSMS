package org.quotesms;

import org.json.JSONObject;
import org.quotesms.GetCoordonateCity;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.URL;
import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;

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
    public static Weather getWeather(String lat, String lon){
        Dotenv dotenv = Dotenv.load();
        String apikey = dotenv.get("API_KEY");

        Weather weather = null;

        try {
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + URLEncoder.encode(lat, "UTF-8")
            + "&lon=" + URLEncoder.encode(lon, "UTF-8") + "&appid=" + URLEncoder.encode(apikey, "UTF-8") + "&units=metric&lang=fr";
            URL url = new URL(apiUrl);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder json = new StringBuilder();

            while(scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }
            scanner.close();
            String jsonString = json.toString();

             // Parsing JSON
            JSONObject jsonObject = new JSONObject(jsonString);
            double temp = jsonObject.getJSONObject("main").getDouble("temp");
            String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

            weather = new Weather(String.valueOf(temp), description);
            System.out.println(String.format("Current temperature: %s°C, Weather: %s", temp, description));

        } catch(IOException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
