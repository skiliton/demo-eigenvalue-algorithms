package com.repeta.numerical_analysis.lab1;

public class DemoEigenvalueAlgorithmFactory implements EigenvalueAlgorithmFactory {

    private static final String JACOBI_METHOD = "jacobi";
    private static final String POWER_METHOD = "power";
    private static final String QR_METHOD = "qr";

    @Override
    public EigenvalueAlgorithm createAlgorithm(String type) {
        if(type.equals(JACOBI_METHOD)){
            return new JacobiMethod();
        }
        if(type.equals(POWER_METHOD)){
            return new PowerMethod();
        }
        if(type.equals(QR_METHOD)){
            return new QRMethod();
        }
        return null;
    }
}
