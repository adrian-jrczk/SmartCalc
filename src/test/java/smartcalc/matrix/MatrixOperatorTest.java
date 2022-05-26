package smartcalc.matrix;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

class MatrixOperatorTest {

    private MatrixOperator matrixOperator = new MatrixOperator();

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#validInversionMatrices")
    void inverse_ValidMatrix(double[][] inputMatrix, double[][] expectedMatrix) {
        double[][] actualMatrix = assertDoesNotThrow(() -> matrixOperator.inverse(inputMatrix));
        for(int row = 0; row < expectedMatrix.length; row++) {
            assertArrayEquals(expectedMatrix[row], actualMatrix[row], 0.0001);
        }
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#rectangularInversionMatrices")
    void inverse_RectangularMatrix(double[][] inputMatrix) {
        String expectedMessage = "Inversion of rectangular matrix is not supported";
        Exception exception = assertThrows(OperationNotAllowedException.class, () -> matrixOperator.inverse(inputMatrix));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#matricesWithDeterminants")
    void calculateDeterminant(double[][] inputMatrix, double expectedDeterminant) {
        double actual = matrixOperator.calculateDeterminant(inputMatrix);
        assertEquals(expectedDeterminant, actual);
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#validAdditionMatrices")
    void addMatrices_OperationAllowed(double[][] firstMatrix, double[][] secondMatrix, double[][] expectedMatrix) {
        double[][] actualMatrix = assertDoesNotThrow(() -> matrixOperator.addMatrices(firstMatrix, secondMatrix));
        for(int row = 0; row < expectedMatrix.length; row++) {
            assertArrayEquals(expectedMatrix[row], actualMatrix[row], 0.0001);
        }
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#invalidAdditionMatrices")
    void addMatrices_OperationNotAllowed(double[][] firstMatrix, double[][] secondMatrix) {
        String expectedMessage = "Adding this two matrices is not allowed";
        Exception exception = assertThrows(OperationNotAllowedException.class, () -> matrixOperator.addMatrices(firstMatrix, secondMatrix));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#validMultiplicationMatrices")
    void multiplyMatrices_OperationAllowed(double[][] firstMatrix, double[][] secondMatrix, double[][] expectedMatrix) {
        double[][] actualMatrix = assertDoesNotThrow(() -> matrixOperator.multiplyMatrices(firstMatrix, secondMatrix));
        for(int row = 0; row < expectedMatrix.length; row++) {
            assertArrayEquals(expectedMatrix[row], actualMatrix[row], 0.0001);
        }
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#invalidMultiplicationMatrices")
    void multiplyMatrices_OperationNotAllowed(double[][] firstMatrix, double[][] secondMatrix) {
        String expectedMessage = "Multiplying this two matrices is not allowed";
        Exception exception = assertThrows(OperationNotAllowedException.class, () -> matrixOperator.multiplyMatrices(firstMatrix, secondMatrix));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#matricesWithScalars")
    void multiplyByScalar(double[][] matrix, double scalar, double[][] expectedMatrix) {
        matrixOperator.multiplyByScalar(matrix, scalar);
        for(int row = 0; row < expectedMatrix.length; row++) {
            assertArrayEquals(expectedMatrix[row], matrix[row], 0.0001);
        }
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#transposeMatrices")
    void transpose(double[][] matrix, double[][] expectedMatrix) {
        double[][] actualMatrix = matrixOperator.transpose(matrix);
        for(int row = 0; row < expectedMatrix.length; row++) {
            assertArrayEquals(expectedMatrix[row], actualMatrix[row], 0.0001);
        }
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#verticalFlipMatrices")
    void flipVertically(double[][] matrix, double[][] expectedMatrix) {
        double[][] actualMatrix = matrixOperator.flipVertically(matrix);
        for(int row = 0; row < expectedMatrix.length; row++) {
            assertArrayEquals(expectedMatrix[row], actualMatrix[row], 0.0001);
        }
    }

    @ParameterizedTest
    @MethodSource("smartcalc.matrix.ParameterProvider#horizontalFlipMatrices")
    void flipHorizontally(double[][] matrix, double[][] expectedMatrix) {
        double[][] actualMatrix = matrixOperator.flipHorizontally(matrix);
        for(int row = 0; row < expectedMatrix.length; row++) {
            assertArrayEquals(expectedMatrix[row], actualMatrix[row], 0.0001);
        }
    }
}