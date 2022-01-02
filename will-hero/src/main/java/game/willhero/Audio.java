package game.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Objects;

public class Audio extends Thread{

    private static boolean playMusic = true;
    private static boolean playSound = true;

    private static Thread musicThread;

    public static boolean isPlayMusic() {
        return playMusic;
    }

    public static boolean isPlaySound() {
        return playSound;
    }

    private static MediaPlayer mainMenuMusic;
    private static MediaPlayer gameMusic;
    private static MediaPlayer current;
    private static AudioClip buttonSound;
    private static AudioClip heroMoveSound;
    private static AudioClip heroDeadSound;
    private static AudioClip heroFallSound;
    private static AudioClip gameOverSound;
    private static AudioClip rocketSound;

    public void run(){
        musicThread=this;
        initializeMedia();
    }

    public static void initializeMedia() {
        mainMenuMusic = new MediaPlayer(new Media(Objects.requireNonNull(Main.class.getResource("sounds/menu.m4a")).toString()));
        mainMenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        mainMenuMusic.setStartTime(Duration.seconds(0));
        mainMenuMusic.setStopTime(Duration.seconds(200));
        mainMenuMusic.setRate(60.0/56.0);
        mainMenuMusic.setVolume(0.1);
        current= mainMenuMusic;

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

        rocketSound = new AudioClip(new Media(Objects.requireNonNull(Main.class.getResource("sounds/explodeRocket.m4a")).toString()).getSource());
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

    public static void playRocketSound() {
        if(playSound)rocketSound.play();
    }

    public static void setupButtons(ImageView btnSound, ImageView btnMusic, boolean menuOrGame) {
        if (isPlaySound()) {
            btnSound.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/btnsound0.png"))));
        }else {
            btnSound.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/btnsound1.png"))));
        }
        if(Audio.isPlayMusic()){
            btnMusic.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/btnmusic0.png"))));
            stopMainMenuMusic();
            stopGameMusic();
            if(menuOrGame)Audio.playMainMenuMusic();
            else Audio.playGameMusic();
        }else {
            btnMusic.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/btnmusic1.png"))));
        }
    }

    public static void onSoundButtonClick(ImageView btnSound) {
        if (Audio.isPlaySound()) {
            btnSound.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/btnsound1.png"))));
            Audio.stopSound();
        }else {
            btnSound.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/btnsound0.png"))));
            Audio.playSound();
        }
        Audio.playButtonSound();
    }

    public static void onMusicButtonClick(ImageView btnMusic, boolean menuOrGame) {
        Audio.playButtonSound();
        if(Audio.isPlayMusic()){
            btnMusic.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/btnmusic1.png"))));
            Audio.stopMainMenuMusic();
            Audio.stopGameMusic();
        }else {
            btnMusic.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/btnmusic0.png"))));
            if(menuOrGame)Audio.playMainMenuMusic();
            else Audio.playGameMusic();
        }
    }

    public static void changeToMenu(){
        if(Audio.isPlayMusic()){
            Audio.stopGameMusic();
            Audio.playMainMenuMusic();
        }current=mainMenuMusic;
    }

    public static void changeToGame(){
        if(Audio.isPlayMusic()){
            Audio.stopMainMenuMusic();
            Audio.playGameMusic();
        }current=gameMusic;
    }

}
