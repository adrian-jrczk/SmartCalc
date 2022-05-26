package smartcalc.expression;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionSolverTest {

    private ExpressionSolver solver = new ExpressionSolver();

    @ParameterizedTest
    @MethodSource("smartcalc.expression.ParameterProvider#validExpressions")
    void solveExpression_ValidExpression(String expression, String expectedResult) throws InvalidExpressionException, InvalidAssignmentException {
        assertDoesNotThrow(() -> solver.resolveRawInput(expression));
        assertEquals(expectedResult, solver.resolveRawInput(expression).trim());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.expression.ParameterProvider#expressionsWithInvalidElement")
    void solveExpression_ExpressionsWithInvalidElement(String expression, String expectedExceptionMessage) {
        InvalidExpressionException exception = assertThrows(InvalidExpressionException.class, () -> solver.resolveRawInput(expression));
        assertEquals(expectedExceptionMessage, exception.getMessage().trim());
    }

    @ParameterizedTest
    @MethodSource("smartcalc.expression.ParameterProvider#expressionsWithInvalidStructure")
    void solveExpression_ExpressionsWithInvalidStructure(String expression, String expectedExceptionMessage) {
        InvalidExpressionException exception = assertThrows(InvalidExpressionException.class, () -> solver.resolveRawInput(expression));
        assertEquals(expectedExceptionMessage, exception.getMessage().trim());
    }
}