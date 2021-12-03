package game.willhero;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Group clouds;

    @FXML
    private Group islands;

    @FXML
    private ImageView btnSound;

    @FXML
    private ImageView btnMusic;

    @FXML
    private ImageView btnBack;

    @FXML
    private ImageView island1;

    private Hero hero;



    public void initialize(){
        Main.setGameStarted(true);
        hero = new Hero(61,-183);
        ((Group)anchorPane.getChildren().get(3)).getChildren().add(hero);
//        MainController.menuAnimations(hero, clouds);
        anchorPane.setOnKeyPressed((event) -> System.out.println("key pressed"));
//        anchorPane.getChildren().add(new Hero(121,337));
        startTimers();
    }

    @FXML
    public void onSoundButtonClick() {
        if (Audio.isPlaySound()) {
            btnSound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnsound1.png"))));
            Audio.stopSound();
        }else {
            btnSound.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnsound0.png"))));
            Audio.playSound();
        }
        Audio.playButtonSound();
    }

    @FXML
    public void onMusicButtonClick() {
        Audio.playButtonSound();
        if(Audio.isPlayMusic()){
            btnMusic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnmusic1.png"))));
            Audio.stopGameMusic();
        }else {
            btnMusic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/btnmusic0.png"))));
            Audio.playGameMusic();
        }
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        Audio.playButtonSound();
        if(Audio.isPlayMusic()){
            Audio.stopGameMusic();
            Audio.playMainMenuMusic();
        }
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Main.getPrimaryStage().setScene(new Scene(loader.load()));
//        Main.setGameStarted(false);
    }

    @FXML
    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE -> {
                if(Main.isGameStarted()) {
                    Audio.playHeroMoveSound();
                    hero.getPosition().add(new Vector(100,0));
                }
            }
        }
    }


    public void startTimers(){
        AnimationTimer timer = new AnimationTimer() {
            long lastUpdate=System.nanoTime();
            @Override
            public void handle(long now) {
                double deltaTime=(now-lastUpdate)/1000000000.0;
                for (Node i:islands.getChildren()) {
                    if (GameObject.isColliding(hero, (ImageView) i)) {
                        hero.setSpeed(new Vector(0,-400));
//                        break;
                    }
                }
                if(hero.getPosition().getX()>=300){
                    anchorPane.getChildren().get(4).setTranslateX(300-hero.getPosition().getX());
                }
                hero.accelerate(deltaTime);
                hero.move(deltaTime);
                lastUpdate=now;

            }
        };
        timer.start();
    }
}
