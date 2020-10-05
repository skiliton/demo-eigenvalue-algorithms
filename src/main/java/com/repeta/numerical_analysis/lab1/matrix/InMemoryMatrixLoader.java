package com.repeta.numerical_analysis.lab1.matrix;

public class InMemoryMatrixLoader extends MatrixLoader{
    public static final String MTRX1 = "t1-m20";
    public static final String MTRX2 = "t2-m20";
    public double[][] loadMatrix(String matrix){
        if(matrix.equals(MTRX1)){
            return new double[][]{
                    { 818, -287, -162,  184, -383},
                    {-287,  718, -161,   10,    3},
                    {-162, -161,  667, -588, -239},
                    { 184,   10, -588,  828,   -4},
                    {-383,    3, -239,   -4,  843}
            };
        }
        if(matrix.equals(MTRX2)){
            return new double[][]{
                    {-751, -193,   -2, -437,  337},
                    { 405, -773,  404, -834,  114},
                    {-916, -490, -488,  294,  915},
                    { -76,  -15,  349, -913,  233},
                    { 826,  462,  759, -953, -984}
            };
        }
        return null;
    }
}
