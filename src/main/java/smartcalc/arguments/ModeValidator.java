package smartcalc.arguments;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ModeValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.matches("equation|expression|matrix")) {
            throw new ParameterException("""
                    Incorrect mode selected. Available modes:
                    equation
                    expression
                    matrix
                    """);
        }
    }
}
