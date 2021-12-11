package game.willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class SaveGameMenuController {
    public void initialize(){

    }

    @FXML
    public void onSave1ButtonClick() throws IOException, ClassNotFoundException {
        onSaveibuttonClick(1);
    }

    @FXML
    public void onSave2ButtonClick() throws IOException, ClassNotFoundException {
        onSaveibuttonClick(2);
    }

    @FXML
    public void onSave3ButtonClick() throws IOException, ClassNotFoundException {
        onSaveibuttonClick(3);
    }

    @FXML
    public void onSave4ButtonClick() throws IOException, ClassNotFoundException {
        onSaveibuttonClick(4);
    }

    @FXML
    public void onSave5ButtonClick() throws IOException, ClassNotFoundException {
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
    public void onBackButtonClick() throws IOException {
        Main.getGameController().onPauseButtonClick();
    }
}
