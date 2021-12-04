package game.willhero;

public class Rocket extends Weapon{
    private long range;

    public long getRange(){
        return this.range;
    }

    public Rocket(Vector position, Vector speed, Vector acceleration){
        super(position, speed, acceleration);
    }
}
