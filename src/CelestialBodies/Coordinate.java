package CelestialBodies;

import java.math.BigDecimal;

public class Coordinate {
    private float x;
    private float y;

    public Coordinate(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(BigDecimal angleFromNorth, BigDecimal radius, Coordinate previous) {
        this.x = previous.x + (float) Math.sin(angleFromNorth.doubleValue()) * radius.floatValue();
        this.y = previous.y + (float) Math.cos(angleFromNorth.doubleValue()) * radius.floatValue();
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
