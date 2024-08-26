package HoyoJava.View;

import HoyoJava.Clients.Client;
import javafx.scene.layout.GridPane;
public class MainView extends GridPane {
    private Client client;

    public MainView(Client client) {
        super();
        super.setStyle("-fx-background-color: #474747");
        super.setGridLinesVisible(true);
        this.client = client;

        UserProfile userProfile = new UserProfile(client);
        
        super.add(userProfile, 0, 0);
    }

    public Client getClient() { return this.client; }
}
