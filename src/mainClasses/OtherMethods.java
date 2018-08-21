package mainClasses;

public class OtherMethods {

    public static boolean splitAndCheckWeatherData (WeatherQueryResult weatherData) {
        String weatherDataS = weatherData.getWeatherData();
        return (!weatherDataS.regionMatches(0, "Error", 0, 5));
    }

}
