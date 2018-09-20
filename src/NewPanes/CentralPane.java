package NewPanes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

public class CentralPane extends VBox {

    private VBox mainContainer;
    private VBox topPane;
    private VBox centerPane;
    private VBox bottomPane;

    private Label hintForUser;
    public Button returnToTitleScreen;
    private ImageView positiveManImage;
    private ImageView negativeManImage;
    private ImageView neutralManImage;

    CentralPane() {

        this.hintForUser = new Label("Rozwiń jedną z powyższych zakładek aby zobaczyć pogodę na kolejne dni.");
        this.hintForUser.setWrapText(true);
        this.hintForUser.getStyleClass().add("centerPaneTips_style");
        this.topPane = new VBox(this.hintForUser);
        this.topPane.getStyleClass().add("mainPane_style");

        File file = new File("img/plus.png");
        Image tempImage = new Image(file.toURI().toString());
        this.positiveManImage = new ImageView(tempImage);
        file = new File("img/minus.png");
        tempImage = new Image(file.toURI().toString());
        this.negativeManImage = new ImageView(tempImage);
        file = new File("img/neutral.png");
        tempImage = new Image(file.toURI().toString());
        this.neutralManImage = new ImageView(tempImage);
        this.centerPane = new VBox();
        this.centerPane.setId("centerPane_style");

        this.returnToTitleScreen = new Button("powrót do wyboru miast");
        returnToTitleScreen.getStyleClass().add("button_style");
        this.bottomPane = new VBox(returnToTitleScreen);

        mainContainer = new VBox(topPane, centerPane, bottomPane);

        topPane.prefHeightProperty().bind(mainContainer.widthProperty().multiply(0.2));
        centerPane.prefHeightProperty().bind(mainContainer.widthProperty().multiply(0.7));
        bottomPane.prefHeightProperty().bind(mainContainer.widthProperty().multiply(0.1));
    }

    public void setHintForUser(String hint) {
        this.hintForUser.setText(hint);
        this.hintForUser.setWrapText(true);
    }

    private int compareWeatherForSelection (Float  homeTemp, int homeClouds, float homeWindSpeed, float destTemp, int
            destClouds, float destWindSpeed){
        int scoreTemp = 0;

        if (destTemp > homeTemp){scoreTemp++;}
        else if (destTemp < homeTemp) {scoreTemp--;}

        if (destClouds < homeClouds) {scoreTemp++;}
        else if (destClouds > homeClouds) {scoreTemp--;}

        if (destWindSpeed < homeWindSpeed){scoreTemp++;}
        else if (destWindSpeed > homeWindSpeed){scoreTemp--;}

        return scoreTemp;
    }

    private ImageView estimateWeatherRating(Float homeTemp, int homeClouds, float homeWindSpeed, float destTemp, int
            destClouds, float destWindSpeed) {
        int score = compareWeatherForSelection(homeTemp, homeClouds, homeWindSpeed, destTemp, destClouds,
                destWindSpeed);

        if (score < 0) {
            return this.negativeManImage;
        } else if (score > 0) {
            return positiveManImage;
        } else {
            return neutralManImage;
        }
    }

    public void setImageInCentralPane(Float homeTemp, int homeClouds, float homeWindSpeed, float destTemp, int
            destClouds, float destWindSpeed){
        this.centerPane.getChildren().clear();
        this.centerPane.getChildren().add(this.estimateWeatherRating(homeTemp, homeClouds, homeWindSpeed, destTemp,
        destClouds, destWindSpeed));
    }

    public VBox getMainContainer() {
        return mainContainer;
    }
}
