package hw10;

import java.util.LinkedList;

public class Queue {

	private static int MAX_SIZE = 10;
	LinkedList<Integer> queue = new LinkedList<Integer>();
	
	public Queue(){
		
	}
	
	public synchronized void add(Integer number) throws InterruptedException{
		while (queue.size() == MAX_SIZE){
			wait();
		}
		queue.add(number);
		notifyAll();
	}
	

	public synchronized Integer remove() throws InterruptedException{
		while (queue.isEmpty()){
			
			wait();
		}
		notifyAll();
		return queue.remove();
	}
	
	public synchronized boolean isEmpty(){
		return queue.isEmpty();
	}
	
	
	public synchronized int size(){
		return queue.size();
	}
	
	
	public synchronized void waitUntilQueueBecomesEmpty() throws InterruptedException{
		while (!queue.isEmpty()){			
			wait();
		}			
	}
	
	public static void main(String[] args) throws InterruptedException {
		Queue queue = new Queue();
		Producer prod1 = new Producer(queue);
		Producer prod2 = new Producer(queue);
		Consumer cons = new Consumer(queue);
		
		cons.start();
		prod1.start();
		prod2.start();
		
		prod1.join();
		prod2.join();
		
		queue.waitUntilQueueBecomesEmpty();
		
		cons.interrupt();
		System.out.println(queue.size());
		
		
	}

}

