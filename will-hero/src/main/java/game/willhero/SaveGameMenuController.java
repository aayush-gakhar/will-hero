package game.willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class SaveGameMenuController {
    public void initialize(){

    }

    @FXML
    protected void onSave1ButtonClick() throws IOException, ClassNotFoundException {
        onSaveibuttonClick(1);
    }

    @FXML
    protected void onSave2ButtonClick() throws IOException, ClassNotFoundException {
        onSaveibuttonClick(2);
    }

    @FXML
    protected void onSave3ButtonClick() throws IOException, ClassNotFoundException {
        onSaveibuttonClick(3);
    }

    @FXML
    protected void onSave4ButtonClick() throws IOException, ClassNotFoundException {
        onSaveibuttonClick(4);
    }

    @FXML
    protected void onSave5ButtonClick() throws IOException, ClassNotFoundException {
        onSaveibuttonClick(5);
    }

    public void onSaveibuttonClick(int i) throws IOException, ClassNotFoundException{
        Main.getGame().serialize(i);
        Audio.playButtonSound();
        Audio.changeToMenu();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Scene scene = new Scene(loader.load());
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        Main.getGameController().onPauseButtonClick();
    }
}
