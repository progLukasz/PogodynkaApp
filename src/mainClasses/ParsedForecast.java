package mainClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParsedForecast {

    private static long dt;
    private static String dateAndTime;
    // main
    private static float temperature;
    private static float tempMin;
    private static float tempMax;
    private static float pressure;
    private static float pressureSeaLevel;
    private static float pressureGroundLevel;
    private static int humidity;
    private static float tempDifference;

    // weather
    private static String weatherId;
     private static String main;
     private static String mainDescription;
     private static String weatherIcon;

    //clouds
    private static int clouds;

    // wind
    private static float windSpeed;
    private static float windDegree;

    private static List<String> weatherDataList;

    public ParsedForecast(String weatherData) {

        this.weatherDataList = splitDataIntoList(weatherData, "\"|\\{|\\}|:|,|\\[|\\]");

       // this.dt = Integer.parseInt(weatherSingleDataFetcher(weatherDataList,"dt"));
        this.dt = Long.parseLong(weatherData.substring(0,10));

        this.dateAndTime = weatherSingleDataFetcher(weatherDataList, "dt_txt");
        this.temperature = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp"));
        this.tempMin = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp_min"));
        this.tempMax = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp_max"));
        this.pressure = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "pressure"));
        this.pressureSeaLevel = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "sea_level"));
        this.pressureGroundLevel = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "grnd_level"));
        this.humidity = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "humidity"));
        this.tempDifference = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp_kf"));
        this.weatherId = weatherSingleDataFetcher(weatherDataList, "id");
        this.main = weatherSingleDataFetcher(weatherDataList,"main");
        this.mainDescription = weatherSingleDataFetcher(weatherDataList,"description");
        this.weatherIcon = weatherSingleDataFetcher(weatherDataList,"icon");
        this.clouds = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "all"));
        this.windSpeed = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "speed"));
        this.windDegree = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "deg"));
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

    public static long getDt() {
        return dt;
    }

    public static String getDateAndTime() {
        return dateAndTime;
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

    public static float getPressure() {
        return pressure;
    }

    public static float getPressureSeaLevel() {
        return pressureSeaLevel;
    }

    public static float getPressureGroundLevel() {
        return pressureGroundLevel;
    }

    public static int getHumidity() {
        return humidity;
    }

    public static float getTempDifference() {
        return tempDifference;
    }

    public static String getWeatherId() {
        return weatherId;
    }

    public static String getMain() {
        return main;
    }

    public static String getMainDescription() {
        return mainDescription;
    }

    public static String getWeatherIcon() {
        return weatherIcon;
    }

    public static int getClouds() {
        return clouds;
    }

    public static float getWindSpeed() {
        return windSpeed;
    }

    public static float getWindDegree() {
        return windDegree;
    }

}
