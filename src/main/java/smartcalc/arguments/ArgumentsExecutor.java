package smartcalc.arguments;

import com.beust.jcommander.JCommander;
import smartcalc.equation.EquationSolver;
import smartcalc.equation.InvalidEquationException;
import smartcalc.expression.ExpressionSolver;
import smartcalc.expression.InvalidAssignmentException;
import smartcalc.expression.InvalidExpressionException;
import smartcalc.matrix.InvalidMatrixException;
import smartcalc.matrix.MatrixOperator;
import smartcalc.matrix.OperationNotAllowedException;
import smartcalc.utils.FileUtils;
import java.util.*;

public class ArgumentsExecutor {

    private final EquationSolver equationSolver = new EquationSolver();
    private final ExpressionSolver expressionSolver = new ExpressionSolver();
    private final MatrixOperator matrixOperator = new MatrixOperator();

    public void run(String[] args) {
        try {
            Arguments arguments = new Arguments();
            JCommander.newBuilder().addObject(arguments).build().parse(args);
            String fileData = FileUtils.loadData(arguments.inputFileName);
            String results = "";
            switch (arguments.calculationType) {
                case "equation" -> results = solveEquations(fileData);
                case "expression" -> results = solveExpression(fileData);
                case "matrix" -> {
                    switch (arguments.matrixOperation) {
                        case "add" -> results = addMatrices(fileData);
                        case "multiply" -> results = multiplyMatrices(fileData);
                        case "multiply-by-scalar" -> results = multiplyMatrixByScalar(fileData);
                        case "determinant" -> results = calculateMatrixDeterminant(fileData);
                        case "inverse" -> results = inverseMatrix(fileData);
                        case "transpose" -> results = transposeMatrix(fileData);
                        case "flip-vertical" -> results = flipMatrixVertically(fileData);
                        case "flip-horizontal" -> results = flipMatrixHorizontally(fileData);
                        default -> System.out.println("No matrix operation specified");
                    }
                }
            }
            if (arguments.outputFileName.isEmpty()) {
                int dotIndex = arguments.inputFileName.lastIndexOf(".");
                FileUtils.saveResults(results, arguments.inputFileName.substring(0, dotIndex) + "-results.txt");
            } else {
                FileUtils.saveResults(results, arguments.outputFileName);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private String solveEquations(String fileData) throws InvalidEquationException {
        double[][] equations = extractEquations(fileData);
        return equationSolver.solve(equations);
    }

    private String solveExpression(String fileData) throws InvalidExpressionException, InvalidAssignmentException {
        return expressionSolver.resolveRawInput(fileData);
    }

    private String addMatrices(String fileData) throws InvalidMatrixException, OperationNotAllowedException {
        List<double[][]> matrices = extractMatrices(fileData, 2);
        return matrixOperator.getAsString(matrixOperator.addMatrices(matrices.get(0), matrices.get(1)));
    }

    private String multiplyMatrices(String fileData) throws InvalidMatrixException, OperationNotAllowedException {
        List<double[][]> matrices = extractMatrices(fileData, 2);
        return matrixOperator.getAsString(matrixOperator.multiplyMatrices(matrices.get(0), matrices.get(1)));
    }

    private String multiplyMatrixByScalar(String fileData) throws InvalidMatrixException, InputMismatchException {
        double scalar = new Scanner(fileData).nextDouble();
        double[][] matrix = extractMatrices(fileData, 1).get(0);
        matrixOperator.multiplyByScalar(matrix, scalar);
        return matrixOperator.getAsString(matrix);
    }

    private String calculateMatrixDeterminant(String fileData) throws InvalidMatrixException {
        double[][] matrix = extractMatrices(fileData, 1).get(0);
        return String.valueOf(matrixOperator.calculateDeterminant(matrix));
    }

    private String inverseMatrix(String fileData) throws InvalidMatrixException, OperationNotAllowedException {
        double[][] matrix = extractMatrices(fileData, 1).get(0);
        return matrixOperator.getAsString(matrixOperator.inverse(matrix));
    }

    private String transposeMatrix(String fileData) throws InvalidMatrixException {
        double[][] matrix = extractMatrices(fileData, 1).get(0);
        return matrixOperator.getAsString(matrixOperator.transpose(matrix));
    }

    private String flipMatrixVertically(String fileData) throws InvalidMatrixException {
        double[][] matrix = extractMatrices(fileData, 1).get(0);
        return matrixOperator.getAsString(matrixOperator.flipVertically(matrix));
    }

    private String flipMatrixHorizontally(String fileData) throws InvalidMatrixException {
        double[][] matrix = extractMatrices(fileData, 1).get(0);
        return matrixOperator.getAsString(matrixOperator.flipHorizontally(matrix));
    }

    private double[][] extractEquations(String data) throws InvalidEquationException {
        try (Scanner scanner = new Scanner(data)) {
            int numberOfVariables = scanner.nextInt();
            int numberOfEquations = scanner.nextInt();
            double[][] equations = new double[numberOfEquations][numberOfVariables + 1];
            for (int row = 0; row < numberOfEquations; row++) {
                for (int col = 0; col < numberOfVariables + 1; col++) {
                    equations[row][col] = scanner.nextDouble();
                }
            }
            return equations;
        } catch (InputMismatchException exception) {
            throw new InvalidEquationException("Incorrect data type detected");
        } catch (NoSuchElementException exception) {
            throw new InvalidEquationException("Number of variables and number of equations do not match input");
        }
    }

    private List<double[][]> extractMatrices(String data, int numberOfMatrices) throws InvalidMatrixException {
        List<double[][]> matrices = new ArrayList<>();
        try (Scanner scanner = new Scanner(data)) {
            while (numberOfMatrices > 0) {
                int numberOfRows = scanner.nextInt();
                int numberOfColumns = scanner.nextInt();
                double[][] matrix = new double[numberOfRows][numberOfColumns];
                for (int row = 0; row < numberOfRows; row++) {
                    for (int col = 0; col < numberOfColumns; col++) {
                        matrix[row][col] = scanner.nextDouble();
                    }
                }
                matrices.add(matrix);
                numberOfMatrices--;
            }
            return matrices;
        } catch (InputMismatchException exception) {
            throw new InvalidMatrixException("Incorrect data type detected");
        } catch (NoSuchElementException exception) {
            throw new InvalidMatrixException("Number of rows and number of columns do not match input");
        }
    }
}
