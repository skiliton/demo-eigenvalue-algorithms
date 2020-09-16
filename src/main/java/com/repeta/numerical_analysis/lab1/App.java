package com.repeta.numerical_analysis.lab1;

import org.ejml.simple.SimpleMatrix;

import java.util.function.Function;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SimpleMatrix A = new SimpleMatrix(new double[][]{
                {1, 2, 3},
                {2, 5, 2},
                {3, 2, 1}
        });
        PowerMethod powerMethod = new PowerMethod();
        EigenSpace eigenSpace = powerMethod.apply(A);
        System.out.print(eigenSpace.toString());
    }
}
