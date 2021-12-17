package game.willhero;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;

public class TNT extends GameObject{
    private long radius=200;
    private long damage;
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

    public void explode(){
        if(exploded){
            return;
        }
        exploded = true;
        System.out.println(distance(Main.getGame().getHero()));
        if(distance(Main.getGame().getHero())<=radius){
            try {
                Main.getGameController().onGameOver();
            } catch (IOException e) {
                e.printStackTrace();
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
    }
}
