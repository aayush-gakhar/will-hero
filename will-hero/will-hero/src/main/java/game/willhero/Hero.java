package game.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Hero extends GameObject {

    private final ImageView heroImage;


    Hero(int x, int y) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,0),0);
        heroImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/hero.png"))));
        heroImage.setX(x);
        heroImage.setY(y);

    }

    public ImageView getImageView() {
        return heroImage;
    }
}
