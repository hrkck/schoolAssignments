import java.util.*;

public class IntegerCalculatorImpl implements IntegerCalculator {
        int opd1 = 0;
        int opd2 = 0;
        int result = 0;
        Map<String, String> map = new LinkedHashMap<>();

        @Override
        public int perform(String operand1, String operator, String operand2){
                opd1 = Integer.parseInt(operand1);
                opd2 = Integer.parseInt(operand2);

                // Decision Algorithm for a corresponding operator
                if (operator.equals("+")) {
                        return result = opd1 + opd2;
                } else if (operator.equals("-")) {
                        return result = opd1 - opd2;
                } else if (operator.equals("*")) {
                        return result = opd1 * opd2;
                } else if (operator.equals("/")) {
                        return result = opd1 / opd2;
                } else if (operator.equals("%")) {
                        return result = opd1 % opd2;
                } else {
                        System.out.println(operator + " is not a valid operator");
                }
                return 0;
        }

        @Override
        public Map<String, String> getOperatorDesc() {
                map.put("+", "Calculates the sum of the given operands");
                map.put("-", "Subtracts the second operand from the first one");
                map.put("*", "Multiplies the operands");
                map.put("/", "Divides the first operand by the second one");
                map.put("%", "Finds the remainder after division of first operand by second one");

                return map;
        }
}
