package mainClasses;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class TitlePane extends BorderPane {

    private TextField homeCityBox;
    private TextField destCityBox;
    Button acceptButton;
    private String startMessage = "Podaj nazwę miasta w ktorym się obecnie znajdujesz oraz miasta do którego " +
            "się wybierasz. Jeśli miasto znajduje się poza granicami Polski, wpisz je w języku angielskim.";
    private Label errorMessage;

    TitlePane() {

        Text appTitle = new Text("PogodynkApp");
        appTitle.setId("appTitle_style");
        Text appSubtitle = new Text("Nie daj się już nigdy zaskoczyć pogodzie");
        appSubtitle.setId("appSubtitle_style");
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
        VBox topNode = new VBox(appTitle, appSubtitle);
        topNode.getStyleClass().add("titleNode_style");
        topNode.setId("titleNodeTop_style");
        VBox centerNode = new VBox(homeCityBox, destCityBox, acceptButton);
        centerNode.getStyleClass().add("titleNode_style");
        centerNode.setId("titleNodeCenter_style");
        VBox bottomNode = new VBox(errorMessage);
        bottomNode.getStyleClass().add("titleNode_style");
        bottomNode.setId("titleNodeBottom_style");

        this.setTop(topNode);
        this.setCenter(centerNode);
        this.setBottom(bottomNode);
    }


    void changeHomeCityBoxClass(String classToBeAdded, String classToBeRemoved){
        this.homeCityBox.getStyleClass().remove(classToBeRemoved);
        this.homeCityBox.getStyleClass().add(classToBeAdded);
    }

    void changeDestCityBoxClass(String classToBeAdded, String classToBeRemoved){
        this.destCityBox.getStyleClass().remove(classToBeRemoved);
        this.destCityBox.getStyleClass().add(classToBeAdded);
    }

    String getHomeCityBox() {
        return homeCityBox.getText();
    }

    String getDestCityBox() {
        return destCityBox.getText();
    }

    String getStartMessage() {
        return startMessage;
    }

    Label getErrorMessage() {
        return errorMessage;
    }
}
