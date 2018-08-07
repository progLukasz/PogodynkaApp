package mainClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class ParsedForecast {

    // weather
    private static int id;
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
    private static float speed;
    private static int degree;

    //clouds
    private static int clouds;

    private static String tokens[];

    public ParsedForecast(String weatherData) {
        this.tokens = splitDataWithDelimeters(weatherData, "\"|\\{|\\}|:|,|\\[|\\]");
    }

    private static String[] splitDataWithDelimeters (String data, String delims){

//        Pattern pattern = Pattern.compile(Pattern.quote(delims));
//        String[] newData = pattern.split(data);
        String[] rawSplitData = data.split(delims);
        List<String> clearedWeatherData = new ArrayList<String>(Arrays.asList(rawSplitData));

        clearedWeatherData.removeAll(Collections.singleton(null));
        for (String d : clearedWeatherData) {
            System.out.println(d + System.lineSeparator());
        }
        return tokens;
    }

    public static int getId() {
        return id;
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

    public static float getSpeed() {
        return speed;
    }

    public static int getDegree() {
        return degree;
    }

    public static int getClouds() {
        return clouds;
    }
}
