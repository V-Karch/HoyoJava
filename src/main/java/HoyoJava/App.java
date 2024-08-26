package HoyoJava;

import javafx.scene.Scene;
import javafx.stage.Stage;
import HoyoJava.View.EntranceView;
import javafx.application.Application;

/**
 * The main App class, being used for testing ATM
 * 
 * @author Luna Karch
 */
public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Testing!");
        stage.setResizable(false);

        EntranceView entranceScreen = new EntranceView();

        stage.setScene(new Scene(entranceScreen, 188, 150));
        stage.show();
    }
} // 613792348