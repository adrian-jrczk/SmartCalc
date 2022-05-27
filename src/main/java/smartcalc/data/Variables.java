package smartcalc.data;

import smartcalc.expression.ExpressionElementType;
import smartcalc.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;
import static smartcalc.expression.ExpressionElementType.NUMBER;
import static smartcalc.expression.ExpressionElementType.VARIABLE;

public class Variables {

    static private Variables INSTANCE;
    private final Map<String, String> variables = new HashMap<>();

    private Variables() {
    }

    static public Variables getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Variables();
        }
        return INSTANCE;
    }

    public void assignVariable(String assignment) throws InvalidAssignmentException {
        String[] elements = StringUtils.getAssignmentElements(assignment);
        ExpressionElementType firstType = ExpressionElementType.recognize(elements[0]);
        ExpressionElementType secondType = ExpressionElementType.recognize(elements[1]);
        if (firstType == VARIABLE && (secondType == NUMBER || (secondType == VARIABLE && variables.containsKey(elements[1])))) {
            variables.put(elements[0], variables.getOrDefault(elements[1], elements[1]));
        } else {
            throw new InvalidAssignmentException("Invalid assignment");
        }
    }

    public String getValue(String variableName) {
        return variables.get(variableName);
    }

    public String getValueOrDefault(String variableName, String defaultValue) {
        return variables.getOrDefault(variableName, defaultValue);
    }

    public String getAllVariablesString() {
        StringBuilder builder = new StringBuilder();
        variables.forEach((x, y) -> builder.append(x).append(" = ").append(y).append("\n"));
        if (builder.length() == 0) {
            return builder.append("No variables defined").toString();
        }
        return builder.toString();
    }
}
