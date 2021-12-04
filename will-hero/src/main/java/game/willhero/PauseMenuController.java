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

public class PauseMenuController {

    @FXML
    private AnchorPane anchorPane;


    public void initialize(){

    }

    @FXML
    public void onPauseButtonClick() throws IOException {
//        if(!Main.getGame().isPaused()){
//            stopTimers();
//            pauseMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pauseMenu.fxml")));
//            anchorPane.getChildren().add(pauseMenu);
//            Main.getGame().setPaused(true);
//        }else {
//            anchorPane.getChildren().remove(pauseMenu);
//            startTimers();
//            Main.getGame().setPaused(false);
//        }
    }

    @FXML
    protected void onSave1ButtonClick() throws IOException {
        Audio.playButtonSound();
        if(Audio.isPlayMusic()) {
            Audio.stopMainMenuMusic();
            Audio.playGameMusic();
        }
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(loader.load());
        scene.setOnKeyPressed(event -> ((GameController)loader.getController()).keyPressed(event));
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    protected void onSave2ButtonClick() throws IOException {
        Audio.playButtonSound();
        if(Audio.isPlayMusic()) {
            Audio.stopMainMenuMusic();
            Audio.playGameMusic();
        }
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(loader.load());
        scene.setOnKeyPressed(event -> ((GameController)loader.getController()).keyPressed(event));
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    protected void onSave3ButtonClick() throws IOException {
        Audio.playButtonSound();
        if(Audio.isPlayMusic()) {
            Audio.stopMainMenuMusic();
            Audio.playGameMusic();
        }
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(loader.load());
        scene.setOnKeyPressed(event -> ((GameController)loader.getController()).keyPressed(event));
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    protected void onSave4ButtonClick() throws IOException {
        Audio.playButtonSound();
        if(Audio.isPlayMusic()) {
            Audio.stopMainMenuMusic();
            Audio.playGameMusic();
        }
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(loader.load());
        scene.setOnKeyPressed(event -> ((GameController)loader.getController()).keyPressed(event));
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    protected void onSave5ButtonClick() throws IOException {
        Audio.playButtonSound();
        if(Audio.isPlayMusic()) {
            Audio.stopMainMenuMusic();
            Audio.playGameMusic();
        }
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(loader.load());
        scene.setOnKeyPressed(event -> ((GameController)loader.getController()).keyPressed(event));
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        Audio.playButtonSound();
        AnchorPane a = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        anchorPane.getChildren().setAll(a);
    }
}
