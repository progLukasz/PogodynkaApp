package mainClasses;

import NewPanes.MainPane;
import NewPanes.NoConnectionPane;
import NewPanes.TitlePane;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private final String apiKey =  "6a9f0069bab2b2553d52eab3c86b66f4";

    @Override
    public void start(Stage primaryStage) {

        Scene scene1;
        NoConnectionPane newNoConnPane = new NoConnectionPane();
        TitlePane newTitlePane = new TitlePane();

        if (!checkInternetConnection()) {
            newNoConnPane.confirmButton.setOnAction((event) -> {primaryStage.close();});
            scene1 = new Scene(newNoConnPane);
        } else {
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
            String home = newTitlePane.getHomeCityBox();
            String destination = newTitlePane.getDestCityBox();

            if (!(home.isEmpty() || destination.isEmpty())) {
                newTitlePane.changeHomeCityBoxClass("textField_style", "textFieldHighlighted_style");
                newTitlePane.changeDestCityBoxClass("textField_style", "textFieldHighlighted_style");

                WeatherQueryResult weatherHere = new WeatherQueryResult(home, apiKey);
                WeatherQueryResult weatherThere = new WeatherQueryResult(destination, apiKey);

                try {
                    FiveDaysWeather weatherForHomeTown = new FiveDaysWeather(weatherHere.getWeatherData());
                    FiveDaysWeather weatherForDestinationTown = new FiveDaysWeather((weatherThere.getWeatherData()));

                    MainPane newMainPane = new MainPane(home, weatherForHomeTown, destination, weatherForDestinationTown);

                    Scene scene2 = new Scene(newMainPane);
                    primaryStage.setScene(scene2);
                    primaryStage.setResizable(true);
                    primaryStage.show();
                    primaryStage.centerOnScreen();
                    scene2.getStylesheets().add("mainClasses/style.css");

                    newMainPane.centralGrid.returnToTitleScreen.setOnAction((even) -> {
                        primaryStage.setScene(scene1);
                        primaryStage.setResizable(false);
                        primaryStage.show();
                        primaryStage.centerOnScreen();
                    });

                } catch (Exception e) {

                    displayMessageForWhile(newTitlePane.getErrorMessage(), "Niestety, nie udało nawiązać się połączenia z serwerem. " +
                                    "Prosimy upewnić " + "się, iż wpisano poprawne nazwy miast lub spróbować później.",
                            "errorMessage_error", newTitlePane.getStartMessage(), "errorMessage_info", 4);
                }
            }
            else {
                displayMessageForWhile(newTitlePane.getErrorMessage(), "Proszę uzupełnić wszystkie niezbędne pola",
                        "errorMessage_error", newTitlePane.getStartMessage(), "errorMessage_info", 4);
                if (home.isEmpty()){newTitlePane.changeHomeCityBoxClass("textFieldHighlighted_style", "");}
                if(destination.isEmpty()) {newTitlePane.changeDestCityBoxClass("textFieldHighlighted_style", "");}
            }
        });
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
        try {
            WeatherQueryResult connectionTest = new WeatherQueryResult("Warsaw", apiKey);
            FiveDaysWeather connectionTest2 = new FiveDaysWeather(connectionTest.getWeatherData());
            String test = connectionTest2.getWeatherForThreeHours(0).getDateAndTime();

        } catch (Exception e){
            return false;
        }
        return true;
    }
}
