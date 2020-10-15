package com.repeta.numerical_analysis.lab1.eigenvalue;

public class DemoEigenvalueAlgorithmFactory implements EigenvalueAlgorithmFactory {

    @Override
    public EigenvalueAlgorithm createAlgorithm(Algorithm type) {
        switch (type){
            case QR:
                return new QRAlgorithm();
            case JACOBI:
                return new JacobiAlgorithm();
            case POWER_ITERATION:
                return new PowerIteration();
            default:
                return null;
        }
    }
}
