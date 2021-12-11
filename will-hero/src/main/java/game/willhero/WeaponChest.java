package game.willhero;

public class WeaponChest extends Chest {
    private Weapon weapon;

    public Weapon getWeapon(){
        return this.weapon;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public WeaponChest(double x,boolean opened,Weapon weapon){
        super(x,opened);
        this.weapon = weapon;
    }

    @Override
    public void open(){
        if(this.isOpened()){
            return;
        }
        this.setOpened(true);
        super.setImage("assets/chestOpen.png");
        Main.getGame().getHero().getWeapons().add(this.weapon);
    }
}
