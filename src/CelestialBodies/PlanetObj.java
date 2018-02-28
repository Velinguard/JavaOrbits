package CelestialBodies;

import CelestialBodies.Objects.Ball;
import CelestialBodies.Objects.Paintable;
import CelestialBodies.api.MathsModule;

import java.awt.*;
import java.math.BigDecimal;

public class PlanetObj implements Planet, Paintable, Star {
    private Ball painter;
    private Coordinate position;
    private BigDecimal relativeAngle;

    public PlanetObj(float x, float y, float r, BigDecimal angle){
        painter = new Ball(x, y, r);
        position = new Coordinate(x, y);
        relativeAngle = angle;
    }

    @Override
    public void paint(Graphics2D g) {
        painter.paint(g);
    }

    public void setPosition(Coordinate position) {
        this.position = position;
        painter.setX(position.getX());
        painter.setY(position.getY());
    }

    @Override
    public void incrementAngle(BigDecimal alpha) {
        this.relativeAngle = MathsModule.mod(this.relativeAngle.add(alpha), BigDecimal.valueOf(Math.PI* 2));
    }

    public void setRelativeAngle(BigDecimal relativeAngle) {
        this.relativeAngle = MathsModule.mod(relativeAngle, BigDecimal.valueOf((Math.PI * 2)));
    }

    @Override
    public double getMass() {
        return 0;
    }

    @Override
    public double getOrbiting() {
        return 0;
    }

    @Override
    public BigDecimal getRelativeAngle() {
        return relativeAngle;
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    public Ball newBall(){
        return painter.newBall();
    }
}
