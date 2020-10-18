package com.repeta.numerical_analysis.lab1.eigenvalue;

import com.repeta.numerical_analysis.lab1.matrix.SimpleMatrixHelper;
import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacobiAlgorithm extends EigenvalueAlgorithm {

    private int n;
    private SimpleMatrix S;
    private SimpleMatrix GChain;

    @Override
    protected void init(SimpleMatrix A) {
        this.n = A.numRows();
        S = new SimpleMatrix(A);
        GChain = SimpleMatrix.identity(n);
    }

    @Override
    protected void iteration() {
        MatrixElement pivot = getMaxOffDiagonalElement(S);
        SimpleMatrix G = createGivensRotationMatrix(S,pivot);
        GChain = GChain.mult(G);
        S = G.transpose().mult(S.mult(G));
    }

    @Override
    protected Map<Double, List<SimpleMatrix>> createEigenSpaces() {
        ArrayList<Double> eValues = SimpleMatrixHelper.fetchDiagonalValues(S);
        ArrayList<SimpleMatrix> eVectors = SimpleMatrixHelper.fetchColumnVectors(GChain);
        Map<Double,List<SimpleMatrix>> res = new HashMap<>();
        for (int i=0;i<n;i++){
            res.put(eValues.get(i),eVectors.subList(i,i+1));
        }
        return res;
    }

    private SimpleMatrix createGivensRotationMatrix(SimpleMatrix A, MatrixElement aij){
        double fi = 0.5*Math.atan(2*aij.val/(A.get(aij.i, aij.i) - A.get(aij.j, aij.j)));
        double c = Math.cos(fi);
        double s = Math.sin(fi);
        SimpleMatrix G = SimpleMatrix.identity(A.numRows());
        G.set(aij.i,aij.i,c);
        G.set(aij.j,aij.j,c);
        G.set(aij.i,aij.j,-s);
        G.set(aij.j,aij.i,s);
        return G;
    }

    private MatrixElement getMaxOffDiagonalElement(SimpleMatrix A) {
        int n = A.numRows();
        MatrixElement aij = new MatrixElement(0,-1,-1);
        for(int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                if(Math.abs(aij.val)<Math.abs(A.get(i,j))){
                    aij = new MatrixElement(A.get(i,j),i,j);
                }
            }
        }
        return aij;
    }

    private class MatrixElement {
        public double val;
        public int i;
        public int j;

        public MatrixElement(double val, int i, int j) {
            this.val = val;
            this.i = i;
            this.j = j;
        }
    }
}
