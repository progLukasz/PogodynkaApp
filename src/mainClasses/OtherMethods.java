package mainClasses;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class OtherMethods {

    public static boolean splitAndCheckWeatherData (WeatherQueryResult weatherData) {
        String weatherDataS = weatherData.getWeatherData();
        return (!weatherDataS.regionMatches(0, "Error", 0, 5));
    }

    public static VBox createWeatherTile(String imageId, String dateStamp, String timeStamp) {
        Label date = new Label(dateStamp);
        Label time = new Label(timeStamp);
        Image image = new Image("http://openweathermap.org/img/w/10d.png" + imageId);
        ImageView imageV = new ImageView();
        imageV.setImage(image);
        VBox weatherTile = new VBox();
        weatherTile.setMinHeight(50);
        weatherTile.setMinWidth(50);
        weatherTile.getStyleClass().add("weatherTile_style");
        weatherTile.getChildren().addAll(date, time, imageV);
        return weatherTile;
    }
}
