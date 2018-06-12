package sinavOncesi;

import java.util.*;

public class Question21 {
        public static void main(String[] args) {
                // setlerde ayni eleman tekrarlanmaz.
                Set<Integer> set = new LinkedHashSet<>();
                set.add(5);
                set.add(4);
                set.add(83);
                set.add(3);
                // set.add(4);
                set.add(2);
                set.add(324);
                System.out.println(set);
        }
}
