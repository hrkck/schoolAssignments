package hw10;

import java.util.Random;

public class Consumer extends Thread {

	Queue queue;
	
	public Consumer(Queue queue){
		this.queue = queue;
	}
	
	public void run(){
		while (true){
			try {
				System.out.println(Thread.currentThread().getName() + ": " +queue.remove());
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + ": Thread interrupted");
				return;
			}
			if (Thread.interrupted()){
				System.out.println(Thread.currentThread().getName() + ": Thread interrupted");
				return;
			}
		}
	}
}
