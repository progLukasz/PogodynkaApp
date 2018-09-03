package mainClasses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class AppController {

    FiveDaysWeather weatherForHomeTown;
    FiveDaysWeather weatherForDestinationTown;

    public void fillTilePanesWithData(ActionEvent actionEvent) {
        WeatherQueryResult weatherHere = new WeatherQueryResult("Leszno", "6a9f0069bab2b2553d52eab3c86b66f4");
        if (new OtherMethods().splitAndCheckWeatherData(weatherHere)) {
            weatherForHomeTown = new FiveDaysWeather(weatherHere.getWeatherData());
            WeatherPane homeTown = new WeatherPane("Leszno", weatherForHomeTown, 2);

        } else {
            System.out.println("NOK");
        }
    }



    private EventHandler<? super MouseEvent> createTileHandler(int x) {
        return event -> tileHandler(x);
    }

    private void tileHandler (int x){
    }


















    }


