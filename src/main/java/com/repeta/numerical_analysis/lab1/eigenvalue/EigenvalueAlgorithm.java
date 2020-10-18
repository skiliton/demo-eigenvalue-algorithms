package com.repeta.numerical_analysis.lab1.eigenvalue;

import org.ejml.simple.SimpleMatrix;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class EigenvalueAlgorithm implements Function<SimpleMatrix,Map<Double,List<SimpleMatrix>>> {

    public static final int ITER_INCREMENT = 1000;
    private double eps;
    private int maxIterations = 500_000;


    public EigenvalueAlgorithm setEps(double eps) {
        this.eps = eps;
        return this;
    }

    public EigenvalueAlgorithm setMaxIterations(int maxIterations){
        this.maxIterations = maxIterations;
        return this;
    }


    abstract protected void init(SimpleMatrix A);

    abstract protected void iteration();

    abstract protected Map<Double, List<SimpleMatrix>> createEigenSpaces();

    @Override
    public Map<Double, List<SimpleMatrix>> apply(SimpleMatrix A) {
        int iterations = 0;
        Map<Double, List<SimpleMatrix>> eSpaces;
        init(A);
        do{
            for(int i=0;i<ITER_INCREMENT;i++){
                iteration();
            }
            eSpaces = createEigenSpaces();
            iterations+=ITER_INCREMENT;
        }while (calcBias(A,eSpaces)>eps && iterations<=maxIterations);
        return eSpaces;
    }

    private double calcBias(SimpleMatrix A, Map<Double,List<SimpleMatrix>> eSpaces){
        double maxBias = 0;
        for (Map.Entry<Double,List<SimpleMatrix>> eSpace: eSpaces.entrySet()) {
            double lambda = eSpace.getKey();
            for(SimpleMatrix x: eSpace.getValue()){
                double bias = A.mult(x).minus(x.scale(lambda)).elementMaxAbs();
                maxBias = (maxBias>bias ? maxBias : bias);
            }
        }
        return maxBias;
    }
}
