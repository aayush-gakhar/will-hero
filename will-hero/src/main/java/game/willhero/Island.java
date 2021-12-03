package game.willhero;

import javafx.scene.image.ImageView;

public class Island extends GameObject {

    public Island(Vector position, Vector speed, Vector acceleration){
        super(position, speed, acceleration);
    }

    Island(double x, double y, ImageView imageView) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,20),imageView);

//        heroImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/hero.png"))));
//        heroImage.setX(x);
//        heroImage.setY(y);
    }
}
