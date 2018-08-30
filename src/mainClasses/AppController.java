package mainClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class AppController {


    @FXML
    private TilePane topTilePane;
    @FXML
    private TilePane bottomTilePane;

    @FXML
    private Label homeLabelCity;
    @FXML
    private Label homeLabelDate;
    @FXML
    private Label homeLabelTime;
    @FXML
    private ImageView homeIVWeather;
    @FXML
    private Label homeLabelWeather;
    @FXML
    private Label homeLabelClouds;
    @FXML
    private Label homeLabelRain;
    @FXML
    private Label homeLabelWind;
    @FXML
    private Label destinationLabelCity;
    @FXML
    private Label destinationLabelDate;
    @FXML
    private Label destinationLabelTime;
    @FXML
    private ImageView destinationIVWeather;
    @FXML
    private Label destinationLabelWeather;
    @FXML
    private Label destinationLabelClouds;
    @FXML
    private Label destinationLabelRain;
    @FXML
    private Label destinationLabelWind;

    @FXML
    public void displayWeatherData(ActionEvent actionEvent) {

        homeLabelCity.setText("Miasto: test");
        homeLabelDate.setText("Data:  test");
        homeLabelTime.setText("");
        Image homeWeatherImage = new Image("http://openweathermap.org/img/w/10d.png");
        homeIVWeather.setImage(homeWeatherImage);
        homeLabelWeather.setText("");
        homeLabelClouds.setText("duzo chmur");
        homeLabelRain.setText("");
        homeLabelWind.setText("");

        destinationLabelCity.setText("Miasto: test");
        destinationLabelDate.setText("Data:  test");
        destinationLabelTime.setText("");
        Image destinationWeatherImage = new Image("http://openweathermap.org/img/w/10d.png");
        destinationIVWeather.setImage(destinationWeatherImage);
        destinationLabelWeather.setText("");
        destinationLabelClouds.setText("duzo chmur");
        destinationLabelRain.setText("");
        destinationLabelWind.setText("");
    }


    public void fillTilePanesWithData(ActionEvent actionEvent) {
        VBox[] weatherTileMatrix = new VBox[40];
        for (int i = 0; i < 40; i++) {
            weatherTileMatrix[i] = OtherMethods.createWeatherTile("", "2018-08-29", "12:00");
            topTilePane.getChildren().add(weatherTileMatrix[i]);
        }

        // Uporządokować te funkcje
        FiveDaysWeather weatherForMyTown;

        WeatherQueryResult weatherHere = new WeatherQueryResult("Leszno", "6a9f0069bab2b2553d52eab3c86b66f4");
        if (OtherMethods.splitAndCheckWeatherData(weatherHere)) {
            FiveDaysWeather weatherForHomeTown = new FiveDaysWeather(weatherHere.getWeatherData());
            System.out.println(weatherForHomeTown.getWeatherForThreeHours(1).getTime());
            System.out.println(weatherForHomeTown.getWeatherForThreeHours(1).getDate());
            weatherForMyTown = weatherForHomeTown;

        } else {
            System.out.println("NOK");

        }
    }
}

