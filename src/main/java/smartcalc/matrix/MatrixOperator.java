package smartcalc.matrix;

public class MatrixOperator {

    public double[][] addMatrices(double[][] firstMatrix, double[][] secondMatrix) throws OperationNotAllowedException {
        if (firstMatrix.length != secondMatrix.length || firstMatrix[0].length != secondMatrix[0].length) {
            throw new OperationNotAllowedException("Adding this two matrices is not allowed");
        }
        double[][] resultMatrix = new double[firstMatrix.length][firstMatrix[0].length];
        for (int row = 0; row < firstMatrix.length; row++) {
            for (int col = 0; col < firstMatrix[0].length; col++) {
                resultMatrix[row][col] = firstMatrix[row][col] + secondMatrix[row][col];
            }
        }
        return resultMatrix;
    }

    public double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) throws OperationNotAllowedException {
        if (firstMatrix[0].length != secondMatrix.length) {
            throw new OperationNotAllowedException("Multiplying this two matrices is not allowed");
        }
        double[][] resultMatrix = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < resultMatrix.length; row++) {
            for (int col = 0; col < resultMatrix[0].length; col++) {
                resultMatrix[row][col] = calculateDotProduct(firstMatrix, secondMatrix, row, col);
            }
        }
        return resultMatrix;
    }

    private double calculateDotProduct(double[][] firstMatrix, double[][] secondMatrix, int row, int column) {
        double dotProduct = 0;
        for (int i = 0; i < firstMatrix[0].length; i++) {
            dotProduct += firstMatrix[row][i] * secondMatrix[i][column];
        }
        return dotProduct;
    }

    public void multiplyByScalar(double[][] matrix, double scalar) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] *= scalar;
            }
        }
    }

    public double calculateDeterminant(double[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        } else if (matrix.length == 2) {
            return ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
        }
        double result = 0;
        for (int col = 0; col < matrix[0].length; col++) {
            double[][] reducedMatrix = getMatrixWithDeletedRowAndCol(matrix, 0, col);
            result += matrix[0][col] * Math.pow(-1, col) * calculateDeterminant(reducedMatrix);
        }
        return result;
    }

    public double[][] inverse(double[][] matrix) throws OperationNotAllowedException {
        if (matrix.length != matrix[0].length) {
            throw new OperationNotAllowedException("Inversion of rectangular matrix is not supported");
        }
        double determinant = calculateDeterminant(matrix);
        if (determinant == 0) {
            throw new OperationNotAllowedException("Singular matrix is not invertible");
        }
        double[][] matrixInverse = transpose(getCofactorMatrix(matrix));
        multiplyByScalar(matrixInverse, 1 / determinant);
        return matrixInverse;
    }

    public double[][] getCofactorMatrix(double[][] matrix) {
        double[][] cofactorMatrix = new double[matrix.length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                int signModifier = ((row + col) % 2 == 0) ? 1 : -1;
                cofactorMatrix[row][col] = signModifier * calculateDeterminant(getMatrixWithDeletedRowAndCol(matrix, row, col));
            }
        }
        return cofactorMatrix;
    }

    private double[][] getMatrixWithDeletedRowAndCol(double[][] matrix, int deletionRow, int deletionCol) {
        double[][] reducedMatrix = new double[matrix.length - 1][matrix.length - 1];
        int currRow = 0;
        int currCol = 0;
        for (int row = 0; row < matrix.length; row++) {
            if (row == deletionRow) {
                continue;
            }
            for (int col = 0; col < matrix.length; col++) {
                if (col == deletionCol) {
                    continue;
                }
                reducedMatrix[currRow][currCol++] = matrix[row][col];
            }
            currRow++;
            currCol = 0;
        }
        return reducedMatrix;
    }

    public double[][] transpose(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix[0].length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                transposedMatrix[col][row] = matrix[row][col];
            }
        }
        return transposedMatrix;
    }

    public double[][] flipVertically(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                transposedMatrix[row][col] = matrix[row][matrix[0].length - 1 - col];
            }
        }
        return transposedMatrix;
    }

    public double[][] flipHorizontally(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                transposedMatrix[row][col] = matrix[matrix.length - 1 - row][col];
            }
        }
        return transposedMatrix;
    }

    public String getAsString(double[][] matrix) {
        StringBuilder builder = new StringBuilder();
        for (double[] row : matrix) {
            for (double num : row) {
                builder.append(String.format("%.5f", num)).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
