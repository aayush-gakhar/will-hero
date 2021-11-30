package game.willhero;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(Vector v) {
        return Math.sqrt(Math.pow(this.x - v.getX(), 2) + Math.pow(this.y - v.getY(), 2));
    }
}
