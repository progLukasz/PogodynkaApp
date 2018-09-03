package mainClasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        //https://api.openweathermap.org/data/2.5/forecast?q=Leszno&appid=6a9f0069bab2b2553d52eab3c86b66f4&units=metric
/*        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("frontend.fxml"));
        Parent layout = fxmlLoader.load();
        Scene scene = new Scene(layout); */


        String home = "Leszno";
        String destination = "Madrid";


        WeatherQueryResult weatherHere = new WeatherQueryResult(home, "6a9f0069bab2b2553d52eab3c86b66f4");

        WeatherQueryResult weatherThere = new WeatherQueryResult(destination, "6a9f0069bab2b2553d52eab3c86b66f4");


        if (new OtherMethods().splitAndCheckWeatherData(weatherHere)) {

            FiveDaysWeather weatherForHomeTown = new FiveDaysWeather(weatherHere.getWeatherData());
            FiveDaysWeather weatherForDestinationTown = new FiveDaysWeather((weatherThere.getWeatherData()));

            BorderPane mainPane = new BorderPane();
            mainPane.setPrefSize(1200, 900);


            WeatherPane homeTown = new WeatherPane(home,  weatherForHomeTown, 0);
            WeatherPane destinationTown = new WeatherPane(destination, weatherForDestinationTown, 0);

            mainPane.setLeft(homeTown.getWeatherData());
            mainPane.setRight(destinationTown.getWeatherData());
            mainPane.setCenter(addGridPane());


            TitledPane t1 = new TitledPane("Temperatura", new OtherMethods().addTemperatureChart(home, destination,
                    weatherForHomeTown, weatherForDestinationTown));
            TitledPane t2 = new TitledPane("Zachmurzenie", new OtherMethods().addCloudsChart(home, destination,
                    weatherForHomeTown, weatherForDestinationTown));
            TitledPane t3 = new TitledPane("Wiatr", new OtherMethods().addTWindChart(home, destination,
                    weatherForHomeTown, weatherForDestinationTown));
            Accordion accordion = new Accordion();
            accordion.getPanes().addAll(t1, t2, t3);

            mainPane.setTop(accordion);


            Scene scene = new Scene(mainPane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("PogodynkApp");
            primaryStage.show();
            scene.getStylesheets().add("mainClasses/style.css");

        } else {
            System.out.println("NOK");
        }
    }

    //funkcja testowa, do usuniecia
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
