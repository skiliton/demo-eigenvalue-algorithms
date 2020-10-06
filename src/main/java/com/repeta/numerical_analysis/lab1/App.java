package com.repeta.numerical_analysis.lab1;

import com.repeta.numerical_analysis.lab1.eigenvalue.DemoEigenvalueAlgorithmFactory;
import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithm;
import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithmFactory;
import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithmFactory.Algorithm;
import com.repeta.numerical_analysis.lab1.matrix.CSVMatrixLoader;
import com.repeta.numerical_analysis.lab1.matrix.InMemoryMatrixLoader;
import com.repeta.numerical_analysis.lab1.matrix.MatrixLoader;
import org.ejml.simple.SimpleMatrix;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(
    name = "evd",
    mixinStandardHelpOptions = true,
    version = "evd 1.0",
    description = "Finds eigenvalues and eigenvectors for a given matrix"
)
public class App implements Callable<Integer>
{
    @Parameters(index = "0", paramLabel = "MATRIX", description = "matrix filename or name of internal in-memory matrix")
    private String matrixName;

    @Option(names = {"-a","-algorithm"}, description = "Valid values: ${COMPLETION-CANDIDATES}")
    private Algorithm algorithm;

    public static void main( String[] args )
    {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        MatrixLoader matrixLoader = new InMemoryMatrixLoader();
        MatrixLoader csvMatrixLoader = new CSVMatrixLoader();
        matrixLoader.setNext(csvMatrixLoader);
        double[][] matrix = matrixLoader.load(matrixName);
        if (matrix==null){
            System.out.println("Cannot find matrix "+matrixName);
            return 128;
        }
        SimpleMatrix A = new SimpleMatrix(matrix);
        EigenvalueAlgorithmFactory algorithmFactory = new DemoEigenvalueAlgorithmFactory();
        EigenvalueAlgorithm method = algorithmFactory.createAlgorithm(algorithm);
        System.out.println("Algorithm: "+algorithm);
        System.out.println("Matrix: "+matrixName);
        A.print();
        Map<Double,List<SimpleMatrix>> eSpace = method.apply(A);
        System.out.println("----------RESULT----------");
        System.out.print(eSpace.toString());
        return 0;
    }
}