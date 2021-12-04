package game.willhero;

public class User {

    private Hero hero;
    private Game game;
    private int score;
    private long coins;

    public Hero getHero(){
        return this.hero;
    }

    public long getScore(){
        return this.score;
    }

    public long getCoins(){
        return this.coins;
    }

    public void setCoins(long coins){
        this.coins = coins;
    }

    public void setScore(int score){
        this.score = score;
    }

}
