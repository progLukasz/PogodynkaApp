package mainClasses;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        //https://api.openweathermap.org/data/2.5/forecast?q=Leszno&appid=6a9f0069bab2b2553d52eab3c86b66f4&units=metric
/*        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("frontend.fxml"));
        Parent layout = fxmlLoader.load();
        Scene scene = new Scene(layout); */

        BorderPane titlePane = new BorderPane();
        titlePane.setPrefSize(1200, 900);


        Text appTitle = new Text("PogodynkApp");
        Text appSubtitle = new Text("Nie daj się już nigdy zaskoczyć pogodzie");
        TextField homeCityBox = new TextField();
        TextField destCityBox = new TextField();
        Button acceptButton = new Button("Wyszukaj prognozę");
        Label errorMessage = new Label();

        homeCityBox.setPromptText("wpisz miasto w któtym się znajdujesz");
        destCityBox.setPromptText("wpisz miasto do którego się wybierasz");

        VBox topNode = new VBox(appTitle, appSubtitle);
        VBox centerNode = new VBox(homeCityBox, destCityBox, acceptButton);
        VBox bottomNode = new VBox(errorMessage);

        titlePane.setTop(topNode);
        titlePane.setCenter(centerNode);
        titlePane.setBottom(bottomNode);

        Scene scene1 = new Scene(titlePane);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("PogodynkApp");
        primaryStage.show();
        scene1.getStylesheets().add("mainClasses/style.css");


        boolean weatherDataDownloaded = false;

        acceptButton.setOnAction((event) -> {
            boolean dataOK = checkIfFieldsNotEmpty(homeCityBox, destCityBox);

            if (dataOK) {
                String home = homeCityBox.getText();
                String destination = destCityBox.getText();
                WeatherQueryResult weatherHere = new WeatherQueryResult(home,
                        "6a9f0069bab2b2553d52eab3c86b66f4");
                WeatherQueryResult weatherThere = new WeatherQueryResult(destination,
                        "6a9f0069bab2b2553d52eab3c86b66f4");
                if ((new OtherMethods().splitAndCheckWeatherData(weatherHere)) && new OtherMethods()
                        .splitAndCheckWeatherData(weatherThere)) {

                    FiveDaysWeather weatherForHomeTown = new FiveDaysWeather(weatherHere.getWeatherData());
                    FiveDaysWeather weatherForDestinationTown = new FiveDaysWeather((weatherThere.getWeatherData()));

                    BorderPane mainPane = new BorderPane();
                    mainPane.setPrefSize(1200, 900);

                    WeatherPane homeTown = new WeatherPane(home, weatherForHomeTown, weatherForHomeTown
                            .getWeatherForThreeHours(0).getDateAndTime());
                    WeatherPane destinationTown = new WeatherPane(destination, weatherForDestinationTown,
                            weatherForDestinationTown.getWeatherForThreeHours(0).getDateAndTime());

                    mainPane.setLeft(homeTown.getWeatherData());
                    mainPane.setRight(destinationTown.getWeatherData());

                    TitledPane t1 = new TitledPane("Temperatura", new OtherMethods().addChart("Temperatura", home, destination,
                            weatherForHomeTown, weatherForDestinationTown, homeTown, destinationTown, mainPane));
                    TitledPane t2 = new TitledPane("Zachmurzenie", new OtherMethods().addChart("Zachmurzenie", home, destination,
                            weatherForHomeTown, weatherForDestinationTown, homeTown, destinationTown, mainPane));
                    TitledPane t3 = new TitledPane("Wiatr", new OtherMethods().addChart("Wiatr", home, destination,
                            weatherForHomeTown, weatherForDestinationTown, homeTown, destinationTown, mainPane));
                    Accordion accordion = new Accordion();
                    accordion.getPanes().addAll(t1, t2, t3);
                    mainPane.setTop(accordion);

                    CentralPane centralGrid = new CentralPane();
                    centralGrid.setStyle("-fx-background-color: #FFFFFF");
                    centralGrid.setAlignment(Pos.CENTER);
                    mainPane.setCenter(centralGrid);

                    Scene scene2 = new Scene(mainPane);
                    primaryStage.setScene(scene2);
                    primaryStage.setTitle("PogodynkApp");
                    primaryStage.show();
                    scene2.getStylesheets().add("mainClasses/style.css");

                } else {
                    errorMessage.setText("Niestety, nie udało nawiązać się połączenia z serwerem. Prosimy upewnić " +
                            "się, iż wpisano poprawne nazwy miast lub spróbować później");
                }
            } else {
                errorMessage.setText("Proszę wpisz nazwy miast do obu pól");
            }
        });

    }

    public boolean checkIfFieldsNotEmpty(TextField homeTown, TextField destTown) {
        if ((homeTown.getText().trim().isEmpty()) || (destTown.getText().trim().isEmpty())) {
            return false;
        } else {
            return true;
        }
    }
}
