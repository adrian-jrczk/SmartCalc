package smartcalc.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionSolverTest {

    /*
        Map of <inputData, expectedResult>
     */
    private Map<String, String> testCases = new HashMap<>();

    @Test
    void resolveRawInput() {
        ExpressionSolver solver = new ExpressionSolver();
        for (Map.Entry<String, String> testCase : testCases.entrySet()) {
            try {
                assertEquals(testCase.getValue(), solver.resolveRawInput(testCase.getKey()));
            } catch (Exception exception) {
                assertEquals(testCase.getValue(), exception.getMessage());
            }
        }
    }

    @BeforeEach
    void assignTestCases() {
        testCases.put("33 + 20 + 11 + 49 - 32 - 9 + 1 - 80 + 4", "-3");
        testCases.put("5 --- 2 ++++++ 4 -- 2 ---- 1", "10");
        testCases.put("12 * (-3 + 5 * (-2))", "-156");
        testCases.put("4 * 3", "12");
        testCases.put("7 + 3 * ((4 + 3) * 7 + 1) - 6 / (2 + 1)", "155");
        testCases.put("3 + (9 + ( 68 * 3/9)) * ((7-2 * 5) / 2) * 6", "-282.000030");
        testCases.put("7 - 1 = 5", "Invalid expression structure");
        testCases.put("2 ************ 2", "Invalid element: '************'");
        testCases.put("8 * (2 + 3", "Invalid expression structure");
        testCases.put("2 // 2", "Invalid element: '//'");
        testCases.put("a = 3\na + 5", "8");
        testCases.put("n = 32\n33 + 20 + 11 + 49 - n - 9 + 1 - 80 + 4", "-3");
    }
}