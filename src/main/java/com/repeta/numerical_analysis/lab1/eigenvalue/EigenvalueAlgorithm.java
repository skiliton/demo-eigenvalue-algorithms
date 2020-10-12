package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class EigenvalueAlgorithm implements Function<SimpleMatrix,Map<Double,List<SimpleMatrix>>> {
    protected double eps = 0.0001;

    public EigenvalueAlgorithm setEps(double eps) {
        this.eps = eps;
        return this;
    }
}
