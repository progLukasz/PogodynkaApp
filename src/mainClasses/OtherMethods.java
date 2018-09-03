package mainClasses;

import javafx.scene.chart.*;

public class OtherMethods {

    public boolean splitAndCheckWeatherData (WeatherQueryResult weatherData) {
        String weatherDataS = weatherData.getWeatherData();
        return (!weatherDataS.regionMatches(0, "Error", 0, 5));
    }

    public AreaChart addTemperatureChart(String cityHome, String cityDest, FiveDaysWeather
            weatherDataHome, FiveDaysWeather weatherDataDest) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        AreaChart tempChart = new AreaChart<String,Number>(xAxis, yAxis);
        tempChart.setTitle("Temperatura");
        XYChart.Series seriesTempHome = new XYChart.Series();
        XYChart.Series seriesTempDest = new XYChart.Series();
        seriesTempHome.setName(cityHome);
        seriesTempDest.setName(cityDest);
        tempChart.getData().addAll(seriesTempHome, seriesTempDest);
        for (int i = 0; i < weatherDataHome.getWeatherArrayCount(); i++) {
            XYChart.Data<String, Number> data1 = new XYChart.Data<>(weatherDataHome.getWeatherForThreeHours
                    (i).getDateAndTime(), weatherDataHome.getWeatherForThreeHours(i).getTemperature());
            seriesTempHome.getData().add(data1);

            data1.getNode().setOnMouseClicked(e ->
                    System.out.printf("Click on data [%s, %b]%n", data1.getXValue(), data1.getYValue()));
            seriesTempDest.getData().add(new XYChart.Data<>(weatherDataDest.getWeatherForThreeHours
                    (i).getDateAndTime(), weatherDataDest.getWeatherForThreeHours(i).getTemperature()));
        }
        return tempChart;
    }

    public AreaChart addCloudsChart(String cityHome, String cityDest, FiveDaysWeather
            weatherDataHome, FiveDaysWeather weatherDataDest) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        AreaChart tempChart = new AreaChart<String,Number>(xAxis, yAxis);
        tempChart.setTitle("Zachmurzenie");
        tempChart.setStyle("-fx-background-radius: 30px;");
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

}
