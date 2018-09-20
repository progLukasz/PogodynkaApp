package mainClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParsedForecast {

    private long dt;
    private String dateAndTime;
    private String date;
    private String time;
    // main
    private float temperature;
    private float tempMin;
    private float tempMax;
    private float pressure;
    private float pressureSeaLevel;
    private float pressureGroundLevel;
    private int humidity;
    private float tempDifference;
    // weather
    private int weatherId;
    private String main;
    private String mainDescription;
    private String weatherIcon;
    //clouds
    private int clouds;
    // wind
    private float windSpeed;
    private float windDegree;


    private float latitude;
    private float longitude;

    private List<String> weatherDataList;

    public ParsedForecast(String weatherData) {

        this.weatherDataList = splitDataIntoList(weatherData, "\"|\\{|\\}|:|,|\\[|\\]");
       // this.dt = Integer.parseInt(weatherSingleDataFetcher(weatherDataList,"dt"));
        this.dt = Long.parseLong(weatherData.substring(0,10));
        this.dateAndTime = weatherSingleDataFetcher(weatherDataList, "dt_txt");
        this.date = dateAndTime.substring(0,10);
        this.time = dateAndTime.substring(11,13);
        this.time += ":00";
        this.temperature = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp"));
        this.tempMin = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp_min"));
        this.tempMax = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp_max"));
        this.pressure = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "pressure"));
        this.pressureSeaLevel = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "sea_level"));
        this.pressureGroundLevel = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "grnd_level"));
        this.humidity = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "humidity"));
        this.tempDifference = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "temp_kf"));
        this.weatherId = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "id"));
        this.main = weatherSingleDataFetcher(weatherDataList,"main");
        this.mainDescription = weatherSingleDataFetcher(weatherDataList,"description");
        this.weatherIcon = weatherSingleDataFetcher(weatherDataList,"icon");
        this.clouds = Integer.parseInt(weatherSingleDataFetcher(weatherDataList, "all"));
        this.windSpeed = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "speed"));
        this.windDegree = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "deg"));
        this.latitude = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "lat"));
        this.longitude = Float.parseFloat(weatherSingleDataFetcher(weatherDataList, "lon"));
    }


    protected List<String> splitDataIntoList (String data, String delims){

        String[] rawSplitData = data.split(delims);
        List<String> clearedWeatherData = new ArrayList<String>(Arrays.asList(rawSplitData));
        clearedWeatherData.removeAll(Collections.singleton(""));

        return clearedWeatherData;
    }

    protected <T> T weatherSingleDataFetcher (List<T> list, String keyword){
        int index = list.indexOf(keyword);
        T data = list.get(index + 1);

        return data;
    }

    public long getDt() {
        return dt;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public float getPressure() {
        return pressure;
    }

    public float getPressureSeaLevel() {
        return pressureSeaLevel;
    }

    public float getPressureGroundLevel() {
        return pressureGroundLevel;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getTempDifference() {
        return tempDifference;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public String getMain() {
        return main;
    }

    public String getMainDescription() {
        return mainDescription;
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

    public float getLatitude() { return latitude; }

    public float getLongitude() {
        return longitude;
    }
}
