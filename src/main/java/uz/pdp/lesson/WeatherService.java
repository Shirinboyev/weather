package uz.pdp.lesson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    private static final String API_KEY = "7bf38058d63444f8849144947242904";

    public Weather getWeather(String city) {
        try {
            String apiUrl = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + city;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            JSONObject location = json.getJSONObject("location");
            JSONObject current = json.getJSONObject("current");

            String cityName = location.getString("name");
            String country = location.getString("country");
            double tempC = current.getDouble("temp_c");
            double windKph = current.getDouble("wind");
            String lastUpdateTime = current.getString("last_updated");
            String condition = current.getJSONObject("condition").getString("text");

            return new Weather(cityName, country, tempC,condition,windKph,lastUpdateTime);
        } catch (IOException e) {
            System.out.println("Xatolik: " + e.getMessage());
            return null;
        }
    }
}
