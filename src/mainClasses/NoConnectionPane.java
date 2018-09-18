package mainClasses;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class NoConnectionPane extends BorderPane {

    Button confirmButton;
    private String connectionMessageLine1 = "Niestety ale nie udało się nawiązać połączenia z serwerem OpenWeatherMap.";
    private String connectionMessageLine2 = "Sprawdź połączenie z internetem i uruchom aplikację ponownie.";
    private Label connectionMessage;
    private Text appTitle;
    private Text appSubtitle;
    private VBox topNode;
    private VBox centerNode;
    private VBox bottomNode;

    NoConnectionPane() {
        this.setPrefSize(850,440);
        this.setId("titlePane_style");
        this.appTitle = new Text("PogodynkApp");
        this.appTitle.setId("appTitle_style");
        this.appSubtitle = new Text("Nie daj się już nigdy zaskoczyć pogodzie");
        this.appSubtitle.setId("appSubtitle_style");
        this.confirmButton = new Button("Zamknij");
        this.confirmButton.getStyleClass().add("button_style");
        this.connectionMessage = new Label(connectionMessageLine1 + "\n" + connectionMessageLine2);
        this. connectionMessage.getStyleClass().add("errorMessage_style");
        this.connectionMessage.setId("errorMessage_error");

        this.topNode = new VBox(this.appTitle, this.appSubtitle);
        this.topNode.getStyleClass().add("titleNode_style");
        this.topNode.setId("titleNodeTop_style");
        this.centerNode = new VBox(this.connectionMessage);
        this.centerNode.getStyleClass().add("titleNode_style");
        this.bottomNode = new VBox(confirmButton);
        this.bottomNode.getStyleClass().add("titleNode_style");
        this.bottomNode.setStyle("-fx-min-height: 100px;");


        this.setTop(topNode);
        this.setCenter(centerNode);
        this.setBottom(bottomNode);

    }



}
