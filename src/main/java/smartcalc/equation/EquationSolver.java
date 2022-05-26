package smartcalc.equation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EquationSolver {

    private List<String> swaps = new ArrayList<>();

    public String solve(double[][] equations) {
        if (equations == null) {
            return "Empty equations provided";
        }
        if (equations.length == 0) {
            return "Empty equations provided";
        }
        transformToRREForm(equations);
        reverseSwaps(equations);
        if (hasContradiction(equations)) {
            return "No solutions";
        }
        if (countSignificantEquations(equations) < equations[0].length - 1) {
            return "Infinite solutions";
        }
        return getResultsString(equations);
    }

    private void transformToRREForm(double[][] equations) {
        int pivotRow = 0;
        for (int col = 0; col < equations[0].length - 1; col++) {
            for (int row = 0; row < equations.length; row++) {
                if (row == pivotRow) {
                    if (equations[row][col] == 1) {
                        continue;
                    }
                    if (equations[row][col] == 0) {
                        if (!tryToSwapRowsAndColumns(equations, row, col)) {
                            return;
                        }
                    }
                    double rowMultiplier = 1 / equations[row][col];
                    equations[row] = getMultipliedRow(equations[row], rowMultiplier);
                } else {
                    if (equations[row][col] == 0) {
                        continue;
                    }
                    if (equations[pivotRow][col] == 0) {
                        if (!tryToSwapRowsAndColumns(equations, pivotRow, col)) {
                            return;
                        }
                    }
                    double rowMultiplier = -(equations[row][col] / equations[pivotRow][col]);
                    equations[row] = getAddedRows(equations[row], getMultipliedRow(equations[pivotRow], rowMultiplier));
                }
            }
            if (pivotRow + 1 < equations.length) {
                pivotRow++;
            }
        }
    }

    private void reverseSwaps(double[][] equations) {
        for (String swap : swaps) {
            String[] swapInfo = swap.split(" ");
            if (swapInfo[0].equals("rows")) {
                swapRows(equations, Integer.parseInt(swapInfo[1]), Integer.parseInt(swapInfo[2]));
            } else if (swapInfo[0].equals("columns")) {
                swapColumns(equations, Integer.parseInt(swapInfo[1]), Integer.parseInt(swapInfo[2]));
            }
        }
        swaps.clear();
    }

    private boolean hasContradiction(double[][] equations) {
        for (double[] equation : equations) {
            for (int col = 0; col < equation.length; col++) {
                if (equation[col] != 0) {
                    if (col == equation.length - 1) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    private int countSignificantEquations(double[][] equations) {
        int significantEquations = 0;
        for (double[] row : equations) {
            for (double value : row) {
                if (value != 0) {
                    significantEquations++;
                    break;
                }
            }
        }
        return significantEquations;
    }

    private String getResultsString(double[][] equations) {
        StringBuilder builder = new StringBuilder();
        for (double[] solvedEquation : equations) {
            builder.append(String.format(Locale.US,"%.4f", solvedEquation[solvedEquation.length - 1])).append("\n");
        }
        return builder.toString();
    }

    private boolean tryToSwapRowsAndColumns(double[][] equations, int currentRow, int currentCol) {
        int bottomRow = findBottomRowWithNonZeroCoefficient(equations, currentRow, currentCol);
        if (bottomRow != -1) {
            swapRows(equations, currentRow, bottomRow);
            return true;
        }
        int rightColumn = findRightColumnWithNonZeroCoefficient(equations, currentRow, currentCol);
        if (rightColumn != -1) {
            swapColumns(equations, currentCol, rightColumn);
            swaps.add(String.format("columns %d %d", currentCol, rightColumn));
            return true;
        }
        int[] rowAndColumn = searchBottomRowsForNonZeroCoefficient(equations, currentRow);
        if (rowAndColumn.length != 0) {
            swapRows(equations, currentRow, rowAndColumn[0]);
            swapColumns(equations, currentCol, rowAndColumn[1]);
            swaps.add(String.format("rows %d %d", currentRow, rowAndColumn[0]));
            swaps.add(String.format("columns %d %d", currentCol, rowAndColumn[1]));
            return true;
        }
        return false;
    }

    private int findBottomRowWithNonZeroCoefficient(double[][] equations, int startRow, int column) {
        for (int row = startRow + 1; row < equations.length; row++) {
            if (equations[row][column] != 0) {
                return row;
            }
        }
        return -1;
    }

    private int[] searchBottomRowsForNonZeroCoefficient(double[][] equations, int startRow) {
        for (int row = startRow + 1; row < equations.length; row++) {
            for (int col = 0; col < equations[row].length; col++) {
                if (equations[row][col] != 0) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[0];
    }

    private int findRightColumnWithNonZeroCoefficient(double[][] equations, int row, int startColumn) {
        for (int col = startColumn + 1; col < equations[row].length - 1; col++) {
            if (equations[row][col] != 0) {
                return col;
            }
        }
        return -1;
    }

    private void swapColumns(double[][] equations, int firstColumn, int secondColumn) {
        for (int row = 0; row < equations.length; row++) {
            double firstColumnValue = equations[row][firstColumn];
            equations[row][firstColumn] = equations[row][secondColumn];
            equations[row][secondColumn] = firstColumnValue;
        }
    }

    private void swapRows(double[][] equations, int firstRow, int secondRow) {
        double[] firstRowCopy = equations[firstRow];
        equations[firstRow] = equations[secondRow];
        equations[secondRow] = firstRowCopy;
    }

    private double[] getMultipliedRow(double[] row, double value) {
        double[] resultRow = new double[row.length];
        for (int i = 0; i < row.length; i++) {
            resultRow[i] = row[i] * value;
        }
        return resultRow;
    }

    private double[] getAddedRows(double[] row1, double[] row2) {
        double[] resultRow = new double[row1.length];
        for (int i = 0; i < row1.length; i++) {
            resultRow[i] = row1[i] + row2[i];
        }
        return resultRow;
    }
}