package com.repeta.numerical_analysis.lab1.matrix;

public abstract class MatrixLoader {

    protected MatrixLoader next;

    public MatrixLoader setNext(MatrixLoader next) {
        this.next = next;
        return next;
    }

    public double[][] load(String matrixName){
        double[][] matrix = loadMatrix(matrixName);
        if(matrix!=null){
            return matrix;
        }else if(next!=null){
            return next.loadMatrix(matrixName);
        }
        return null;
    }

    abstract protected double[][] loadMatrix(String matrixName);
}
