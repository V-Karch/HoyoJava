package HoyoJava.View;

import javafx.concurrent.Task;
import HoyoJava.Clients.Client;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.application.Platform;

public class ClientTask extends Task<Client> {

    private Label output;
    private String input;
    private EntranceView entranceView;

    public ClientTask(String input, Label output, EntranceView entranceView) {
        this.input = input;
        this.output = output;
        this.entranceView = entranceView;
    }

    @Override
    protected Client call() throws Exception {
        Client client = new Client(input);

        if (client.hasNullSource()) {
            throw new Error("UID Invalid");
        }

        return client;
    }

    @Override
    protected void failed() {
        Platform.runLater(() -> {
            output.setText("Failed to retrieve data.");
            output.setTextFill(Color.RED);
        }); 
    }

    @Override
    public void succeeded() {
        Platform.runLater(() -> {
            output.setText("Success.");
            output.setTextFill(Color.WHITE);

            MainView mainView = new MainView(getValue());
            this.entranceView.changeStage(mainView);
        });
    }
}
