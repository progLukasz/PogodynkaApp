package NewPanes;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mainClasses.FiveDaysWeather;
import mainClasses.OtherMethods;

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

    WeatherPane(String city, FiveDaysWeather weather, String date) {
        updateWeatherData(city, weather, date);
    }


    public void updateWeatherData (String city, FiveDaysWeather weather, String date){
        int requestedDateIndex = 0;
        for (int i = 0; i < weather.getWeatherArrayCount(); i++){
            String w = weather.getWeatherForThreeHours(i).getDateAndTime();
            if (w.equals(date)){
                requestedDateIndex = i;
                break;
            }
        }

        this.labelCity = new Label(city);
        Image weatherImage = new Image("http://openweathermap.org/img/w/" + weather.getWeatherForThreeHours(requestedDateIndex)
                .getWeatherIcon() + ".png");
        this.iVWeather = new ImageView(weatherImage);
        this.iVWeather.setFitHeight(150);
        this.iVWeather.setFitWidth(150);
        HBox cityHB = new HBox(labelCity, iVWeather);
        Text labelDateStaticText = new Text("Data: ");
        labelDateStaticText.getStyleClass().add("sidePaneDescription_style");
        this.labelDate = new Label(weather.getWeatherForThreeHours(requestedDateIndex).getDate());
        this.labelDate.getStyleClass().add("sidePaneData_style");
        HBox dateHB = new HBox(labelDateStaticText, labelDate);
        Text labelTimeStaticText = new Text("Czas: ");
        labelTimeStaticText.getStyleClass().add("sidePaneDescription_style");
        this.labelTime = new Label(weather.getWeatherForThreeHours(requestedDateIndex).getTime());
        this.labelTime.getStyleClass().add("sidePaneData_style");
        HBox timeHB = new HBox(labelTimeStaticText, labelTime);
        Text labelWeatherStaticText = new Text("Pogoda: ");
        labelWeatherStaticText.getStyleClass().add("sidePaneDescription_style");
        this.labelWeather = new Label( new OtherMethods().weatherDescriptor(weather.getWeatherForThreeHours
                (requestedDateIndex).getWeatherId()));
        this.labelWeather.getStyleClass().add("sidePaneData_style");
        HBox weatherHB = new HBox(labelWeatherStaticText, labelWeather);
        Text labelTempStaticText = new Text("Temperatura: ");
        labelTempStaticText.getStyleClass().add("sidePaneDescription_style");
        this.labelTemp = new Label(weather.getWeatherForThreeHours(requestedDateIndex).getTemperature() +
                " °C");
        this.labelTemp.getStyleClass().add("sidePaneData_style");
        HBox tempHB = new HBox(labelTempStaticText, labelTemp);
        Text labelCloudsStaticText = new Text("Zachmurzenie: ");
        labelCloudsStaticText.getStyleClass().add("sidePaneDescription_style");
        this.labelClouds = new Label(weather.getWeatherForThreeHours(requestedDateIndex).getClouds() + "%");
        this.labelClouds.getStyleClass().add("sidePaneData_style");
        HBox cloudsHB = new HBox(labelCloudsStaticText, labelClouds);
        Text labelHumidityStaticText = new Text("Wilgotność: ");
        labelHumidityStaticText.getStyleClass().add("sidePaneDescription_style");
        this.labelHumidity = new Label (weather.getWeatherForThreeHours(requestedDateIndex)
                .getHumidity() + "%");
        this.labelHumidity.getStyleClass().add("sidePaneData_style");
        HBox humidityHB = new HBox(labelHumidityStaticText, labelHumidity);
        Text labelPressureStaticText = new Text("Ciśnienie: ");
        labelPressureStaticText.getStyleClass().add("sidePaneDescription_style");
        this.labelPressure = new Label (weather.getWeatherForThreeHours(requestedDateIndex).getPressure() + " hPa");
        this.labelPressure.getStyleClass().add("sidePaneData_style");
        HBox pressureHB = new HBox(labelPressureStaticText, labelPressure);
        Text labelWindSpeedStaticText = new Text("Prędkość wiatru: ");
        labelWindSpeedStaticText.getStyleClass().add("sidePaneDescription_style");
        this.labelWindSpeed = new Label(weather.getWeatherForThreeHours(requestedDateIndex)
                .getWindSpeed() + " m/sek");
        this.labelWindSpeed.getStyleClass().add("sidePaneData_style");
        HBox windSpeedHB = new HBox(labelWindSpeedStaticText, labelWindSpeed);
        Text labelWindDirectionStaticText = new Text("Kierunek wiatru:");
        labelWindDirectionStaticText.getStyleClass().add("sidePaneDescription_style");
        this.windDirection = weather.getWeatherForThreeHours(requestedDateIndex).getWindDegree();
        File file = new File("img/windrose.png");
        Image northImage = new Image(file.toURI().toString());
        this.NESW = new ImageView(northImage);
        NESW.setFitWidth(200);
        NESW.setFitHeight(200);
        file = new File("img/arrow.png");
        northImage = new Image(file.toURI().toString());
        this.windArrow = new ImageView(northImage);
        windArrow.setFitWidth(100);
        windArrow.setFitHeight(100);
        windArrow.setRotate(-90 + windDirection);
        this.windPic = new StackPane(NESW, windArrow);

        container = new VBox();

        this.container.getChildren().addAll(cityHB, dateHB, timeHB, weatherHB, tempHB, cloudsHB,
                humidityHB, pressureHB, windSpeedHB, labelWindDirectionStaticText, windPic);
    }

    public void setIdForLabelCity(String idName){
        labelCity.setId(idName);
    }

    public VBox getWeatherData(){
        return container;
    }

    public String getClouds() {
        return labelClouds.getText().substring(0,1);
    }

    public String getTemp() {
        String delims = "[ ]+";
        return labelTemp.getText().split(delims)[0];
    }

    public String getWindSpeed() {
        String delims = "[ ]+";
        return labelWindSpeed.getText().split(delims)[0];
    }
}
