package game.willhero;

import java.io.Serializable;

public class Vector implements Serializable,Cloneable {
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

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Vector clone() throws CloneNotSupportedException {
        return (Vector) super.clone();
    }

    public double distance(Vector v) {
        return Math.sqrt(Math.pow(this.x - v.getX(), 2) + Math.pow(this.y - v.getY(), 2));
    }

    public void add(Vector v) {
        this.x += v.getX();
        this.y += v.getY();
    }

    public void subtract(Vector v) {
        this.x -= v.getX();
        this.y -= v.getY();
    }

    public void reverse() {
        this.x= -this.x;
        this.y= -this.y;
    }

    public Vector scale(double s) {
        return new Vector(this.x*s, this.y*s);
    }
}
