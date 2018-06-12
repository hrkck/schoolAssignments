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

public class MapDemo {

	public static void main(String[] args) {
		
		 // Create a map
		Map<String,Integer> map = new TreeMap<>();
		
		

		// Text in String
		 String text = "Have a good day. have a good class. " + "Have a good visit. Have fun!";

		// Extract words from text
		  StringTokenizer st = new StringTokenizer(text, " .!?");
		  while (st.hasMoreTokens()){
			  String word = st.nextToken();
			  System.out.println("processing " + word);
			  if (map.containsKey(word)){
				  int occurrence =map.get(word);
				  occurrence++;
				  map.put(word, occurrence);
			  }else{
				  map.put(word, 1);
			  }	      
		  }
		  
		  
			//Display map
			System.out.println(map);
		
		
	}

}
