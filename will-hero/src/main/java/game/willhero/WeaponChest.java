package game.willhero;

public class WeaponChest extends Chest {
//    private Weapon weapon;
    private boolean swordOrRocket;

//    public Weapon getWeapon(){
//        return this.weapon;
//    }

    public WeaponChest(double x,boolean opened,boolean swordOrRocket){
        super(x,opened);
//        this.weapon = weapon;
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
            Main.getGame().getHero().getHelmet().upgradeWeapon1();
        else
            Main.getGame().getHero().getHelmet().upgradeWeapon2();
    }
}
