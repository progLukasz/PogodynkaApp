package mainClasses;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class CentralPane extends GridPane {

    public Button firstDayButton;
    public Button secondDayButton;
    public Button thirdDayButton;
    public Button forthDayButton;
    public Button fifthDayButton;
    private VBox top;
    private VBox middLeft;
    private VBox center;
    private VBox middRight;
    private VBox bottom;

    public CentralPane(Button firstDayButton, Button secondDayButton, Button thirdDayButton, Button forthDayButton, Button fifthDayButton) {
        this.firstDayButton = firstDayButton;
        this.secondDayButton = secondDayButton;
        this.thirdDayButton = thirdDayButton;
        this.forthDayButton = forthDayButton;
        this.fifthDayButton = fifthDayButton;
        this.top = new VBox();
        this.middLeft = new VBox();
        this.center = new VBox();
        this.middRight = new VBox();
        this.bottom = new VBox();
        HBox hbox = new HBox(this.firstDayButton, this.secondDayButton, this.thirdDayButton,
                this.forthDayButton, this.fifthDayButton);
        top.getChildren().add(hbox);
        File file = new File("img/vs-icon.jpg");
        Image vsImage = new Image(file.toURI().toString());
        ImageView vs = new ImageView(vsImage);
        vs.setFitHeight(200);
        vs.setFitWidth(200);
        center.getChildren().add(vs);
        this.add(top, 0, 0,3,1);
        this.add(middLeft,0,1,1,1);
        this.add(center,1,1,1,1);
        this.add(middRight,2,1,1,1);
        this.add(bottom,0,2,3,1);

    }


}
