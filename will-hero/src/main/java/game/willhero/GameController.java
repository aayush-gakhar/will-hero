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

public class GameController {

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

    static class customInterpolator extends Interpolator {
        @Override
//        protected double curve(double t) {
//            return Math.abs(0.5-t)*2 ;
//        }
        protected double curve(double var1) {
            return (var1 < 0.2D ? 2.7777777777777777D * var1 * var1 : 1.1111111111111112D * var1 - 0.1111111111111111D);
        }
    }

    public void initialize(){
        MainController.menuAnimations(hero, clouds);
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
    protected void onBackButtonClick() throws IOException {
        Main.playButtonSound();
        if(Main.isPlayMusic()){
            Main.stopGameMusic();
            Main.playMainMenuMusic();
        }
        AnchorPane a = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        anchorPane.getChildren().setAll(a);
    }
}
