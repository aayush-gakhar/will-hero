package game.willhero;

public abstract class GameObject {
    private Vector position;
    private Vector speed;
    private Vector acceleration;

    public GameObject(Vector position, Vector speed, Vector acceleration) {
        this.position = position;
        this.speed = speed;
        this.acceleration = acceleration;
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

    public boolean isColliding(GameObject other) {
        return false;
    }

//    public abstract void collisionWith(GameObject other);


}
