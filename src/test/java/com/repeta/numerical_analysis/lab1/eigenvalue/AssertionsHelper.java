package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertionsHelper {
    public static void assertEigenPairHasBiasLessThanEps(SimpleMatrix A, double lambda, SimpleMatrix x, double eps){
        double bias = A.mult(x).minus(x.scale(lambda)).elementMaxAbs();
        assertTrue(
            bias < eps,
            String.format("Bias=%f higher than eps=%f: Ax-lambda*x>=eps",bias,eps)
        );
    }

    public static void assertEachEigenPairHasBiasLessThanEps(SimpleMatrix A, Map<Double,List<SimpleMatrix>> eSpaces, double eps){
        eSpaces.forEach((lambda, vectors) -> {
            vectors.forEach(
                    (x)->assertEigenPairHasBiasLessThanEps(A,lambda,x,eps)
            );
        });
    }

    public static void assertDoubleIsHighest(double compared, double[] comparedTo){
        for (int i=0; i<comparedTo.length; i++){
            assertTrue(
                compared>comparedTo[i],
                String.format(
                    "Compared value is less than one of that it is compared to: compared=%f <= comparedTo[%d]=%f",
                    compared,i,comparedTo[i]
                )
            );
        }
    }
}
