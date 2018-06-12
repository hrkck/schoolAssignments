package sampleQuestions;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Question20 implements Comparator<Integer> {
	public int compare(Integer o1, Integer o2) {
		int result =  (o2 % 10) - (o1 % 10);
		if (result == 0 && !o1.equals(o2))
			return o2 - o1;
		return result;
		
	}
	public static void main(String[] args) {
		Set<Integer> map = new TreeSet<>(new Question20());  
		map.add(12);
		map.add(21);
		map.add(30);
		map.add(41);
		
		System.out.println(map);
	}	
}