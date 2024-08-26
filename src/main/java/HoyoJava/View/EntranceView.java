package HoyoJava.View;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

public class EntranceView extends GridPane {
    private boolean sanitziedInput(String input) {
        if (input.length() == 0) {
            return false;
        }

        for (char c: input.toCharArray()) {
            if (!(Character.isDigit(c))) {
                return false;
            }
        }

        return true;
    }

    private void processInput(String input, Label output) {
        System.out.println(input + ": " + sanitziedInput(input));

        if (!sanitziedInput(input)) {
            output.setText("That is not your UID.");
            output.setTextFill(Color.RED);
            return;
        }
        
        Platform.runLater(() -> {
            output.setText("Attempting to fetch data...");
            output.setTextFill(Color.WHITE);
        });

        ClientTask clientTask = new ClientTask(input, output, this);

        new Thread(clientTask).start();
    }

    public EntranceView() {
        super();

        super.setStyle("-fx-background-color: #474747");
        super.setAlignment(Pos.CENTER);

        TextField inputID = new TextField();
        Label inputLabel = new Label("Enter UID:");
        Button inputButton = new Button("Fetch");
        Label checkLabel = new Label();

        inputLabel.setTextFill(Color.WHITE);

        inputButton.setMaxWidth(Double.MAX_VALUE);

        inputButton.setOnAction(event -> {
            processInput(inputID.getText(), checkLabel);
        });
    
        inputID.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                processInput(inputID.getText(), checkLabel);
            }
        });

        inputButton.setOnAction(event -> {
            processInput(inputID.getText(), checkLabel);
        });

        super.setVgap(10);

        super.add(inputLabel, 0, 0);
        super.add(inputID, 0, 1);
        super.add(inputButton, 0, 2);
        super.add(checkLabel, 0, 3);
    }

    public void changeStage(MainView mainView) {
        ((Stage)this.getScene().getWindow()).setScene(new Scene(mainView, 1200, 800));
    }
}
