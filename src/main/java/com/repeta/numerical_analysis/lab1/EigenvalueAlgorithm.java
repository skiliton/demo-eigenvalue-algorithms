package com.repeta.numerical_analysis.lab1;

import org.ejml.simple.SimpleMatrix;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface EigenvalueAlgorithm extends Function<SimpleMatrix,Map<Double,List<SimpleMatrix>>> {
}
