package game.willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class GameOverMenuController {

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
    public void onReviveButtonClick() throws IOException {
        Audio.playButtonSound();
        if(Main.getGame().isRevivable()){
            Main.getGame().revive();
            Main.getGameController().onRevive();
        }else {
            System.out.println("You can't revive");
        }
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        Audio.playButtonSound();
        AnchorPane a = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        anchorPane.getChildren().setAll(a);
    }
}
