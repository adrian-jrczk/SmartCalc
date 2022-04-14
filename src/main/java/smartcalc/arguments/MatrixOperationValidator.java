package smartcalc.arguments;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

class MatrixOperationValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.matches("add|multiply|multiply-by-scalar|determinant|inverse|transpose|flip-vertical|flip-horizontal")) {
            throw new ParameterException("""
                    Incorrect operation selected. Available matrix operations:
                    add
                    multiply
                    multiply-by-scalar
                    determinant
                    inverse
                    transpose
                    flip-vertical
                    flip-horizontal
                    """);
        }
    }
}
