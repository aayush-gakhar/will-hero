package game.willhero;

public abstract class Weapon extends GameObject {

    private long damage;
    private int level;
    private final boolean projectile;

    public long getDamage(){
        return this.damage*this.level;
    }

    public int getLevel(){
        return this.level;
    }

    public void upgrade(){
        this.level++;
    }

    public boolean isProjectile(){
        return this.projectile;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public Weapon(String imagePath, boolean projectile, long damage, int level){
        super(new Vector(0,0), new Vector(0,0), new Vector(0,0),imagePath);
        this.projectile=projectile;
        this.damage=damage;
        this.level=level;
    }

    public void update(double deltaTime){
        super.update(deltaTime);
    }
}
