package mainClasses;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WeatherPane extends Pane {

    private Label labelCity;
    private Label labelDate;
    private Label labelTime;
    private ImageView iVWeather;
    private Label labelWeather;
    private Label labelClouds;
    private Label labelTemp;
    private Label labelWind;

    private VBox container;

    public WeatherPane(String city, FiveDaysWeather weather,int i) {
        updateWeatherData(city, weather, i);
    }

    public void updateWeatherData (String city, FiveDaysWeather weather,int i){
        this.labelCity = new Label(city);
        this.labelDate = new Label("Data: " + weather.getWeatherForThreeHours(i).getDate());
        this.labelTime = new Label("Czas: " + weather.getWeatherForThreeHours(i).getTime());
        Image weatherImage = new Image("http://openweathermap.org/img/w/" + weather.getWeatherForThreeHours(i)
                .getWeatherIcon() + ".png");
        this.iVWeather = new ImageView(weatherImage);
        this.labelWeather = new Label("Pogoda: " + weather.getWeatherForThreeHours(i).getMain());
        this.labelClouds = new Label("Zachmurzenie: " + weather.getWeatherForThreeHours(i).getClouds());
        this.labelTemp = new Label("Temperatura: " + weather.getWeatherForThreeHours(i).getTemperature());
        this.labelWind = new Label("Prędkość wiatru: " + weather.getWeatherForThreeHours(i).getWindSpeed());
        container = new VBox();
        this.container.getChildren().addAll(labelCity, labelDate, labelTime, labelWeather, iVWeather, labelClouds,
                labelTemp, labelWind);
    }

    public VBox getWeatherData(){
        return container;
    }
}
