package mainClasses;


public class FiveDaysWeather {

    private ParsedForecast[] weatherForThreeHours = new ParsedForecast[40];
    private int weatherArrayLength;


    public FiveDaysWeather(String weatherData ) {

        String threeHoursForecasts[] = weatherData.split("dt\":");
        this.weatherArrayLength =  threeHoursForecasts.length - 1;
        for (int i = 0; i < weatherArrayLength; i++) {
            weatherForThreeHours[i] = new ParsedForecast(threeHoursForecasts[i + 1]);
         }
    }

    public int getWeatherArrayCount(){
        return weatherArrayLength;
    }

    public ParsedForecast getWeatherForThreeHours(int index) { return weatherForThreeHours[index]; }
}
