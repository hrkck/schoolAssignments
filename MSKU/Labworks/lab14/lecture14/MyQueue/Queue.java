
interface Queue<T>{
	//Inserts the specified element into this queue 
	void add(T obj)throws QueueException;

	//Retrieves and removes the head of this queue.
	T remove() throws QueueException ;
	
	//Returns the size of this queue.
	int size();
}