package CelestialBodies;

import java.math.BigDecimal;

public interface Planet {
    double getMass();
    double getOrbiting();
    BigDecimal getRelativeAngle();
    void setPosition(Coordinate position);
    void incrementAngle(BigDecimal alpha);
}
