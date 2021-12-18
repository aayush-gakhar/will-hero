package game.willhero;

public class Boss extends Orc{

    public Boss(double x) {
        super(x,-500,"assets/orcBoss.png");
        setJumpHeight(300);
        setHealth(2000);
    }

    public Boss(double x,double y) {
        super(x,y,"assets/orcBoss.png");
        setJumpHeight(300);
        setHealth(2000);
    }
}
