package com.repeta.numerical_analysis.lab1.eigenvalue;

import com.repeta.numerical_analysis.lab1.matrix.SimpleMatrixHelper;
import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacobiMethod extends EigenvalueAlgorithm {

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

    @Override
    public Map<Double,List<SimpleMatrix>> apply(SimpleMatrix A) {
        SimpleMatrix S = new SimpleMatrix(A);
        int n = A.numRows();
        SimpleMatrix GChain = SimpleMatrix.identity(n);
        ArrayList<Double> eValues;
        ArrayList<SimpleMatrix> eVectors;
        double bias;

        do{
            MatrixElement pivot = getMaxOffDiagonalElement(S);
            SimpleMatrix G = createGivensRotationMatrix(S,pivot);
            GChain = GChain.mult(G);
            S = G.transpose().mult(S.mult(G));
            eValues = SimpleMatrixHelper.fetchDiagonalValues(S);
            eVectors = SimpleMatrixHelper.fetchColumnVectors(GChain);
            bias=0;
            for(int i=0; i<n; i++){
                SimpleMatrix biasVec = A.mult(eVectors.get(i)).minus(eVectors.get(i).scale(eValues.get(i)));
                bias+= biasVec.elementMaxAbs();
            };
        }while (bias > n*eps);

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
}
