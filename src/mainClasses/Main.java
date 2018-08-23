package mainClasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //https://api.openweathermap.org/data/2.5/forecast?q=Leszno&appid=6a9f0069bab2b2553d52eab3c86b66f4&units=metric
        WeatherQueryResult weatherHere = new WeatherQueryResult("Leszno", "6a9f0069bab2b2553d52eab3c86b66f4");
        if (OtherMethods.splitAndCheckWeatherData(weatherHere)) {
            FiveDaysWeather weatherForHomeTown = new FiveDaysWeather(weatherHere.getWeatherData());
            System.out.println(weatherForHomeTown.getWeatherForThreeHours(1).getTime());
            System.out.println(weatherForHomeTown.getWeatherForThreeHours(1).getDate());

        } else {
            System.out.println("NOK");
        }


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("frontend.fxml"));
        Parent layout = fxmlLoader.load();
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PogodynkApp");
        primaryStage.show();


        //dodawanie plytek d ogornego/dolnego okna








     /*   public void fillPaneWithParticularDays(){

        }
     */

    }
}
