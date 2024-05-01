package uz.pdp.lesson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;


public class WeatherApp {
        public static void main(String[] args) {
            try {
                while (true) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Shahar nomini kiriting: ");
                    String city = reader.readLine();

                    String apiKey = "7bf38058d63444f8849144947242904";
                    String apiUrl = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;

                    URL url = new URL(apiUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

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
                    double windKph = current.getDouble("wind_kph");
                    String lastUpdate = current.getString("last_updated");
                    String condition = current.getJSONObject("condition").getString("text");
                    System.out.println(new Date());
                    System.out.println(cityName + ", " + country + " da hozirgi ob-havo:");
                    System.out.println("Temperatura: " + tempC + " °C");
                    System.out.println("WindKph: " + windKph);
                    System.out.println("LastUpdate: " + lastUpdate);
                    System.out.println("Holat: " + condition);
                    System.out.println("=".repeat(30));
                }
            } catch (IOException e) {
                System.out.println("Xatolik: " + e.getMessage());
            }
        }

}
