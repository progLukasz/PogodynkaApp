package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainClasses.ParsedForecast;
import mainClasses.WeatherQueryResult;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{



        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
      //  primaryStage.setTitle(newForecast.getWeatherData());
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        WeatherQueryResult newForecast = new WeatherQueryResult("Warsaw", "b6907d289e10d714a6e88b30761fae22");
        ParsedForecast forecast = new ParsedForecast(newForecast.getWeatherData());
        System.out.println();
    }
}
