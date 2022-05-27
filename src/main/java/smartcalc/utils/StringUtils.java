package smartcalc.utils;

public class StringUtils {

    private static String OPERATORS = "([+\\-*/=^])\\1*";
    private static String BRACKETS = "[()]";
    private static String ADDITIONAL_SPACES = "\\s+";
    private static String PLUSES = "\\++";
    private static String MINUSES = "-+";

    public static String separateElementsWithOneSpace(String elements) {
        return elements.replaceAll(OPERATORS, " $0 ")
                    .replaceAll(BRACKETS, " $0 ")
                    .replaceAll(ADDITIONAL_SPACES, " ")
                    .trim();
    }

    public static String[] getAssignmentElements(String assignment) {
        String[] elements = assignment.split("=");
        elements[0] = elements[0].replaceAll(ADDITIONAL_SPACES, "");
        elements[1] = elements[1].replaceAll(ADDITIONAL_SPACES, "");
        return elements;
    }

    public static String parsePlusesAndMinuses(String operator) {
        return operator.matches(PLUSES) ? "+" :
               operator.matches(MINUSES) ? (operator.length() % 2 == 0 ? "+" : "-") : operator;
    }
}