package game.willhero;

public class Factory {
    public static Rocket createRocket(int level, double x, double y) {
        return new Rocket(level,x, y);
    }
}
