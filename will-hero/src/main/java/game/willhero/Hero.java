package game.willhero;

import java.util.List;

public class Hero extends GameObject{

    private Helmet helmet;

    private long moves;

    public Helmet getHelmet(){
        return this.helmet;
    }

    public List<Weapon> getWeapons(){
        return this.helmet.getWeapons();
    }

    public long getMoves(){
        return this.moves;
    }

    public void setHelmet(Helmet helmet){
        this.helmet = helmet;
    }

    public Weapon getCurrentWeapon(){
        return this.helmet.getCurrentWeapon();
    }

//    public void upgradeWeapon(Weapon weapon){
//        this.helmet.upgradeWeapon(weapon);
//    }

    public void setCurrentWeapon(Weapon currentWeapon){
        this.helmet.setCurrentWeapon(currentWeapon);
    }

    public void setMoves(long moves){
        this.moves = moves;
    }


    public Hero(Helmet helmet){
        super(new Vector(60,-500),new Vector(0,0),new Vector(0,500),"assets/hero.png");
        this.helmet=helmet;
    }

    public Hero(double x, double y, Helmet helmet) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,500),"assets/hero.png");
        this.helmet=helmet;
    }

    public Hero(Vector position, Helmet helmet) {
        super(position,new Vector(0,0),new Vector(0,500),"assets/hero.png");
        this.helmet=helmet;
    }

}
