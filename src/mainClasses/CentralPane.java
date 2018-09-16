package mainClasses;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class CentralPane extends VBox {

    private VBox mainContainer;
    private VBox topPane;
    private Pane centerPane;
    private VBox bottomPane;

    private Label hintForUser;
    public Button returnToTitleScreen;


    public CentralPane() {

        this.hintForUser = new Label("Rozwiń jedną z powyższych zakładek aby zobaczyć pogodę na kolejne dni.");
        this.hintForUser.setWrapText(true);
        this.hintForUser.getStyleClass().add("centerPaneTips_style");


        this.returnToTitleScreen = new Button("powrót do wyboru miast");
        this.topPane = new VBox(this.hintForUser);
        this.topPane.getStyleClass().add("mainPane_style");
        this.centerPane = new Pane();
        this.bottomPane = new VBox(returnToTitleScreen);


        mainContainer = new VBox(topPane, centerPane, bottomPane);

        topPane.prefHeightProperty().bind(mainContainer.widthProperty().multiply(0.2));
        centerPane.prefHeightProperty().bind(mainContainer.widthProperty().multiply(0.7));
        bottomPane.prefHeightProperty().bind(mainContainer.widthProperty().multiply(0.1));

    }

    public void setHintForUser(String hint) {
        this.hintForUser.setText(hint);
    }

    public VBox getMainContainer() {
        return mainContainer;
    }


}
