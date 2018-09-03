package mainClasses;


public class FiveDaysWeather {

    private ParsedForecast[] weatherForThreeHours = new ParsedForecast[40];

    public FiveDaysWeather(String weatherData ) {

        String threeHoursForecasts[] = weatherData.split("dt\":");

        for (int i = 0; i < threeHoursForecasts.length - 1; i++) {
            weatherForThreeHours[i] = new ParsedForecast(threeHoursForecasts[i + 1]);
         }
    }

    public int getWeatherArrayCount(){
        return weatherForThreeHours.length;
    }

    public ParsedForecast getWeatherForThreeHours(int index) { return weatherForThreeHours[index]; }
}
