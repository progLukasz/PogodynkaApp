package mainClasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        //https://api.openweathermap.org/data/2.5/forecast?q=Leszno&appid=6a9f0069bab2b2553d52eab3c86b66f4&units=metric
/*        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("frontend.fxml"));
        Parent layout = fxmlLoader.load();
        Scene scene = new Scene(layout); */

        FiveDaysWeather weatherForHomeTown;
        FiveDaysWeather weatherForDestinationTown;




        WeatherQueryResult weatherHere = new WeatherQueryResult("Leszno", "6a9f0069bab2b2553d52eab3c86b66f4");
        if (OtherMethods.splitAndCheckWeatherData(weatherHere)) {

            BorderPane mainPane = new BorderPane();
            mainPane.setPrefSize(1200, 900);

            weatherForHomeTown = new FiveDaysWeather(weatherHere.getWeatherData());
            WeatherPane homeTown = new WeatherPane("Leszno", weatherForHomeTown, 0);

            mainPane.setLeft(homeTown.getWeatherData());
            mainPane.setCenter(addGridPane());

            Scene scene = new Scene(mainPane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("PogodynkApp");
            primaryStage.show();
            scene.getStylesheets().add("mainClasses/style.css");

        } else {
            System.out.println("NOK");
        }


    }

    public CentralPane addGridPane(){
        Button[] buttons = new Button[5];
        buttons[0] = new Button("Przycisk 1");
        buttons[1] = new Button("Przycisk 2");
        buttons[2] = new Button("Przycisk 3");
        buttons[3] = new Button("Przycisk 4");
        buttons[4] = new Button("Przycisk 5");

        CentralPane centralGrid = new CentralPane(buttons[0], buttons[1], buttons[2], buttons[3], buttons[4]);
        centralGrid.setStyle("-fx-background-color: #FFFFFF");
        centralGrid.setAlignment(Pos.CENTER);

        return centralGrid;
    }

}
