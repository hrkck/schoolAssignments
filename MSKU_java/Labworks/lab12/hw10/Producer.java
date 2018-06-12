package hw10;

import java.util.Random;

public class Producer extends Thread {

	Queue queue;
	
	public Producer(Queue queue){
		this.queue = queue;
	}
	
	public void run(){
		Random random = new Random();
		for (int i =0; i<20; i++){
			int number = random.nextInt(1000);
			System.out.println(Thread.currentThread().getName()+": generated number = " + number);
			try {
				queue.add(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
