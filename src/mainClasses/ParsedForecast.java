package mainClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParsedForecast {

    private String dateAndTime;
    private String date;
    private String time;
    private float temperature;
    private float pressure;
    private int humidity;
    private int weatherId;
    private String weatherIcon;
    private int clouds;
    private float windSpeed;
    private float windDegree;

    ParsedForecast(String weatherData) {

        List<String> weatherDataList = splitDataIntoList(weatherData, "\"|\\{|\\}|:|,|\\[|\\]");
        this.dateAndTime = weatherSingleDataFetcher(weatherDataList, "dt_txt");
        this.date = dateAndTime.substring(0,10);
        this.time = dateAndTime.substring(11,13);
        this.time += ":00";
        this.temperature = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp"));
        this.pressure = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "pressure"));
        this.humidity = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "humidity"));
        this.weatherId = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "id"));
        this.weatherIcon = weatherSingleDataFetcher(weatherDataList,"icon");
        this.clouds = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "all"));
        this.windSpeed = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "speed"));
        this.windDegree = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "deg"));
    }

    private List<String> splitDataIntoList (String data, String delims){

        String[] rawSplitData = data.split(delims);
        List<String> clearedWeatherData = new ArrayList<String>(Arrays.asList(rawSplitData));
        clearedWeatherData.removeAll(Collections.singleton(""));

        return clearedWeatherData;
    }

    private <T> T weatherSingleDataFetcher (List<T> list, String keyword){
        int index = list.indexOf(keyword);
        return list.get(index + 1);
    }

    public String getDateAndTime() { return dateAndTime; }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public int getClouds() {
        return clouds;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getWindDegree() {
        return windDegree;
    }
}
