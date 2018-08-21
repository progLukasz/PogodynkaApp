package mainClasses;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherQueryResult {

    private static String weatherData;

    final static String url1 = "https://api.openweathermap.org/data/2.5/forecast?q=";
    final static String url2 = "&appid=";
    final static String url3 = "&units=metric";

    final String city;

    public WeatherQueryResult(String cityName, String apiID) {
        this.city = cityName;
        this.weatherData = this.getUrlContents(this.createUrlAddress(cityName, apiID));
    }

    private static String createUrlAddress(String cityName, String apiID) {
        String url = url1 + cityName + url2 + apiID + url3;
        return url;
    }

    private static String getUrlContents(String url) throws RuntimeException {
        StringBuilder content = new StringBuilder();

        try{
            URL urlNew = new URL(url);

            URLConnection urlConnection = urlNew.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();

       } catch (Exception e) {
            content.append("Error:" + e);
       }
        return content.toString();
    }

    public static String getWeatherData() { return weatherData; }

    public String getCity() {
        return city;
    }
}