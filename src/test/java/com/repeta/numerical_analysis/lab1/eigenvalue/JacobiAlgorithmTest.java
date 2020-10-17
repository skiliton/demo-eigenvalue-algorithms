package com.repeta.numerical_analysis.lab1.eigenvalue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class JacobiAlgorithmTest extends EigenvalueAlgorithmTest{

    @Override
    protected EigenvalueAlgorithm createAlgorithm() {
        return new JacobiAlgorithm();
    }
}