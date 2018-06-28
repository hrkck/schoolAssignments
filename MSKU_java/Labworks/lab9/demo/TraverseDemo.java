package demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TraverseDemo {

	public static void main(String[] args) {
		//traverseCollections();
		removeWhileTraversing();
	}
	public static void traverseCollections(){
		Collection<String> col = new ArrayList<>();
		col.add("abc");
		col.add("xyz");

		//for-each
		for (String str: col)
			System.out.println(str);
		
		//Aggregate Operations 
		col.stream().forEach(e -> System.out.println(e));
		
		//Iterator
		Iterator<String> itr = col.iterator();
	    while (itr.hasNext())
	    	System.out.println(itr.next());
	    
	    //index based looping
		for (int i = 0; i<col.size(); i++){
			System.out.println(((List)col).get(i));
		}
	}
	
	public static void removeWhileTraversing(){
		Collection<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(7);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(21);
		
		
		Iterator<Integer> itr = list.iterator();
		System.out.println("Contents Before Removal:" + list);
		while (itr.hasNext()){
			int element = itr.next();
			System.out.println("Checking element " + element);
			if ((element>=10)&&(element<20)){
				itr.remove();
			}	
		}
		
		System.out.println("Contents After Removal:" + list);
		
		

	}
	
}
