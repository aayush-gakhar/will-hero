package game.willhero;

public class Sword extends Weapon {
    private static final long length=40;

    public long getLength(){
        return length;
    }

    public Sword(int level){
        super("assets/weaponSword.png",false,100,level);
    }
}
