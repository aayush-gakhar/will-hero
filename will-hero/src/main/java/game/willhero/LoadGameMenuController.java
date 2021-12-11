package game.willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class LoadGameMenuController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView hero;

    @FXML
    private Group clouds;

    @FXML
    private ImageView btnSound;

    @FXML
    private ImageView btnMusic;

    public void initialize(){
        Audio.setupButtons(btnSound, btnMusic,true);
        MainController.menuAnimations(hero, clouds);
    }

    @FXML
    public void onSoundButtonClick() {
        Audio.onSoundButtonClick(btnSound);
    }

    @FXML
    public void onMusicButtonClick() {
        Audio.onMusicButtonClick(btnMusic,true);
    }

    @FXML
    public void onSave1ButtonClick() throws IOException, ClassNotFoundException {
        onSaveiButtonClick(1);
    }

    @FXML
    public void onSave2ButtonClick() throws IOException, ClassNotFoundException {
        onSaveiButtonClick(2);
    }

    @FXML
    public void onSave3ButtonClick() throws IOException {
        onSaveiButtonClick(3);
    }

    @FXML
    public void onSave4ButtonClick() throws IOException {
        onSaveiButtonClick(4);
    }

    @FXML
    public void onSave5ButtonClick() throws IOException {
        onSaveiButtonClick(5);
    }

    public void onSaveiButtonClick(int i) throws IOException {
        try{
            Game game=Game.deserialize(i);
            if (game==null){
                System.out.println("No game found");
                throw new Exception("Save file empty");
            }
            Main.setGame(new Game(game));
            System.out.println("Loaded game from save file");

        }catch (Exception e){
            System.out.println("exception: "+e);
            e.printStackTrace();
            Main.setGame(new Game());
        }
        Audio.playButtonSound();
        Audio.changeToGame();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("game.fxml")));
        Scene scene = new Scene(loader.load());
        scene.setOnKeyPressed(event -> ((GameController)loader.getController()).keyPressed(event));
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        Audio.playButtonSound();
        AnchorPane a = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        anchorPane.getChildren().setAll(a);
    }
}
