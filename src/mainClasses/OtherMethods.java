package mainClasses;

import NewPanes.CentralPane;
import NewPanes.WeatherPane;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class OtherMethods {

    public AreaChart addChart(String displayedData, String cityHome, String cityDest, FiveDaysWeather
            weatherDataHome, FiveDaysWeather weatherDataDest, WeatherPane leftPane, WeatherPane rightPane, BorderPane
                                      mainPane, CentralPane centralPane) {
        Float tempArrayHome[] = new Float[weatherDataHome.getWeatherArrayCount()];
        Float tempArrayDest[] = new Float[weatherDataHome.getWeatherArrayCount()];
        String units = "";

        switch (displayedData) {
            case "Temperatura":
                for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
                    tempArrayHome[i] = weatherDataHome.getWeatherForThreeHours(i).getTemperature();
                    tempArrayDest[i] = weatherDataDest.getWeatherForThreeHours(i).getTemperature();
                    units = " [°C]";
                }
                break;
            case "Zachmurzenie":
                for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
                    tempArrayHome[i] = (float) weatherDataHome.getWeatherForThreeHours(i).getClouds();
                    tempArrayDest[i] = (float) weatherDataDest.getWeatherForThreeHours(i).getClouds();
                    units = " [%]";
                }
                break;
            case "Wiatr":
                for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
                    tempArrayHome[i] = weatherDataHome.getWeatherForThreeHours(i).getWindSpeed();
                    tempArrayDest[i] = weatherDataDest.getWeatherForThreeHours(i).getWindSpeed();
                    units = " [/sek]";
                }
                break;
        }
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        AreaChart tempChart = new AreaChart<String, Number>(xAxis, yAxis);
        tempChart.setTitle(displayedData + units);
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
                            dataHomeCity, dataDestCity, leftPane, rightPane, mainPane, centralPane));
            dataDestCity.getNode().setOnMouseClicked(e ->
                    updateWeatherDetails(cityHome, cityDest, weatherDataHome, weatherDataDest,
                            dataHomeCity, dataDestCity, leftPane, rightPane, mainPane, centralPane));
        }
        tempChart.applyCss();
        tempChart.setLegendVisible(false);
        return tempChart;
    }

    private void updateWeatherDetails(String hCity, String dCity, FiveDaysWeather hWeather, FiveDaysWeather
            dWeather, XYChart.Data<String, Number> hDate, XYChart.Data<String, Number> dDate,
                                      WeatherPane hPane, WeatherPane dPane, BorderPane bPane, CentralPane centralPane) {
        hPane.updateWeatherData(hCity, hWeather, hDate.getXValue());
        hPane.setIdForLabelCity("leftPaneCity_style");
        dPane.updateWeatherData(dCity, dWeather, dDate.getXValue());
        dPane.setIdForLabelCity("rightPaneCity_style");
        VBox homePane = new VBox(hPane.getWeatherData());
        VBox destPane = new VBox(dPane.getWeatherData());
        bPane.setLeft(homePane);
        bPane.setRight(destPane);
        homePane.getStyleClass().add("mainPane_style");
        destPane.getStyleClass().add("mainPane_style");
        centralPane.setHintForUser("Zwiń zakładkę aby zobaczyć szczegółową pogodę i wynik porównania pogody");
        centralPane.calculateWeatherScore(Float.parseFloat(hPane.getTemp()), Integer.parseInt(hPane.getClouds()),
                Float.parseFloat(hPane.getWindSpeed()), Float.parseFloat(dPane.getTemp()),
                Integer.parseInt(dPane.getClouds()), Float.parseFloat(dPane.getWindSpeed()));
    }


    public String weatherDescriptor(int weatherId) {
        try {
            FileInputStream input = new FileInputStream(new File("txt/weatherDescriptions.txt"));
            CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
            InputStreamReader reader = new InputStreamReader(input, decoder);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            int lineNr = 0;
            while ((weatherId -1) != lineNr) {
                line = bufferedReader.readLine();
                lineNr++;
            }
            bufferedReader.close();
            return line;

        } catch (Exception e) {
            return "Błąd. Nie znaleziono pliku.";
        }
    }
}
