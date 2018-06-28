package demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AutoBoxDemo {

	public static void main(String[] args) {
		List<Integer> lst = new ArrayList<>();
		lst.add(1);
		lst.add(2);
		lst.add(3);
		lst.add(2);
		lst.add(2);		
		
		//remove the second 2 from the list
		List lstSub1 = lst.subList(0, lst.indexOf(2)+1);
		List lstSub2 = lst.subList(lst.indexOf(2)+1, lst.size());
		lstSub2.remove(new Integer(2));
		
		System.out.println(lst);
	}

}
