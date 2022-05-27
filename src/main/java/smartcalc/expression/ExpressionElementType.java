package smartcalc.expression;

import java.util.Arrays;

public enum ExpressionElementType {

    NUMBER("-?[0-9,.]+"),
    OPERATOR("[+-]+|[*/^]"),
    BRACKET("[()]"),
    VARIABLE("[a-zA-Z_]+[0-9]*"),
    INVALID(".*");

    private final String PATTERN;

    ExpressionElementType(String PATTERN) {
        this.PATTERN = PATTERN;
    }

    static public ExpressionElementType recognize(String element) {
        return Arrays.stream(ExpressionElementType.values())
                .filter(x -> element.matches(x.PATTERN))
                .findFirst()
                .get();
    }
}