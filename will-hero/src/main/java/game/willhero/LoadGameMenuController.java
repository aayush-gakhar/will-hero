package game.willhero;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class LoadGameMenuController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public ImageView background;

    @FXML
    private ImageView hero;

    @FXML
    public Group clouds;

    @FXML
    private ImageView btnSound;

    @FXML
    private ImageView btnMusic;

    @FXML
    private ImageView btnBack;

    @FXML
    private Text save1;

    @FXML
    private Text save2;

    @FXML
    private Text save3;

    @FXML
    private Text save4;

    @FXML
    private Text save5;

    public void initialize(){
        MainController.menuAnimations(hero, clouds);
    }

    @FXML
    protected void onSoundButtonClick() {
        Audio.onSoundButtonClick(btnSound);
    }

    @FXML
    protected void onMusicButtonClick() {
        Audio.onMusicButtonClick(btnMusic);
    }

    @FXML
    protected void onSave1ButtonClick() throws IOException {
        Main.setGame(new Game());
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
        Main.setGame(new Game());
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
        Main.setGame(new Game());
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
        Main.setGame(new Game());
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
        Main.setGame(new Game());
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
