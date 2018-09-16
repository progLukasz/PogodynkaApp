package mainClasses;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        //https://api.openweathermap.org/data/2.5/forecast?q=Leszno&appid=6a9f0069bab2b2553d52eab3c86b66f4&units=metric
/*        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("frontend.fxml"));
        Parent layout = fxmlLoader.load();
        Scene scene = new Scene(layout); */

        BorderPane titlePane = new BorderPane();
        titlePane.setPrefSize(850, 440);
        titlePane.setId("titlePane_style");


        Text appTitle = new Text("PogodynkApp");
        appTitle.setId("appTitle_style");
        Text appSubtitle = new Text("Nie daj się już nigdy zaskoczyć pogodzie");
        appSubtitle.setId("appSubtitle_style");
        TextField homeCityBox = new TextField();
        TextField destCityBox = new TextField();
        Button acceptButton = new Button("Wyszukaj prognozę");
        acceptButton.getStyleClass().add("button_style");
        Label errorMessage = new Label("Podaj nazwę miasta w ktorym się obecnie znajdujesz oraz miasta do którego " +
                "chcesz wyjechać na wakację według podanego przykładu. Jeśli miasto znajduje się poza granicami " +
                "Polski, wpisz je w języku angielskim.");

        errorMessage.setWrapText(true);
        errorMessage.getStyleClass().add("errorMessage_style");
        errorMessage.setId("errorMessage_info");

        homeCityBox.setPromptText("Poznań, Polska");
        destCityBox.setPromptText("Madrid, Spain");

        VBox topNode = new VBox(appTitle, appSubtitle);
        topNode.getStyleClass().add("titleNode_style");
        topNode.setId("titleNodeTop_style");
        VBox centerNode = new VBox(homeCityBox, destCityBox, acceptButton);
        centerNode.getStyleClass().add("titleNode_style");
        centerNode.setId("titleNodeCenter_style");
        VBox bottomNode = new VBox(errorMessage);
        bottomNode.getStyleClass().add("titleNode_style");


        titlePane.setTop(topNode);
        titlePane.setCenter(centerNode);
        titlePane.setBottom(bottomNode);

        Scene scene1 = new Scene(titlePane);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("PogodynkApp");
        primaryStage.show();
        primaryStage.centerOnScreen();
        scene1.getStylesheets().add("mainClasses/style.css");

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
                    mainPane.setId("mainPane_style");

                    WeatherPane homeTown = new WeatherPane(home, weatherForHomeTown, weatherForHomeTown
                            .getWeatherForThreeHours(0).getDateAndTime());
                    VBox leftWeatherPane = new VBox(homeTown.getWeatherData());
                    leftWeatherPane.getStyleClass().add("mainSidePane_style");
                    WeatherPane destinationTown = new WeatherPane(destination, weatherForDestinationTown,
                            weatherForDestinationTown.getWeatherForThreeHours(0).getDateAndTime());
                    VBox rightWeatherPane = new VBox(destinationTown.getWeatherData());
                    rightWeatherPane.getStyleClass().add("mainSidePane_style");
                    mainPane.setLeft(leftWeatherPane);
                    mainPane.setRight(rightWeatherPane);

                    TitledPane tab1 = new TitledPane("Temperatura", new OtherMethods().addChart("Temperatura", home,
                            destination,
                            weatherForHomeTown, weatherForDestinationTown, homeTown, destinationTown, mainPane));
                    TitledPane tab2 = new TitledPane("Zachmurzenie", new OtherMethods().addChart("Zachmurzenie", home,
                            destination,
                            weatherForHomeTown, weatherForDestinationTown, homeTown, destinationTown, mainPane));
                    TitledPane tab3 = new TitledPane("Wiatr", new OtherMethods().addChart("Wiatr", home, destination,
                            weatherForHomeTown, weatherForDestinationTown, homeTown, destinationTown, mainPane));
                    Accordion accordion = new Accordion();
                    accordion.getPanes().addAll(tab1, tab2, tab3);
                    mainPane.setTop(accordion);


                    CentralPane centralGrid = new CentralPane();
                    centralGrid.setId("mainCentralPane_style");
                    centralGrid.setAlignment(Pos.CENTER);
                    mainPane.setCenter(centralGrid);

                    Scene scene2 = new Scene(mainPane);
                    primaryStage.setScene(scene2);
                    primaryStage.setTitle("PogodynkApp");
                    primaryStage.show();
                    primaryStage.centerOnScreen();
                    scene2.getStylesheets().add("mainClasses/style.css");

                } else {
                    errorMessage.setId("errorMessage_error");
                    errorMessage.setText("Niestety, nie udało nawiązać się połączenia z serwerem. Prosimy upewnić " +
                            "się, iż wpisano poprawne nazwy miast lub spróbować później");
                }
            } else {
                errorMessage.setId("errorMessage_error");
                errorMessage.setText("Proszę wpisz nazwy miast do obu pól");
            }
        });
    }

    private boolean checkIfFieldsNotEmpty(TextField homeTown, TextField destTown) {
        return (!homeTown.getText().trim().isEmpty()) && (!destTown.getText().trim().isEmpty());
    }
}
