package smartcalc.expression;

import java.util.Arrays;

enum ExpressionInstruction {

    EMPTY_LINE("^$"),
    VARIABLE_ASSIGNMENT("[a-zA-Z_]+ = \\w+"),
    EXPRESSION("^[a-zA-Z0-9+\\-*/()^ ]+"),
    INCORRECT(".*");

    private final String PATTERN;

    ExpressionInstruction(String pattern) {
        this.PATTERN = pattern;
    }

    static ExpressionInstruction recognize(String line) {
        return Arrays.stream(ExpressionInstruction.values())
                .filter(x -> line.matches(x.PATTERN))
                .findFirst()
                .get();
    }
}
