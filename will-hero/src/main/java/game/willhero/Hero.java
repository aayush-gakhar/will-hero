package game.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Hero extends GameObject {

    Hero(double x, double y) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,500),"assets/hero.png");
    }
    Hero(double x, double y,ImageView imageView) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,500),imageView);
    }

    Hero(Vector position, Vector velocity, Vector acceleration, ImageView imageView) {
        super(position, velocity, acceleration, imageView);
    }

}
