package smartcalc.console;

import smartcalc.equation.EquationSolver;
import smartcalc.equation.InvalidEquationException;
import smartcalc.expression.ExpressionSolver;
import smartcalc.matrix.InvalidMatrixException;
import smartcalc.matrix.MatrixOperator;
import smartcalc.matrix.OperationNotAllowedException;

public class ConsoleInterface {

    private final EquationSolver equationSolver = new EquationSolver();
    private final ExpressionSolver expressionSolver = new ExpressionSolver();
    private final MatrixOperator matrixOperator = new MatrixOperator();

    public void open() {
        while (true) {
            try {
                String inputLine = ConsoleInput.getLine();
                switch (InputRecognizer.recognize(inputLine)) {
                    case NUMBER -> System.out.println(inputLine);
                    case VARIABLE_ASSIGNMENT -> expressionSolver.assignVariable(inputLine);
                    case VARIABLE_CALL -> System.out.println(expressionSolver.getVariable(inputLine));
                    case SHOW_VARIABLES -> System.out.println(expressionSolver.getAllVariables());
                    case EXPRESSION -> System.out.println(expressionSolver.solveExpression(inputLine));
                    case EQUATIONS_FLAG -> solveEquations();
                    case MATRIX_ADD -> addMatrices();
                    case MATRIX_MULTIPLY -> multiplyMatrices();
                    case MATRIX_MULTIPLY_BY_SCALAR -> multiplyMatrixByScalar();
                    case MATRIX_DETERMINANT -> calculateDeterminant();
                    case MATRIX_INVERSE -> inverseMatrix();
                    case MATRIX_TRANSPOSE -> transposeMatrix();
                    case MATRIX_FLIP_VERTICAL -> flipMatrixVertically();
                    case MATRIX_FLIP_HORIZONTAL -> flipMatrixHorizontally();
                    case HELP -> printHelpMessage();
                    case INCORRECT -> System.out.println("Incorrect input");
                    case EXIT -> {
                        return;
                    }
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void solveEquations() throws InvalidEquationException {
        System.out.println("Enter linear equations in correct form:");
        System.out.println(equationSolver.solve(ConsoleInput.getEquations()));
    }

    private void addMatrices() throws InvalidMatrixException, OperationNotAllowedException {
        System.out.println("Enter first matrix:");
        double[][] firstMatrix = ConsoleInput.getMatrix();
        System.out.println("Enter second matrix:");
        double[][] secondMatrix = ConsoleInput.getMatrix();
        double[][] result = matrixOperator.addMatrices(firstMatrix, secondMatrix);
        System.out.println("The result is:");
        System.out.println(matrixOperator.getAsString(result));
    }

    private void multiplyMatrices() throws InvalidMatrixException, OperationNotAllowedException {
        System.out.println("Enter first matrix:");
        double[][] firstMatrix = ConsoleInput.getMatrix();
        System.out.println("Enter second matrix:");
        double[][] secondMatrix = ConsoleInput.getMatrix();
        double[][] resultMatrix = matrixOperator.multiplyMatrices(firstMatrix, secondMatrix);
        System.out.println("The result is:");
        System.out.println(matrixOperator.getAsString(resultMatrix));
    }

    private void multiplyMatrixByScalar() throws InvalidMatrixException {
        System.out.println("Enter matrix:");
        double[][] matrix = ConsoleInput.getMatrix();
        System.out.println("Enter scalar value:");
        matrixOperator.multiplyByScalar(matrix, ConsoleInput.getDouble());
        System.out.println("The result is:");
        System.out.println(matrixOperator.getAsString(matrix));
    }

    private void calculateDeterminant() throws InvalidMatrixException {
        System.out.println("Enter matrix:");
        double[][] matrix = ConsoleInput.getMatrix();
        System.out.println("The result is:");
        System.out.println(matrixOperator.calculateDeterminant(matrix));
    }

    private void inverseMatrix() throws InvalidMatrixException, OperationNotAllowedException {
        System.out.println("Enter matrix:");
        double[][] matrix = ConsoleInput.getMatrix();
        double[][] inverted = matrixOperator.inverse(matrix);
        System.out.println("The result is:");
        System.out.println(matrixOperator.getAsString(inverted));
    }

    private void transposeMatrix() throws InvalidMatrixException {
        System.out.println("Enter matrix:");
        double[][] resultMatrix = matrixOperator.transpose(ConsoleInput.getMatrix());
        System.out.println("The result is:");
        System.out.println(matrixOperator.getAsString(resultMatrix));
    }

    private void flipMatrixVertically() throws InvalidMatrixException {
        System.out.println("Enter matrix:");
        double[][] resultMatrix = matrixOperator.flipVertically(ConsoleInput.getMatrix());
        System.out.println("The result is:");
        System.out.println(matrixOperator.getAsString(resultMatrix));
    }

    private void flipMatrixHorizontally() throws InvalidMatrixException {
        System.out.println("Enter matrix:");
        double[][] resultMatrix = matrixOperator.flipHorizontally(ConsoleInput.getMatrix());
        System.out.println("The result is:");
        System.out.println(matrixOperator.getAsString(resultMatrix));
    }

    private void printHelpMessage() {
        System.out.println("""
                            
                With this program you can perform 3 types of calculations:
                            
                EXPRESSIONS - this module calculates simple mathematical expressions.
                You can use numbers, operators(+ - * / ^), variables and brackets.
                Before using variable you need to assign it like this: name = value.
                                
                LINEAR EQUATIONS - this module calculates value of variables in linear equations systems.
                Example of correct equations format and results:
                    
                input:          | description
                -------------------------------------------------------------
                3 3             | number of variables and number of equations
                1 1 2 9         | equation: 1x + 1y + 2z = 9
                2 4 -3 1        | equation: 2x + 4y - 3z = 1
                3 6 -5 0        | equation: 3x + 6y - 5z = 0
                -------------------------------------------------------------
                results:        |
                -------------------------------------------------------------
                1.0             | value of variable x
                2.0             | value of variable y
                3.0             | value of variable z
                            
                MATRICES - this module allows you to operate on matrices.
                Available operations:
                - matrices addition
                - matrices multiplication
                - matrix multiplication by scalar
                - matrix determinant calculation
                - matrix inversion
                - matrix transposition
                - matrix vertical flip
                - matrix horizontal flip
                            
                            
                Example of correct matrix format:
                            
                input:          | description
                -------------------------------------------------------------
                3 3             | number of rows and number of columns
                1 2 2           |
                2 4 -3          | matrix elements
                3 6 -5          |


                Available commands:
                /variables                  - shows all defined variables
                /equation                   - marks next input as equations
                /matrix add                 - type to add matrices
                /matrix multiple            - type to multiply matrices
                /matrix multiple by scalar  - type to multiply matrix by scalar
                /matrix determinant         - type to calculate matrix determinant
                /matrix inverse             - type to inverse matrix
                /matrix transpose           - type to transpose matrix
                /matrix flip vertical       - type to flip matrix vertically
                /matrix flip horizontal     - type to flip matrix horizontally
                /help                       - type to display this message
                /exit                       - type to exit program
                """);
    }
}
