import java.util.*;

public interface IntegerCalculator {
        int perform(String operand1, String operator, String operand2);
        Map<String, String> getOperatorDesc();
}
