package smartcalc.console;

import smartcalc.equation.InvalidEquationException;
import smartcalc.utils.StringUtils;
import smartcalc.matrix.InvalidMatrixException;
import java.util.InputMismatchException;
import java.util.Scanner;

class ConsoleInput {

    private static final Scanner SCANNER = new Scanner(System.in);

    static double[][] getEquations() throws InvalidEquationException {
        try {
            int numberOfVariables = SCANNER.nextInt();
            int numberOfEquations = SCANNER.nextInt();
            double[][] equations = new double[numberOfEquations][numberOfVariables + 1];
            for (int row = 0; row < numberOfEquations; row++) {
                for (int col = 0; col < numberOfVariables + 1; col++) {
                    equations[row][col] = SCANNER.nextDouble();
                }
            }
            SCANNER.nextLine();
            return equations;
        } catch (InputMismatchException exception) {
            SCANNER.nextLine();
            throw new InvalidEquationException("Wrong data type detected in the input");
        }
    }

    static double[][] getMatrix() throws InvalidMatrixException {
        try {
            int numberOfRows = SCANNER.nextInt();
            int numberOfColumns = SCANNER.nextInt();
            double[][] matrix = new double[numberOfRows][numberOfColumns];
            for (int row = 0; row < numberOfRows; row++) {
                for (int col = 0; col < numberOfColumns; col++) {
                    matrix[row][col] = SCANNER.nextDouble();
                }
            }
            SCANNER.nextLine();
            return matrix;
        } catch (InputMismatchException exception) {
            SCANNER.nextLine();
            throw new InvalidMatrixException("Wrong data type detected in the input");
        }
    }

    static double getDouble() {
        while (true) {
            try {
                return SCANNER.nextDouble();
            } catch (InputMismatchException exception) {
                System.out.println("Incorrect input type. Try again:");
                SCANNER.nextLine();
            }
        }
    }

    static String getLine() {
        System.out.print(">>> ");
        return StringUtils.separateElementsWithOneSpace(SCANNER.nextLine());
    }
}
