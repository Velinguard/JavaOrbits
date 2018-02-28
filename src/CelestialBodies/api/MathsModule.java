package CelestialBodies.api;

import CelestialBodies.Orbit;

import java.math.BigDecimal;

public class MathsModule {

    double position(Orbit orbit){
        //TODO: Get p, epsilon and theta.
        //return p / (1 + Math.multiplyExact( epsilon, Math.cos( theta )));
        return 0;
    }

    public static BigDecimal mod(BigDecimal a, BigDecimal b){
        if (a.compareTo(b) == -1 && a.compareTo(b.negate()) == 1){
            return a;
        } else if (a.compareTo(b) == 1){
            return mod(a.subtract(b), b);
        } else {
            return mod(a.add(b), b);
        }
    }
}
