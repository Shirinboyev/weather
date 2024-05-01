package uz.pdp.lesson;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Weather {
    private String city;
    private String country;
    @SerializedName("temp_c")
    private double tempC;
    private String condition;
    @SerializedName("wind_kph")
    private Double windKph;
    @SerializedName("last_updated")
    private String lastUpdated;

    public Weather(String city, String country, double temperature, String condition,Double windKph,String lastUpdated) {
        this.city = city;
        this.country = country;
        this.tempC = temperature;
        this.condition = condition;
        this.windKph = windKph;
        this.lastUpdated = lastUpdated;
    }

}
