package com.repeta.numerical_analysis.lab1;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;

public class SimpleMatrixHelper {

    public static ArrayList<SimpleMatrix> fetchColumnVectors(SimpleMatrix A){
        ArrayList<SimpleMatrix> res= new ArrayList<>();
        for (int i=0;i<A.numRows();i++){
            res.add(A.extractVector(false,i));
        }
        return res;
    }

    public static ArrayList<Double> fetchDiagonalValues(SimpleMatrix A){
        ArrayList<Double> res= new ArrayList<>();
        for (int i=0;i<A.numRows();i++){
            res.add(A.get(i,i));
        }
        return res;
    }
}
