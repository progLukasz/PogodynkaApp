package mainClasses;

public class FiveDaysWeather {

    public static ParsedForecast[] weatherForThreeHours = new ParsedForecast[40];

    public FiveDaysWeather(String city) {
        WeatherQueryResult newForecast = new WeatherQueryResult(city, "6a9f0069bab2b2553d52eab3c86b66f4");
        String weatherData = newForecast.getWeatherData();
        String threeHoursForecasts[] = weatherData.split("dt\":");

        for (int i = 0; i < 40; i++) weatherForThreeHours[i] = new ParsedForecast(threeHoursForecasts[i + 1]);
    }

    public static ParsedForecast getWeatherForThreeHours(int index) {
        return weatherForThreeHours[index];
    }
}
