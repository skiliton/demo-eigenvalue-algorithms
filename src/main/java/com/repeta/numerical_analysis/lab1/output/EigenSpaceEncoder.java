package com.repeta.numerical_analysis.lab1.output;

import org.ejml.simple.SimpleMatrix;

import java.util.List;
import java.util.Map;

public interface EigenSpaceEncoder {
    public String encode(Map<Double,List<SimpleMatrix>> eSpace);
}
