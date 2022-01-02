package game.willhero;

public class WeaponChest extends Chest {
    private final Weapon weapon;
    private final boolean swordOrRocket;

    public Weapon getWeapon(){
        return this.weapon;
    }

    public WeaponChest(double x,boolean opened,boolean swordOrRocket){
        super(x,opened);
        this.weapon = swordOrRocket ? new Sword(1) : new Rocket(1);
        this.swordOrRocket=swordOrRocket;
    }

    public boolean isSwordOrRocket(){
        return this.swordOrRocket;
    }

    @Override
    public void open(){
        if(this.isOpened()){
            return;
        }
        this.setOpened(true);
        super.setImage("assets/chestOpen.png");
        if(swordOrRocket)
            Game.getInstance().getHero().getHelmet().upgradeWeapon1();
        else
            Game.getInstance().getHero().getHelmet().upgradeWeapon2();
    }
}
