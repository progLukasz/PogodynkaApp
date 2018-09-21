package mainClasses;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class WeatherQueryResult {

    private String weatherData;

    private final static String url1 = "https://api.openweathermap.org/data/2.5/forecast?q=";
    private final static String url2 = "&appid=";
    private final static String url3 = "&units=metric";

    WeatherQueryResult(String cityName, String apiID) {
        this.weatherData = this.getUrlContents(this.createUrlAddress(cityName, apiID));
    }

    private String createUrlAddress(String cityName, String apiID) {
        return url1 + cityName + url2 + apiID + url3;
    }

    private String getUrlContents(String url) throws RuntimeException {
        StringBuilder content = new StringBuilder();

        try{
            URL urlNew = new URL(url);

            URLConnection urlConnection = urlNew.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();

       } catch (Exception e) {
            content.append("Error:").append(e);
       }
        return content.toString();
    }

    String getWeatherData() { return weatherData; }
}