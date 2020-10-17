package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.repeta.numerical_analysis.lab1.eigenvalue.AssertionsHelper.assertEachEigenPairHasBiasLessThanEps;
import static com.repeta.numerical_analysis.lab1.eigenvalue.MatrixMother.createSymmetricMatrix5X5;
import static org.junit.jupiter.api.Assertions.*;

class PowerIterationTest extends EigenvalueAlgorithmTest{

    @Override
    protected EigenvalueAlgorithm createAlgorithm() {
        return new PowerIteration();
    }

    @Test
    public void Should_OnlyContainOneEigenPairWithGreatestInAbsEigenvalue_When_SymmetricMatrixIsPassed(){
        PowerIteration powerIteration = (PowerIteration) createAlgorithm().setEps(0.0001);
        SimpleMatrix A = createSymmetricMatrix5X5();

        Map<Double,List<SimpleMatrix>> eSpaces = powerIteration.apply(A);

        assertEachEigenPairHasBiasLessThanEps(A,eSpaces,0.0001);
        assertTrue(eSpaces.size()==1);

    }
}