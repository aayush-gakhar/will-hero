package game.willhero;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TNT extends GameObject{
    private final long radius=200;
    private double time=2;
    private boolean exploded;
    private boolean timerStarted;

    public TNT(double x,boolean exploded){
        super(new Vector(x,-200), new Vector(0,0), new Vector(0,1000),"assets/TNT.png");
        this.exploded = exploded;
    }

    public boolean isExploded(){
        return exploded;
    }

    public void startTimer(){
        timerStarted = true;
    }

    public boolean isTimerStarted(){
        return timerStarted;
    }

    @Override
    public void update(double deltaTime){
        if(timerStarted){
            timer(deltaTime);
        }
        super.update(deltaTime);
    }

    public void timer(double elapsedTime){
        timerStarted= true;
        this.time-=elapsedTime;
        if(time<=0){
            explode();
        }

    }

    public List<GameObject> explode(){
        if(exploded){
            return new ArrayList<>();
        }
        exploded = true;
//        System.out.println(distance(Main.getGame().getHero()));
        if(distance(Game.getInstance().getHero())<=radius){
            try {
                System.out.println("TNT hit");
                Main.getGameController().onGameOver();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<GameObject> dead = new ArrayList<>();
        for (GameObject gameObject : Game.getInstance().getCharacters()) {
            if (distance(gameObject)<=radius) {
                if (gameObject instanceof Orc) {
                    if (!((Orc) gameObject).isDead()) {
                        Main.getGameController().getCharacters().getChildren().remove(gameObject);
                        dead.add(gameObject);
                        ((Orc) gameObject).die();
                    }
                }
            }
        }
        setImage("assets/explosion.png");
        setScaleX(15);
        setScaleY(15);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(opacityProperty(),0, Interpolator.EASE_OUT);
        KeyFrame cloudKeyFrame  = new KeyFrame(Duration.millis(1000), kv);
        timeline.getKeyFrames().addAll(cloudKeyFrame);
        timeline.play();
//        setOpacity(0);
        return dead;
    }

    public void destroy(){
        if(exploded){
            return;
        }
        exploded = true;
//        System.out.println(distance(Main.getGame().getHero()));
//        if(distance(Main.getGame().getHero())<=radius){
//            try {
//                System.out.println("TNT hit");
//                Main.getGameController().onGameOver();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        setImage("assets/explosion.png");
        setScaleX(15);
        setScaleY(15);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(opacityProperty(),0, Interpolator.EASE_OUT);
        KeyFrame cloudKeyFrame  = new KeyFrame(Duration.millis(1000), kv);
        timeline.getKeyFrames().addAll(cloudKeyFrame);
        timeline.play();
//        setOpacity(0);
    }
}
