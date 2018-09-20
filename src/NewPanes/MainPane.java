package NewPanes;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import mainClasses.FiveDaysWeather;
import mainClasses.OtherMethods;

public class MainPane extends BorderPane {

    private WeatherPane homeTown;
    private  VBox leftWeatherPane;
    private WeatherPane destinationTown;
    private VBox rightWeatherPane;
    public CentralPane centralGrid;
    private TitledPane tab1;
    private TitledPane tab2;
    private TitledPane tab3;
    Accordion accordion;


    public MainPane(String homeCity, FiveDaysWeather homeCityWeather, String destinationCity, FiveDaysWeather
            destinationCityWeather) {
        this.setPrefSize(1200, 900);
        this.setId("mainPane_style");

        this.homeTown = new WeatherPane(homeCity, homeCityWeather, homeCityWeather.getWeatherForThreeHours(0).getDateAndTime());
        this.homeTown.setIdForLabelCity("leftPaneCity_style");
        this.leftWeatherPane = new VBox(homeTown.getWeatherData());
        this.leftWeatherPane.getStyleClass().add("mainPane_style");
        this.destinationTown = new WeatherPane(destinationCity, destinationCityWeather, destinationCityWeather.getWeatherForThreeHours(0).getDateAndTime());
        this.destinationTown.setIdForLabelCity("rightPaneCity_style");
        this.rightWeatherPane = new VBox(destinationTown.getWeatherData());
        this.rightWeatherPane.getStyleClass().add("mainPane_style");
        this.setLeft(leftWeatherPane);
        this.setRight(rightWeatherPane);

        this.centralGrid = new CentralPane();
        this.centralGrid.setId("mainCentralPane_style");
        this.setCenter(centralGrid.getMainContainer());

        this.tab1 = new TitledPane("Temperatura", new OtherMethods().addChart("Temperatura", homeCity,
                destinationCity, homeCityWeather, destinationCityWeather, homeTown, destinationTown, this, centralGrid));
        this.tab2 = new TitledPane("Zachmurzenie", new OtherMethods().addChart("Zachmurzenie", homeCity,
                destinationCity, homeCityWeather, destinationCityWeather, homeTown, destinationTown, this, centralGrid));
        this.tab3 = new TitledPane("Wiatr", new OtherMethods().addChart("Wiatr", homeCity, destinationCity,
                homeCityWeather, destinationCityWeather, homeTown, destinationTown, this, centralGrid));
        this.accordion = new Accordion();
        this.accordion.getPanes().addAll(tab1, tab2, tab3);
        this.setTop(accordion);
        this.accordion.expandedPaneProperty().addListener((property, oldPane, newPane) -> {
            if (newPane != null) {
                centralGrid.setHintForUser("Możesz wyświetlić szczegółową pogodę po kliknięciu na jeden z" +
                        " węzłów wykresu");
            } else {
                centralGrid.setHintForUser("Rozwiń jedną z powyższych zakładek aby zobaczyć pogodę na kolejne dni.");
            }
        });
    }














}
