package mainClasses;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TitlePane extends BorderPane {

    private Text appTitle;
    private Text appSubtitle;
    private TextField homeCityBox;
    private TextField destCityBox;
    public Button acceptButton;
    private String startMessage = "Podaj nazwę miasta w ktorym się obecnie znajdujesz oraz miasta do którego " +
            "chcesz wyjechać na wakację według podanego przykładu. Jeśli miasto znajduje się poza granicami " +
            "Polski, wpisz je w języku angielskim.";
    private Label errorMessage;
    private VBox topNode;
    private VBox centerNode;
    private VBox bottomNode;

    public TitlePane() {

        this.appTitle = new Text("PogodynkApp");
        this.appTitle.setId("appTitle_style");
        this.appSubtitle = new Text("Nie daj się już nigdy zaskoczyć pogodzie");
        this.appSubtitle.setId("appSubtitle_style");
        this.setPrefSize(850, 440);
        this.setId("titlePane_style");
        this.homeCityBox = new TextField();
        this.homeCityBox.getStyleClass().add("textField_style");
        this.destCityBox = new TextField();
        this.destCityBox.getStyleClass().add("textField_style");
        this.acceptButton = new Button("Wyszukaj prognozę");
        this.acceptButton.getStyleClass().add("button_style");
        this.errorMessage = new Label(startMessage);
        this.errorMessage.setWrapText(true);
        this.errorMessage.getStyleClass().add("errorMessage_style");
        this.errorMessage.setId("errorMessage_info");
        this.homeCityBox.setPromptText("Poznań, Polska");
        this.destCityBox.setPromptText("Madrid, Spain");
        this.topNode = new VBox(appTitle, appSubtitle);
        this.topNode.getStyleClass().add("titleNode_style");
        this.topNode.setId("titleNodeTop_style");
        this.centerNode = new VBox(homeCityBox, destCityBox, acceptButton);
        this.centerNode.getStyleClass().add("titleNode_style");
        this.centerNode.setId("titleNodeCenter_style");
        this.bottomNode = new VBox(errorMessage);
        this.bottomNode.getStyleClass().add("titleNode_style");
        this.bottomNode.setId("titleNodeBottom_style");

        this.setTop(topNode);
        this.setCenter(centerNode);
        this.setBottom(bottomNode);
    }

}
