package game.willhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class Main extends Application implements Serializable {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private static boolean playMusic = true;
    private static boolean playSound = true;

    public static boolean isPlayMusic() {
        return playMusic;
    }

    public static boolean isPlaySound() {
        return playSound;
    }

    private static MediaPlayer mainMenuMusic;
    private static MediaPlayer gameMusic;
    private static AudioClip buttonSound;








    @Override
    public void start(Stage stage) throws IOException {
        primaryStage=stage;
        stage.setTitle("Will Hero");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/icon-256.png"))));
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setScene(scene);
        initializeMedia();
        playMainMenuMusic();
        stage.show();
//        stage.setTitle("Will Hero");
//        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/icon-256.png"))));
//        stage.setResizable(false);
//        AnchorPane anchorPane= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
//        stage.setScene(new Scene(anchorPane, 1024, 768));
//        playMusic();
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void initializeMedia() {
        Media media = new Media(Objects.requireNonNull(Main.class.getResource("sounds/menu.m4a")).toString());
        mainMenuMusic = new MediaPlayer(media);
        mainMenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        mainMenuMusic.setStartTime(Duration.seconds(0));
        mainMenuMusic.setStopTime(Duration.seconds(200));
        mainMenuMusic.setRate(60.0/56.0);

        media = new Media(Objects.requireNonNull(Main.class.getResource("sounds/inGame.m4a")).toString());
        gameMusic = new MediaPlayer(media);
        gameMusic.setCycleCount(MediaPlayer.INDEFINITE);
        gameMusic.setStartTime(Duration.seconds(0));
        gameMusic.setStopTime(Duration.seconds(200));

        media = new Media(Objects.requireNonNull(Main.class.getResource("sounds/button.m4a")).toString());
        buttonSound = new AudioClip(media.getSource());
    }

    public static void playMainMenuMusic() {
        playMusic = true;
        mainMenuMusic.play();
    }

    public static void stopMainMenuMusic() {
        playMusic = false;
        mainMenuMusic.stop();
    }

    public static void playGameMusic() {
        playMusic = true;
        gameMusic.play();
    }

    public static void stopGameMusic() {
        playMusic = false;
        gameMusic.stop();
    }

    public static void playSound() {
        playSound = true;
    }

    public static void stopSound() {
        playSound = false;
    }

    public static void playButtonSound() {
        if(playSound)buttonSound.play();
    }

}