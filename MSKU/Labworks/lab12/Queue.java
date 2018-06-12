package lab12;

import java.util.LinkedList;

public class Queue {

	LinkedList<Integer> queue = new LinkedList<Integer>();
	
	public Queue(){
		
	}
	
	public  void add(Integer number){
		queue.add(number);

	}
	

	public Integer remove(){

		return queue.remove();
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	
	public int size(){
		return queue.size();
	}
	
	public static void main(String[] args) {

	}

}

