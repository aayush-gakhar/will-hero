package game.willhero;

public class WeaponChest extends Chest {
    private Weapon weapon;

    public Weapon getWeapon(){
        return this.weapon;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public WeaponChest(Vector position, Vector speed, Vector acceleration){
        super(position, speed, acceleration);
    }
}
