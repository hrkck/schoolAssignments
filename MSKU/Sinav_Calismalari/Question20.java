package sinavOncesi;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Question20 implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
                int result = (o1 % 10) - (o2 % 10);
                if ((result == 0) && !o1.equals(o2)) {
                        return o1 - o2;
                }
                return result;
        }

        public static void main(String[] args) {
                Set<Integer> map = new TreeSet<>(new Question20());
                map.add(12);
                map.add(21);
                map.add(30);
                map.add(41);
                map.add(99);
                map.add(19);
                System.out.println(map);
        }
}
