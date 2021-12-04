package game.willhero;

public abstract class Chest extends GameObject {
    private boolean opened;

    public boolean isOpened(){
        return this.opened;
    }

    public void setOpened(boolean opened){
        this.opened = opened;
    }

    public Chest(Vector position, Vector speed, Vector acceleration){
        super(position, speed, acceleration);
    }
}
