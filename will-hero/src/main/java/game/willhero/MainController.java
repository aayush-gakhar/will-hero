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
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class MainController {

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
    public ImageView btnStartGame;

    @FXML
    public ImageView btnLoadGame;

    class customInterpolator extends Interpolator {
        @Override
//        protected double curve(double t) {
//            return Math.abs(0.5-t)*2 ;
//        }
        protected double curve(double var1) {
            return (var1 < 0.2D ? 2.7777777777777777D * var1 * var1 : 1.1111111111111112D * var1 - 0.1111111111111111D);
        }
    }

    public void initialize(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyValue yValue  = new KeyValue(hero.yProperty(), 130, Interpolator.EASE_IN);
        KeyFrame keyFrame  = new KeyFrame(Duration.millis(500), yValue);
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
        Timeline timeline2 = new Timeline();

        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(true);
        KeyValue cloudYValue  = new KeyValue(clouds.translateYProperty(), -20, Interpolator.EASE_BOTH);
        KeyValue cloudXValue  = new KeyValue(clouds.translateXProperty(), -30, Interpolator.EASE_BOTH);
        KeyFrame cloudKeyFrame  = new KeyFrame(Duration.millis(4000), cloudXValue, cloudYValue);
        timeline2.getKeyFrames().addAll(cloudKeyFrame);
        timeline2.play();
    }

    @FXML
    protected void onSoundButtonClick() {
        if (Main.isPlaySound()) {
            btnSound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnsound1.png"))));
            Main.stopSound();
        }else {
            btnSound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnsound0.png"))));
            Main.playSound();
        }
        Main.playButtonSound();
    }

    @FXML
    protected void onMusicButtonClick() {
        Main.playButtonSound();
        if(Main.isPlayMusic()){
            btnMusic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnmusic1.png"))));
            Main.stopMainMenuMusic();
        }else {
            btnMusic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnmusic0.png"))));
            Main.playMainMenuMusic();
        }
    }

    @FXML
    protected void onStartGameButtonClick() throws IOException {
        Main.playButtonSound();
        if(Main.isPlayMusic()) {
            Main.stopMainMenuMusic();
            Main.playGameMusic();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    protected void onLoadGameButtonClick() throws IOException {
        Main.playButtonSound();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loadGameMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        Main.getPrimaryStage().setScene(scene);
    }
}
