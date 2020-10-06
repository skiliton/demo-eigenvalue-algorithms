package com.repeta.numerical_analysis.lab1.output;

public interface EigenSpaceEncoderFactory {
    enum Mode{TEXT,CSV}
    public EigenSpaceEncoder createEncoder(Mode type);
}
