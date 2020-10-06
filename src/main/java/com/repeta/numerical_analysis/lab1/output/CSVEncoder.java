package com.repeta.numerical_analysis.lab1.output;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.ejml.simple.SimpleMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVEncoder implements EigenSpaceEncoder{
    @Override
    public String encode(Map<Double, List<SimpleMatrix>> eSpaces) {
        StringBuffer buffer = new StringBuffer();
        try {
            CSVPrinter printer = new CSVPrinter(buffer, CSVFormat.RFC4180);
            boolean isHeaderSet = false;
            for (Map.Entry<Double,List<SimpleMatrix>> eSpace: eSpaces.entrySet()) {
                int n = eSpace.getValue().get(0).numRows();
                if(!isHeaderSet){
                    ArrayList<String> header = new ArrayList<>();
                    header.add("lambda");
                    for(int i = 0; i< n; i++){
                        header.add("x"+(i+1));
                    }
                    printer.printRecord(header);
                    isHeaderSet=true;
                }
                for (SimpleMatrix vec: eSpace.getValue()){
                    ArrayList<Double> eigenPair = new ArrayList<>();
                    eigenPair.add(eSpace.getKey());
                    for (int i=0;i<n;i++){
                        eigenPair.add(vec.get(i));
                    }
                    printer.printRecord(eigenPair);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
