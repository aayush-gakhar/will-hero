package game.willhero;

import javafx.animation.Interpolator;
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

public class GameOverMenuController {

    @FXML
    private AnchorPane anchorPane;


    public void initialize(){

    }


    @FXML
    protected void onHomeButtonClick() throws IOException {
        Audio.playButtonSound();
        Audio.changeToGame();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Main.getPrimaryStage().setScene(new Scene(loader.load()));
    }

    @FXML
    protected void onRestartButtonClick() throws IOException {
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
    protected void onBackButtonClick() throws IOException {
        Audio.playButtonSound();
        AnchorPane a = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        anchorPane.getChildren().setAll(a);
    }
}
