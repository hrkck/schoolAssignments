package demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BulkDemo {
        public static void main(String[] args) {
                Collection<Integer> col = new ArrayList<>();
                col.add(5);
                col.add(7);
                col.add(5);
                col.add(12);
                col.add(5);

                System.out.println("Contents Before Removal:" + col);

                col.removeAll(Collections.singleton(5));

                System.out.println("Contents After Removal:" + col);
        }
}
