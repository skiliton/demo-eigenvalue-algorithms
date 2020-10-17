package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;

public class MatrixMother {
    public static SimpleMatrix createSymmetricMatrix5X5(){
        return new SimpleMatrix(new double[][]{
            { 818, -287, -162,  184, -383},
            {-287,  718, -161,   10,    3},
            {-162, -161,  667, -588, -239},
            { 184,   10, -588,  828,   -4},
            {-383,    3, -239,   -4,  843}
        });
    }
    public static SimpleMatrix createDiagonizableMatrix5X5(){
        return new SimpleMatrix(new double[][]{
            {-751, -193,   -2, -437,  337},
            { 405, -773,  404, -834,  114},
            {-916, -490, -488,  294,  915},
            { -76,  -15,  349, -913,  233},
            { 826,  462,  759, -953, -984}
        });
    }

}
