package game.willhero;

public abstract class Orc extends GameObject{

    private long health;
    private long jumpHeight;
    private long coinsOnKill=10;
    private boolean dead=false;

    public boolean isDead(){
        return dead;
    }

    public long getHealth(){
        return this.health;
    }

    public long getJumpHeight(){
        return this.jumpHeight;
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

    public Orc(Vector position, Vector speed, Vector acceleration){
        super(position, speed, acceleration);
    }

    Orc(double x, double y, String imagePath) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,200),imagePath);
    }

    public void die(){
        this.dead = true;
        Main.getGame().addCoins(this.coinsOnKill);
//        this.
    }



}
