package game.willhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class Main extends Application implements Serializable {

    private static boolean gameStarted = false;
    private static Game game;
    private static Stage primaryStage;
    private static GameController gameController;

//    public static Game getGame() {
//        return Game.getInstance();
//    }

    public static void setGame(Game game) {
        Main.game = game;
    }

    public static GameController getGameController() {
        return gameController;
    }

    public static void setGameController(GameController gameController) {
        Main.gameController = gameController;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }


    @Override
    public void start(Stage stage) throws IOException {
        Audio.initializeMedia();
        Audio.playMainMenuMusic();
        primaryStage=stage;
        stage.setTitle("Will Hero");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/icon-256.png"))));
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setGameStarted(boolean gameStarted) {
        Main.gameStarted = gameStarted;
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

}