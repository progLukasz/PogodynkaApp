package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.FiveDaysWeather;
import mainClasses.ParsedForecast;
import mainClasses.WeatherQueryResult;
import mainClasses.OtherMethods;

import java.net.URL;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        AnchorPane titlePage = new AnchorPane();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("PogodynkApp");
        primaryStage.setScene(new Scene(titlePage, 600, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

        //https://api.openweathermap.org/data/2.5/forecast?q=Leszno&appid=6a9f0069bab2b2553d52eab3c86b66f4&units=metric
        WeatherQueryResult weatherHere = new WeatherQueryResult("Leszno", "6a9f0069bab2b2553d52eab3c86b66f4");
       if (OtherMethods.splitAndCheckWeatherData(weatherHere)){
           System.out.println("OK");
           FiveDaysWeather weatherForHomeTown = new FiveDaysWeather(weatherHere.getWeatherData());
           // System.out.println(weatherForHomeTown.getWeatherForThreeHours(0).getDateAndTime());
       }else{
           System.out.println("NOK");


       }
    }
}
