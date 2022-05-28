package smartcalc.expression;

import java.util.Arrays;

public enum ExpressionInstruction {

    EMPTY_LINE("^$"),
    VARIABLE_ASSIGNMENT("[a-zA-Z_]+\\s*=\\s*[0-9a-zA-Z,.]+"),
    EXPRESSION("^[a-zA-Z0-9+\\-*/()^., ]+"),
    INCORRECT(".*");

    private final String PATTERN;

    ExpressionInstruction(String pattern) {
        this.PATTERN = pattern;
    }

    public static ExpressionInstruction recognize(String line) {
        return Arrays.stream(ExpressionInstruction.values())
                .filter(x -> line.matches(x.PATTERN))
                .findFirst()
                .get();
    }
}
