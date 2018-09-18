package mainClasses;


import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {


        Scene scene1;

        if (!checkInternetConnection()) {
            NoConnectionPane newNoConnPane = new NoConnectionPane();
            newNoConnPane.confirmButton.setOnAction((event) -> {primaryStage.close();});
            scene1 = new Scene(newNoConnPane);
        } else {

            TitlePane newTitlePane = new TitlePane();
            newTitlePane.acceptButton.setDefaultButton(true);
            scene1 = new Scene(newTitlePane);
        }


        primaryStage.setScene(scene1);
        primaryStage.setTitle("PogodynkApp");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
        scene1.getStylesheets().add("mainClasses/style.css");


        newTitlePane.acceptButton.setOnAction((event) -> {

            String home = homeCityBox.getText();
            String destination = destCityBox.getText();

            if (checkIfStringsAreNotEmpty(home, destination)) {
                homeCityBox.getStyleClass().remove("textFieldHighlighted_style");
                destCityBox.getStyleClass().remove("textFieldHighlighted_style");
                homeCityBox.getStyleClass().add("textField_style");
                destCityBox.getStyleClass().add("textField_style");

                WeatherQueryResult weatherHere = new WeatherQueryResult(home,
                        "6a9f0069bab2b2553d52eab3c86b66f4");
                WeatherQueryResult weatherThere = new WeatherQueryResult(destination,
                        "6a9f0069bab2b2553d52eab3c86b66f4");

                try {
                    FiveDaysWeather weatherForHomeTown = new FiveDaysWeather(weatherHere.getWeatherData());
                    FiveDaysWeather weatherForDestinationTown = new FiveDaysWeather((weatherThere.getWeatherData()));

                    BorderPane mainPane = new BorderPane();
                    mainPane.setPrefSize(1200, 900);
                    mainPane.setId("mainPane_style");

                    WeatherPane homeTown = new WeatherPane(home, weatherForHomeTown, weatherForHomeTown
                            .getWeatherForThreeHours(0).getDateAndTime());
                    VBox leftWeatherPane = new VBox(homeTown.getWeatherData());
                    leftWeatherPane.getStyleClass().add("mainPane_style");
                    WeatherPane destinationTown = new WeatherPane(destination, weatherForDestinationTown,
                            weatherForDestinationTown.getWeatherForThreeHours(0).getDateAndTime());
                    VBox rightWeatherPane = new VBox(destinationTown.getWeatherData());
                    rightWeatherPane.getStyleClass().add("mainPane_style");
                    mainPane.setLeft(leftWeatherPane);
                    mainPane.setRight(rightWeatherPane);

                    CentralPane centralGrid = new CentralPane();
                    centralGrid.setId("mainCentralPane_style");
                    mainPane.setCenter(centralGrid.getMainContainer());

                    TitledPane tab1 = new TitledPane("Temperatura", new OtherMethods().addChart("Temperatura", home,
                            destination, weatherForHomeTown, weatherForDestinationTown, homeTown, destinationTown, mainPane, centralGrid));
                    TitledPane tab2 = new TitledPane("Zachmurzenie", new OtherMethods().addChart("Zachmurzenie", home,
                            destination, weatherForHomeTown, weatherForDestinationTown, homeTown, destinationTown, mainPane, centralGrid));
                    TitledPane tab3 = new TitledPane("Wiatr", new OtherMethods().addChart("Wiatr", home, destination,
                            weatherForHomeTown, weatherForDestinationTown, homeTown, destinationTown, mainPane, centralGrid));
                    Accordion accordion = new Accordion();
                    accordion.getPanes().addAll(tab1, tab2, tab3);
                    mainPane.setTop(accordion);
                    accordion.expandedPaneProperty().addListener((property, oldPane, newPane) -> {
                        if (newPane != null) {
                            centralGrid.setHintForUser("Możesz wyświetlić szczegółową pogodę po kliknięciu na jeden z" +
                                    " węzłów wykresu");
                        } else {
                            centralGrid.setHintForUser("Rozwiń jedną z powyższych zakładek aby zobaczyć pogodę na kolejne dni.");
                        }
                    });

                    Scene scene2 = new Scene(mainPane);
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                    primaryStage.centerOnScreen();
                    scene2.getStylesheets().add("mainClasses/style.css");

                    centralGrid.returnToTitleScreen.setOnAction((even) -> {
                        primaryStage.setScene(scene1);
                        primaryStage.show();
                        primaryStage.centerOnScreen();
                    });

                } catch (Exception e) {

                    displayMessageForWhile(errorMessage, "Niestety, nie udało nawiązać się połączenia z serwerem. " +
                                    "Prosimy upewnić " + "się, iż wpisano poprawne nazwy miast lub spróbować później.",
                            "errorMessage_error", startMessage, "errorMessage_info", 4);
                }
            }
            else {
                displayMessageForWhile(errorMessage, "Proszę uzupełnić wszystkie niezbędne pola",
                        "errorMessage_error", startMessage, "errorMessage_info", 4);
                if (home.isEmpty()){homeCityBox.getStyleClass().add("textFieldHighlighted_style");}
                if(destination.isEmpty()) {destCityBox.getStyleClass().add("textFieldHighlighted_style");}
            }
        });
    }

    private boolean checkIfStringsAreNotEmpty(String phrase1, String phrase2){
        return (!(phrase1.isEmpty() || phrase2.isEmpty()));
    }

    private void displayMessageForWhile(Label messageDestination, String messageText, String messageStyle, String
            messageAfter, String styleAfter, int duration){
        messageDestination.setText(messageText);
        messageDestination.setId(messageStyle);
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(duration));
        pauseTransition.setOnFinished(a -> changeMessageTextAndStyling(messageDestination, messageAfter, styleAfter));
        pauseTransition.play();
    }

    private void changeMessageTextAndStyling (Label label, String text, String style){
        label.setText(text);
        label.setId(style);
    }

    private boolean checkInternetConnection () {
        int dataCode = 0;
        try {
            WeatherQueryResult connectionTest = new WeatherQueryResult("Warsaw",
                    "6a9f0069bab2b2553d52eab3c86b66f4");
            FiveDaysWeather connectionTest2 = new FiveDaysWeather(connectionTest.getWeatherData());

        } catch (Exception e){
            return false;
        }
        return true;
    }
}
