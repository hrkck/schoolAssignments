package demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CollectionDemo {

	public static void main(String[] args) {
		
		conversionConstructor();

	}
	
	public static void conversionConstructor(){
		Collection<String> list = new ArrayList<>();
		list.add("abc");
		list.add("xyz");
		list.add("abc");
		
		System.out.println("Printing List Contents: " + list);

		//conversion constructor
		Collection<String> set = new HashSet<>(list);
		
		System.out.println("Printing Set Contents: " + set);
	}
	


}
