package game.willhero;

public class CoinChest extends Chest {
    private long coins;

    public long getCoins(){
        return this.coins;
    }

    public void setCoins(long coins){
        this.coins = coins;
    }

    public CoinChest(Vector position, Vector speed, Vector acceleration){
        super(position, speed, acceleration);
    }
}
