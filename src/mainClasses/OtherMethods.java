package mainClasses;

import NewPanes.CentralPane;
import NewPanes.WeatherPane;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
                                      WeatherPane hPane, WeatherPane dPane, BorderPane bPane, CentralPane centralPane){
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
        centralPane.setHintForUser("Zwiń zakładkę aby zobaczyć wszystkie szczegóły prognozy pogody.");
        centralPane.setImageInCentralPane(Float.parseFloat(hPane.getTemp()), Integer.parseInt(hPane.getClouds()),
                Float.parseFloat(hPane.getWindSpeed()), Float.parseFloat(dPane.getTemp()), Integer.parseInt(dPane
                        .getClouds()), Float.parseFloat(dPane.getWindSpeed()));

    }

    public String weatherDescriptor(int weatherId){
        switch (weatherId){
            case 200: return "burza z lekkimi opadami deszczu";
            case 201: return "burza z opadami deszczu";
            case 202: return "burza z silnymi opadami deszczu";
            case 210: return "łagodna burza";
            case 211: return "burza";
            case 212: return "silna burza";
            case 221: return "nierówna burza";
            case 230: return "burza z lekką mżawką";
            case 231: return "burza z mżawką";
            case 232: return "burza z silną mżawką";
            case 300: return "lekka intensywna mżawka";
            case 301: return "mżawka";
            case 302: return "cieżka intensywna mżawka";
            case 310: return "lekki intensywny mżący deszcz";
            case 311: return "mżący deszcz";
            case 312: return "ciężki intensywny mżący deszcz";
            case 313: return "przelotne opady deszczu z mżawką";
            case 314: return "silne przelotne opady deszczu z mżawką";
            case 321: return "przelotna mżawka";
            case 500: return "lekki deszcz";
            case 501: return "umiarkowane opady";
            case 502: return "silne intensywne opady deszczu";
            case 503: return "bardzo silne opady deszczu";
            case 504: return "ekstremalne opady deszczu";
            case 511: return "marznący deszcz";
            case 520: return "lekkie intensywne przelotne opady deszczu";
            case 521: return "przelotne opady deszczu";
            case 522: return "silne intensywne przelotne opady deszczu";
            case 531: return "nierówne przelotne opady deszczu";
            case 600: return "lekki opady śniegu";
            case 601: return "opady śniegu";
            case 602: return "silne opady śniegu";
            case 611: return "opady ";
            case 612: return "przelotne opady deszczu ze śniegiem";
            case 615: return "lekkie opady deszczu ze śniegiem";
            case 616: return "opady deszczu ze śniegiem";
            case 620: return "lekkie przelotne opady śniegu";
            case 621: return "przelotne opady śniegu";
            case 622: return "silne przelotne opady śniegu";
            case 701: return "lekka mgiełka";
            case 711: return "dym";
            case 721: return "zmętnienie powietrza";
            case 731: return "wiry kurzu i piasku";
            case 741: return "mgła";
            case 751: return "piasek";
            case 761: return "pył i kurz";
            case 762: return "pył wulkaniczny";
            case 771: return "szkwał";
            case 781: return "tornado";
            case 800: return "czyste niebo";
            case 801: return "nieliczne chmury";
            case 802: return "rozproszone chmury";
            case 803: return "przewaga chmur";
            case 804: return "niebo całościowo zachmurzone";
            default: return "apokalipsa Zombie";
        }
    }


}
