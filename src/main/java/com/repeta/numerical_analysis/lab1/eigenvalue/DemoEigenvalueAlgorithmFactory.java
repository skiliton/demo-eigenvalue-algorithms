package com.repeta.numerical_analysis.lab1.eigenvalue;

public class DemoEigenvalueAlgorithmFactory implements EigenvalueAlgorithmFactory {

    @Override
    public EigenvalueAlgorithm createAlgorithm(Algorithm type) {
        switch (type){
            case QR:
                return new QRMethod();
            case JACOBI:
                return new JacobiMethod();
            case POWER_ITERATION:
                return new PowerMethod();
            default:
                return null;
        }
    }
}
