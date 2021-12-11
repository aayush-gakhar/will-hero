package game.willhero;

import java.util.ArrayList;
import java.util.List;

public class Hero extends GameObject{

    private Helmet helmet;
    private final List<Weapon> weapons=new ArrayList<>();
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


    public Hero(){
        super(new Vector(60,0),new Vector(0,0),new Vector(0,500),"assets/hero.png");
    }

    public Hero(double x, double y) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,500),"assets/hero.png");
    }

    public Hero(Vector position) {
        super(position,new Vector(0,0),new Vector(0,500),"assets/hero.png");
    }

}
