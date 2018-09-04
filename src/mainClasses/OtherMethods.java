package mainClasses;

import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class OtherMethods {

    private String selectedDateToBeDisplayed;
    private boolean dataChangeRequest;

    public boolean splitAndCheckWeatherData (WeatherQueryResult weatherData) {
        String weatherDataS = weatherData.getWeatherData();
        return (!weatherDataS.regionMatches(0, "Error", 0, 5));
    }

    public AreaChart addTemperatureChart(String cityHome, String cityDest, FiveDaysWeather
            weatherDataHome, FiveDaysWeather weatherDataDest, WeatherPane leftPane, WeatherPane rightPane,  BorderPane
            mainPane) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        AreaChart tempChart = new AreaChart<String,Number>(xAxis, yAxis);
        tempChart.setTitle("Temperatura");
       // tempChart.setStyle("-fx-background-color: #ff0000;");
        XYChart.Series seriesTempHome = new XYChart.Series();
        XYChart.Series seriesTempDest = new XYChart.Series();
        seriesTempHome.setName(cityHome);
        seriesTempDest.setName(cityDest);
        tempChart.getData().addAll(seriesTempHome, seriesTempDest);
        for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
            XYChart.Data<String, Number> dataHomeCity = new XYChart.Data<>(weatherDataHome.getWeatherForThreeHours
                    (i).getDateAndTime(), weatherDataHome.getWeatherForThreeHours(i).getTemperature());
            seriesTempHome.getData().add(dataHomeCity);
            dataHomeCity.getNode().setOnMouseClicked(e ->
                            updateWeatherDetailsLeft(cityHome, weatherDataHome, dataHomeCity, leftPane, mainPane));
            XYChart.Data<String, Number> dataDestCity = new XYChart.Data<>(weatherDataDest.getWeatherForThreeHours
                    (i).getDateAndTime(), weatherDataDest.getWeatherForThreeHours(i).getTemperature());
            seriesTempDest.getData().add(dataDestCity);
            dataDestCity.getNode().setOnMouseClicked(e ->
                    updateWeatherDetailsRight(cityHome, weatherDataHome, dataHomeCity, rightPane, mainPane));
        }
        return tempChart;
    }

    public AreaChart addCloudsChart(String cityHome, String cityDest, FiveDaysWeather
            weatherDataHome, FiveDaysWeather weatherDataDest) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        AreaChart tempChart = new AreaChart<String,Number>(xAxis, yAxis);
        tempChart.setTitle("Zachmurzenie");
        XYChart.Series seriesTempHome = new XYChart.Series();
        XYChart.Series seriesTempDest = new XYChart.Series();
        seriesTempHome.setName(cityHome);
        seriesTempDest.setName(cityDest);
        for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
            seriesTempHome.getData().add(new XYChart.Data<>(weatherDataHome.getWeatherForThreeHours(i)
                    .getDateAndTime(), weatherDataHome.getWeatherForThreeHours(i).getClouds()));
            seriesTempDest.getData().add(new XYChart.Data<>(weatherDataDest.getWeatherForThreeHours(i).getDateAndTime()
                    , weatherDataDest.getWeatherForThreeHours(i).getClouds()));
        }
        tempChart.getData().addAll(seriesTempHome, seriesTempDest);
        return tempChart;
    }

    public AreaChart addTWindChart(String cityHome, String cityDest, FiveDaysWeather
            weatherDataHome, FiveDaysWeather weatherDataDest) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        AreaChart tempChart = new AreaChart<String,Number>(xAxis, yAxis);
        tempChart.setTitle("Wiatr");
        XYChart.Series seriesTempHome = new XYChart.Series();
        XYChart.Series seriesTempDest = new XYChart.Series();
        seriesTempHome.setName(cityHome);
        seriesTempDest.setName(cityDest);
        for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
            seriesTempHome.getData().add(new XYChart.Data(weatherDataHome.getWeatherForThreeHours(i)
                    .getDateAndTime(), weatherDataHome.getWeatherForThreeHours(i).getWindSpeed()));
            seriesTempDest.getData().add(new XYChart.Data(weatherDataDest.getWeatherForThreeHours(i).getDateAndTime()
                    , weatherDataDest.getWeatherForThreeHours(i).getWindSpeed()));
        }
        tempChart.getData().addAll(seriesTempHome, seriesTempDest);
        return tempChart;
    }

    public void updateWeatherDetailsLeft(String city, FiveDaysWeather weather, XYChart.Data<String, Number> date,
                                      WeatherPane pane, BorderPane bPane){
        pane.updateWeatherData(city, weather, date.getXValue());
        bPane.setLeft(pane.getWeatherData());
    }
    public void updateWeatherDetailsRight(String city, FiveDaysWeather weather, XYChart.Data<String, Number> date,
                                         WeatherPane pane, BorderPane bPane){
        pane.updateWeatherData(city, weather, date.getXValue());
        bPane.setRight(pane.getWeatherData());
    }

    public String selectedDateToBeDisplayed() {
        return selectedDateToBeDisplayed;
    }
}
