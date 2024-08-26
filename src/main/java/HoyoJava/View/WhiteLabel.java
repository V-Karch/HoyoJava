package HoyoJava.View;

import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class WhiteLabel extends Label {
    public WhiteLabel(String text) {
        super(text);
        super.setTextFill(Color.WHITE);
        super.setStyle("-fx-font: 16 arial;");
    }
}
