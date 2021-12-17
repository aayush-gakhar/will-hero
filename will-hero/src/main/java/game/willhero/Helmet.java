package game.willhero;

import java.util.ArrayList;
import java.util.List;

public abstract class Helmet {
    private final List<Weapon> Weapons=new ArrayList<>();
    private Weapon currentWeapon;

    public Weapon getCurrentWeapon(){
        return this.currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon){
        this.currentWeapon = currentWeapon;
    }

    public List<Weapon> getWeapons(){
        return this.Weapons;
    }

    public abstract String getWeapon1Level();

    public abstract String getWeapon2Level();

    public abstract Weapon getWeapon1();

    public abstract Weapon getWeapon2();

    public abstract void upgradeWeapon(Weapon weapon);

    public abstract void upgradeWeapon1();

    public abstract void upgradeWeapon2();
}
