package smartcalc.equation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EquationSolverTest {

    /*
        Map of <inputData, expectedResult>
    */
    private Map<String, String> testCases = new HashMap<>();

    @Test
    void solve() {
        EquationSolver solver = new EquationSolver();
        for (Map.Entry<String, String> testCase : testCases.entrySet()) {
            double[][] equations = parseEquationsFromString(testCase.getKey());
            assertEquals(testCase.getValue().trim(), solver.solve(equations).trim());
        }
    }

    @BeforeEach
    void assignTestCases() {
        testCases.put("""
                        3 3
                        1 1 2 9
                        2 4 -3 1
                        3 6 -5 0
                        """,
                        """                                  
                        1.0000
                        2.0000
                        3.0000
                        """);

        testCases.put("""
                        2 2
                        1 0 1
                        0 1 1
                        """,
                        """                                  
                        1.0000
                        1.0000
                        """);

        testCases.put("""
                        3 4
                        0 1 2 9
                        0 1 3 1
                        1 0 6 0
                        2 0 2 0
                        """,
                        """                                  
                        No solutions
                        """);

        testCases.put("""
                        20 20
                        26 90 17 67 68 9 60 38 37 38 76 14 33 94 88 58 99 84 9 45 18
                        37 34 20 53 10 61 75 49 91 84 55 84 84 81 30 22 42 76 33 27 2
                        99 69 63 57 39 45 33 43 99 26 25 24 80 91 62 90 54 77 88 32 94
                        37 40 14 5 47 30 5 21 36 77 57 38 29 3 61 12 81 19 39 56 9
                        36 73 71 39 9 9 31 10 84 7 13 45 9 34 2 14 88 43 17 4 86
                        31 29 76 89 26 35 11 55 37 5 41 96 19 18 100 20 21 49 83 5 20
                        18 18 25 70 79 74 30 66 41 93 63 2 90 4 46 1 77 89 21 47 52
                        32 62 27 80 57 10 35 44 97 18 58 19 5 81 33 54 83 66 25 75 75
                        56 53 13 91 30 11 72 52 13 86 73 88 94 20 25 77 90 75 73 52 36
                        63 9 40 40 35 90 55 92 12 98 34 37 64 21 67 91 15 65 82 87 30
                        71 5 65 64 6 20 9 81 40 56 39 93 74 55 83 81 74 2 58 86 58
                        13 50 31 86 73 36 83 27 37 96 37 28 75 91 15 78 90 56 57 18 18
                        34 9 51 11 92 54 25 91 61 69 37 37 89 91 95 50 10 16 69 71 66
                        25 16 79 36 6 28 51 100 5 28 97 23 44 32 50 2 96 18 5 48 44
                        80 38 47 96 41 72 85 79 2 3 96 14 2 65 97 38 76 73 88 59 89
                        31 98 88 52 49 68 46 79 26 30 31 76 84 87 27 16 66 55 78 2 46
                        57 2 32 78 70 7 95 56 77 97 49 14 74 7 85 48 83 59 71 5 44
                        4 64 4 43 54 99 77 7 72 82 27 22 29 94 53 48 65 88 26 86 42
                        69 59 62 63 42 29 73 18 82 78 48 60 84 73 84 9 82 53 14 1 12
                        98 2 47 62 69 11 28 14 83 32 94 24 71 1 16 91 53 50 38 26 17
                        """,
                        """                                  
                        0.5428
                        -2.3923
                        1.5789
                        -1.3679
                        0.6433
                        -1.7531
                        -0.0432
                        -0.7503
                        -0.8245
                        -0.4562
                        -1.2163
                        0.3093
                        -0.1105
                        1.1717
                        -0.5873
                        -1.3933
                        1.1229
                        3.0693
                        1.1995
                        1.5399
                        """);

        testCases.put("""
                        2 2
                        0 1 1
                        1 0 1
                        """,
                        """                                  
                        1.0000
                        1.0000
                        """);

        testCases.put("""
                        3 4
                        1 1 2 9
                        0 1 3 1
                        0 2 6 2
                        0 0 0 0
                        """,
                        """                                  
                        Infinite solutions
                        """);

        testCases.put("""
                        4 3
                        1 1 2 9 7
                        0 1 3 1 2
                        0 2 6 2 9
                        """,
                        """                                  
                        No solutions
                        """);

        testCases.put("""
                        4 3
                        1 1 2 9 7
                        0 1 3 1 2
                        0 2 6 3 9
                        """,
                        """                                  
                        Infinite solutions
                        """);

        testCases.put("""
                        4 3
                        1 0 0 0 1
                        0 0 0 0 0
                        1 0 0 0 0
                        """,
                        """                                  
                        No solutions
                        """);

    }

    private double[][] parseEquationsFromString(String string) {
        Scanner scanner = new Scanner(string);
        int numberOfVariables = scanner.nextInt();
        int numberOfEquations = scanner.nextInt();
        double[][] equations = new double[numberOfEquations][numberOfVariables + 1];
        for (int row = 0; row < numberOfEquations; row++) {
            for (int col = 0; col < numberOfVariables + 1; col++) {
                equations[row][col] = scanner.nextDouble();
            }
        }
        return equations;
    }
}