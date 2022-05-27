package smartcalc.expression;

import smartcalc.data.InvalidAssignmentException;
import smartcalc.data.Variables;
import smartcalc.utils.StringUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import static smartcalc.expression.ExpressionElementType.*;

public class ExpressionSolver {

    private final Map<String, Integer> OPERATOR_PRIORITIES = Map.of("+", 1, "-", 1, "*", 2,"/", 2, "^", 3);
    private final Variables variables = Variables.getInstance();

    public String resolveRawInput(String data) throws InvalidAssignmentException, InvalidExpressionException {
        Scanner scanner = new Scanner(data);
        StringBuilder resultBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            String line = StringUtils.optimizeElementsDistribution(scanner.nextLine());
            switch (ExpressionInstruction.recognize(line)) {
                case VARIABLE_ASSIGNMENT -> variables.assignVariable(line);
                case EXPRESSION -> resultBuilder.append(solveExpression(line));
                case INCORRECT -> throw new InvalidExpressionException("Invalid expression structure");
            }
        }
        return resultBuilder.toString();
    }

    public String solveExpression(String expression) throws InvalidExpressionException  {
        List<String> postfix = transformToPostfix(expression);
        Stack<BigDecimal> numStack = new Stack<>();
        for (String element : postfix) {
            if (ExpressionElementType.recognize(element) == NUMBER) {
                numStack.push(new BigDecimal(element));
            } else {
                BigDecimal num2 = numStack.pop();
                BigDecimal num1 = numStack.pop();
                switch (element) {
                    case "+" -> numStack.push(num1.add(num2));
                    case "-" -> numStack.push(num1.subtract(num2));
                    case "*" -> numStack.push(num1.multiply(num2));
                    case "/" -> numStack.push(num1.divide(num2, MathContext.DECIMAL32));
                    case "^" -> numStack.push(num1.pow(num2.intValue()));
                }
            }
        }
        return numStack.pop().toString();
    }

    private List<String> transformToPostfix(String input) throws InvalidExpressionException {
        List<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Scanner scanner = new Scanner(input);
        String previousScannerElement = "";
        while (scanner.hasNext()) {
            String item = scanner.next();
            switch (ExpressionElementType.recognize(item)) {
                case VARIABLE:
                    String variable = variables.getValueOrDefault(item, null);
                    if (variable == null) {
                        throw new InvalidExpressionException("Unknown variable used: '" + item + "'");
                    }
                    postfix.add(variables.getValue(item));
                    break;
                case NUMBER:
                    postfix.add(item);
                    break;
                case OPERATOR:
                    if (item.matches("[+-]+")) {
                        item = StringUtils.parsePlusesAndMinuses(item);
                    }
                    if (item.matches("[-]")) {
                        if (previousScannerElement.isEmpty() || previousScannerElement.matches("[(]")) {
                            postfix.add("-" + scanner.next());
                            break;
                        }
                    }
                    int checks = Math.min(stack.size(), 2);
                    while (checks != 0) {
                        String previous = stack.peek();
                        if (ExpressionElementType.recognize(previous) != BRACKET) {
                            if (OPERATOR_PRIORITIES.getOrDefault(previous, 0) >= OPERATOR_PRIORITIES.getOrDefault(item, 0)) {
                                previous = stack.pop();
                                postfix.add(previous);
                            }
                        }
                        checks--;
                    }
                    stack.push(item);
                    break;
                case BRACKET:
                    if (item.equals(")")) {
                        try {
                            String previous = stack.pop();
                            while (!previous.equals("(")) {
                                postfix.add(previous);
                                previous = stack.pop();
                            }
                        } catch (EmptyStackException e) {
                            throw new InvalidExpressionException("Invalid expression structure");
                        }
                    } else {
                        stack.push(item);
                    }
                    break;
                case INVALID:
                    throw new InvalidExpressionException(String.format("Invalid element: '%s'", item));
            }
            previousScannerElement = item;
        }

        while (!stack.empty()) {
            if (ExpressionElementType.recognize(stack.peek()) == BRACKET) {
                throw new InvalidExpressionException("Invalid expression structure");
            }
            postfix.add(stack.pop());
        }

        return postfix;
    }
}