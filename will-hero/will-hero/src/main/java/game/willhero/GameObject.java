package game.willhero;

public abstract class GameObject {
    private Vector position;
    private Vector speed;
    private Vector acceleration;
    private double mass;

    public GameObject(Vector position, Vector speed, Vector acceleration, double mass) {
        this.position = position;
        this.speed = speed;
        this.acceleration = acceleration;
        this.mass = mass;
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
        return this.position.distance(other.position) < (this.mass + other.mass);
    }

//    public abstract void collisionWith(GameObject other);


}
