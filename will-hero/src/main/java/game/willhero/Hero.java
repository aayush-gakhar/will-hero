package game.willhero;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hero extends GameObject{

    private Helmet helmet;
    private List<Weapon> weapons=new ArrayList<>();
    private Weapon currentWeapon;
    private long moves;

    public Helmet getHelmet(){
        return this.helmet;
    }

    public List<Weapon> getWeapons(){
        return this.weapons;
    }

    public Weapon getCurrentWeapon(){
        return this.currentWeapon;
    }

    public long getMoves(){
        return this.moves;
    }

    public void setHelmet(Helmet helmet){
        this.helmet = helmet;
    }

    public void setCurrentWeapon(Weapon currentWeapon){
        this.currentWeapon = currentWeapon;
    }

    public void setMoves(long moves){
        this.moves = moves;
    }


    Hero(){
        super(new Vector(60,0),new Vector(0,0),new Vector(0,500),"assets/hero.png");
    }

    Hero(double x, double y) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,500),"assets/hero.png");
    }

    Hero(Vector position) {
        super(position,new Vector(0,0),new Vector(0,500),"assets/hero.png");
    }

}
