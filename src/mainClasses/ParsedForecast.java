package mainClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParsedForecast {

    // weather
    private static String main;
    private static String mainDescription;
    // main
    private static float temperature;
    private static float tempMin;
    private static float tempMax;
    private static int pressure;
    private static int humidity;
    // visibility
    private static int visibility;
    // wind
    private static float windSpeed;
    private static int windDegree;
    //clouds
    private static int clouds;
    //place
    private static String city;

    private static List<String> weatherDataList;

    public ParsedForecast(String weatherData) {
        this.weatherDataList = splitDataIntoList(weatherData, "\"|\\{|\\}|:|,|\\[|\\]");
        this.main = weatherSingleDataFetcher(weatherDataList,"main");
        this.mainDescription = weatherSingleDataFetcher(weatherDataList,"description");
        this.temperature = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp"));
        this.tempMin = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp_min"));
        this.tempMax = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp_max"));
        this.pressure = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "pressure"));
        this.humidity = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "humidity"));
        this.visibility = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "visibility"));
        this.windSpeed = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "speed"));
        this.windDegree = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "deg"));
        this.clouds = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "all"));
        this.city = weatherSingleDataFetcher(weatherDataList, "name");
    }

    private static List<String> splitDataIntoList (String data, String delims){

        String[] rawSplitData = data.split(delims);
        List<String> clearedWeatherData = new ArrayList<String>(Arrays.asList(rawSplitData));
        clearedWeatherData.removeAll(Collections.singleton(""));

        return clearedWeatherData;
    }

    private static <T> T weatherSingleDataFetcher (List<T> list, String keyword){
        int index = list.indexOf(keyword);
        T data = list.get(index + 1);

        return data;
    }

    public static String getMain() {
        return main;
    }

    public static String getMainDescription() {
        return mainDescription;
    }

    public static float getTemperature() {
        return temperature;
    }

    public static float getTempMin() {
        return tempMin;
    }

    public static float getTempMax() {
        return tempMax;
    }

    public static int getPressure() {
        return pressure;
    }

    public static int getHumidity() {
        return humidity;
    }

    public static int getVisibility() {
        return visibility;
    }

    public static float getWindSpeed() {
        return windSpeed;
    }

    public static int getWindDegree() {
        return windDegree;
    }

    public static int getClouds() {
        return clouds;
    }

    public static String getCity() { return city; }
}
