package game.willhero;

public class Sword extends Weapon {
    private long length;

    public long getLength(){
        return this.length;
    }

    public Sword(Vector position, Vector speed, Vector acceleration){
        super(position, speed, acceleration);
    }
}
