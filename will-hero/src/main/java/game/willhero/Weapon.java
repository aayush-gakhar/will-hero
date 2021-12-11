package game.willhero;

public abstract class Weapon extends GameObject {

    private int damage;
    private int level;
    private boolean projectile;

    public int getDamage(){
        return this.damage;
    }

    public int getLevel(){
        return this.level;
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

    public void setProjectile(boolean projectile){
        this.projectile = projectile;
    }

    public Weapon(String imagePath){
        super(new Vector(0,0), new Vector(0,0), new Vector(0,0),imagePath);
    }
}
