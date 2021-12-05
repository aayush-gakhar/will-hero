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
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    private AnimationTimer timer;
    private Hero hero;
    private AnchorPane pauseMenu;
    private boolean paused;
    private Game game;



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
    public ImageView btnOptions;


    public void initialize(){
        Main.setGameStarted(true);
        Game game = Main.getGame();
        hero = game.getHero();
        System.out.println(hero.getPosition());
//        hero.setImage();
        ((Group)anchorPane.getChildren().get(3)).getChildren().add(hero);
        hero.setX(hero.getPosition().getX());
        hero.setY(hero.getPosition().getY());
        moveClouds();
        initializeTimers();
        startTimers();
    }

    public void moveClouds(){
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
    public void onBackButtonClick() throws IOException {
        Audio.playButtonSound();
        stopTimers();
        if(Audio.isPlayMusic()){
            Audio.stopGameMusic();
            Audio.playMainMenuMusic();
        }
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Main.getPrimaryStage().setScene(new Scene(loader.load()));
//        Main.setGameStarted(false);
    }

    @FXML
    public void onPauseButtonClick() throws IOException {
        if(!Main.getGame().isPaused()){
            stopTimers();
            pauseMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pauseMenu.fxml")));
            anchorPane.getChildren().add(pauseMenu);
            pauseMenu.setLayoutX(anchorPane.getWidth()/2 - 118);
            pauseMenu.setLayoutY(anchorPane.getHeight()/2 - 100);
            Main.getGame().setPaused(true);
        }else {
            anchorPane.getChildren().remove(pauseMenu);
            startTimers();
            Main.getGame().setPaused(false);
        }
    }

    @FXML
    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE -> {
                if(Main.isGameStarted()) {
                    Timeline timeline = new Timeline();
                    KeyValue keyValue = new KeyValue(hero.xProperty(), hero.getX()+100, Interpolator.EASE_BOTH);
                    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
                    timeline.getKeyFrames().add(keyFrame);
                    timeline.play();
                    Audio.playHeroMoveSound();
//                    hero.getPosition().add(new Vector(100,0));
                }
            }
        }
    }

    public void initializeTimers(){
        System.out.println(hero.getPosition().getX()+islands.getTranslateX());
        timer = new AnimationTimer() {
            long lastUpdate=System.nanoTime();
            @Override
            public void handle(long now) {
                double deltaTime=(now-lastUpdate)/1000000000.0;
                if(deltaTime>0.02){
                    deltaTime=0.02;
                }
                for (Node i:islands.getChildren()) {
                    if (GameObject.isColliding(hero, (ImageView) i)) {
                        hero.setSpeed(new Vector(0,-400));
                        while (GameObject.isColliding(hero, (ImageView) i)){
                            hero.move(deltaTime);
                        }
                    }
                }
                if(hero.getPosition().getX()+islands.getTranslateX()>=300){
//                    islands.setTranslateX(300-hero.getPosition().getX());
                    islands.setTranslateX(islands.getTranslateX()-(hero.getPosition().getX()+islands.getTranslateX()-300)/10.0);
                }
                hero.accelerate(deltaTime);
                hero.move(deltaTime);
                lastUpdate=now;
            }
        };
    }

    public void startTimers(){
        timer.start();
    }

    public void stopTimers(){
        timer.stop();
    }
}
