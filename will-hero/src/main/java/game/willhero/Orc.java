package game.willhero;

public abstract class Orc extends GameObject{

    private long health;
    private long jumpHeight;
    private long size;
    private long coinsOnKill;

    public long getHealth(){
        return this.health;
    }

    public long getJumpHeight(){
        return this.jumpHeight;
    }

    public long getSize(){
        return this.size;
    }

    public long getCoinsOnKill(){
        return this.coinsOnKill;
    }

    public void setHealth(long health){
        this.health = health;
    }

    public void setJumpHeight(long jumpHeight){
        this.jumpHeight = jumpHeight;
    }

    public void setSize(long size){
        this.size = size;
    }

    public void setCoinsOnKill(long coinsOnKill){
        this.coinsOnKill = coinsOnKill;
    }

    public Orc(Vector position, Vector speed, Vector acceleration){
        super(position, speed, acceleration);
    }
}
