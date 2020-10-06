package com.repeta.numerical_analysis.lab1.eigenvalue;

import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithm;

public interface EigenvalueAlgorithmFactory {
    enum Algorithm {QR,POWER_ITERATION, JACOBI}
    public EigenvalueAlgorithm createAlgorithm(Algorithm type);
}
