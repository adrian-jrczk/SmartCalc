package smartcalc.expression;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import smartcalc.data.InvalidAssignmentException;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionSolverTest {

    private ExpressionSolver solver = new ExpressionSolver();

    @ParameterizedTest
    @MethodSource("smartcalc.expression.ParameterProvider#validExpressions")
    void solve_ValidExpression(String expression, String expectedResult) throws InvalidExpressionException, InvalidAssignmentException {
        assertDoesNotThrow(() -> solver.solve(expression));
        assertEquals(expectedResult, solver.solve(expression).trim());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.expression.ParameterProvider#expressionsWithInvalidElement")
    void solve_ExpressionsWithInvalidElement(String expression, String expectedExceptionMessage) {
        InvalidExpressionException exception = assertThrows(InvalidExpressionException.class, () -> solver.solve(expression));
        assertEquals(expectedExceptionMessage, exception.getMessage().trim());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.expression.ParameterProvider#expressionsWithInvalidStructure")
    void solve_ExpressionsWithInvalidStructure(String expression, String expectedExceptionMessage) {
        InvalidExpressionException exception = assertThrows(InvalidExpressionException.class, () -> solver.solve(expression));
        assertEquals(expectedExceptionMessage, exception.getMessage().trim());
    }
}