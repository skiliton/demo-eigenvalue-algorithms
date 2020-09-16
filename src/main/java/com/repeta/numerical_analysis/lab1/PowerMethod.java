package com.repeta.numerical_analysis.lab1;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.function.Function;


public class PowerMethod implements Function<SimpleMatrix,EigenSpace>
{
    private static double THRESHOLD = 0.0001;

    @Override
    public EigenSpace apply(SimpleMatrix A) {
        SimpleMatrix y = new SimpleMatrix(A.numRows(),1);
        y.fill(1);
        SimpleMatrix x = new SimpleMatrix(y);
        SimpleMatrix l1 = new SimpleMatrix(y);
        SimpleMatrix l0;
        int k=0;
        do{
            l0=l1;
            y = A.mult(x);
            l1 = y.elementDiv(x);
            x = y.divide(y.normF());
            k++;
        }while (l1.minus(l0).elementMaxAbs()>=THRESHOLD);
        double eigenVal = l1.elementSum()/l1.numRows();
        ArrayList<SimpleMatrix> eigenVecs = new ArrayList<>();
        eigenVecs.add(x);
        return new EigenSpace(eigenVal,eigenVecs);
    }
}
