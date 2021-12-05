package game.willhero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Hero hero;
    private long score;
    private long coins;
    private boolean revivedOnce;
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


    private List<GameObject> gameObjects=new ArrayList<>();

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

    public boolean isRevivedOnce() {
        return revivedOnce;
    }

    public void over(){
        gameOver=true;
    }

    public boolean isRevivable() {
        return true;
//        return !revivedOnce && coins >= coinsForRevive;
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
        gameObjects.add(hero);
        gameObjects.add(new GreenOrc(500));
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Game() {
        hero = new Hero(60,0);
        score = 0;
        this.coins = 0;
        revivedOnce = false;
        setGameObjects();
    }

    public Game(Game prevGame) {
        hero = new Hero(prevGame.getHero().getPosition().getX(),prevGame.getHero().getPosition().getY());
        score = prevGame.getScore();
        this.coins = prevGame.getCoins();
        revivedOnce = prevGame.isRevivedOnce();
        paused=prevGame.isPaused();
        gameObjects= prevGame.getGameObjects();
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
