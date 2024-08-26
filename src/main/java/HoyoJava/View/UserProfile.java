package HoyoJava.View;

import HoyoJava.Clients.Client;
import HoyoJava.HSR.HSRProfile;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.GridPane;
import javafx.scene.input.ClipboardContent;

public class UserProfile extends GridPane {
    private Client client;

    public UserProfile(Client client) {
        super();
        this.client = client;
        super.setVgap(2);

        HSRProfile userProfile = this.client.getHSRProfile();

        WhiteLabel profileLabel = new WhiteLabel("User Profile\n");
        WhiteLabel nameLabel = new WhiteLabel("Name: " + userProfile.getNickname());
        WhiteLabel UIDLabel = new WhiteLabel("UID: " + userProfile.getUID());
        WhiteLabel levelLabel = new WhiteLabel("Level: " + userProfile.getLevel());
        WhiteLabel statusLabel = new WhiteLabel("Status: \"" + userProfile.getSignature() + "\"");
        WhiteLabel friendsLabel = new WhiteLabel("Friends: " + userProfile.getFriendCount());
        WhiteLabel worldLevelLabel = new WhiteLabel("Equilibrium: " + userProfile.getWorldLevel());
        WhiteLabel achievementsLabel = new WhiteLabel("Achievements: " + userProfile.getSpaceInfo().getAchievementCount());
        Button copyButton = new Button("Copy UID");
        WhiteLabel copyNotification = new WhiteLabel("");

        copyButton.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(userProfile.getUID());
            clipboard.setContent(content);
            copyNotification.setText("Copied!");
        });

        copyButton.setMaxWidth(Double.MAX_VALUE);

        Image avatar = new Image(userProfile.getAvatar().getUrl());
        ImageView avatarView = new ImageView(avatar);

        avatarView.setPreserveRatio(true);
        avatarView.setFitWidth(50);

        super.add(profileLabel, 0, 0);
        super.add(avatarView, 0, 1);
        super.add(nameLabel, 0, 2);
        super.add(UIDLabel, 0, 3);
        super.add(levelLabel, 0, 4);
        super.add(worldLevelLabel, 0, 5);
        super.add(statusLabel, 0, 6);
        super.add(friendsLabel, 0, 7);
        super.add(achievementsLabel, 0, 8);
        super.add(copyButton, 0, 9);
        super.add(copyNotification, 0, 10);
    }
}
