package smartcalc.matrix;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class ParameterProvider {

    static Stream<Arguments> validInversionMatrices() {
        return Stream.of(
                Arguments.of(new double[][]{{4, 7}, {2, 6}}, new double[][]{{0.6, -0.7}, {-0.2, 0.4}}),
                Arguments.of(new double[][]{{1, -2}, {3, 4}}, new double[][]{{0.4, 0.2}, {-0.3, 0.1}})
        );
    }

    static Stream<double[][]> rectangularInversionMatrices() {
        return Stream.of(
                new double[][]{{1, -2, 1}, {3, 4, -2}},
                new double[][]{{1, 5, 5, 8}, {3, 4, -2, 0}}
        );
    }

    static Stream<Arguments> matricesWithDeterminants() {
        return Stream.of(
                Arguments.of(new double[][]{{2, 1, 3}, {4, -2, -1}, {-5, 2, 6}}, -45),
                Arguments.of(new double[][]{{3, 8}, {4, 6}}, -14),
                Arguments.of(new double[][]{{6, 1, 1}, {4, -2, 5}, {2, 8, 7}}, -306)
        );
    }

    static Stream<Arguments> validAdditionMatrices() {
        return Stream.of(
                Arguments.of(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new double[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}},
                        new double[][]{{10, 10, 10}, {10, 10, 10}, {10, 10, 10}})
        );
    }

    static Stream<Arguments> invalidAdditionMatrices() {
        return Stream.of(
                Arguments.of(new double[][]{{3, 2}, {1, 4}},
                        new double[][]{{3, 2}, {1, 4}, {5, 3}})
        );
    }

    static Stream<Arguments> validMultiplicationMatrices() {
        return Stream.of(
                Arguments.of(new double[][]{{1, 2, 3}, {4, 5, 6}},
                        new double[][]{{10, 11}, {20, 21}, {30, 31}},
                        new double[][]{{140, 146}, {320, 335}}),
                Arguments.of(new double[][]{{2, 3}, {-5, 6}, {9, -7}},
                        new double[][]{{1, -2, 0}, {3, 4, -5}},
                        new double[][]{{11, 8, -15}, {13, 34, -30}, {-12, -46, 35}})
        );
    }

    static Stream<Arguments> invalidMultiplicationMatrices() {
        return Stream.of(
                Arguments.of(new double[][]{{2, 3}, {-5, 6}, {9, -7}},
                        new double[][]{{1, -2, 0}, {3, 4, -5}, {1, 1, 1}})
        );
    }

    static Stream<Arguments> matricesWithScalars() {
        return Stream.of(
                Arguments.of(new double[][]{{1, 2, 3}, {4, 5, 6}},
                        5,
                        new double[][]{{5, 10, 15}, {20, 25, 30}}),
                Arguments.of(new double[][]{{1, 2, 3}, {4, 5, 6}},
                        2,
                        new double[][]{{2, 4, 6}, {8, 10, 12}})
        );
    }

    static Stream<Arguments> transposeMatrices() {
        return Stream.of(
                Arguments.of(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new double[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}}),
                Arguments.of(new double[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}},
                        new double[][]{{1, 3, 5, 7}, {2, 4, 6, 8}})
        );
    }

    static Stream<Arguments> verticalFlipMatrices() {
        return Stream.of(
                Arguments.of(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new double[][]{{3, 2, 1}, {6, 5, 4}, {9, 8, 7}}),
                Arguments.of(new double[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}},
                        new double[][]{{2, 1}, {4, 3}, {6, 5}, {8, 7}})
        );
    }

    static Stream<Arguments> horizontalFlipMatrices() {
        return Stream.of(
                Arguments.of(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new double[][]{{7, 8, 9}, {4, 5, 6}, {1, 2, 3}}),
                Arguments.of(new double[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}},
                        new double[][]{{7, 8}, {5, 6}, {3, 4}, {1, 2}})
        );
    }
}
