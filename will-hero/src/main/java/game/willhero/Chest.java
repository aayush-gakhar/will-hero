package game.willhero;

public abstract class Chest extends GameObject {
    private boolean opened;

    public boolean isOpened(){
        return this.opened;
    }

    public void setOpened(boolean opened){
        this.opened = opened;
    }

    public abstract void open();

    public Chest(double x,boolean opened){
        super(new Vector(x,-200), new Vector(0,0), new Vector(0,1000),opened?"assets/ChestOpen.png":"assets/ChestClosed.png");
        this.opened = opened;
    }
}
