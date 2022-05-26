package smartcalc.equation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EquationSolverTest {

    private EquationSolver solver = new EquationSolver();

    @ParameterizedTest
    @MethodSource("smartcalc.equation.ParameterProvider#emptyAndNullEquations")
    void solve_EmptyOrNullEquation(double[][] equation) {
        String expectedMessage = "Empty equations provided";
        assertEquals(expectedMessage, solver.solve(equation).trim());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.equation.ParameterProvider#equationsWithoutSolution")
    void solve_EquationWithoutSolution(double[][] equation) {
        String expectedMessage = "No solutions";
        assertEquals(expectedMessage, solver.solve(equation).trim());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.equation.ParameterProvider#equationsWithInfiniteSolutions")
    void solve_EquationWithInfiniteSolutions(double[][] equation) {
        String expectedMessage = "Infinite solutions";
        assertEquals(expectedMessage, solver.solve(equation).trim());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.equation.ParameterProvider#equationsWithMultipleSolutions")
    void solve_EquationWithMultipleSolutions(double[][] equation, String expectedMessage) {
        assertEquals(expectedMessage, solver.solve(equation).trim());
    }
}