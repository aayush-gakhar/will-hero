package game.willhero;

public abstract class Orc extends GameObject{

    private long health=200;
    private long jumpHeight=250;
    private final long coinsOnKill=10;
    private boolean dead=false;

    public boolean isDead(){
        return dead;
    }

    public long getHealth(){
        return this.health;
    }

    public void takeDamage(long damage){
        this.health -= damage;
//        if(this.health <= 0){
//            this.die();
//        }
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

    public Orc(double x, double y, String imagePath) {
        super(new Vector(x,y),new Vector(0,0),new Vector(0,250),imagePath);
    }

    public void die(){
        this.dead = true;
        Game.getInstance().addCoins(this.coinsOnKill);
//        this.
    }



}
