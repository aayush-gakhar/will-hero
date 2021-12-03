package game.willhero;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Objects;

public class Audio {

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
    private static AudioClip heroMoveSound;
    private static AudioClip heroDeadSound;
    private static AudioClip heroFallSound;
    private static AudioClip gameOverSound;



    public static void initializeMedia() {
        mainMenuMusic = new MediaPlayer(new Media(Objects.requireNonNull(Main.class.getResource("sounds/menu.m4a")).toString()));
        mainMenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        mainMenuMusic.setStartTime(Duration.seconds(0));
        mainMenuMusic.setStopTime(Duration.seconds(200));
        mainMenuMusic.setRate(60.0/56.0);
        mainMenuMusic.setVolume(0.1);

        gameMusic = new MediaPlayer(new Media(Objects.requireNonNull(Main.class.getResource("sounds/inGame.m4a")).toString()));
        gameMusic.setCycleCount(MediaPlayer.INDEFINITE);
        gameMusic.setStartTime(Duration.seconds(0));
        gameMusic.setStopTime(Duration.seconds(200));
        gameMusic.setVolume(0.1);

        buttonSound = new AudioClip(new Media(Objects.requireNonNull(Main.class.getResource("sounds/button.m4a")).toString()).getSource());

        heroMoveSound = new AudioClip(new Media(Objects.requireNonNull(Main.class.getResource("sounds/charDash.m4a")).toString()).getSource());

        heroDeadSound = new AudioClip(new Media(Objects.requireNonNull(Main.class.getResource("sounds/charDead.m4a")).toString()).getSource());

        heroFallSound = new AudioClip(new Media(Objects.requireNonNull(Main.class.getResource("sounds/charFall.m4a")).toString()).getSource());

        gameOverSound = new AudioClip(new Media(Objects.requireNonNull(Main.class.getResource("sounds/lose.m4a")).toString()).getSource());
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

    public static void playHeroMoveSound() {
        if(playSound)heroMoveSound.play();
    }

    public static void playHeroDeadSound() {
        if(playSound)heroDeadSound.play();
    }

    public static void playHeroFallSound() {
        if(playSound)heroFallSound.play();
    }

    public static void playGameOverSound() {
        if(playSound)gameOverSound.play();
    }

}
