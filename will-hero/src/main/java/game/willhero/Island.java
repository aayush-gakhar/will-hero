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

}
