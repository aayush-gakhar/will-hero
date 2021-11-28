package game.willhero;

import javafx.scene.image.ImageView;

public class Hero extends GameObject {

    private ImageView heroImage;


    Hero(int x, int y) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,0),0);
        heroImage = new ImageView("assets/hero.png");
    }
}
