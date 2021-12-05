package game.willhero;

import java.io.*;
import java.util.List;

public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Hero hero;
    private long score;
    private long coins;
    private boolean revivedOnce;
    private boolean paused=false;

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }


    private List<GameObject> gameObjects;

    public Hero getHero() {
        return hero;
    }

    public long getScore() {
        return score;
    }

    public long getCoins() {
        return coins;
    }

    public boolean isRevivedOnce() {
        return revivedOnce;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Game() {
        hero = new Hero(60,0);
        score = 0;
        this.coins = 0;
        revivedOnce = false;
    }

    public Game(Game prevGame) {
        System.out.println("loading game: "+prevGame.getHero().getPosition());
        hero = new Hero(prevGame.getHero().getPosition().getX(),prevGame.getHero().getPosition().getY());
        score = prevGame.getScore();
        this.coins = prevGame.getCoins();
        revivedOnce = prevGame.isRevivedOnce();
        paused=prevGame.isPaused();
        gameObjects= prevGame.getGameObjects();
    }

    public void serialize(int i) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("./saves/save" + i + ".txt"));
            out.writeObject(this);
        }catch (Exception e){
            System.out.println(e);
            File file = new File(".");
            System.out.println(file.getAbsolutePath());
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
            System.out.println("des: "+game.getHero().getPosition());
        }catch(Exception e){
            System.out.println("game not found");
        }finally {
            if(in != null)
                in.close();
        }
        return game;
    }
}
