package game.willhero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private ImageView hero;

    @FXML
    private Text score;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onHeroButtonClick() {
    	hero.setX(hero.getX() - 10);
    }




}