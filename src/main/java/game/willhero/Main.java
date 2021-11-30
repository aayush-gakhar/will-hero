package game.willhero;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class Main extends Application implements Serializable {


//    private static Main main;
    private boolean playMusic=true;
    private boolean playSound=true;

//    Main(){
//        main = this;
//    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public ImageView background;

    @FXML
    private ImageView hero;

    @FXML
    private ImageView btnSound;

    @FXML
    private ImageView btnMusic;

    public void initialize(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyValue yValue  = new KeyValue(hero.yProperty(), 130, Interpolator.EASE_IN);
        KeyFrame keyFrame  = new KeyFrame(Duration.millis(500), yValue);
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
    }


    @FXML
    protected void onSoundButtonClick() {
        if (playSound) {
            btnSound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnsound1.png"))));
            playSound=false;
        }else {
            btnSound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnsound0.png"))));
            playSound=true;
        }
    }

    @FXML
    protected void onMusicButtonClick() {
        if(playMusic){
            btnMusic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnmusic1.png"))));
            playMusic=false;
        }else {
            btnMusic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnmusic0.png"))));
            playMusic=true;
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Will Hero");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/icon-256.png"))));
        stage.setResizable(false);
        AnchorPane anchorPane= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        stage.setScene(new Scene(anchorPane, 1024, 768));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}