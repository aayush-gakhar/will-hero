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

    public static int collisionDirection(ImageView a, ImageView b) {
        if(!isColliding(a,b)) return 0;
        int o=10;
        if(a.getX()+getWidth(a)<b.getX()+o){

            return 1;
        }
        else if(a.getX()+o>b.getX()+getWidth(b)){
            return 3;
        }else {
            if(a.getY()+getHeight(a)<b.getY()){
                return 2;
            }else return 4;
        }
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

}
