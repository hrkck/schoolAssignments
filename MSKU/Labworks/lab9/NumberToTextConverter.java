package demo;

import java.util.HashMap;
import java.util.Map;

public class NumberToTextConverter {
        Map<Integer, String> map = new HashMap<>();

        public void addConversion(int number, String str) {
                map.put(number, str);
        }

        public String convert(int number) {
                String inMap = map.get(number);
                if (inMap != null) {
                        if ((number == 100) || (number == 1000)) {
                                return map.get(number) + " " + inMap;
                        }
                        return inMap;
                } else if (number < 100) {
                        return map.get((number / 10) * 10) + " " + map.get(number % 10);
                } else if (number < 1000) {
                        return map.get((number / 100)) + " " + map.get(100)
                               + ((number % 100 == 0) ? "" : (" " + convert(number % 100)));
                }

                return "Cannot convert " + number;
        }
}
