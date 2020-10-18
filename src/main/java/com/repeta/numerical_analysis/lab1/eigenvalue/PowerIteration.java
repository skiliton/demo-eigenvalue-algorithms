package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PowerIteration extends EigenvalueAlgorithm {

    private SimpleMatrix y;
    private SimpleMatrix x;
    private SimpleMatrix l1;
    private SimpleMatrix A;

    @Override
    protected void init(SimpleMatrix A) {
        this.A = A;
        y = new SimpleMatrix(A.numRows(),1);
        y.fill(1);
        x = new SimpleMatrix(y);
        l1 = new SimpleMatrix(y);
    }

    @Override
    protected void iteration() {
        y = A.mult(x);
        l1 = y.elementDiv(x);
        x = y.divide(y.normF());
    }

    @Override
    protected Map<Double, List<SimpleMatrix>> createEigenSpaces() {
        double eigenVal = l1.elementSum()/l1.numRows();
        List<SimpleMatrix> eigenVecs = new ArrayList<>();
        eigenVecs.add(x);
        Map<Double,List<SimpleMatrix>> res = new HashMap<>();
        res.put(eigenVal,eigenVecs);
        return res;
    }
}
