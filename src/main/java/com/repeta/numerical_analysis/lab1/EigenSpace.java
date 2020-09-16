package com.repeta.numerical_analysis.lab1;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.ejml.simple.SimpleMatrix;

import java.util.List;

@Data
public class EigenSpace {
    @NotNull private double eigenValue;
    @NotNull private List<SimpleMatrix> eigenVectors;
}
