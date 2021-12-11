package game.willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class PauseMenuController {

    @FXML
    private AnchorPane anchorPane;


    public void initialize(){

    }

    @FXML
    public void onHomeButtonClick() throws IOException {
        Audio.playButtonSound();
        Audio.changeToMenu();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Main.getPrimaryStage().setScene(new Scene(loader.load()));
    }

    @FXML
    public void onRestartButtonClick() throws IOException {
        Main.setGame(new Game());
        Audio.playButtonSound();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(loader.load());
        scene.setOnKeyPressed(event -> ((GameController)loader.getController()).keyPressed(event));
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    public void onSaveButtonClick() throws IOException {
        Audio.playButtonSound();
        Main.getGameController().onSaveMenuOpen();
    }

}
