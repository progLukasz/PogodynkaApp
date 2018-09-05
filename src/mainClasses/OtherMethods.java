package mainClasses;

import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;

public class OtherMethods {


    public boolean splitAndCheckWeatherData (WeatherQueryResult weatherData) {
        String weatherDataS = weatherData.getWeatherData();
        return (!weatherDataS.regionMatches(0, "Error", 0, 5));
    }

    public AreaChart addChart(String displayedData, String cityHome, String cityDest, FiveDaysWeather
            weatherDataHome, FiveDaysWeather weatherDataDest, WeatherPane leftPane, WeatherPane rightPane,  BorderPane
                                      mainPane) {
        Float tempArrayHome[] = new Float[weatherDataHome.getWeatherArrayCount()];
        Float tempArrayDest[] = new Float[weatherDataHome.getWeatherArrayCount()];

        switch (displayedData) {
            case "Temperatura":
                for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
                    tempArrayHome[i] = weatherDataHome.getWeatherForThreeHours(i).getTemperature();
                    tempArrayDest[i] = weatherDataDest.getWeatherForThreeHours(i).getTemperature();
                }
                break;
            case "Zachmurzenie":
                for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
                    tempArrayHome[i] = (float) weatherDataHome.getWeatherForThreeHours(i).getClouds();
                    tempArrayDest[i] = (float) weatherDataDest.getWeatherForThreeHours(i).getClouds();
                }
                break;
            case "Wiatr":
                for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
                    tempArrayHome[i] = weatherDataHome.getWeatherForThreeHours(i).getWindSpeed();
                    tempArrayDest[i] = weatherDataDest.getWeatherForThreeHours(i).getWindSpeed();
                }
                break;
        }
                CategoryAxis xAxis = new CategoryAxis();
                NumberAxis yAxis = new NumberAxis();
                AreaChart tempChart = new AreaChart<String, Number>(xAxis, yAxis);
                tempChart.setTitle(displayedData);
                // tempChart.setStyle("-fx-background-color: #ff0000;");
                XYChart.Series seriesTempHome = new XYChart.Series();
                XYChart.Series seriesTempDest = new XYChart.Series();
                seriesTempHome.setName(cityHome);
                seriesTempDest.setName(cityDest);
                tempChart.getData().addAll(seriesTempHome, seriesTempDest);
                for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
                    XYChart.Data<String, Number> dataHomeCity = new XYChart.Data<>(weatherDataHome.getWeatherForThreeHours
                            (i).getDateAndTime(), tempArrayHome[i]);
                    seriesTempHome.getData().add(dataHomeCity);
                    XYChart.Data<String, Number> dataDestCity = new XYChart.Data<>(weatherDataDest.getWeatherForThreeHours
                            (i).getDateAndTime(), tempArrayDest[i]);
                    seriesTempDest.getData().add(dataDestCity);
                    dataHomeCity.getNode().setOnMouseClicked(e ->
                            updateWeatherDetails(cityHome, cityDest, weatherDataHome, weatherDataDest,
                                    dataHomeCity, dataDestCity, leftPane, rightPane, mainPane));
                    dataDestCity.getNode().setOnMouseClicked(e ->
                            updateWeatherDetails(cityHome, cityDest, weatherDataHome, weatherDataDest,
                                    dataHomeCity, dataDestCity, leftPane, rightPane, mainPane));
                }
                return tempChart;
        }

    public void updateWeatherDetails(String hCity, String dCity, FiveDaysWeather hWeather, FiveDaysWeather
            dWeather, XYChart.Data<String, Number> hDate, XYChart.Data<String, Number> dDate,
                                     WeatherPane hPane, WeatherPane dPane, BorderPane bPane){
        hPane.updateWeatherData(hCity, hWeather, hDate.getXValue());
        dPane.updateWeatherData(dCity, dWeather, dDate.getXValue());
        bPane.setLeft(hPane.getWeatherData());
        bPane.setRight(dPane.getWeatherData());
    }



}
