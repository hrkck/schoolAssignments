package demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class SetDemo {
        public static void main(String[] args) {
                // creates a hash set filled with strings
                // Create a hash set
                Set<String> set = new HashSet<>();



                // Text in String
                String text = "Have a good day. Have a good class. " + "Have a good visit. Have fun!";

                // Extract words from text
                StringTokenizer st = new StringTokenizer(text, " .!?");
                while (st.hasMoreTokens()) {
                        String word = st.nextToken();
                        set.add(word);
                }


                // Display set
                System.out.println(set);


                //			// Obtain an iterator for the hash set
                //			Iterator iterator = set.iterator();
                //
                //			// Display the elements in the hash set
                //			while (iterator.hasNext()) {
                //			  System.out.print(iterator.next() + " ");
                //			}
                //			System.out.println();
        }
}
