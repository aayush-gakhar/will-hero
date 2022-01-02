package game.willhero;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView hero;

    @FXML
    private Group clouds;

    @FXML
    private Group buttons;

    @FXML
    private ImageView btnSound;

    @FXML
    private ImageView btnMusic;

    @FXML
    private ImageView btnStartGame;

    @FXML
    private ImageView btnLoadGame;

    @FXML
    private ImageView btnExit;

    public void initialize(){
        Audio.setupButtons(btnSound, btnMusic,true);
        menuAnimations(hero, clouds);
    }

    public static void menuAnimations(ImageView hero, Group clouds) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyValue yValue  = new KeyValue(hero.yProperty(), 120, Interpolator.EASE_IN);
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
    public void onSoundButtonClick() {
        Audio.onSoundButtonClick(btnSound);
    }

    @FXML
    public void onMusicButtonClick() {
        Audio.onMusicButtonClick(btnMusic,true);
    }

    @FXML
    public void onStartGameButtonClick() throws IOException {
        Audio.playButtonSound();
        Audio.changeToGame();
        Main.setGame(new Game());
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(loader.load(),1024,768);
        scene.setOnKeyPressed(event -> ((GameController)loader.getController()).keyPressed(event));
        Main.getPrimaryStage().setScene(scene);


//        AnchorPane a = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));
//        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void onLoadGameButtonClick() throws IOException {
        Audio.playButtonSound();
        AnchorPane a = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loadGameMenu.fxml")));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void onExitButtonClick() {
        Audio.playButtonSound();
        System.exit(0);
    }

    @FXML
    public void onInstructionButton() throws IOException {
        AnchorPane a = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("instructionScreen.fxml")));
        anchorPane.getChildren().setAll(a);
    }
}
