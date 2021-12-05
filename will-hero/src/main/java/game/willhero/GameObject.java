package game.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class GameObject extends ImageView {
    private Vector position;
    private Vector speed;
    private Vector acceleration;

    public GameObject(Vector position, Vector speed, Vector acceleration, String imagePath) {
        super(new Image(Objects.requireNonNull(Main.class.getResourceAsStream(imagePath))));
        this.position = position;
        this.speed = speed;
        this.acceleration = acceleration;
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public GameObject(Vector position, Vector speed, Vector acceleration,ImageView imageView) {
        super(imageView.getImage());
        this.position = position;
        this.speed = speed;
        this.acceleration = acceleration;
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public GameObject(Vector position, Vector speed, Vector acceleration) {
//        super(new Image(imagePath));
        this.position = position;
        this.speed = speed;
        this.acceleration = acceleration;
    }

    GameObject(){

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
