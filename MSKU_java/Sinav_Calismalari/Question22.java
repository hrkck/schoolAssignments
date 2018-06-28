package sinavOncesi;

import java.util.ArrayList;
import java.util.Collection;

public class Question22 {
        public static void main(String[] args) {
                // Collection'larda index sorgulanamaz.
                // ArrayList'lerde index sorgulanabilir.
                Collection<Integer> list = new ArrayList<>();
                list.add(0);
                list.add(99);
                list.add(5);
                list.add(7);
                list.add(2);
                list.add(10);
                for (int i = 0; i < list.size(); i++) {
                        list.remove(i);
                }
                System.out.println(list);
        }
}
