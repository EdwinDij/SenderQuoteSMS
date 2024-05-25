package org.quotesms;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.URL;
import java.util.Scanner;
// import io.github.cdimascio.dotenv.Dotenv;

public class GetCoordonateCity {
    
    public static class Coordonate {
        private String latitude;
        private String longitude;

        public Coordonate(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getLatitude(){
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }
    }
    public static Coordonate getCoordonate(){
        // Dotenv dotenv = Dotenv.load();
        String city = System.getenv("CITY");
        // System.out.println(city);

        Coordonate coordonate = null;

        try {
            String encodedCity = URLEncoder.encode(city, "UTF-8");
            String apiUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + encodedCity;
            URL url = new URL(apiUrl);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder json = new StringBuilder();

            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }
            scanner.close();
            String jsonString = json.toString();

            int latIndex = jsonString.indexOf("\"lat\":");
            int lonIndex = jsonString.indexOf("\"lon\":");

            String latitude = jsonString.substring(latIndex + 6, jsonString.indexOf(",", latIndex));
            String longitude = jsonString.substring(lonIndex + 6, jsonString.indexOf(",", lonIndex));
            // System.out.println("Latitude de " + city + " : " + latitude);
            // System.out.println("Longitude de " + city + " : " + longitude);

            coordonate = new Coordonate(latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coordonate;
    }
}
