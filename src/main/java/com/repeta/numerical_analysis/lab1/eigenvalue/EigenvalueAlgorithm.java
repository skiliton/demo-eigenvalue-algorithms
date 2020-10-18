package com.repeta.numerical_analysis.lab1.eigenvalue;

import com.repeta.numerical_analysis.lab1.ConfigurationSingleton;
import org.ejml.simple.SimpleMatrix;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.repeta.numerical_analysis.lab1.ConfigurationSingleton.*;

public abstract class EigenvalueAlgorithm implements Function<SimpleMatrix,Map<Double,List<SimpleMatrix>>> {
    protected double eps = getConfiguration().getDouble("algorithm.default-eps");

    public EigenvalueAlgorithm setEps(double eps) {
        this.eps = eps;
        return this;
    }
}
