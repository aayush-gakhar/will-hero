package game.willhero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Hero hero;
    private long score=0;
    private long coins=100;
    private boolean revivedOnce=false;
    private boolean paused=false;
    private boolean gameOver=false;
    private boolean gameWon=false;

    private static final long coinsForRevive = 100;

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }


    private List<GameObject> characters =new ArrayList<>();

    public Hero getHero() {
        return hero;
    }

    public long getScore() {
        return score;
    }

    public void addScore(long score){
        this.score+=score;
    }

    public long getCoins() {
        return coins;
    }

    public void addCoins(long coins){
        this.coins+=coins;
    }

    public boolean isRevivedOnce() {
        return revivedOnce;
    }

    public void over(){
        gameOver=true;
    }

    public boolean isRevivable() {
        return !revivedOnce && coins >= coinsForRevive;
    }

    public void revive(){
        if(isRevivable()){
            coins-=coinsForRevive;
            gameOver=false;
            hero.setY(-400);
            hero.setSpeed(new Vector(0,0));
            revivedOnce=true;
        }
    }

    public void setGameObjects() {
        characters.add(hero);
        characters.add(new GreenOrc(500));
        characters.add(new GreenOrc(1200));
        characters.add(new GreenOrc(1600));
        characters.add(new GreenOrc(1950));
        characters.add(new RedOrc(2700));
        characters.add(new GreenOrc(3300));
        characters.add(new RedOrc(4100));
        characters.add(new GreenOrc(4600));
        characters.add(new GreenOrc(5500));
        characters.add(new RedOrc(5800));
        characters.add(new GreenOrc(6550));
        characters.add(new RedOrc(7500));
        characters.add(new GreenOrc(8300));
        characters.add(new GreenOrc(9000));
        characters.add(new GreenOrc(9900));
        characters.add(new RedOrc(10500));
        characters.add(new GreenOrc(11300));
        characters.add(new RedOrc(11900));
    }

    public void setGameObjects(List<GameObject> characters) {
        this.characters.add(hero);
        for(GameObject gameObject:characters){
            if(!(gameObject instanceof Hero) && !((Orc)gameObject).isDead()) {
                this.characters.add(new GreenOrc(gameObject.getPosition().getX()));
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public List<GameObject> getCharacters() {
        return characters;
    }

    public Game() {
        hero = new Hero(60,0);
        setGameObjects();
    }

    public Game(Game prevGame) {
        hero = new Hero(prevGame.getHero().getPosition().getX(),prevGame.getHero().getPosition().getY());
        score = prevGame.getScore();
        this.coins = prevGame.getCoins();
        revivedOnce = prevGame.isRevivedOnce();
        paused=prevGame.isPaused();
        characters = prevGame.getCharacters();
        gameOver=prevGame.isGameOver();
        gameWon=prevGame.isGameWon();
        setGameObjects();
    }

    public void serialize(int i) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("./saves/save" + i + ".txt"));
            out.writeObject(this);
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            if(out != null)
                out.close();
        }

    }

    public static Game deserialize(int i) throws FileNotFoundException,IOException,ClassNotFoundException {
        ObjectInputStream in = null;
        Game game = null;
        try {
            in = new ObjectInputStream(new FileInputStream("saves/save" + i + ".txt"));
            game = (Game) in.readObject();
        }catch(Exception e){
            System.out.println("game not found");
        }finally {
            if(in != null)
                in.close();
        }
        return game;
    }
}
