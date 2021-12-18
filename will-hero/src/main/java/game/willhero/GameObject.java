package game.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Objects;

public abstract class GameObject extends ImageView implements Serializable {
    private Vector position;
    private Vector speed;
    private Vector acceleration;

    public GameObject(Vector position, Vector speed, Vector acceleration, String imagePath) {
        super(new Image(Objects.requireNonNull(Main.class.getResourceAsStream(imagePath))));

        Image image=new Image(Objects.requireNonNull(Main.class.getResourceAsStream(imagePath)));
//        this.setFitHeight(image.getHeight());
//        this
//        image.
        this.position = position;
        this.speed = speed;
        this.acceleration = acceleration;
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public GameObject(Vector position, Vector speed, Vector acceleration, String imagePath,int width,int height) {
        super(new Image(Objects.requireNonNull(Main.class.getResourceAsStream(imagePath))));
        super.setFitWidth(width);
        super.setFitHeight(height);


        this.position = position;
        this.speed = speed;
        this.acceleration = acceleration;
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public GameObject(Vector position, Vector speed, Vector acceleration) {
        super(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/hero.png"))));
        this.position = position;
        this.speed = speed;
        this.acceleration = acceleration;
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public void setImage(String imagePath) {
        this.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream(imagePath))));
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getSpeed() {
        return speed;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public static boolean isColliding(ImageView a,ImageView b) {
        return a!=b && a.getLayoutBounds().intersects(b.getLayoutBounds());
    }

    public static double getWidth(ImageView imageView) {
        return imageView.getImage().getWidth();
    }

    public static double getHeight(ImageView imageView) {
        return imageView.getImage().getHeight();
    }

    public double getWidth() {
        return getImage().getWidth();
    }

    public double getHeight() {
        return getImage().getHeight();
    }

    public int collisionDirection(GameObject o) {
//        hero.getPosition().getY()>character.getPosition().getY()+character.getHeight()-16 &&
//                hero.getPosition().getY()<character.getPosition().getY()+character.getHeight()-10 &&
//                hero.getPosition().getX()+hero.getWidth()>character.getPosition().getX()+character.getWidth()/4 &&
//                hero.getPosition().getX()<character.getPosition().getX()+3*character.getWidth()/4;
        if(!isColliding(this,o)) return 0;
        //        2
        //    1   o   3
        //        4
        double m=o.getWidth()/4;
        if(getPosition().getX()+getWidth()>o.getPosition().getX()+o.getWidth()/4 &&
                getPosition().getX()<o.getPosition().getX()+3*o.getWidth()/4){
            if(getPosition().getY()>o.getPosition().getY()+o.getHeight()/4){
                return 4;
            }else if(getPosition().getY()+getHeight()<o.getPosition().getY()+m) {
                return 2;
            }

        }else{
            if(getPosition().getX()+getWidth()<o.getPosition().getX()+m){
                return 1;
            }else if(getPosition().getX()>o.getPosition().getX()+o.getWidth()-m){
                return 3;
            }
        }
        return 0;
    }

    public double distance(GameObject gameObject) {
        return Math.abs(this.getX()-gameObject.getX())+Math.abs(this.getY()-gameObject.getY());
    }

    public void move(double deltaTime) {
        this.position.setX(this.getX());
        this.position.setY(this.getY());
//        System.out.println(position.getX()+" "+position.getY());
        this.position.add(this.speed.scale(deltaTime));
//        System.out.println(position.getX()+" "+position.getY());
        this.setX(this.position.getX());
        this.setY(this.position.getY());
    }

    public void accelerate(double deltaTime) {
        this.speed.add(this.acceleration.scale(deltaTime));
//        System.out.println(speed.getX()+" "+speed.getY());
    }

    public void update(double deltaTime) {
        accelerate(deltaTime);
        move(deltaTime);
    }

}
