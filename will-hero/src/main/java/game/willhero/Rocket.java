package game.willhero;

public class Rocket extends Weapon{
    private double timeRange=0.7;
    private boolean isFired=false;
    private boolean exploded=false;

    public double getRange(){
        return timeRange;
    }

    public void update(double elapsedTime){
        if(isFired){
            if(!exploded){
                super.update(elapsedTime);
                timeRange-=elapsedTime;
                if(timeRange<=0){
                    explode();
                }
            }
        }
    }

    public Rocket(int level){
        super("assets/weaponRocket.png",true,200,level);
    }

    public Rocket(int level, double x, double y){
        super("assets/weaponRocket.png",true,200,level);
        setX(x);
        setY(y);
        setPosition(new Vector(x,y));
        setSpeed(new Vector(1000,0));
        isFired=true;
    }

    public boolean isFired(){
        return isFired;
    }

    public boolean isExploded(){
        return exploded;
    }

    public void explode(){
        exploded=true;
        setOpacity(0);
        Audio.playRocketSound();
    }

    public void vanish(){
        exploded=true;
        setOpacity(0);
    }
}
