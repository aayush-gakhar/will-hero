package game.willhero;

public class CoinChest extends Chest {
    private final long coins;

    public long getCoins(){
        return this.coins;
    }

//    public void setCoins(long coins){
//        this.coins = coins;
//    }

    public CoinChest(double x,boolean opened,long coins){
        super(x,opened);
        this.coins= coins;
    }

    @Override
    public void open(){
        if(this.isOpened()){
           return;
        }
        this.setOpened(true);
        super.setImage("assets/chestOpen.png");
        Game.getInstance().addCoins(this.coins);
    }
}
