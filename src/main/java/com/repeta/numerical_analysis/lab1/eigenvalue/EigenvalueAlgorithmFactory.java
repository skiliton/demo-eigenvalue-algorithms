package com.repeta.numerical_analysis.lab1.eigenvalue;

import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithm;

public interface EigenvalueAlgorithmFactory {
    public EigenvalueAlgorithm createAlgorithm(String type);
}
