package com.repeta.numerical_analysis.lab1;

import com.repeta.numerical_analysis.lab1.eigenvalue.DemoEigenvalueAlgorithmFactory;
import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithm;
import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithmFactory;
import com.repeta.numerical_analysis.lab1.matrix.CSVMatrixLoader;
import com.repeta.numerical_analysis.lab1.matrix.InMemoryMatrixLoader;
import com.repeta.numerical_analysis.lab1.matrix.MatrixLoader;
import org.ejml.simple.SimpleMatrix;

import java.util.List;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
        MatrixLoader matrixLoader = new InMemoryMatrixLoader();
        MatrixLoader csvMatrixLoader = new CSVMatrixLoader();
        matrixLoader.setNext(csvMatrixLoader);
        double[][] matrix = matrixLoader.load(args[1]);
        if (matrix==null){
            System.out.println("Cannot find matrix "+args[1]);
            System.exit(128);
            return;
        }
        SimpleMatrix A = new SimpleMatrix(matrix);
        EigenvalueAlgorithmFactory algorithmFactory = new DemoEigenvalueAlgorithmFactory();
        EigenvalueAlgorithm method = algorithmFactory.createAlgorithm(args[0]);
        System.out.println("Algorithm: "+args[0]);
        System.out.println("Matrix: "+args[1]);
        A.print();
        Map<Double,List<SimpleMatrix>> eSpace = method.apply(A);
        System.out.println("----------RESULT----------");
        System.out.print(eSpace.toString());
    }
}
