package smartcalc.console;

import java.util.Arrays;

enum InputRecognizer {

    EMPTY("^$"),
    NUMBER("\\d+"),
    VARIABLE_ASSIGNMENT("[a-zA-Z_]+[0-9]* = (([a-zA-Z_]+)|((- )?[0-9,.]+))"),
    VARIABLE_CALL("[a-zA-Z_]+"),
    SHOW_VARIABLES("(?i)/ variables"),
    EXPRESSION("^[^/][a-zA-Z0-9+\\-*/()^ ]+"),
    EQUATIONS_FLAG("(?i)/ equations?"),
    MATRIX_ADD("(?i)/ matrix add"),
    MATRIX_MULTIPLY("(?i)/ matrix multiply"),
    MATRIX_MULTIPLY_BY_SCALAR("(?i)/ matrix multiply by scalar"),
    MATRIX_DETERMINANT("(?i)/ matrix determinant"),
    MATRIX_INVERSE("(?i)/ matrix inverse"),
    MATRIX_TRANSPOSE("(?i)/ matrix transpose"),
    MATRIX_FLIP_VERTICAL("(?i)/ matrix flip vertical"),
    MATRIX_FLIP_HORIZONTAL("(?i)/ matrix flip horizontal"),
    HELP("(?i)/ help"),
    EXIT("(?i)/ exit"),
    INCORRECT(".*");

    private final String PATTERN;

    InputRecognizer(String pattern) {
        this.PATTERN = pattern;
    }

    static InputRecognizer recognize(String line) {
        return Arrays.stream(InputRecognizer.values())
                .filter(x -> line.matches(x.PATTERN))
                .findFirst()
                .get();
    }
}
