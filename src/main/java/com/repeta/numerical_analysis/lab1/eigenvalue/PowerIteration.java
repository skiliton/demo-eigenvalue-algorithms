package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PowerIteration extends EigenvalueAlgorithm {

    @Override
    public Map<Double,List<SimpleMatrix>> apply(SimpleMatrix A) {
        SimpleMatrix y = new SimpleMatrix(A.numRows(),1);
        y.fill(1);
        SimpleMatrix x = new SimpleMatrix(y);
        SimpleMatrix l1 = new SimpleMatrix(y);
        SimpleMatrix l0;

        do{
            l0=l1;
            y = A.mult(x);
            l1 = y.elementDiv(x);
            x = y.divide(y.normF());
        }while (l1.minus(l0).elementMaxAbs()>=eps);

        double eigenVal = l1.elementSum()/l1.numRows();
        List<SimpleMatrix> eigenVecs = new ArrayList<>();
        eigenVecs.add(x);
        Map<Double,List<SimpleMatrix>> res = new HashMap<>();
        res.put(eigenVal,eigenVecs);

        return res;
    }
}
