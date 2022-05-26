package smartcalc.equation;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class ParameterProvider {

    static Stream<double[][]> emptyAndNullEquations() {
        return Stream.of(null, new double[0][0]);
    }

    static Stream<double[][]> equationsWithoutSolution() {
        return Stream.of(
                new double[][]{{0, 1, 2, 9}, {0, 1, 3, 1}, {1, 0, 6, 0}, {2, 0, 2, 0}},
                new double[][]{{1, 1, 2, 9, 7}, {0, 1, 3, 1, 2}, {0, 2, 6, 2, 9}},
                new double[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}}
        );
    }

    static Stream<double[][]> equationsWithInfiniteSolutions() {
        return Stream.of(
                new double[][]{{1, 1, 2, 9}, {0, 1, 3, 1}, {0, 2, 6, 2}, {0, 0, 0, 0}},
                new double[][]{{1, 1, 2, 9, 7}, {0, 1, 3, 1, 2}, {0, 2, 6, 3, 9}}
        );
    }

    static Stream<Arguments> equationsWithMultipleSolutions() {
        return Stream.of(
                Arguments.of(
                new double[][]{{1, 1, 2, 9}, {2, 4, -3, 1}, {3, 6, -5, 0}},
                        "1.0000\n2.0000\n3.0000"),
                Arguments.of(
                        new double[][]{{1, 0, 1}, {0, 1, 1}},
                        "1.0000\n1.0000"),
                Arguments.of(
                        new double[][]{{0, 1, 1}, {1, 0, 1}},
                        "1.0000\n1.0000")
        );
    }
}
