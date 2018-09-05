package mainClasses;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;

public class WeatherPane extends Pane {

    private Label labelCity;
    private Label labelDate;
    private Label labelTime;
    private ImageView iVWeather;
    private Label labelWeather;
    private Label labelClouds;
    private Label labelHumidity;
    private Label labelTemp;
    private Label labelPressure;
    private Label labelWindSpeed;
    private Float windDirection;
    private ImageView NESW;
    private ImageView windArrow;
    private StackPane windPic;

    private VBox container;

    public WeatherPane(String city, FiveDaysWeather weather, String date) {
        updateWeatherData(city, weather, date);
    }


    public void updateWeatherData (String city, FiveDaysWeather weather, String date){
        int requestedDateIndex = 0;
        for (int i = 0; i < weather.getWeatherArrayCount(); i++){
            String w = weather.getWeatherForThreeHours(i).getDateAndTime();
            if (w == date){
                requestedDateIndex = i;
                break;
            }
        }
        this.labelCity = new Label(city);
        this.labelDate = new Label("Data: " + weather.getWeatherForThreeHours(requestedDateIndex).getDate());
        this.labelTime = new Label("Czas: " + weather.getWeatherForThreeHours(requestedDateIndex).getTime());
        Image weatherImage = new Image("http://openweathermap.org/img/w/" + weather.getWeatherForThreeHours(requestedDateIndex)
                .getWeatherIcon() + ".png");
        this.iVWeather = new ImageView(weatherImage);
        this.labelWeather = new Label("Pogoda: " + weather.getWeatherForThreeHours(requestedDateIndex).getMainDescription());
        this.labelClouds = new Label("Zachmurzenie: " + weather.getWeatherForThreeHours(requestedDateIndex).getClouds());
        this.labelHumidity = new Label ("Wilgotność: " + weather.getWeatherForThreeHours(requestedDateIndex)
                .getHumidity());
        this.labelTemp = new Label("Temperatura: " + weather.getWeatherForThreeHours(requestedDateIndex).getTemperature());
        this.labelPressure = new Label ("Ciśnienie: " + weather.getWeatherForThreeHours(requestedDateIndex).getPressure
                ());
        this.labelWindSpeed = new Label("Prędkość wiatru: " + weather.getWeatherForThreeHours(requestedDateIndex)
                .getWindSpeed());
        this.windDirection = weather.getWeatherForThreeHours(requestedDateIndex).getWindDegree();
        File file = new File("img/compass_3.png");
        Image northImage = new Image(file.toURI().toString());
        this.NESW = new ImageView(northImage);
        NESW.setFitWidth(200);
        NESW.setFitHeight(200);
//      NESW.setStyle("-fx-background-color:transparent");
        file = new File("img/arrow_blue.png");
        northImage = new Image(file.toURI().toString());
        this.windArrow = new ImageView(northImage);
        windArrow.setFitWidth(100);
        windArrow.setFitHeight(100);
        windArrow.setRotate(-90 + windDirection);
        this.windPic = new StackPane(NESW, windArrow);

        container = new VBox();

        this.container.getChildren().addAll(labelCity, labelDate, labelTime, labelWeather, iVWeather, labelClouds,
                labelHumidity, labelTemp, labelPressure, labelWindSpeed, windPic);
    }

    public VBox getWeatherData(){
        return container;
    }
}
