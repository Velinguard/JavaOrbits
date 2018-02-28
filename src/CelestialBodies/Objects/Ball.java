package CelestialBodies.Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Float implements Paintable {
    Color colour;

    public Ball(float x, float y, float r){
        super (x - r/2, y - r / 2, r, r);
        System.out.println("x: " + x + ", centre:" + getCenterX());
        colour = Color.GRAY;
    }

    public Ball newBall(){
        return new Ball((float) getCenterX(), (float) getCenterY(), 3);
    }

    public void setX(float x){
        this.x = x - width/2;
    }

    public void setY(float y){
        this.y = y - height/2;
    }

    public void paint(Graphics2D g) {
        g.setColor(colour);
        g.fill(this);
    }

}