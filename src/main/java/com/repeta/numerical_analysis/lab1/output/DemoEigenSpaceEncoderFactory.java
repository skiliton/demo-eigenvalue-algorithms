package com.repeta.numerical_analysis.lab1.output;

public class DemoEigenSpaceEncoderFactory implements EigenSpaceEncoderFactory {
    @Override
    public EigenSpaceEncoder createEncoder(Mode type) {
        switch (type){
            case CSV:
                return new CSVEncoder();
            case TEXT:
                return new SimpleTextEncoder();
            default:
                return null;
        }
    }
}
