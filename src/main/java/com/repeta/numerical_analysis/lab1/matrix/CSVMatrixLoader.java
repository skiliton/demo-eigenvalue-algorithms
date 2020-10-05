package com.repeta.numerical_analysis.lab1.matrix;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CSVMatrixLoader extends MatrixLoader{
    @Override
    protected double[][] loadMatrix(String matrixName) {
        if(matrixName.matches(".*\\.csv$")) {
            File csvData = new File(matrixName);
            CSVParser parser = null;
            try {
                parser = CSVParser.parse(csvData, StandardCharsets.UTF_8, CSVFormat.RFC4180);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            double[][] matrix = null;
            int i = 0;
            for (CSVRecord csvRecord : parser) {
                if (matrix == null) {
                    matrix = new double[csvRecord.size()][csvRecord.size()];
                }
                for (int j = 0; j < csvRecord.size(); j++) {
                    matrix[i][j] = new Double(csvRecord.get(j));
                }
                i++;
            }
            return matrix;
        }
        return null;
    }
}
