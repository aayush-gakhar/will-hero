package game.willhero;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    private AnimationTimer timer;
    private Hero hero;
    private AnchorPane pauseMenu;
    private AnchorPane gameOverMenu;
    private AnchorPane saveGameMenu;
    private boolean paused;
    private boolean firstFrame=true;



    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView background;

    @FXML
    private Group clouds;

    @FXML
    private Group islands;

    @FXML
    private Group characters;

    @FXML
    private ImageView btnSound;

    @FXML
    private ImageView btnMusic;

    @FXML
    private ImageView btnBack;

    @FXML
    public ImageView btnOptions;

    @FXML
    private Text score;

    @FXML
    private Text coins;

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public AnchorPane getSaveGameMenu() {
        return saveGameMenu;
    }


    public void initialize(){
        Main.setGameController(this);
        Main.setGameStarted(true);
        Main.getGame().setPaused(false);
        hero = Main.getGame().getHero();
        score.setText(""+Main.getGame().getScore());
        coins.setText(""+Main.getGame().getCoins());
        System.out.println("Game started");
        for(GameObject object: Main.getGame().getCharacters()) {
            characters.getChildren().add(object);
        }
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
        System.out.println("Game ended");
        Audio.playButtonSound();
        stopTimers();
        if(Audio.isPlayMusic()){
            Audio.stopGameMusic();
            Audio.playMainMenuMusic();
        }
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Main.getPrimaryStage().setScene(new Scene(loader.load()));
        Main.setGameStarted(false);
    }

    @FXML
    public void onPauseButtonClick() throws IOException {
        if(!Main.getGame().isPaused()){
            System.out.println("Game paused");
            stopTimers();
            pauseMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pauseMenu.fxml")));
            anchorPane.getChildren().add(pauseMenu);
            pauseMenu.setLayoutX(anchorPane.getWidth()/2 - 118);
            pauseMenu.setLayoutY(anchorPane.getHeight()/2 - 100);
            Main.getGame().setPaused(true);
        }else {
            System.out.println("Game resumed");
            anchorPane.getChildren().remove(pauseMenu);
            startTimers();
            Main.getGame().setPaused(false);
        }
    }

    public void onGameOver()throws IOException {
        System.out.println("Game over");
        stopTimers();
        gameOverMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameOverMenu.fxml")));
        anchorPane.getChildren().add(gameOverMenu);
        gameOverMenu.setLayoutX(anchorPane.getWidth()/2 - 118);
        gameOverMenu.setLayoutY(anchorPane.getHeight()/2 - 100);
//            System.out.println("Game resumed");
//            anchorPane.getChildren().remove(pauseMenu);
//            startTimers();
//            Main.getGame().setPaused(false);
    }

    public void onSaveMenuOpen()throws IOException {
        System.out.println("Save game");
        anchorPane.getChildren().remove(pauseMenu);
        saveGameMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("saveGameMenu.fxml")));
        anchorPane.getChildren().add(saveGameMenu);
        saveGameMenu.setLayoutX(anchorPane.getWidth()/2 - 200);
        saveGameMenu.setLayoutY(anchorPane.getHeight()/2 - 300);
    }

    public void onRevive()throws IOException {
        System.out.println("Revived!");
        anchorPane.getChildren().remove(gameOverMenu);
        startTimers();
    }

    @FXML
    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE -> {
                if(Main.isGameStarted() && !Main.getGame().isPaused() && !Main.getGame().isGameOver()){
                    Timeline timeline = new Timeline();
                    KeyValue keyValue = new KeyValue(hero.xProperty(), hero.getX()+100, Interpolator.EASE_BOTH);
                    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
                    timeline.getKeyFrames().add(keyFrame);
                    timeline.play();
                    Main.getGame().addScore(1);
                    Audio.playHeroMoveSound();
                }
            }
        }
    }

    public void initializeTimers(){
        timer = new AnimationTimer() {
            long lastUpdate=System.nanoTime();
            @Override
            public void handle(long now) {
                if(firstFrame){
                    firstFrame = false;
                    lastUpdate= now;
                    return;
                }
                score.setText(""+Main.getGame().getScore());
                coins.setText(""+Main.getGame().getCoins());
                double deltaTime=(now-lastUpdate)/1000000000.0;
                if(deltaTime>0.02){
                    deltaTime=0.02;
                }
                for(GameObject gameObject: Main.getGame().getCharacters()){
                    for (Node i : islands.getChildren()) {
                        if (GameObject.isColliding(gameObject, (ImageView) i)) {
                            gameObject.setSpeed(gameObject.getAcceleration().scale(-1));
                            while (GameObject.isColliding(gameObject, (ImageView) i)) {
                                gameObject.move(deltaTime);
                            }
                        }
                    }
                }
                if(hero.getPosition().getX()+islands.getTranslateX()>=300){
//                    islands.setTranslateX(300-hero.getPosition().getX());
                    islands.setTranslateX(islands.getTranslateX()-(hero.getPosition().getX()+islands.getTranslateX()-300)/10.0);
                    characters.setTranslateX(islands.getTranslateX()-(hero.getPosition().getX()+islands.getTranslateX()-300)/10.0);
                }
                for (GameObject object:Main.getGame().getCharacters()) {
                    object.accelerate(deltaTime);
                    object.move(deltaTime);
                }

                if(hero.getPosition().getY()>185){
                    Main.getGame().over();
                    System.out.println("Game Over");
                    stopTimers();
                    try {
                        onGameOver();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                lastUpdate=now;
            }
        };
    }

    public void startTimers(){
        firstFrame=true;
        timer.start();
    }

    public void stopTimers(){
        timer.stop();
    }
}
