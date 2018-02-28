package CelestialBodies;

import CelestialBodies.api.MathsModule;

import java.math.BigDecimal;
import java.math.MathContext;

public class Orbit {
    public Planet planet;
    public Star star;
    BigDecimal theta; //angle from north (clockwise, in radians) of shortest point
    public BigDecimal p; //p defined as semi-latus rectum
    public BigDecimal epsilon; //epsilon defined as eccentricity
    private final MathContext accuracy = MathContext.DECIMAL128;

    public Orbit(BigDecimal rmin, BigDecimal rmax, Planet planet, Star star, BigDecimal theta){
        semiLatusRectum(rmin, rmax);
        eccentricity(rmin, rmax);
        this.planet = planet;
        this.star = star;
        this.theta = theta;
    }

    public void updatePlanetPosition(){
        System.out.println(epsilon);
        BigDecimal angleFromNorth = MathsModule.mod(theta.add(planet.getRelativeAngle()),
                BigDecimal.valueOf( Math.PI * 2));
        planet.setPosition(new Coordinate(angleFromNorth, getRadius(), star.getPosition()));
    }

    public BigDecimal getRadius()
    //Pre: angle theta is in radians
    {
        return p.divide(epsilon.multiply(
                        BigDecimal.valueOf(Math.cos(planet.getRelativeAngle().doubleValue())),
                accuracy)
                .add(BigDecimal.valueOf(1)), accuracy);
    }

    public BigDecimal getApogee(){
        return p.divide(BigDecimal.valueOf(1).subtract(epsilon), accuracy);
    }

    public BigDecimal getPerigee(){
        return p.divide(BigDecimal.valueOf(1).add(epsilon), accuracy);
    }

    public BigDecimal semiMajorAxis(){
        return p.divide(BigDecimal.valueOf(1).subtract(epsilon.pow(2)), accuracy);
    }

    public BigDecimal semiMinorAxis(){
        //Accuracy lost here, avoid use.
        return p.divide((BigDecimal.valueOf(1).subtract(epsilon.pow(2))).sqrt(accuracy), accuracy);
    }

    public void semiLatusRectum(){
        p = getApogee().multiply(getPerigee()).divide(semiMajorAxis(), accuracy);
    }

    public void semiLatusRectum(BigDecimal rmin, BigDecimal rmax){
        p = rmin.multiply(rmax).multiply(BigDecimal.valueOf(2)).divide(rmax.add(rmin), accuracy);
    }

    public void eccentricity(BigDecimal rmin, BigDecimal rmax){
        epsilon = rmax.subtract(rmin).divide(rmax.add(rmin), accuracy);
    }
}
