package smartcalc.expression;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class ParameterProvider {

    static Stream<Arguments> validExpressions() {
        return Stream.of(
                Arguments.of("33 + 20 + 11 + 49 - 32 - 9 + 1 - 80 + 4", "-3"),
                Arguments.of("5 --- 2 ++++++ 4 -- 2 ---- 1", "10"),
                Arguments.of("12 * (-3 + 5 * (-2))", "-156"),
                Arguments.of("4 * 3", "12"),
                Arguments.of("7 + 3 * ((4 + 3) * 7 + 1) - 6 / (2 + 1)", "155"),
                Arguments.of("3 + (9 + ( 68 * 3/9)) * ((7-2 * 5) / 2) * 6", "-282.000030")
        );
    }

    static Stream<Arguments> expressionsWithInvalidElement() {
        return Stream.of(
                Arguments.of("2 ************ 2", "Invalid expression element: '************'"),
                Arguments.of("2 // 2", "Invalid expression element: '//'"),
                Arguments.of("7 - 1 = 5", "Invalid expression element: '='")
        );
    }

    static Stream<Arguments> expressionsWithInvalidStructure() {
        return Stream.of(
                Arguments.of("8 * (2 + 3", "Invalid expression structure"),
                Arguments.of("(2 + 3))", "Invalid expression structure")
        );
    }
}
