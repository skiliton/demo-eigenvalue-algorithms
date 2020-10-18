package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

import static com.repeta.numerical_analysis.lab1.eigenvalue.AssertionsHelper.assertEachEigenPairHasBiasLessThanEps;
import static com.repeta.numerical_analysis.lab1.eigenvalue.MatrixMother.createSymmetricMatrix5X5;

public abstract class EigenvalueAlgorithmTest {

    abstract protected EigenvalueAlgorithm createAlgorithm();

    @ParameterizedTest
    @ValueSource(doubles = {10000,0.0001})
    //All symmetric matricies is diagonalizable thus can be processed by algorithm successfully
    public void Should_ReturnEigenPairs_When_SymmetricMatrixIsGiven(double eps){
        EigenvalueAlgorithm algorithm = createAlgorithm();
        SimpleMatrix A = createSymmetricMatrix5X5();
        algorithm.setEps(eps);

        Map<Double,List<SimpleMatrix>> eSpaces = algorithm.apply(A);

        assertEachEigenPairHasBiasLessThanEps(A,eSpaces,eps);
    }
}
