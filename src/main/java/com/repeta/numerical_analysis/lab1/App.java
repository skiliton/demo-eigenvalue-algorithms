package com.repeta.numerical_analysis.lab1;

import com.repeta.numerical_analysis.lab1.eigenvalue.DemoEigenvalueAlgorithmFactory;
import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithm;
import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithmFactory;
import com.repeta.numerical_analysis.lab1.eigenvalue.EigenvalueAlgorithmFactory.Algorithm;
import com.repeta.numerical_analysis.lab1.matrix.CSVMatrixLoader;
import com.repeta.numerical_analysis.lab1.matrix.MatrixLoader;
import com.repeta.numerical_analysis.lab1.output.*;
import com.repeta.numerical_analysis.lab1.output.EigenSpaceEncoderFactory.Mode;
import org.ejml.simple.SimpleMatrix;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name = "evd",
    mixinStandardHelpOptions = true,
    version = "evd 1.0",
    description = "Finds eigenvalues and eigenvectors for a given matrix"
)
public class App implements Callable<Integer>
{
    @Spec CommandSpec spec;

    @Parameters(
        index = "0",
        paramLabel = "<matrix>",
        description = "matrix filename matrix"
    )
    @Pattern(regexp = "^(.+)\\/([^/]+)$", message = "Invalid matrix filepath")
    private String matrixName;

    @Option(
        names = {"-a","-algorithmName"},
        required = true,
        description = "type of algorithmName to be used, valid values: ${COMPLETION-CANDIDATES}"
    )
    private Algorithm algorithmName;

    @Option(
        names = {"-o","-output"},
        defaultValue = "TEXT",
        description = "output format, valid values: ${COMPLETION-CANDIDATES}. By default option is set to ${DEFAULT-VALUE}"
    )
    private Mode outputFormat = Mode.TEXT;

    @Option(
        names = {"-e","-eps"},
        defaultValue = "0.0001",
        description = "Threshold value for algorithms by default is set to ${DEFAULT-VALUE}"
    )
    @Positive(message = "Value of epsilon must be greater than 0")
    private double eps;

    public static void main( String[] args )
    {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        validate();
        MatrixLoader matrixLoader = new CSVMatrixLoader();
        double[][] matrix = matrixLoader.load(matrixName);
        if (matrix==null){
            throw new ParameterException(
                spec.commandLine(),
                String.format("Invalid value '%s' for parameter 'MATRIX': matrix cannot be loaded",matrixName)
            );
        }
        SimpleMatrix A = new SimpleMatrix(matrix);
        EigenvalueAlgorithmFactory algorithmFactory = new DemoEigenvalueAlgorithmFactory();
        EigenvalueAlgorithm algorithm = algorithmFactory.createAlgorithm(algorithmName);
        algorithm.setEps(eps);
        Map<Double,List<SimpleMatrix>> eSpaces = algorithm.apply(A);
        EigenSpaceEncoderFactory encoderFactory = new DemoEigenSpaceEncoderFactory();
        EigenSpaceEncoder encoder = encoderFactory.createEncoder(outputFormat);
        System.out.print(encoder.encode(eSpaces));
        return 0;
    }

    private void validate() {
        System.out.println(spec.commandLine().getParseResult().originalArgs());
        System.out.println(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<App>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder();
            for (ConstraintViolation<App> violation : violations) {
                errorMsg.append("ERROR: ").append(violation.getMessage()).append("\n");
            }
            throw new ParameterException(spec.commandLine(), errorMsg.toString());
        }
    }
}