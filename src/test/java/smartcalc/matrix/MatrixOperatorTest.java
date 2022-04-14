package smartcalc.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixOperatorTest {

    private MatrixOperator matrixOperator = new MatrixOperator();

    @Test
    void inverse() throws OperationNotAllowedException {

        double[][] inputMatrix = {{4, 7}, {2, 6}};
        double[][] expectedMatrix = {{0.6, -0.7}, {-0.2, 0.4}};
        double[][] actualMatrix = matrixOperator.inverse(inputMatrix);
        assertMatricesEquality(expectedMatrix, actualMatrix);

        double[][] inputMatrix2 = {{1, -2}, {3, 4}};
        double[][] expectedMatrix2 = {{0.4, 0.2}, {-0.3, 0.1}};
        double[][] actualMatrix2 = matrixOperator.inverse(inputMatrix2);
        assertMatricesEquality(expectedMatrix2, actualMatrix2);

        double[][] inputMatrix3 = {{1, -2, 1}, {3, 4, -2}};
        Exception exception = assertThrows(OperationNotAllowedException.class, () -> matrixOperator.inverse(inputMatrix3));
        assertEquals("Inversion of rectangular matrix is not supported", exception.getMessage());
    }

    @Test
    void calculateDeterminant() {
        double[][] inputMatrix = {{2, 1, 3}, {4, -2, -1}, {-5, 2, 6}};
        double expected = -45;
        double actual = matrixOperator.calculateDeterminant(inputMatrix);
        assertEquals(expected, actual);

        double[][] inputMatrix2 = {{3, 8}, {4, 6}};
        double expected2 = -14;
        double actual2 = matrixOperator.calculateDeterminant(inputMatrix2);
        assertEquals(expected2, actual2);

        double[][] inputMatrix3 = {{6, 1, 1}, {4, -2, 5}, {2, 8, 7}};
        double expected3 = -306;
        double actual3 = matrixOperator.calculateDeterminant(inputMatrix3);
        assertEquals(expected3, actual3);
    }

    @Test
    void addMatrices() throws OperationNotAllowedException {

        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] secondMatrix = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        double[][] expectedMatrix = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};
        double[][] actualMatrix = matrixOperator.addMatrices(firstMatrix, secondMatrix);
        assertMatricesEquality(expectedMatrix, actualMatrix);

        double[][] firstMatrix2 = {{3, 2}, {1, 4}};
        double[][] secondMatrix2 = {{3, 2}, {1, 4}, {5, 3}};
        Exception exception = assertThrows(OperationNotAllowedException.class, () -> matrixOperator.addMatrices(firstMatrix2, secondMatrix2));
        assertEquals("Adding this two matrices is not allowed", exception.getMessage());
    }

    @Test
    void multiplyMatrices() throws OperationNotAllowedException {

        double[][] firstMatrix = {{1, 2, 3}, {4, 5, 6}};
        double[][] secondMatrix = {{10, 11}, {20, 21}, {30, 31}};
        double[][] expectedMatrix = {{140, 146}, {320, 335}};
        double[][] actualMatrix = matrixOperator.multiplyMatrices(firstMatrix, secondMatrix);
        assertMatricesEquality(expectedMatrix, actualMatrix);

        double[][] firstMatrix2 = {{2, 3}, {-5, 6}, {9, -7}};
        double[][] secondMatrix2 = {{1, -2, 0}, {3, 4, -5}};
        double[][] expectedMatrix2 = {{11, 8, -15}, {13, 34, -30}, {-12, -46, 35}};
        double[][] actualMatrix2 = matrixOperator.multiplyMatrices(firstMatrix2, secondMatrix2);
        assertMatricesEquality(expectedMatrix2, actualMatrix2);

        double[][] firstMatrix3 = {{2, 3}, {-5, 6}, {9, -7}};
        double[][] secondMatrix3 = {{1, -2, 0}, {3, 4, -5}, {1, 1, 1}};
        Exception exception = assertThrows(OperationNotAllowedException.class, () -> matrixOperator.multiplyMatrices(firstMatrix3, secondMatrix3));
        assertEquals("Multiplying this two matrices is not allowed", exception.getMessage());
    }

    @Test
    void multiplyByScalar() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        double scalar = 5;
        matrixOperator.multiplyByScalar(matrix, scalar);
        double[][] expectedMatrix = {{5, 10, 15}, {20, 25, 30}};
        assertMatricesEquality(expectedMatrix, matrix);
    }

    @Test
    void transpose() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expectedMatrix = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        double[][] actualMatrix = matrixOperator.transpose(matrix);
        assertMatricesEquality(expectedMatrix, actualMatrix);

        double[][] matrix2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        double[][] expectedMatrix2 = {{1, 3, 5, 7}, {2, 4, 6, 8}};
        double[][] actualMatrix2 = matrixOperator.transpose(matrix2);
        assertMatricesEquality(expectedMatrix2, actualMatrix2);
    }

    @Test
    void flipVertically() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expectedMatrix = {{3, 2, 1}, {6, 5, 4}, {9, 8, 7}};
        double[][] actualMatrix = matrixOperator.flipVertically(matrix);
        assertMatricesEquality(expectedMatrix, actualMatrix);

        double[][] matrix2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        double[][] expectedMatrix2 = {{2, 1}, {4, 3}, {6, 5}, {8, 7}};
        double[][] actualMatrix2 = matrixOperator.flipVertically(matrix2);
        assertMatricesEquality(expectedMatrix2, actualMatrix2);
    }

    @Test
    void flipHorizontally() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expectedMatrix = {{7, 8, 9}, {4, 5, 6}, {1, 2, 3}};
        double[][] actualMatrix = matrixOperator.flipHorizontally(matrix);
        assertMatricesEquality(expectedMatrix, actualMatrix);

        double[][] matrix2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        double[][] expectedMatrix2 = {{7, 8}, {5, 6}, {3, 4}, {1, 2}};
        double[][] actualMatrix2 = matrixOperator.flipHorizontally(matrix2);
        assertMatricesEquality(expectedMatrix2, actualMatrix2);
    }

    void assertMatricesEquality(double[][] firstMatrix, double[][] secondMatrix) {
        for(int row = 0; row < firstMatrix.length; row++) {
            assertArrayEquals(firstMatrix[row], secondMatrix[row], 0.0001);
        }
    }
}