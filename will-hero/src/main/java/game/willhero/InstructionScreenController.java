package game.willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class InstructionScreenController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView hero;

    @FXML
    private Group clouds;

    @FXML
    private ImageView btnSound;

    @FXML
    private ImageView btnMusic;

    @FXML
    private ImageView light;



    @FXML
    public void onSoundButtonClick() {
        Audio.onSoundButtonClick(btnSound);
    }

    @FXML
    public void onMusicButtonClick() {
        Audio.onMusicButtonClick(btnMusic,true);
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        Audio.playButtonSound();
        AnchorPane a = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        anchorPane.getChildren().setAll(a);
    }
}
