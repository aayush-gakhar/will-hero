package game.willhero;

import java.io.Serializable;

public class HyenaHelmet extends Helmet implements Serializable {
    HyenaHelmet(){
        getWeapons().add(new Sword(0));
        getWeapons().add(new Rocket(0));
//        setCurrentWeapon(getWeapons().get(0));
    }

    HyenaHelmet(Helmet prevHelmet){
        getWeapons().add(new Sword(prevHelmet.getWeapons().get(0).getLevel()));
        getWeapons().add(new Rocket(prevHelmet.getWeapons().get(1).getLevel()));
        if(prevHelmet.getCurrentWeapon() instanceof Sword){
            setCurrentWeapon(getWeapons().get(0));
        }else if(prevHelmet.getCurrentWeapon() instanceof Rocket){
            setCurrentWeapon(getWeapons().get(1));
        }
    }

    @Override
    public String getWeapon1Level(){
        return getWeapons().get(0).getLevel() + "";
    }

    @Override
    public String getWeapon2Level(){
        return getWeapons().get(1).getLevel() + "";
    }

    @Override
    public void upgradeWeapon(Weapon weapon){
        if(weapon instanceof Sword) {
            getWeapons().get(0).upgrade();
        }else if(weapon instanceof Rocket){
            getWeapons().get(1).upgrade();
        }
    }

    @Override
    public void upgradeWeapon1(){
        getWeapons().get(0).upgrade();
    }

    @Override
    public void upgradeWeapon2(){
        getWeapons().get(1).upgrade();
    }

    @Override
    public Weapon getWeapon1(){
        return getWeapons().get(0);
    }

    @Override
    public Weapon getWeapon2(){
        return getWeapons().get(1);
    }
}
