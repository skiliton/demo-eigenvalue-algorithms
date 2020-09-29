package com.repeta.numerical_analysis.lab1;

import org.ejml.simple.SimpleMatrix;

import java.util.List;
import java.util.Map;
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
                { 818, -287, -162,  184, -383},
                {-287,  718, -161,   10,    3},
                {-162, -161,  667, -588, -239},
                { 184,   10, -588,  828,   -4},
                {-383,    3, -239,   -4,  843}
        });

        EigenvalueAlgorithm method = new JacobiMethod();
        Map<Double,List<SimpleMatrix>> eSpace = method.apply(A);
        System.out.print(eSpace.toString());
    }
}
