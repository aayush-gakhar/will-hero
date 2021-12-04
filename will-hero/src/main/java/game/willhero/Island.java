package game.willhero;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Island extends GameObject {

    private long length;
    private ArrayList<GameObject> objects;

    public long getLength(){
        return this.length;
    }

    public ArrayList<GameObject> getObjects(){
        return this.objects;
    }

    public void setLength(long length){
        this.length = length;
    }

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
