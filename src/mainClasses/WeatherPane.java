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
        this.labelWeather = new Label("Pogoda: " + weather.getWeatherForThreeHours(requestedDateIndex).getMain());
        this.labelClouds = new Label("Zachmurzenie: " + weather.getWeatherForThreeHours(requestedDateIndex).getClouds());
        this.labelTemp = new Label("Temperatura: " + weather.getWeatherForThreeHours(requestedDateIndex).getTemperature());
        this.labelWind = new Label("Prędkość wiatru: " + weather.getWeatherForThreeHours(requestedDateIndex).getWindSpeed());
        container = new VBox();
        this.container.getChildren().addAll(labelCity, labelDate, labelTime, labelWeather, iVWeather, labelClouds,
                labelTemp, labelWind);
    }

    public VBox getWeatherData(){
        return container;
    }
}
