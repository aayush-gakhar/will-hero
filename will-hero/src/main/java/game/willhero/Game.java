package game.willhero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static Game instance;
    private final Hero hero;
    private long score=0;
    private long coins=100;
    private boolean revivedOnce=false;
    private boolean paused=false;
    private boolean gameOver=false;
    private boolean gameWon=false;
    private final List<GameObject> characters =new ArrayList<>();
    private final List<GameObject> chests =new ArrayList<>();
    private final List<GameObject> obstacles =new ArrayList<>();
    private Boss boss;

    private static final long coinsForRevive = 100;

    public boolean isPaused() {
        return paused;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public List<GameObject> getObstacles() {
        return obstacles;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Hero getHero() {
        return hero;
    }

    public Boss getBoss() {
        return boss;
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

    public void win(){
        gameWon=true;
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
        characters.add(new RedOrc(500));
        characters.add(new GreenOrc(1200));
        characters.add(new GreenOrc(1650));
        characters.add(new GreenOrc(1950));
        characters.add(new RedOrc(2700));
        characters.add(new GreenOrc(3100));
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
        boss=new Boss(10950);
        characters.add(boss);


        chests.add(new WeaponChest(300,false,true));
        chests.add(new WeaponChest(900,false,false));
        chests.add(new CoinChest(1500,false,50));
        chests.add(new CoinChest(2200,false,50));
        chests.add(new WeaponChest(3630,false,false));
        chests.add(new CoinChest(4450,false,50));
        chests.add(new WeaponChest(6270,false,true));
        chests.add(new CoinChest(7610,false,50));
        chests.add(new CoinChest(9100,false,50));

        obstacles.add(new TNT(1300,false));
        obstacles.add(new TNT(2500,false));
        obstacles.add(new TNT(3300,false));
        obstacles.add(new TNT(5120,false));
        obstacles.add(new TNT(7000,false));
        obstacles.add(new TNT(8500,false));
        obstacles.add(new TNT(10200,false));
    }

    public void setGameObjects(List<GameObject> prevCharacters, List<GameObject> prevChests, List<GameObject> prevObstacles) {
        for(GameObject orc:prevCharacters){
            if(!((Orc)orc).isDead()) {
                if(orc instanceof GreenOrc) {
                    this.characters.add(new GreenOrc(orc.getPosition().getX(), orc.getPosition().getY()));
                }else if(orc instanceof RedOrc){
                    this.characters.add(new RedOrc(orc.getPosition().getX(), orc.getPosition().getY()));
                }
            }
        }
        this.characters.add(boss);
        for (GameObject chest: prevChests) {
            if (chest instanceof WeaponChest) {
                this.chests.add(new WeaponChest(chest.getPosition().getX(), ((Chest) chest).isOpened(), ((WeaponChest) chest).isSwordOrRocket()));
            }else if (chest instanceof CoinChest) {
                this.chests.add(new CoinChest(chest.getPosition().getX(), ((Chest) chest).isOpened(), ((CoinChest) chest).getCoins()));
            }
        }
        for (GameObject obstacle: prevObstacles) {
            if(!((TNT)obstacle).isExploded()){
                this.obstacles.add(new TNT(obstacle.getPosition().getX(), ((TNT) obstacle).isExploded()));
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

    public List<GameObject> getChests() {
        return chests;
    }

    public Game() {
        instance = this;
        hero = new Hero(60,-200, new HyenaHelmet());
        setGameObjects();
    }

    public Game(Game prevGame) {
        instance = this;
        this.hero = new Hero(prevGame.getHero().getPosition().getX(),prevGame.getHero().getPosition().getY(), new HyenaHelmet(prevGame.getHero().getHelmet()));
        this.boss=new Boss(prevGame.getBoss().getPosition().getX(),prevGame.getBoss().getPosition().getY());
        this.score = prevGame.getScore();
        this.coins = prevGame.getCoins();
        this.revivedOnce = prevGame.isRevivedOnce();
        setGameObjects(prevGame.getCharacters(),prevGame.getChests(),prevGame.getObstacles());
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

    public static Game deserialize(int i) throws IOException,ClassNotFoundException {
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
