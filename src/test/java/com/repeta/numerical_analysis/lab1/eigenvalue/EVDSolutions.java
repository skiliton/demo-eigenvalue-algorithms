package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;

import static com.repeta.numerical_analysis.lab1.eigenvalue.MatrixMother.createDiagonizableMatrix5X5;
import static com.repeta.numerical_analysis.lab1.eigenvalue.MatrixMother.createSymmetricMatrix5X5;

public class EVDSolutions {
    public double[] getEigenvalues(SimpleMatrix A){
        if(A.equals(createSymmetricMatrix5X5())) {
            return new double[]{1437.95, 1303.88, 759.825, 340.203, 32.1422};
        }
        if(A.equals(createDiagonizableMatrix5X5())){
            return new double[]{-1679.28, -953.336, -759.823, -500.079, -16.4853};
        }
        return null;
    }
}
