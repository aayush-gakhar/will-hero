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
import java.util.Iterator;
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
    private Boss boss;

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

    public Group getCharacters(){
        return characters;
    }


    public void initialize(){
        Audio.setupButtons(btnSound, btnMusic,false);
        Main.setGameController(this);
        Main.setGameStarted(true);
        Game.getInstance().setPaused(false);
        hero = Game.getInstance().getHero();
        heroGroup.getChildren().add(hero);
        if(hero.getCurrentWeapon()!=null && hero.getCurrentWeapon().getLevel()>0){
            heroGroup.getChildren().add(hero.getCurrentWeapon());
        }
        score.setText(""+Game.getInstance().getScore());
        coins.setText(""+Game.getInstance().getCoins());
        swordLevel.setText(""+Game.getInstance().getHero().getHelmet().getWeapon1Level());
        rocketLevel.setText(""+Game.getInstance().getHero().getHelmet().getWeapon2Level());
        System.out.println("Game started");

        for(GameObject gameObject: Game.getInstance().getCharacters()) {
            characters.getChildren().add(gameObject);
        }
        boss = Game.getInstance().getBoss();
        for(GameObject gameObject: Game.getInstance().getChests()) {
            chests.getChildren().add(gameObject);
        }
        for(GameObject gameObject: Game.getInstance().getObstacles()) {
            obstacles.getChildren().add(gameObject);
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
        if(Game.getInstance().isGameOver())return;
        if(!Game.getInstance().isPaused()){
            System.out.println("Game paused");
            stopTimers();
            pauseMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pauseMenu.fxml")));
            anchorPane.getChildren().add(pauseMenu);
            pauseMenu.setLayoutX(anchorPane.getWidth()/2 - 118);
            pauseMenu.setLayoutY(anchorPane.getHeight()/2 - 100);
            Game.getInstance().setPaused(true);
        }else {
            System.out.println("Game resumed");
            anchorPane.getChildren().remove(pauseMenu);
            anchorPane.getChildren().remove(saveGameMenu);
            startTimers();
            Game.getInstance().setPaused(false);
        }
    }

    @FXML
    public void onSwordButtonClick(){
        if(hero.getCurrentWeapon()==null || hero.getCurrentWeapon() instanceof Rocket) {
            heroGroup.getChildren().remove(hero.getCurrentWeapon());
            hero.setCurrentWeapon(hero.getHelmet().getWeapon1());
            System.out.println("Sword selected");
        }else {
            heroGroup.getChildren().remove(hero.getCurrentWeapon());
            hero.setCurrentWeapon(null);
        }
    }

    @FXML
    public void onRocketButtonClick(){
        if(hero.getCurrentWeapon()==null || hero.getCurrentWeapon() instanceof Sword) {
            heroGroup.getChildren().remove(hero.getCurrentWeapon());
            hero.setCurrentWeapon(hero.getHelmet().getWeapon2());
            System.out.println("Rocket selected");
        }else {
            heroGroup.getChildren().remove(hero.getCurrentWeapon());
            hero.setCurrentWeapon(null);
        }
    }

    public void onGameOver()throws IOException {
        Game.getInstance().over();
        System.out.println("Game over");
        stopTimers();
        gameOverMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameOverMenu.fxml")));
        anchorPane.getChildren().add(gameOverMenu);
        gameOverMenu.setLayoutX(anchorPane.getWidth()/2 - 118);
        gameOverMenu.setLayoutY(anchorPane.getHeight()/2 - 100);
//            System.out.println("Game resumed");
//            anchorPane.getChildren().remove(pauseMenu);
//            startTimers();
//            Game.getInstance().setPaused(false);
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
            if (Main.isGameStarted() && !Game.getInstance().isPaused() && !Game.getInstance().isGameOver()) {
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(hero.xProperty(), hero.getX() + 100, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
                Game.getInstance().addScore(1);
                Audio.playHeroMoveSound();
                if(hero.getCurrentWeapon()!=null && hero.getCurrentWeapon().getLevel()>0 && hero.getCurrentWeapon().isProjectile()) {
                    Rocket rocket=Factory.createRocket(hero.getCurrentWeapon().getLevel(),hero.getX() + hero.getWidth() / 2, hero.getY() + hero.getHeight() / 2);
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
                deltaTime+=(deltaTime*Game.getInstance().getScore()/300);
//                if(deltaTime>0.02){
//                    deltaTime=0.02;
//                }

                //update ui
                score.setText(""+Game.getInstance().getScore());
                coins.setText(""+Game.getInstance().getCoins());
                swordLevel.setText(""+Game.getInstance().getHero().getHelmet().getWeapon1Level());
                rocketLevel.setText(""+Game.getInstance().getHero().getHelmet().getWeapon2Level());

                List<Orc> dead;
                //collisions
                //hero-islands
                for (Node island : islands.getChildren()) {
                    if (GameObject.isColliding(hero, (ImageView) island)) {
                        if(hero.getY()<((ImageView) island).getY()+((ImageView) island).getImage().getHeight()/2){
                            hero.getSpeed().setY(-hero.getAcceleration().getY());
                            while (GameObject.isColliding(hero, (ImageView) island)) {
                                hero.move(deltaTime);
                            }
                        }
                    }
                }
                //chest-islands, chest-hero
                for(Chest gameObject: Game.getInstance().getChests()) {
                    for (Node island : islands.getChildren()) {
                        if (GameObject.isColliding(gameObject, (ImageView) island)) {
                            gameObject.getSpeed().setY(0);
                            gameObject.getAcceleration().setY(0);
                        }
                    }
                    if(GameObject.isColliding(hero,gameObject)){
                        gameObject.open();
                    }
                }
                //TNT-islands, TNT-hero
                for(TNT gameObject: Game.getInstance().getObstacles()) {
                    for (Node island : islands.getChildren()) {
                        if (GameObject.isColliding(gameObject, (ImageView) island)) {
                            gameObject.getSpeed().setY(0);
                            gameObject.getAcceleration().setY(0);
                        }
                    }
                    if(GameObject.isColliding(hero,gameObject)){
                        if(!gameObject.isTimerStarted())
                            gameObject.startTimer();
                    }
                }
                //orc-orc, orc-islands
                dead = new ArrayList<>();
//                for(GameObject gameObject: Game.getInstance().getCharacters()){
                Iterator<Orc> iterator = Game.getInstance().getCharacters().iterator();
                while (iterator.hasNext()) {
                    Orc gameObject=iterator.next();
                    boolean flag=false;
                    for (GameObject gameObject1: Game.getInstance().getCharacters()) {
                        if(gameObject1==gameObject){
                            flag=true;
                        }else if(flag){
                            if(GameObject.isColliding(gameObject,gameObject1)){
                                double x=gameObject.getSpeed().getX();
                                gameObject.getSpeed().setX(gameObject1.getSpeed().getX());
                                gameObject1.getSpeed().setX(x);
                            }
                        }
                    }
                    for (Node island : islands.getChildren()) {
                        if (GameObject.isColliding(gameObject, (ImageView) island)) {
                            if(gameObject.getPosition().getY()>((ImageView) island).getY() || gameObject.getPosition().getX()+gameObject.getWidth()-10<((ImageView) island).getX()){
                                gameObject.getSpeed().setX(0);
                                continue;
                            }
                            gameObject.getSpeed().setY(-gameObject.getJumpHeight());
                            gameObject.getSpeed().setX(gameObject.getSpeed().getX() * 0.2);
                            while (GameObject.isColliding(gameObject, (ImageView) island)) {
                                gameObject.move(deltaTime);
                            }
                            break;
                        }
                    }
                    for (TNT tnt: Game.getInstance().getObstacles()) {
                        if(GameObject.isColliding(gameObject,tnt)){
                            if(!tnt.isExploded()){
                                characters.getChildren().remove(gameObject);
//                                dead.add(gameObject);
                                iterator.remove();
                                gameObject.die();
                                dead.addAll(tnt.explode());
                            }
                        }
                    }
                }
                Game.getInstance().getCharacters().removeAll(dead);
                //hero-orc
                for(GameObject character: Game.getInstance().getCharacters()){
                    if(GameObject.isColliding(hero,character)){
                        if(hero.getPosition().getX() + hero.getWidth() > character.getPosition().getX() + 10 &&
                                hero.getPosition().getX() < character.getPosition().getX() + character.getWidth() -10){
                            if (hero.getPosition().getY() > character.getPosition().getY() + character.getHeight() - 20 &&
                                    hero.getPosition().getY() < character.getPosition().getY() + character.getHeight() - 10) {
                                try {
                                    System.out.println("below orc");
                                    onGameOver();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else if(hero.getPosition().getY() < character.getPosition().getY()){
                                //hero above orc
                                double y=hero.getSpeed().getY();
                                hero.getSpeed().setY(character.getSpeed().getY());
                                character.getSpeed().setY(y);
                            }
//                            else {
//                                character.getSpeed().setX(200);
//                                while (GameObject.isColliding(hero, character)) {
//                                    character.move(deltaTime);
//                                }
//                            }
                        }else if(hero.getPosition().getX() < character.getPosition().getX()){
                            character.getSpeed().setX(200);
                            while (GameObject.isColliding(hero, character)) {
                                character.move(deltaTime);
                            }
                        }
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
//                        dead = new ArrayList<>();
//                        for (GameObject gameObject : Game.getInstance().getCharacters()) {
                        iterator = Game.getInstance().getCharacters().iterator();
                        while (iterator.hasNext()) {
                            Orc gameObject=iterator.next();
                            if (GameObject.isColliding(hero.getCurrentWeapon(), gameObject)) {
                                if (!gameObject.isDead()) {
                                    characters.getChildren().remove(gameObject);
//                                        dead.add(gameObject);
                                    iterator.remove();
                                    gameObject.die();
                                }
                            }
                        }
//                        Game.getInstance().getCharacters().removeAll(dead);
                    }
                }
                List<Rocket> deadrockets = new ArrayList<>();
                
                for (Rocket rocket:launchedRockets){
                    if(!rocket.isExploded()){
                        rocket.update(deltaTime);
//                        dead = new ArrayList<>();
//                        for (GameObject gameObject : Game.getInstance().getCharacters()) {
                        iterator = Game.getInstance().getCharacters().iterator();
                        while (iterator.hasNext()) {
                            Orc gameObject=iterator.next();
                            if (GameObject.isColliding(rocket, gameObject)) {
                                if (!gameObject.isDead()) {
                                    gameObject.takeDamage(rocket.getDamage());
                                    if(gameObject.getHealth()<=0) {
                                        characters.getChildren().remove(gameObject);
//                                            dead.add(gameObject);
                                        iterator.remove();
                                        gameObject.die();
                                    }
                                    rocket.explode();
                                }
                            }
                        }
//                        Game.getInstance().getCharacters().removeAll(dead);
                        for (Node island : islands.getChildren()) {
                            if (GameObject.isColliding(rocket, (ImageView) island)) {
                                rocket.explode();
                            }
                        }
                        for (TNT gameObject : Game.getInstance().getObstacles()) {
                            if (GameObject.isColliding(rocket, gameObject)) {
                                if(!gameObject.isExploded()){
                                    rocket.explode();
                                    gameObject.destroy();
                                }
                            }
                        }
                    }else {
                        deadrockets.add(rocket);
                    }
                }launchedRockets.removeAll(deadrockets);

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
                        System.out.println("fall down");
                        onGameOver();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                dead = new ArrayList<>();
                iterator = Game.getInstance().getCharacters().iterator();
                while (iterator.hasNext()) {
                    Orc gameObject=iterator.next();
//                for (GameObject gameObject : Game.getInstance().getCharacters()) {
                    gameObject.accelerate(deltaTime);
                    gameObject.move(deltaTime);
                    if (gameObject.getPosition().getY() > 185) {
                        if (!gameObject.isDead()) {
                            characters.getChildren().remove(gameObject);
//                            dead.add(gameObject);
                            gameObject.die();
                            iterator.remove();
                        }
                    }
                }
//                Game.getInstance().getCharacters().removeAll(dead);
                for (GameObject gameObject:Game.getInstance().getChests()) {
                    gameObject.update(deltaTime);
                }
                for (GameObject gameObject:Game.getInstance().getObstacles()) {
                    gameObject.update(deltaTime);
                }

                //win Game
                if(Game.getInstance().getScore()>=125){
                    stopTimers();
                    Audio.changeToMenu();
                    Game.getInstance().win();
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("winGame.fxml")));
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load(),1024,768);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Main.getPrimaryStage().setScene(scene);
                    Main.setGameStarted(false);
                }
                //win game
                if(boss.isDead()){
                    System.out.println("win");
                    stopTimers();
                    Game.getInstance().win();
                    Audio.changeToMenu();
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("winGame.fxml")));
                    try {
                        Main.getPrimaryStage().setScene(new Scene(loader.load(),1024,768));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Main.setGameStarted(false);

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
