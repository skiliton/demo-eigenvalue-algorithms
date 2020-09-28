package com.repeta.numerical_analysis.lab1;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JacobiMethod implements Function<SimpleMatrix,List<EigenSpace>> {

    private static final double EPS = 0.0001;

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
    public List<EigenSpace> apply(SimpleMatrix A) {
        A.print();
        SimpleMatrix S = new SimpleMatrix(A);
        int n = A.numRows();
        SimpleMatrix GChain = SimpleMatrix.identity(n);
        ArrayList<Double> eValues;
        ArrayList<SimpleMatrix> eVectors;
        double bias=0;

        do{
            MatrixElement pivot = getMaxOffDiagonalElement(S);
            System.out.println(pivot.val);
            SimpleMatrix G = createGivensRotationMatrix(S,pivot);
            GChain = GChain.mult(G);
            S = G.transpose().mult(S.mult(G));
            eValues = fetchDiagonalValues(S);
            eVectors = fetchColumnVectors(GChain);
            bias=0;
            for(int i=0; i<n; i++){
                SimpleMatrix biasVec = A.mult(eVectors.get(i)).minus(eVectors.get(i).scale(eValues.get(i)));
                bias+= biasVec.elementMaxAbs();
            };
            System.out.println("bias = "+bias+"\n");
        }while (bias > n*EPS);

        ArrayList<EigenSpace> res = new ArrayList<>();
        for (int i=0;i<n;i++){
            res.add(new EigenSpace(eValues.get(i),eVectors.subList(i,i+1)));
        }
        return res;
    }

    private ArrayList<SimpleMatrix> fetchColumnVectors(SimpleMatrix A){
        ArrayList<SimpleMatrix> res= new ArrayList<>();
        for (int i=0;i<A.numRows();i++){
            res.add(A.extractVector(false,i));
        }
        return res;
    }

    private ArrayList<Double> fetchDiagonalValues(SimpleMatrix A){
        ArrayList<Double> res= new ArrayList<>();
        for (int i=0;i<A.numRows();i++){
            res.add(A.get(i,i));
        }
        return res;
    }

    private SimpleMatrix createGivensRotationMatrix(SimpleMatrix A, MatrixElement aij){
        double fi = 0.5*Math.atan(2*aij.val/(A.get(aij.i, aij.i) - A.get(aij.j, aij.j)));
        double c = Math.cos(fi);
        double s = Math.sin(fi);
        SimpleMatrix G = SimpleMatrix.identity(A.numRows());
        System.out.println("s="+s+" c="+c);
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
