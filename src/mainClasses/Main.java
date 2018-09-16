package mainClasses;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Duration;

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
        String startMessage = "Podaj nazwę miasta w ktorym się obecnie znajdujesz oraz miasta do którego " +
                "chcesz wyjechać na wakację według podanego przykładu. Jeśli miasto znajduje się poza granicami " +
                "Polski, wpisz je w języku angielskim.";
        Label errorMessage = new Label(startMessage);

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
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
        scene1.getStylesheets().add("mainClasses/style.css");

        acceptButton.setOnAction((event) -> {

            try {
                String home = homeCityBox.getText();
                String destination = destCityBox.getText();
                WeatherQueryResult weatherHere = new WeatherQueryResult(home,
                        "6a9f0069bab2b2553d52eab3c86b66f4");
                WeatherQueryResult weatherThere = new WeatherQueryResult(destination,
                        "6a9f0069bab2b2553d52eab3c86b66f4");

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
                accordion.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {
                    @Override public void changed(ObservableValue<? extends TitledPane> property, final TitledPane oldPane, final TitledPane newPane) {
                        if (newPane != null) {
                            centralGrid.setHintForUser("Możesz wyświetlić szczegółową pogodę po kliknięciu na jeden z" +
                                    " węzłów wykresu");
                        }
                        else {
                            centralGrid.setHintForUser("Rozwiń jedną z powyższych zakładek aby zobaczyć pogodę na kolejne dni.");
                        }
                        }});

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

            } catch (Exception e){

                errorMessage.setId("errorMessage_error");
                errorMessage.setText("Niestety, nie udało nawiązać się połączenia z serwerem. Prosimy upewnić " +
                        "się, iż wpisano poprawne nazwy miast lub spróbować później." + e);
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(4));
                pauseTransition.setOnFinished(a -> changeMessageTextAndStyling(errorMessage, startMessage,
                        "errorMessage_info"));
                pauseTransition.play();
            }
        });
    }

    private void changeMessageTextAndStyling (Label label, String text, String style){
        label.setText(text);
        label.setId(style);
    }

}
