package game.willhero;

public class Sword extends Weapon {
    private long length;

    public long getLength(){
        return this.length;
    }

    public Sword(){
        super("assets/weaponSword.png");
    }
}
