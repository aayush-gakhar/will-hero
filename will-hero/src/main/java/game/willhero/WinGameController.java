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

public class WinGameController {

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
    private ImageView light;

    public void initialize(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyValue yValue  = new KeyValue(light.rotateProperty(), 360, Interpolator.LINEAR);
        KeyFrame keyFrame  = new KeyFrame(Duration.millis(10000), yValue);
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
        Audio.onSoundButtonClick(btnSound);
    }

    @FXML
    protected void onMusicButtonClick() {
        Audio.onMusicButtonClick(btnMusic);
    }

    @FXML
    protected void onSave1ButtonClick() throws IOException, ClassNotFoundException {
        onSaveiButtonClick(1);
    }

    @FXML
    protected void onSave2ButtonClick() throws IOException, ClassNotFoundException {
        onSaveiButtonClick(2);
    }

    @FXML
    protected void onSave3ButtonClick() throws IOException {
        onSaveiButtonClick(3);
    }

    @FXML
    protected void onSave4ButtonClick() throws IOException {
        onSaveiButtonClick(4);
    }

    @FXML
    protected void onSave5ButtonClick() throws IOException {
        onSaveiButtonClick(5);
    }

    public void onSaveiButtonClick(int i) throws IOException {
        try{
            Main.setGame(new Game(Game.deserialize(i)));
            if (Main.getGame()==null){
                throw new Exception("Save file empty");
            }
        }catch (Exception e){
            System.out.println(e);
            Main.setGame(new Game());
        }
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
