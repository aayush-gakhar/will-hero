package game.willhero;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameController {

    private AnimationTimer timer;
    private Hero hero;
    private AnchorPane pauseMenu;
    private AnchorPane gameOverMenu;
    private AnchorPane saveGameMenu;
    private boolean firstFrame=true;
    private final List<Rocket> launchedRockets=new ArrayList<>();



    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Group clouds;

    @FXML
    private Group islands;

    @FXML
    private Group characters;

    @FXML
    private Group chests;

    @FXML
    public Group obstacles;

    @FXML
    private Group heroGroup;

    @FXML
    private ImageView btnSound;

    @FXML
    private ImageView btnMusic;

    @FXML
    private Text score;

    @FXML
    private Text coins;

    @FXML
    private Text swordLevel;

    @FXML
    private Text rocketLevel;

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }


    public void initialize(){
        Audio.setupButtons(btnSound, btnMusic,false);
        Main.setGameController(this);
        Main.setGameStarted(true);
        Main.getGame().setPaused(false);
        hero = Main.getGame().getHero();
        heroGroup.getChildren().add(hero);
        if(hero.getCurrentWeapon()!=null && hero.getCurrentWeapon().getLevel()>0){
            heroGroup.getChildren().add(hero.getCurrentWeapon());
        }
        score.setText(""+Main.getGame().getScore());
        coins.setText(""+Main.getGame().getCoins());
        swordLevel.setText(""+Main.getGame().getHero().getHelmet().getWeapon1Level());
        rocketLevel.setText(""+Main.getGame().getHero().getHelmet().getWeapon2Level());
        System.out.println("Game started");

        for(GameObject gameObject: Main.getGame().getCharacters()) {
            characters.getChildren().add(gameObject);
        }
        for(GameObject gameObject: Main.getGame().getChests()) {
            chests.getChildren().add(gameObject);
        }
        for(GameObject gameObject: Main.getGame().getObstacles()) {
            obstacles.getChildren().add(gameObject);
        }
        hero.setX(hero.getPosition().getX());
        hero.setY(hero.getPosition().getY());
        moveClouds();
        initializeTimers();
        startTimers();
        System.out.println(hero.getFitHeight()+" "+hero.getFitWidth());
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
    public void onSoundButtonClick() {
        Audio.onSoundButtonClick(btnSound);
    }

    @FXML
    public void onMusicButtonClick() {
        Audio.onMusicButtonClick(btnMusic,false);
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        System.out.println("Game ended");
        Audio.playButtonSound();
        Audio.changeToMenu();
        stopTimers();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Main.getPrimaryStage().setScene(new Scene(loader.load()));
        Main.setGameStarted(false);
    }

    @FXML
    public void onPauseButtonClick() throws IOException {
        if(Main.getGame().isGameOver())return;
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
            anchorPane.getChildren().remove(saveGameMenu);
            startTimers();
            Main.getGame().setPaused(false);
        }
    }

    @FXML
    public void onSwordButtonClick(){
        if(hero.getCurrentWeapon()==null || hero.getCurrentWeapon() instanceof Rocket) {
            heroGroup.getChildren().remove(hero.getCurrentWeapon());
            hero.setCurrentWeapon(hero.getHelmet().getWeapon1());
            System.out.println("Sword selected");
        }
    }

    @FXML
    public void onRocketButtonClick(){
        if(hero.getCurrentWeapon()==null || hero.getCurrentWeapon() instanceof Sword) {
            heroGroup.getChildren().remove(hero.getCurrentWeapon());
            hero.setCurrentWeapon(hero.getHelmet().getWeapon2());
            System.out.println("Rocket selected");
        }
    }

    public void onGameOver()throws IOException {
        Main.getGame().over();
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
        if (event.getCode() == KeyCode.SPACE) {
            if (Main.isGameStarted() && !Main.getGame().isPaused() && !Main.getGame().isGameOver()) {
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(hero.xProperty(), hero.getX() + 100, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
                Main.getGame().addScore(1);
                Audio.playHeroMoveSound();
                if(hero.getCurrentWeapon()!=null && hero.getCurrentWeapon().getLevel()>0 && hero.getCurrentWeapon().isProjectile()) {
                    Rocket rocket=new Rocket( hero.getCurrentWeapon().getLevel(),hero.getX() + hero.getWidth() / 2, hero.getY() + hero.getHeight() / 2);
                    heroGroup.getChildren().add(rocket);
                    launchedRockets.add(rocket);
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
                double deltaTime=(now-lastUpdate)/1000000000.0;
                if(deltaTime>0.02){
                    deltaTime=0.02;
                }

                //update ui
                score.setText(""+Main.getGame().getScore());
                coins.setText(""+Main.getGame().getCoins());
                swordLevel.setText(""+Main.getGame().getHero().getHelmet().getWeapon1Level());
                rocketLevel.setText(""+Main.getGame().getHero().getHelmet().getWeapon2Level());

                //collisions
                for (Node island : islands.getChildren()) {
                    if (GameObject.isColliding(hero, (ImageView) island)) {
                        hero.getSpeed().setY(-hero.getAcceleration().getY());
                        while (GameObject.isColliding(hero, (ImageView) island)) {
                            hero.move(deltaTime);
                        }
                    }
                }
                for(GameObject gameObject: Main.getGame().getChests()) {
                    for (Node island : islands.getChildren()) {
                        if (GameObject.isColliding(gameObject, (ImageView) island)) {
                            gameObject.getSpeed().setY(0);
                            gameObject.getAcceleration().setY(0);
                        }
                    }
                }
                for(GameObject gameObject: Main.getGame().getObstacles()) {
                    for (Node island : islands.getChildren()) {
                        if (GameObject.isColliding(gameObject, (ImageView) island)) {
                            gameObject.getSpeed().setY(0);
                            gameObject.getAcceleration().setY(0);
                        }
                    }
                }
                for(GameObject gameObject: Main.getGame().getCharacters()){
                    for (Node island : islands.getChildren()) {
                        if (GameObject.isColliding(gameObject, (ImageView) island)) {
                            if(gameObject.getPosition().getY()+50>((ImageView) island).getY()){
                                gameObject.getSpeed().setX(0);
                                continue;
                            }
                            gameObject.getSpeed().setY(-gameObject.getAcceleration().getY());
                            gameObject.getSpeed().setX(gameObject.getSpeed().getX() * 0.2);
                            while (GameObject.isColliding(gameObject, (ImageView) island)) {
                                gameObject.move(deltaTime);
                            }
                        }
                    }
                }
                for(GameObject character: Main.getGame().getCharacters()){
                    if(GameObject.isColliding(hero,character)){
                        character.getSpeed().setX(200);
//                        System.out.println(GameObject.collisionDirection(hero,character));
                    }
                }
                for (GameObject gameObject: Main.getGame().getChests()){
                    if(GameObject.isColliding(hero,gameObject)){
                        ((Chest)gameObject).open();
                    }
                }
                for (GameObject gameObject: Main.getGame().getObstacles()){
                    if(GameObject.isColliding(hero,gameObject)){
                        if(!((TNT)gameObject).isTimerStarted())
                            ((TNT)gameObject).startTimer();
                    }
                }

                //weapons
                if(hero.getCurrentWeapon()!=null && hero.getCurrentWeapon().getLevel()>0){
                    if (hero.getCurrentWeapon() != null && hero.getCurrentWeapon().getLevel() > 0 && !heroGroup.getChildren().contains(hero.getCurrentWeapon())) {
                        heroGroup.getChildren().add(hero.getCurrentWeapon());
                    }
                    if (hero.getCurrentWeapon() != null) {
                        hero.getCurrentWeapon().setX(hero.getX() + hero.getWidth() / 2);
                        hero.getCurrentWeapon().setY(hero.getY() + hero.getHeight() / 2);
                    }
                    if(!hero.getCurrentWeapon().isProjectile()){
                        for (GameObject gameObject : Main.getGame().getCharacters()) {
                            if (GameObject.isColliding(hero.getCurrentWeapon(), gameObject)) {
                                if (gameObject instanceof Orc) {
                                    if (!((Orc) gameObject).isDead()) {
                                        characters.getChildren().remove(gameObject);
                                        ((Orc) gameObject).die();
                                    }
                                }
                            }
                        }
                    }
                }
                for (Rocket rocket:launchedRockets){
                    if(!rocket.isExploded()){
                        rocket.update(deltaTime);
                        for (GameObject gameObject : Main.getGame().getCharacters()) {
                            if (GameObject.isColliding(rocket, gameObject)) {
                                if (gameObject instanceof Orc) {
                                    if (!((Orc) gameObject).isDead()) {
                                        characters.getChildren().remove(gameObject);
                                        ((Orc) gameObject).die();
                                        rocket.explode();
                                    }
                                }
                            }
                        }
                        for (Node island : islands.getChildren()) {
                            if (GameObject.isColliding(rocket, (ImageView) island)) {
                                rocket.explode();
                            }
                        }
                    }
                }

                //move frame
                if(hero.getPosition().getX()+islands.getTranslateX()>=300){
                    islands.setTranslateX(islands.getTranslateX()-(hero.getPosition().getX()+islands.getTranslateX()-300)/10.0);
                    characters.setTranslateX(islands.getTranslateX());
                    chests.setTranslateX(islands.getTranslateX());
                    obstacles.setTranslateX(islands.getTranslateX());
                    heroGroup.setTranslateX(islands.getTranslateX());
                }

                //move objects
                hero.update(deltaTime);

                if(hero.getPosition().getY()>705){
                    try {
                        onGameOver();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                for (GameObject gameObject:Main.getGame().getCharacters()) {
                    gameObject.accelerate(deltaTime);
                    gameObject.move(deltaTime);
                    if(gameObject.getPosition().getY()>185){
                        if(!((Orc)gameObject).isDead()) {
                            characters.getChildren().remove(gameObject);
                            ((Orc)gameObject).die();
                        }
                    }
                }
                for (GameObject gameObject:Main.getGame().getChests()) {
                    gameObject.update(deltaTime);
                }
                for (GameObject gameObject:Main.getGame().getObstacles()) {
                    gameObject.update(deltaTime);
                }

                //win Game
                if(Main.getGame().getScore()>=120){
                    stopTimers();
                    Audio.changeToMenu();
                    Main.getGame().win();
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("winGame.fxml")));
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load(),1024,768);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Main.getPrimaryStage().setScene(scene);
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
