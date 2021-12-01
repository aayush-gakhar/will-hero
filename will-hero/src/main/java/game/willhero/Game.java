package game.willhero;

import java.io.*;
import java.util.List;

public class Game implements Serializable {
    private final Hero hero;
    private long score;
    private long coins;
    private boolean revivedOnce;
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

    public Game() {
        hero = new Hero(0,0);
        score = 0;
        this.coins = 0;
        revivedOnce = false;
    }

    public Game(Game prevGame) {
        hero = prevGame.getHero();
        score = prevGame.getScore();
        this.coins = prevGame.getCoins();
        revivedOnce = prevGame.isRevivedOnce();

    }

    public void serialize(int i) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream( "saves/save"+i+".txt") ) ;
        out.writeObject(this);
        out.close();
    }

    public Game deserialize(int i) throws FileNotFoundException,IOException,ClassNotFoundException {
        ObjectInputStream in = null;
        Game saved = null;
        try {
            in = new ObjectInputStream(new FileInputStream("saves/save" + i + ".txt"));
            saved = (Game) in.readObject();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }finally {
            if(in != null)
                in.close();
        }
        return saved;
    }
}
