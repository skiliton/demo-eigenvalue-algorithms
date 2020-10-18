package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.repeta.numerical_analysis.lab1.eigenvalue.MatrixMother.createDiagonizableMatrix5X5;
import static com.repeta.numerical_analysis.lab1.eigenvalue.AssertionsHelper.assertEachEigenPairHasBiasLessThanEps;


class QRAlgorithmTest extends EigenvalueAlgorithmTest{

    @Override
    protected EigenvalueAlgorithm createAlgorithm() {
        return new QRAlgorithm();
    }

    @Test
    public void Should_ReturnEigenPairs_When_NonSymmetricDiagonizableMatrixIsPassed(){
        QRAlgorithm qrAlgorithm = (QRAlgorithm) createAlgorithm().setEps(0.0001);
        SimpleMatrix A = createDiagonizableMatrix5X5();

        Map<Double,List<SimpleMatrix>> eSpaces = qrAlgorithm.apply(A);

        assertEachEigenPairHasBiasLessThanEps(A,eSpaces,0.0001);
    }

}