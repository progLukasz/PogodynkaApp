package mainClasses;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;

public class Tile extends VBox {

    Label time;
    Label dayOfWeek;
    ImageView icon;

    public Tile(String timeIn, String dayOfWeekIn, String imageName) {
        time = new Label();
        time.setText(timeIn);
        dayOfWeek = new Label();
        dayOfWeek.setText(dayOfWeekIn);
        icon = new ImageView();
        Image homeWeatherImage = new Image("http://openweathermap.org/img/w/" + imageName);
        this.icon.setImage(homeWeatherImage);
    }
}
