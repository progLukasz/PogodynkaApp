package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainClasses.FiveDaysWeather;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("PogodynkApp");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

        //https://api.openweathermap.org/data/2.5/forecast?q=Warsaw&appid=6a9f0069bab2b2553d52eab3c86b66f4&units=metric

        FiveDaysWeather weatherHere = new FiveDaysWeather("Leszno");
        FiveDaysWeather weatherThere = new FiveDaysWeather("Madryt");
    }
}
